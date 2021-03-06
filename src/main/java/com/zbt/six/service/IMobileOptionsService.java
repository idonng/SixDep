package com.zbt.six.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zbt.six.pojo.OptionsInfo;
import com.zbt.six.pojo.OptionsRegistrInfo;
import com.zbt.six.pojo.Result;
import com.zbt.six.pojo.ResultList;
import com.zbt.six.pojo.UserInfo;

/**
 * 类名: IMobileOptionsService 
 * 功能: 积分填报，修改和展示
 * 作者: vivira
 * 日期: 2017-2-27
 */
public interface IMobileOptionsService {

	/**
	 * 功能: 通过个人信息id和当前时间确定个人已填写积分项
	 * 参数: @param user
	 * 参数: @return   
	 * 返回值类型: List<OptionsInfo>
	 * 时间: 2017-2-27 上午10:03:22
	 */
	List<OptionsInfo> selectCheckOptionsByUser(UserInfo user);

	/**
	 * 功能: 通过个人信息和当前时间确定手机端展示所有积分项
	 * 参数: @param req
	 * 参数: @return   
	 * 返回值类型: ResultList<OptionsInfo>
	 * 时间: 2017-2-27 上午10:03:31
	 */
	ResultList<OptionsInfo> selectAllOptionsByUser(HttpServletRequest req);

	/**
	 * 功能: 查询填报信息
	 * 参数: @param record
	 * 参数: @return   
	 * 返回值类型: OptionsRegistrInfo
	 * 时间: 2017-2-27 上午10:03:41
	 */
	OptionsRegistrInfo selectOptionsRegistrByUIdAndOId(OptionsRegistrInfo record);

	/**
	 * 功能: 增加或修改积分填报信息，和pc保持逻辑一致，取消该方法
	 * 参数: @param option
	 * 参数: @param req
	 * 参数: @return   
	 * 返回值类型: Result
	 * 时间: 2017-2-27 上午10:03:47
	 */
//	Result insertOrUpdateOptionRegistr(String option, HttpServletRequest req);
	
}
