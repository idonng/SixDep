package com.zbt.six.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbt.six.pojo.Result;
import com.zbt.six.pojo.ResultObject;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.IUserService;

@Controller
@RequestMapping("/page")
public class LoginController {
	@Resource
	private IUserService userService;

	@ResponseBody
	@RequestMapping("/login.do")
	public ResultObject<UserInfo> toIndex(
			@RequestParam("mobilephone") String mobilephone,
			@RequestParam("openid") String openid,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ResultObject<UserInfo> returnValue = new ResultObject<UserInfo>();
		UserInfo uservo = new UserInfo();
		uservo.setMobilephone(mobilephone);
		uservo.setOpenid(openid);
		uservo = userService.selectUser(uservo);
		if (uservo != null) {
			returnValue.setMessage("登录成功");
			returnValue.setStatus(1);
			returnValue.setSuccess(true);
			returnValue.setObject(uservo);
			session.setAttribute("user", uservo);
		} else {
			returnValue.setMessage("登录失败");
			returnValue.setStatus(0);
			returnValue.setSuccess(false);
		}
		return returnValue;
	}

	@RequestMapping("/index.do")
	public String toMain(HttpServletRequest request, Model model) {
		Result returnValue = new Result();
		returnValue.setMessage("跳转成功");
		returnValue.setStatus(1);
		returnValue.setSuccess(true);
		model.addAttribute(returnValue);
		return "index";
	}
}
