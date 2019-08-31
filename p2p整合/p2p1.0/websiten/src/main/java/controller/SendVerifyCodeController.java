package controller;

import com.gxa.cdut.service.IVerifyCodeService;
import com.gxa.cdut.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendVerifyCodeController {
    /**
     * 发送验证码
     * @param phoneNumber
     * @return
     */

    @Autowired
    private IVerifyCodeService verifyCodeService;

    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public JSONResult sendVerifyCode(String phoneNumber){
        JSONResult json = new JSONResult();
        try {
            this.verifyCodeService.sendVerifyCode(phoneNumber);
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        }
        return json ;
    }

}
