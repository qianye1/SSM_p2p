package controller;

import com.gxa.cdut.domain.Acount;
import com.gxa.cdut.domain.Detail;
import com.gxa.cdut.domain.UserInfo;
import com.gxa.cdut.service.IAccountService;
import com.gxa.cdut.service.IDetailService;
import com.gxa.cdut.service.IUserInfoService;
import com.gxa.cdut.util.JSONResult;
import com.gxa.cdut.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 个人中心 - 账户信息
 * 
 * @author novo
 * 
 */
@Controller
public class AccountController {

	@Autowired
	private IAccountService iAccountService;

	@Autowired
	private IDetailService iDetailService;


	@RequestMapping("personal")
	public String personalCenter(Model model) {


		Detail userInfo = iDetailService.selectById(UserContext.getLoginInfo().getId());

		model.addAttribute("userinf",userInfo);


//		ModelAndView mv = new ModelAndView("personal");
//		mv.addObject("userinfo",userInfo);

		Acount acount = iAccountService.selectById(userInfo.getId());

		model.addAttribute("account",acount);


		return "personal";
	}


}
