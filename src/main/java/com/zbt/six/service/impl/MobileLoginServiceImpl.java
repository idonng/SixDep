package com.zbt.six.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zbt.six.common.DateUtil;
import com.zbt.six.common.MD5Util;
import com.zbt.six.common.smsService;
import com.zbt.six.dao.DepTypeInfoMapper;
import com.zbt.six.dao.UserInfoMapper;
import com.zbt.six.pojo.DepTypeInfo;
import com.zbt.six.pojo.Result;
import com.zbt.six.pojo.ResultObject;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.IMobileLoginService;

/**
 * 类名: MobileLoginServiceImpl 
 * 功能: 手机验证码登录
 * 作者: vivira
 * 日期: 2017-2-27
 */
@Service("mobileLoginService")
public class MobileLoginServiceImpl implements IMobileLoginService{

	@Resource
	UserInfoMapper userInfoMapper;
	
	@Resource
	private DepTypeInfoMapper depMapper;
	
	protected Logger Logger = LoggerFactory.getLogger(this.getClass());
	
	Calendar calendar = new GregorianCalendar();
	//realTime时间格式化
	SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
	String begin = simpleFormate.format(calendar.getTime());
	//判断当天时间是否属于12-次日12点
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String dateByInterval =df.format(calendar.getTime());
	
	
	/**
	 * 功能: 发送验证码
	 * 参数: @param MoblePhone
	 * 参数: @param req
	 * 参数: @return   
	 * 返回值类型: Result
	 * 时间: 2017-2-27 上午10:04:14
	 */
	@Override
	public Result send(String MoblePhone, HttpServletRequest req) {
		// TODO Auto-generated method stub
		Result result = new Result();
		UserInfo userInfo = userInfoMapper.validateLogin(MoblePhone);
		if (userInfo != null) {
			int r = (int) ((Math.random() * 9 + 1) * 1000);
			try {
				smsService.sendSms("zbtbuchang","WangZhan2016", MoblePhone,"您的验证码为:"+ Integer.toString(r)+"【步长制药】",null);	
				req.getSession().setAttribute("code",
						MD5Util.createEncrypPassword(String.valueOf(r)));
				result.setMessage(MoblePhone + "发送验证码:" + r
						+ " 成功");
				result.setStatus(1);
				result.setSuccess(true);
				Logger.info("sendMessage : " + MoblePhone + "发送验证码:" + r
						+ " 成功");
			} catch (Exception e) {
				result.setMessage(MoblePhone + "短信发送失败");
				result.setStatus(0);
				result.setSuccess(false);
				Logger.error(MoblePhone + "短信发送失败", e);
			}
		} else {
			result.setMessage("手机号码不存在");
			result.setStatus(1);
			result.setSuccess(false);
			System.out.println(MoblePhone + "手机号码不存在");
			Logger.info(MoblePhone + "手机号码不存在");
		}
		System.out.println(JSONObject.fromObject(result));
		return result;
	}

	/**
	 * 功能: 登录
	 * 参数: @param UserPass
	 * 参数: @param MoblePhone
	 * 参数: @param req
	 * 参数: @return   
	 * 返回值类型: ResultObject<UserInfo>
	 * 时间: 2017-2-27 上午10:04:17
	 */
	@Override
	public ResultObject<UserInfo> login(String UserPass, String MoblePhone,
			HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		ResultObject<UserInfo> result = new ResultObject<UserInfo>();
		String openid=(String) req.getSession().getAttribute("openid");
		if(openid==null || "".equals(openid)){
			result.setMessage("用户session失效，请重新登录");
			result.setStatus(2);
			result.setSuccess(false);
			Logger.info( "用户session失效，请重新登录");
			return result;
		}
		try {
			UserInfo userInfo = userInfoMapper.validateLogin(MoblePhone);
			if (MoblePhone.equals(userInfo.getMobilephone())) {
				if (MD5Util.createEncrypPassword(UserPass).equals(
						String.valueOf(req.getSession().getAttribute("code")))) {
					
					userInfoMapper.updateOpenidByPhone(openid, MoblePhone);
					//前台展示职位信息
					if(userInfo != null){
						DepTypeInfo dep = depMapper.selectByPrimaryKey(userInfo.getDeptypeid());
						//判断今天12点到明天12点为今天
						String real= DateUtil.getDateByInterval(dateByInterval,DateUtil.addHouse(begin, 12));
						userInfo.setRealTime(real);
						if(dep != null){
							userInfo.setDeptname(dep.getName());
						}
					}
					req.getSession().setAttribute("user", userInfo);
					req.getSession().removeAttribute("code");
					result.setMessage(MoblePhone + "登录信息验证成功");
					result.setStatus(1);
					result.setSuccess(true);
					Logger.info(MoblePhone + "登录信息验证成功");
				} else {
					result.setMessage("验证码错误");
					result.setStatus(1);
					result.setSuccess(false);
					Logger.info(MoblePhone + "验证码错误");
				}
			}
		} catch (Exception e) {
			result.setMessage("网络异常，请稍后再试");
			result.setStatus(0);
			result.setSuccess(false);
			Logger.error("网络异常，请稍后再试", e);
		}
		System.out.println(JSONObject.fromObject(result));
		return result;
	}
	
	/**
	 * 功能: 通过openid得到用户对象
	 * 参数: @param openId
	 * 参数: @return   
	 * 返回值类型: UserInfo
	 * 时间: 2017-2-27 上午10:04:20
	 */
	@Override
	public  UserInfo getUserByOpenId(String openId){

		UserInfo user=userInfoMapper.getUserByOpenId(openId);
		if(user != null){
			DepTypeInfo dep = depMapper.selectByPrimaryKey(user.getDeptypeid());
			//判断今天12点到明天12点为今天
			String real= DateUtil.getDateByInterval(dateByInterval,DateUtil.addHouse(begin, 12));
			user.setRealTime(real);
			if(dep != null){
				user.setDeptname(dep.getName());
			}
		}
		return user;
	}
	
	
	/**
	 * 功能: 个人信息添加openid
	 * 参数: @param openid
	 * 参数: @param phone
	 * 参数: @return   
	 * 返回值类型: int
	 * 时间: 2017-2-27 上午10:04:23
	 */
	@Override
	public  int updateOpenidByPhone(String openid , String phone){
		
		return userInfoMapper.updateOpenidByPhone(openid, phone);
	}

}
