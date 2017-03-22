package com.zbt.six.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zbt.six.common.DateUtil;
import com.zbt.six.dao.OptionsInfoMapper;
import com.zbt.six.dao.OptionsRegistrInfoMapper;
import com.zbt.six.pojo.OptionsInfo;
import com.zbt.six.pojo.OptionsRegistrInfo;
import com.zbt.six.pojo.Result;
import com.zbt.six.pojo.ResultList;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.IMobileOptionsService;

/**
 * 类名: MobileOptionsServiceImpl 
 * 功能: 积分填报接口
 * 作者: vivira
 * 日期: 2017-2-22
 */
@Service("mobileOptionsService")
public class MobileOptionsServiceImpl implements IMobileOptionsService {

	protected Logger Logger = LoggerFactory.getLogger(this.getClass());

	ResultList<OptionsInfo> result = new ResultList<OptionsInfo>();

	@Resource
	OptionsInfoMapper optionsMapper;

	@Resource
	OptionsRegistrInfoMapper optionsRegistrMapper;

	Calendar calendar = new GregorianCalendar();
	//real时间格式化
	SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
	//填报和修改时间格式化
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now =sf.format(calendar.getTime());
	
	/**
	 * 功能: 通过个人信息id和当前时间确定个人已填写积分项
	 * 参数: @param user
	 * 参数: @return   
	 * 返回值类型: List<OptionsInfo>
	 * 时间: 2017-2-27 上午10:03:22
	 */
	@Override
	public List<OptionsInfo> selectCheckOptionsByUser(UserInfo user) {
		// TODO Auto-generated method stub
		int frequency = 1;
		List<OptionsInfo> options = null;
		String  real = user.getRealTime();
		if(real!=null && !"".equals(real)){
			// 判断是否为本月的最后一天
			try {
				if (DateUtil.isLastDay(simpleFormate.parse(real))) {
					frequency = 2;
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Logger.error("real日期转换失败",e1);
			}
			try {
				options = optionsMapper.selectCheckOptionsByUser(user.getId(),
						user.getDeptypeid(), frequency, real);
				Logger.info(user.getName() + "-已填报积分信息查询成功");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.error("网络异常，请稍后再试", e);
			}
		}
		return options;
	}

	/**
	 * 功能: 通过个人信息和当前时间确定手机端展示所有积分项
	 * 参数: @param req
	 * 参数: @return   
	 * 返回值类型: ResultList<OptionsInfo>
	 * 时间: 2017-2-27 上午10:03:31
	 */
	@Override
	public ResultList<OptionsInfo> selectAllOptionsByUser(HttpServletRequest req) {
		// TODO Auto-generated method stub
		
		UserInfo user = (UserInfo) req.getSession().getAttribute("user");
		if (user == null) {
			result.setMessage("用户session失效，请重新登录");
			result.setStatus(2);
			result.setSuccess(false);
			Logger.info( "用户session失效，请重新登录");
			return result;
		}
		int frequency = 1;
		String  real = user.getRealTime();
		if(real!=null && !"".equals(real)){
			// 本月的最后一天
			try {
				if (DateUtil.isLastDay(simpleFormate.parse(real))) {
					frequency = 2;
				}
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Logger.error("real日期转换失败",e1);
			}
		}
		try {
			OptionsInfo option = new OptionsInfo();
			option.setFrequency(frequency);
			option.setDeptypeid(user.getDeptypeid());
			List<OptionsInfo> list = optionsMapper
					.selectAllOptionsByUser(option);
			List<OptionsInfo> checklist = selectCheckOptionsByUser(user);
			result.getList().clear();
			for (OptionsInfo optionsInfo : list) {
				if (optionsInfo != null) {
					for (OptionsInfo options : checklist) {
						if (options != null
								&& optionsInfo.getId() == options.getId()) {
							optionsInfo.setCheck(true);
							int num = 1;
							OptionsRegistrInfo oRe = new  OptionsRegistrInfo();
							oRe.setForuserid(user.getId());
							oRe.setOptionsid(optionsInfo.getId());
							if(real!=null && !"".equals(real)){
								try {
									oRe.setRealtime(simpleFormate.parse(real));
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									Logger.error("real日期转换失败",e1);
								}
							}
							OptionsRegistrInfo oRegistr = selectOptionsRegistrByUIdAndOId(oRe);
							if (oRegistr != null) {
								num = oRegistr.getNum();
							}
							optionsInfo.setNumber(num);
						}
					}
					result.getList().add(optionsInfo);
				}
			}

			result.setMessage("积分信息查询成功");
			result.setStatus(1);
			result.setSuccess(true);
			Logger.info("积分信息查询成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.setMessage("网络异常，请稍后再试");
			result.setStatus(0);
			result.setSuccess(false);
			Logger.error("网络异常，请稍后再试", e);
		}
		System.out.println(JSONObject.fromObject(result));
		return result;
	}

	
	/**
	 * 功能: 增加或修改积分填报信息
	 * 参数: @param option
	 * 参数: @param req
	 * 参数: @return   
	 * 返回值类型: Result
	 * 时间: 2017-2-27 上午10:03:47
	 *//*
	@Override
	public Result insertOrUpdateOptionRegistr(String option,
			HttpServletRequest req) {
		// TODO Auto-generated method stub
		UserInfo user = (UserInfo) req.getSession().getAttribute("user");
		Result result = new Result();

		if (user == null) {
			result.setMessage("用户session失效，请重新登录");
			result.setStatus(2);
			result.setSuccess(false);
			Logger.info( "用户session失效，请重新登录");
			return result;
		}
		try{
			if (option != null && !"".equals(option)) {
				String[] options = option.split(",");
				for (int i = 0; i < options.length; i++) {
					if (options[i] != null && !"".equals(options[i])) {
						int len = 0;
						if(options[i].indexOf("N") > -1){
							len = options[i].indexOf("N");
						}else{
							len = options[i].indexOf("Y");
						}
						long id = Long.parseLong(options[i].substring(0,
								len));
						String type = options[i].substring(len,len+1);
						int number = Integer.parseInt(options[i].substring(
								len+1, options[i].length()));
						OptionsRegistrInfo oRegistr = new  OptionsRegistrInfo();
						oRegistr.setForuserid(user.getId());
						oRegistr.setOptionsid(id);
						String  real = user.getRealTime();
						if(real!=null && !"".equals(real)){
							try {
								oRegistr.setRealtime(simpleFormate.parse(real));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								Logger.error("real日期转换失败",e1);
							}
						}
						oRegistr = selectOptionsRegistrByUIdAndOId(oRegistr);
						OptionsRegistrInfo record = new OptionsRegistrInfo();
						if (oRegistr == null) {
							try {
								record.setAddtime(sf.parse(now));
								if(now!=null && !"".equals(real)){
									record.setRealtime(simpleFormate.parse(real));
								}
								record.setUpdatetime(sf.parse(now));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Logger.error("时间转换异常", e);
							}
							record.setAdduserid(user.getId());
							record.setForuserid(user.getId());
							record.setNum(number);
							record.setOptionsid(id);
							record.setUpdateuserid(user.getId());
							optionsRegistrMapper.insert(record);
						}else if ("Y".equals(type)) {
							try {
								record.setUpdatetime(sf.parse(now));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Logger.error("时间转换异常", e);
							}
							record.setNum(number);
							record.setId(oRegistr.getId());
							record.setUpdateuserid(user.getId());
							optionsRegistrMapper.updateByPrimaryKeySelective(record);
						} else {
							try {
								record.setUpdatetime(sf.parse(now));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								Logger.error("时间转换异常", e);
							}
							record.setNum(0);
							record.setId(oRegistr.getId());
							record.setUpdateuserid(user.getId());
							optionsRegistrMapper.updateByPrimaryKeySelective(record);
						} 
					}
				}
				result.setMessage("积分信息填报成功");
				result.setStatus(1);
				result.setSuccess(true);
				Logger.info("积分信息填报成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setMessage("网络异常，请稍后再试");
			result.setStatus(0);
			result.setSuccess(false);
			Logger.error("网络异常，请稍后再试", e);
		}
		System.out.println(JSONObject.fromObject(result));
		return result;
	}
*/
	/**
	 * 功能: 查询填报信息
	 * 参数: @param record
	 * 参数: @return   
	 * 返回值类型: OptionsRegistrInfo
	 * 时间: 2017-2-27 上午10:03:41
	 */
	@Override
	public OptionsRegistrInfo selectOptionsRegistrByUIdAndOId(OptionsRegistrInfo record) {
		// TODO Auto-generated method stub
		return optionsRegistrMapper.selectOptionsRegistrByUIdAndOId(record);
	}

}
