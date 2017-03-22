package com.zbt.six.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbt.six.pojo.Result;
import com.zbt.six.pojo.ResultObject;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.impl.MobileLoginServiceImpl;
/**
 * 类名: MobileLoginController 
 * 功能: 短信验证码登录
 * 作者: vivira
 * 日期: 2017-2-27
 */
@Controller
@RequestMapping(value = "MobileLoginController")
public class MobileLoginController {

	@Resource
	MobileLoginServiceImpl mobileLoginService;
	
	/**
	 * 功能: 发送验证码接口
	 * 参数: @param MoblePhone
	 * 参数: @param req
	 * 参数: @return   
	 * 返回值类型: Result
	 * 时间: 2017-2-27 上午9:59:26
	 */
	@ResponseBody
	@RequestMapping("GetVerification.do")
	public Result GetVerification(@RequestParam("phone") String MoblePhone,
			HttpServletRequest req) {

		return mobileLoginService.send(MoblePhone, req);
	}

	/**
	 * 功能: 手机号、验证码登录接口
	 * 参数: @param UserPass
	 * 参数: @param MoblePhone
	 * 参数: @param req
	 * 参数: @return
	 * 参数: @throws Exception   
	 * 返回值类型: ResultObject<UserInfo>
	 * 时间: 2017-2-27 上午9:59:36
	 */
	@ResponseBody
	@RequestMapping("UserLogin.do")
	public ResultObject<UserInfo> UserLogin(
			@RequestParam("check") String UserPass,
			@RequestParam("phone") String MoblePhone, HttpServletRequest req)
			throws Exception {

		return mobileLoginService.login(UserPass, MoblePhone, req);
	}

}