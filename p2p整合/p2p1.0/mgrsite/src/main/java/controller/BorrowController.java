package controller;

import com.gxa.cdut.Query.BidRequestQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.domain.BidRequest;
import com.gxa.cdut.domain.BidRequestAuditHistory;
import com.gxa.cdut.domain.Detail;
import com.gxa.cdut.service.IBidRequestAuditHistoryService;
import com.gxa.cdut.service.IBidRequestService;
import com.gxa.cdut.service.IDetailService;
import com.gxa.cdut.service.IUserInfoService;
import com.gxa.cdut.util.BitStatesUtils;
import com.gxa.cdut.util.SysConstant;
import com.gxa.cdut.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class BorrowController {

    @Autowired
    private IDetailService iDetailService;

    @Autowired
    private IBidRequestService iBidRequestService;

    @Autowired
    private IUserInfoService iUserInfoService;

    @Autowired
    private IBidRequestAuditHistoryService iBidRequestAuditHistoryService;

    @RequestMapping("bidrequest_publishaudit_list")
    public String bidrequest(Model model, BidRequestQueryObject bidRequestQueryObject){

        bidRequestQueryObject.setBidRequestState(SysConstant.BIDREQUEST_STATE_PUBLISH_PENDING);
        PageResultSet pageResultSet = iBidRequestService.selectPage(bidRequestQueryObject);
        model.addAttribute("pageResult",pageResultSet);
        model.addAttribute("userInf",UserContext.getLoginInfo());

        return "bidrequest/publish_audit";
    }

    @RequestMapping("bidrequest_publishaudit")
    @ResponseBody
    public String bidrequest_publishaudit(Integer state, String remark,Integer id){

        BidRequest bidRequest = iBidRequestService.selectById(id);
        Detail detail = iDetailService.selectById(bidRequest.getCreateuser_id());

        detail.setBitState(BitStatesUtils.removeState(detail.getBitState(),BitStatesUtils.OP_HAS_WITHDRAW_PROCESS));

        Calendar ca = Calendar.getInstance();
        Date date = ca.getTime();

        bidRequest.setPublishTime(date);

        ca.add(Calendar.DAY_OF_MONTH,bidRequest.getDisableDays());
        date = ca.getTime();

        bidRequest.setDisableDate(date);
        bidRequest.setNote(remark);



        BidRequestAuditHistory bidRequestAuditHistory = new BidRequestAuditHistory();
        bidRequestAuditHistory.setBidRequestId((long)bidRequest.getId());

        bidRequestAuditHistory.setApplyTime(bidRequest.getApplyTime());


        bidRequestAuditHistory.setAuditTime(bidRequest.getPublishTime());

        bidRequestAuditHistory.setRemark(remark);
        bidRequestAuditHistory.setApplier(iUserInfoService.selectById(bidRequest.getCreateuser_id()));
        bidRequestAuditHistory.setAuditor(iUserInfoService.selectById(UserContext.getLoginInfo().getId()));
        bidRequestAuditHistory.setAuditType(BidRequestAuditHistory.PUBLISH_AUDIT);


        if(state == 1){
            bidRequestAuditHistory.setState(BidRequestAuditHistory.STATE_AUDIT);
            bidRequest.setBidRequestState(SysConstant.BIDREQUEST_STATE_BIDDING);

        }else if(state==2){
            bidRequestAuditHistory.setState(BidRequestAuditHistory.STATE_REJECT);
            bidRequest.setBidRequestState(SysConstant.BIDREQUEST_STATE_PUBLISH_REFUSE);
        }

        iDetailService.update(detail);
        iBidRequestService.updateConfirm(bidRequest);
        iBidRequestAuditHistoryService.insert(bidRequestAuditHistory);

        return "bidrequest/publish_audit";
    }

    @RequestMapping("borrow_info")
    public String borrow_info(Model model,int id){

        BidRequest bidRequest = iBidRequestService.selectById(id);
        Detail userinfo = iDetailService.selectById(bidRequest.getCreateuser_id());
        List<BidRequestAuditHistory> bidRequestAuditHistorys = iBidRequestAuditHistoryService.selectByBidId(id);

        model.addAttribute("bidRequest",bidRequest);
        model.addAttribute("userInf",UserContext.getLoginInfo());
        model.addAttribute("audits",bidRequestAuditHistorys);
        model.addAttribute("userInfx",userinfo);


        return "bidrequest/borrow_info";
    }



}
