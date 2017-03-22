package com.zbt.six.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.springframework.cglib.transform.impl.AddInitTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbt.six.common.TransType;
import com.zbt.six.pojo.OptionsInfo;
import com.zbt.six.pojo.PageBean;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.DepTypeInfoService;
import com.zbt.six.service.IUserService;
import com.zbt.six.service.OptionsInfoService;

@Controller
@RequestMapping("/page")
public class OptionController {

	@Resource
	private DepTypeInfoService depTypeInfoService;
	
	@Resource
	private OptionsInfoService optionsInfoService;

	private static final Logger logger = Logger.getLogger(OptionController.class);

	// @RequestMapping("/showUser")
	// public String toIndex(HttpServletRequest request, Model model) {
	// String phone =request.getParameter("id");
	// UserInfo user = this.userService.validateLogin(phone);
	// model.addAttribute("user", user);
	// return "showUser";
	// }
//
//	@ResponseBody
//	@RequestMapping("/updatepwd.do")
//	public String updatepwd(@RequestParam("uid") String id,
//			@RequestParam("userpwd") String userpwd,
//			@RequestParam("newpwd") String newpwd, HttpServletRequest request,
//			HttpServletResponse response) {
//		String oldpwd = userpwd;
//		// UserInfo olduser = new UserInfo();
//		// olduser.setMobilephone(mobilephone);
//		Long userid = Long.parseLong(id);
//		String oldpwdsString = this.userService.selectByPrimaryKey(userid)
//				.getOpenid();
//		if (oldpwd.equals(oldpwdsString)) {
//			UserInfo user = new UserInfo();
//			user.setId(userid);
//			user.setOpenid(newpwd);
//			int i = this.userService.updateByPrimaryKeySelective(user);
//			if (i == 1) {
//				// System.out.println("更新成功！");
//				return "1";
//			} else {
//				// System.out.println("更新失败！");
//				return "2";
//			}
//		} else {
//			// System.out.println("原始密码不正确！");
//			return "0";
//		}
//	}


	@ResponseBody
	@RequestMapping("/selectOptions.do")
	public Map<String, Object> selectOptions(@RequestParam("deptypeid") String deptypeid,HttpServletRequest request,
			HttpServletResponse response) {
		//查询选项信息信息
		Long dtid = Long.parseLong(deptypeid);
		List<OptionsInfo> optionsInfos = this.optionsInfoService.selectByDepTypeId(dtid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("option", optionsInfos);
		return map;
	}

	// 删除人员信息
	@ResponseBody
	@RequestMapping("/deleteOption.do")
	public String deleteOption(@RequestParam("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
		OptionsInfo option=new OptionsInfo();
		option.setId(id);
		option.setIsenable(0);
		int result = this.optionsInfoService.updateByPrimaryKeySelective(option);

		if (result > 0) {
			// 删除成功
			return "1";
		} else {
			// 删除失败
			return "0";
		}
	}

	//
	 //修改选项信息
	@ResponseBody
	@RequestMapping("/updateOption.do")
	public String updateOption(@RequestParam("id") Long id,
			@RequestParam("optionscode") Integer optionscode,
			@RequestParam("name") String name,
			@RequestParam("score") Integer score,
			@RequestParam("unit") String unit,
			@RequestParam("frequency") Integer frequency,
			@RequestParam("deptypeid") String deptypeid, HttpServletRequest request,
			HttpServletResponse response) {
		int result = 0;

		OptionsInfo updateopInfo=new OptionsInfo();
		updateopInfo.setId(id);
		updateopInfo.setOptionscode(optionscode);
		updateopInfo.setName(name);
		updateopInfo.setScore(score);
		updateopInfo.setUnit(unit);
		updateopInfo.setFrequency(frequency);
		Long typeid = Long.parseLong(deptypeid);
		updateopInfo.setDeptypeid(typeid);
		updateopInfo.setIsenable(1);
		
		result=this.optionsInfoService.updateByPrimaryKeySelective(updateopInfo);

		if (result > 0) {
			// 修改成功
			return "1";
		} else {
			// 修改失败
			return "0";
		}
	}
	
	// 添加选项信息
	@ResponseBody
	@RequestMapping("/addOption.do")
	public String addOption(@RequestParam("optionscode") Integer optionscode,
			@RequestParam("name") String name,
			@RequestParam("score") Integer score,
			@RequestParam("unit") String unit,
			@RequestParam("frequency") Integer frequency,
			@RequestParam("deptypeid") String deptypeid, HttpServletRequest request,
			HttpServletResponse response) {
		int result = 0;
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		OptionsInfo addopInfo=new OptionsInfo();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		addopInfo.setOptionscode(optionscode);
		addopInfo.setName(name);
		addopInfo.setScore(score);
		addopInfo.setUnit(unit);
		addopInfo.setFrequency(frequency);
		Long typeid = Long.parseLong(deptypeid);
		addopInfo.setDeptypeid(typeid);
		addopInfo.setIsenable(1);
		addopInfo.setAdduser(user.getName());

		try {
			addopInfo.setAdddate(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result=this.optionsInfoService.insertSelective(addopInfo);

		if (result > 0) {
			// 添加成功
			return "1";
		} else {
			// 添加失败
			return "0";
		}

	}
}