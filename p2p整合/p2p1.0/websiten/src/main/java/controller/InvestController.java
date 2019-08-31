package controller;

import com.gxa.cdut.Query.BidRequestQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.service.IBidRequestService;
import com.gxa.cdut.util.SysConstant;
import com.gxa.cdut.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InvestController {

    @Autowired
    private IBidRequestService iBidRequestService;

    @RequestMapping("invest")
    public String invest(Model model){
        model.addAttribute("logininfo",UserContext.getLoginInfo());
        return "invest";
    }

    @RequestMapping("invest_list")
    public String invest_list(Model model, BidRequestQueryObject bidRequestQueryObject){

        bidRequestQueryObject.setBidRequestState(SysConstant.BIDREQUEST_STATE_BIDDING);

        PageResultSet pageResultSet = iBidRequestService.selectPage(bidRequestQueryObject);

        model.addAttribute("pageResult",pageResultSet);

        return "invest_list";

    }

    @RequestMapping("index")
    public String index(Model model,BidRequestQueryObject bidRequestQueryObject){
        model.addAttribute("logininfo",UserContext.getLoginInfo());
        bidRequestQueryObject.setBidRequestState(SysConstant.BIDREQUEST_STATE_BIDDING);
        bidRequestQueryObject.setCurrentPage(1);
        bidRequestQueryObject.setPageSize(5);

        PageResultSet pageResultSet = iBidRequestService.selectPage(bidRequestQueryObject);

        model.addAttribute("bidRequests",pageResultSet.getListData());

        return "main";
    }


}
