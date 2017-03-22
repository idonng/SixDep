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

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbt.six.common.TransType;
import com.zbt.six.pojo.PageBean;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.DepTypeInfoService;
import com.zbt.six.service.IUserService;

@Controller
@RequestMapping("/page")
public class UserController {
	@Resource
	private IUserService userService;
	@Resource
	private DepTypeInfoService depTypeInfoService;

	private static final Logger logger = Logger.getLogger(UserController.class);

	// @RequestMapping("/showUser")
	// public String toIndex(HttpServletRequest request, Model model) {
	// String phone =request.getParameter("id");
	// UserInfo user = this.userService.validateLogin(phone);
	// model.addAttribute("user", user);
	// return "showUser";
	// }

	@ResponseBody
	@RequestMapping("/updatepwd.do")
	public String updatepwd(@RequestParam("uid") String id,
			@RequestParam("userpwd") String userpwd,
			@RequestParam("newpwd") String newpwd, HttpServletRequest request,
			HttpServletResponse response) {
		String oldpwd = userpwd;
		// UserInfo olduser = new UserInfo();
		// olduser.setMobilephone(mobilephone);
		Long userid = Long.parseLong(id);
		String oldpwdsString = this.userService.selectByPrimaryKey(userid)
				.getOpenid();
		if (oldpwd.equals(oldpwdsString)) {
			UserInfo user = new UserInfo();
			user.setId(userid);
			user.setOpenid(newpwd);
			int i = this.userService.updateByPrimaryKeySelective(user);
			if (i == 1) {
				// System.out.println("更新成功！");
				return "1";
			} else {
				// System.out.println("更新失败！");
				return "2";
			}
		} else {
			// System.out.println("原始密码不正确！");
			return "0";
		}
	}


	@ResponseBody
	@RequestMapping("/loadUserInfo.do")
	public Map<String, Object> loadUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		// 加载人员信息
		// 记录总数
		int total = userService.getUserInfoCount();
		PageBean pageBean = new PageBean(total, request);

		List<UserInfo> userInfos = this.userService.loadUser(pageBean);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", userInfos);
		map.put("pageSize", pageBean.getPageSize());
		List<String> listDepName=new ArrayList<String>();
		for(UserInfo ui:userInfos){
			String depNameString=this.depTypeInfoService.selectByPrimaryKey(ui.getDeptypeid()).getName();
			listDepName.add(depNameString);
		}
 		map.put("position",listDepName);
		return map;
	}

	// 删除人员信息
	@ResponseBody
	@RequestMapping("/deleteUser.do")
	public String deleteUser(@RequestParam("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
		UserInfo user = new UserInfo();
		user.setId(id);
		user.setIsenable(0);
		int result = this.userService.updateByPrimaryKeySelective(user);

		if (result > 0) {
			// 删除成功
			return "1";
		} else {
			// 删除失败
			return "0";
		}
	}

	//
	 //修改人员信息
	@ResponseBody
	@RequestMapping("/updateUser.do")
	public String updateUser(@RequestParam("id") Long id,
			@RequestParam("name") String name,
			@RequestParam("area") String area,
			@RequestParam("mobilephone") String mobilephone,
			@RequestParam("deptypeid") String deptypeid, HttpServletRequest request,
			HttpServletResponse response) {
		int result = 0;
		//UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		UserInfo updateUser = new UserInfo();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//addUser.setAdduserid(user.getId());
		updateUser.setId(id);
		updateUser.setName(name);
		Long typeid = Long.parseLong(deptypeid);
		updateUser.setDeptypeid(typeid);
		updateUser.setMobilephone(mobilephone);
		updateUser.setArea(area);
		updateUser.setIsenable(1);
//		try {
//			addUser.setAdddate(sdf.parse(sdf.format(new Date())));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// 通过手机号码判断该人员是否存在
		UserInfo uInfo = this.userService.selectUserByPhone(mobilephone);
		if (uInfo == null) {
			result = this.userService.updateByPrimaryKeySelective(updateUser);
		} else {
			int in = uInfo.getIsenable();
			Long uidLong=uInfo.getId();
			if (in == 1&&id!=uidLong) {
				// 该手机号码已存在
				return "2";
			} 
			else if(in == 1&&id==uidLong){
			result = this.userService.updateByPrimaryKeySelective(updateUser);
			}
		}
		if (result > 0) {
			// 添加成功
			return "1";
		} else {
			// 添加失败
			return "0";
		}
	}
	
	// 添加人员信息
	@ResponseBody
	@RequestMapping("/addUser.do")
	public String addUser(@RequestParam("name") String name,
			@RequestParam("type") String type,
			@RequestParam("phone") String phone,
			@RequestParam("area") String area, HttpServletRequest request,
			HttpServletResponse response) {
		int result = 0;
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		UserInfo addUser = new UserInfo();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		addUser.setAdduserid(user.getId());
		addUser.setName(name);
		Long typeid = Long.parseLong(type);
		addUser.setDeptypeid(typeid);
		addUser.setMobilephone(phone);
		addUser.setArea(area);
		addUser.setIsenable(1);
		try {
			addUser.setAdddate(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 通过手机号码判断该人员是否存在
		UserInfo uInfo = this.userService.selectUserByPhone(phone);
		if (uInfo == null) {
			result = this.userService.insertSelective(addUser);
		} else {
			int in = uInfo.getIsenable();
			if (in == 1) {
				// 该手机号码已存在
				return "2";
			} else if (in == 0) {
				// 更新人员信息
				addUser.setId(uInfo.getId());
				result = this.userService.updateByPrimaryKeySelective(addUser);
			}
		}
		if (result > 0) {
			// 添加成功
			return "1";
		} else {
			// 添加失败
			return "0";
		}

	}
}