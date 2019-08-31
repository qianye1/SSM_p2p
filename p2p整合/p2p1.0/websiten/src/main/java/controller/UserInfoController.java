package controller;

import com.gxa.cdut.common.JASONResult;
import com.gxa.cdut.domain.Iplog;
import com.gxa.cdut.domain.UserInfo;
import com.gxa.cdut.service.IIplogService;
import com.gxa.cdut.service.IUserInfoService;
import com.gxa.cdut.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class UserInfoController {

    @Autowired
    private IUserInfoService iUserInfoService;
    private ModelAndView modelAndView;

    @Autowired
    private IIplogService iIplogService;

    @RequestMapping("checkUsername")
    @ResponseBody
    public boolean checkUsername(String username){
        int count  = iUserInfoService.selectCount(username);
        return count<=0;
    }

    @RequestMapping("register")
    @ResponseBody
    public JASONResult register(String  username,String password){

        JASONResult json = new JASONResult();
        json.setSuccess(true);
        try {
            iUserInfoService.insert(username,password);
        } catch (RuntimeException re) {
            json.setSuccess(false);
            json.setMessage(re.getMessage());
        }
        return json;

    }
    @RequestMapping("login")
    @ResponseBody
    public JASONResult login(String username, String password, HttpServletRequest request){
        JASONResult json = new JASONResult();
        try{

            UserInfo userInfo = iUserInfoService.selectByUsernameAndType(username,UserInfo.TYPE_USER);

            Iplog iplog = new Iplog();
            iplog.setLoginTime(new Date());
            iplog.setUsername(userInfo.getUsername());
            iplog.setIp(request.getRemoteAddr());


            if(userInfo.getPassword().equals(password)){
                UserContext.putLoginInfo(userInfo);
                iplog.setState(Iplog.LOGIN_SUCCESS);
                json.setSuccess(true);
            }else{
                json.setSuccess(false);
                iplog.setState(Iplog.LOGIN_FAIL);
                json.setMessage("用户名或密码错误！");
            }

            iIplogService.insert(iplog);

        }catch (RuntimeException re){
            json.setSuccess(false);
            json.setMessage("用户名不存在！");
        }
        return json;
    }

}
