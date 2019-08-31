package controller;

import com.gxa.cdut.domain.*;
import com.gxa.cdut.service.IAccountService;
import com.gxa.cdut.service.IBidRequestService;
import com.gxa.cdut.service.IDetailService;
import com.gxa.cdut.util.BitStatesUtils;
import com.gxa.cdut.util.SysConstant;
import com.gxa.cdut.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class BrorrowController {

    @Autowired
    IDetailService iDetailService;

    @Autowired
    IAccountService iAccountService;

    @Autowired
    IBidRequestService iBidRequestService;

    @RequestMapping("borrow")
    public String borrow(Model model){
        UserInfo userInfo = UserContext.getLoginInfo();

        model.addAttribute("acount",iAccountService.selectById(userInfo.getId()));
        model.addAttribute("userinf",iDetailService.selectById(userInfo.getId()));
        model.addAttribute("creditBorrowScore",30);

        Detail detail = iDetailService.selectById(userInfo.getId());

        return "borrow";
    }

    @RequestMapping("borrowInfo")
    public String borrowInfo(Model model){
        UserInfo userInfo = UserContext.getLoginInfo();

        Detail detail = iDetailService.selectById(userInfo.getId());

        if(BitStatesUtils.hasState(detail.getBitState(),BitStatesUtils.OP_HAS_WITHDRAW_PROCESS)){
            return "borrow_apply_result";
        }

        model.addAttribute("account",iAccountService.selectById(userInfo.getId()));
        model.addAttribute("minBidRequestAmount",50);
        model.addAttribute("minBidAmount",50);


        return "borrow_apply";
    }

    @RequestMapping("borrow_apply")
    public String borrow_apply(BidRequest bidRequest){

        UserInfo userInfo = UserContext.getLoginInfo();

        Detail detail = iDetailService.selectById(userInfo.getId());

        if(BitStatesUtils.hasState(detail.getBitState(),BitStatesUtils.OP_HAS_WITHDRAW_PROCESS)){
            return "borrow_apply_result";
        }

        bidRequest.setApplyTime(new Date());
        bidRequest.setCreateUser(UserContext.getLoginInfo());
        bidRequest.setBidRequestState(SysConstant.BIDREQUEST_STATE_PUBLISH_PENDING);

        detail.setBitState(BitStatesUtils.addState(detail.getBitState(),BitStatesUtils.OP_HAS_WITHDRAW_PROCESS));

        iDetailService.update(detail);
        iBidRequestService.insert(bidRequest);
        return "borrow_apply_result";

    }


    @RequestMapping("borrow_info")
    public String borrow_info(Model model,int id){

        BidRequest bidRequest = iBidRequestService.selectById(id);
        Detail userinfo = iDetailService.selectById(bidRequest.getCreateuser_id());
        Acount acount = iAccountService.selectById(bidRequest.getCreateuser_id());

        model.addAttribute("logininfo",UserContext.getLoginInfo());
        model.addAttribute("bidRequest",bidRequest);
        model.addAttribute("self",false);
        model.addAttribute("userInf",userinfo);
        model.addAttribute("account",acount);


        return "borrow_info";
    }


}
