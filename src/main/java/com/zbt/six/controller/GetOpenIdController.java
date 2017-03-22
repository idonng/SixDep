package com.zbt.six.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.IMobileLoginService;

/**
 * 类名: GetOpenIdController 
 * 功能: 通过openid判断是否已登录
 * 作者: vivira
 * 日期: 2017-2-27
 */
@Controller
public class GetOpenIdController {

	@Resource
	IMobileLoginService mobileLoginService;
	
	protected Logger Logger = LoggerFactory.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping("GetOpenId.do")
	public void GetOpenId(HttpServletRequest req , HttpServletResponse resp) throws IOException {

		// 获取从C#项目中获取的用户openid
		String openid = req.getParameter("openid");
		req.getSession().setAttribute("openid", openid);
		UserInfo user = mobileLoginService.getUserByOpenId(openid);
		// Person中无此openid;
		if (user == null) {
			// 跳转到登陆界面
			Logger.info("跳转至登录界面，添加openid！");
			resp.sendRedirect("page/jsp/loginByVerification.jsp");
		} else {// 用户表中存在该openid，直接进入积分查看界面
			req.getSession().setAttribute("user", user);
			Logger.info("openid已存在，直接跳转至积分查看界面");
			resp.sendRedirect("page/jsp/ShowOptions.jsp");
		}
	}
}
