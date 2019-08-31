package controller;

import com.gxa.cdut.Query.LoginInfoQueryObject;
import com.gxa.cdut.Query.PageResultSet;
import com.gxa.cdut.common.JASONResult;
import com.gxa.cdut.domain.UserInfo;
import com.gxa.cdut.service.IUserInfoService;
import com.gxa.cdut.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserInfoController {

    @Autowired
    private IUserInfoService iUserInfoService;



    @RequestMapping("user_list")
    public String list(LoginInfoQueryObject loginInfoQueryObject,Model model){


        PageResultSet pageResultSet = iUserInfoService.loginInfoTable(loginInfoQueryObject);

        model.addAttribute("userInf",UserContext.getLoginInfo());
        model.addAttribute("pageResultSet",pageResultSet);

        return "user/list";
    }


    @RequestMapping("index")
    public String index(Model model){

        model.addAttribute("userInf",UserContext.getLoginInfo());
        return "index";

    }

    @RequestMapping("login")
    @ResponseBody
    public JASONResult login(String username, String password){

        JASONResult json = new JASONResult();

            UserInfo userInfo = iUserInfoService.selectByUsernameAndType(username,UserInfo.TYPE_MGR);

            if(userInfo.getPassword().equals(password)){
                UserContext.putLoginInfo(userInfo);
                json.setSuccess(true);
            }else{
                json.setSuccess(false);
                json.setMessage("用户名或密码错误！");
            }

        return json;
    }



}
