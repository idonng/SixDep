package com.zbt.six.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.zbt.six.dao.UserInfoMapper;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.IUserService;
import com.zbt.six.pojo.PageBean;


@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserInfoMapper userDao;

	@Override
	public UserInfo selectUser(UserInfo record) {
		// TODO Auto-generated method stub
		return this.userDao.selectUser(record);
	}
	@Override
	public int updateByPrimaryKeySelective(UserInfo record) {
		// TODO Auto-generated method stub
		return this.userDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserInfo selectByPrimaryKey(Long userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}
	
	@Override
	public List<UserInfo> loadUser(PageBean pageBean) {
		// TODO Auto-generated method stub
		return this.userDao.loadUser(pageBean);
	}
	@Override
	public List<UserInfo> selectUsersByDepType(Long depTypeId) {
		// TODO Auto-generated method stub
		return this.userDao.selectUsersByDepType(depTypeId);
	}
	
	@Override
	//获取网站配置信息分页的总记录数
	public int getUserInfoCount(){
		// TODO Auto-generated method stub
		return this.userDao.getUserInfoCount();
	}
	
	@Override
	//添加人员信息
	public int insertSelective(UserInfo record){
		// TODO Auto-generated method stub
		return this.userDao.insertSelective(record);
	}
	
	@Override
	//根据手机号码获取人员信息
	public UserInfo selectUserByPhone(String phone){
		// TODO Auto-generated method stub
	return this.userDao.selectUserByPhone(phone);
		
	}
}
