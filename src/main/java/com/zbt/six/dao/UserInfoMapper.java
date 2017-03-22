package com.zbt.six.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zbt.six.pojo.UserInfo;
import com.zbt.six.pojo.PageBean;

public interface UserInfoMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    UserInfo selectUser(UserInfo record);
    
    UserInfo validateLogin(@Param("phone")String phone);
    
    UserInfo getUserByOpenId(@Param("openId")String openId);
    
    int updateOpenidByPhone(@Param("openid")String openid , @Param("phone")String phone);
    
	//获取人员信息
	public List<UserInfo> loadUser(PageBean pageBean);
	
	//通过职位ID获取人员信息 ZED
    List<UserInfo>  selectUsersByDepType(Long depTypeId);
    
	//获取人员信息分页的总记录数
	public int getUserInfoCount();
	
	//根据手机号码获取人员信息
	UserInfo selectUserByPhone(@Param("phone")String phone);
}