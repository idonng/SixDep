package com.zbt.six.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zbt.six.pojo.UserInfo;
import com.zbt.six.pojo.PageBean;

public interface IUserService {

	public UserInfo selectUser(UserInfo record);
	
	int updateByPrimaryKeySelective(UserInfo record);
	
	UserInfo selectByPrimaryKey(Long userId);
	
	//获取人员信息
	public List<UserInfo> loadUser(PageBean pageBean);
	//获取人员信息 通过depTypeIdZED
	List<UserInfo>  selectUsersByDepType(Long depTypeId) ;
	
	//获取人员信息分页的总记录数
	public int getUserInfoCount();
	
	//添加人员信息
	public int insertSelective(UserInfo record);
	
	//根据手机号码获取人员信息
	public UserInfo selectUserByPhone(String phone);
}
