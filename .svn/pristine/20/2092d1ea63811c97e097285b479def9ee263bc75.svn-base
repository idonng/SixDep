package com.zbt.six.service;

import javax.servlet.http.HttpServletRequest;

import com.zbt.six.pojo.ResultObject;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.pojo.Result;

/**
 * 类名: IMobileLoginService 
 * 功能: 手机号验证码登录
 * 作者: vivira
 * 日期: 2017-2-27
 */
public interface IMobileLoginService {
	
	/**
	 * 功能: 发送验证码
	 * 参数: @param MoblePhone
	 * 参数: @param req
	 * 参数: @return   
	 * 返回值类型: Result
	 * 时间: 2017-2-27 上午10:04:14
	 */
	Result send(String MoblePhone,HttpServletRequest req) ;
	
	/**
	 * 功能: 登录
	 * 参数: @param UserPass
	 * 参数: @param MoblePhone
	 * 参数: @param req
	 * 参数: @return   
	 * 返回值类型: ResultObject<UserInfo>
	 * 时间: 2017-2-27 上午10:04:17
	 */
	ResultObject<UserInfo> login(String UserPass,String MoblePhone,HttpServletRequest req);
	
	/**
	 * 功能: 通过openid得到用户对象
	 * 参数: @param openId
	 * 参数: @return   
	 * 返回值类型: UserInfo
	 * 时间: 2017-2-27 上午10:04:20
	 */
	UserInfo getUserByOpenId(String openId);
	
	/**
	 * 功能: 个人信息添加openid
	 * 参数: @param openid
	 * 参数: @param phone
	 * 参数: @return   
	 * 返回值类型: int
	 * 时间: 2017-2-27 上午10:04:23
	 */
	int updateOpenidByPhone(String openid , String phone);
}
