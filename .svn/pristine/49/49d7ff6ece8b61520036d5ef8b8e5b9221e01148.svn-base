package com.zbt.six.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbt.six.pojo.OptionsInfo;
import com.zbt.six.pojo.ResultList;
import com.zbt.six.service.impl.MobileOptionsServiceImpl;

@Controller
@RequestMapping(value = "MobileOptionsController")
public class MobileOptionsController {

	@Resource
	MobileOptionsServiceImpl mobileOptionsService;

	@ResponseBody
	@RequestMapping("selectAllOptionsByUser.do")
	public ResultList<OptionsInfo> selectAllOptionsByUser(HttpServletRequest req) {
		
		return  mobileOptionsService.selectAllOptionsByUser(req);
	}
	
}
