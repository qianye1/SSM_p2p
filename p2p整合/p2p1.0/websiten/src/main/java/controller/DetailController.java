package controller;

import com.gxa.cdut.Query.IplogQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.common.JASONResult;
import com.gxa.cdut.domain.Detail;
import com.gxa.cdut.domain.Iplog;
import com.gxa.cdut.domain.SystemDictionary;
import com.gxa.cdut.domain.UserInfo;
import com.gxa.cdut.service.IAccountService;
import com.gxa.cdut.service.IDetailService;
import com.gxa.cdut.service.IIplogService;
import com.gxa.cdut.service.ISystemDictionaryService;
import com.gxa.cdut.util.BitStatesUtils;
import com.gxa.cdut.util.JSONResult;
import com.gxa.cdut.util.UserContext;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DetailController {

    @Autowired
    private IDetailService iDetailService;

    @Autowired
    private ISystemDictionaryService iSystemDictionaryService;

    @Autowired
    private IIplogService iIplogService;


    @RequestMapping("userInfo")
    public String personalCenter(Model model) {

        ModelMap modelMap = new ModelMap();

        UserInfo userInfo = UserContext.getLoginInfo();
        model.addAttribute("logininfo",userInfo);

        Detail detail = iDetailService.selectItems(userInfo.getId());
        model.addAttribute("userinf",detail);

        SystemDictionary systemDictionary = iSystemDictionaryService.selectAll();

        model.addAttribute("educationBackgrounds",systemDictionary.getEducationBackground());
        model.addAttribute("incomeGrades",systemDictionary.getIncomeGrade());
        model.addAttribute("marriages",systemDictionary.getMarriage());
        model.addAttribute("kidCounts",systemDictionary.getKidCount());
        model.addAttribute("houseConditions",systemDictionary.getHouseCondition());


        return "userInfo";
    }

    @RequestMapping("userInfo_save")
    @ResponseBody
    public JASONResult detailInfoUpdate(Detail detaile){

        JASONResult json = new JASONResult();
        json.setSuccess(true);

        UserInfo userInfo = UserContext.getLoginInfo();
        Detail detail = iDetailService.selectById(userInfo.getId());

        if(!BitStatesUtils.hasState(detail.getBitState(),BitStatesUtils.OP_USER_INFO)) {
            detail.setBitState(BitStatesUtils.addState(detail.getBitState(), BitStatesUtils.OP_USER_INFO));
        }

        detail.setEducationBackground_id(detaile.getEducationBackgroudItem().getId());
        detail.setIncomeGrade_id(detaile.getIncomeGradeItem().getId());
        detail.setMarriage_id(detaile.getMarriageItem().getId());
        detail.setKidCount_id(detaile.getKidCountItem().getId());
        detail.setHouseCondition_id(detaile.getHouseConditionItem().getId());



        try {
            iDetailService.update(detail);
        }catch (RuntimeException re){
            json.setSuccess(false);
            json.setMessage("未知参数错误！");
        }

        return json;
    }

    @RequestMapping("ipLog")
    public String iplog_list(IplogQueryObject iplogQueryObject,Model model){

        model.addAttribute("logininfo",UserContext.getLoginInfo());
        iplogQueryObject.setUsername(UserContext.getLoginInfo().getUsername());

        model.addAttribute("pageResultSet",iIplogService.getPage(iplogQueryObject));

        return "iplog_list";
    }


    /**
     * 用户绑定手机
     *
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    @RequestMapping("bindPhone")
    @ResponseBody
    public JSONResult bindPhone(String phoneNumber, String verifyCode) {
        JSONResult json = new JSONResult();
        try {
            iDetailService.bindPhone(phoneNumber, verifyCode);
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }

    /**
     * 绑定邮件
     */
    @RequestMapping("bindEmail")
    public String bingEmail(String code, Model model) {
        System.out.println(code);
        try {
            iDetailService.bindEmail(code);
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("msg", e.getMessage());
        }
        return "checkmail_result";
    }


}
