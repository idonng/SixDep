package com.zbt.six.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zbt.six.pojo.OptionsRegistrInfo;

public interface OptionsRegistrInfoMapper {
	int deleteByPrimaryKey(Long id);
	//插入新选项记录
	int insert(OptionsRegistrInfo ori);

	int insertSelective(OptionsRegistrInfo record);

	OptionsRegistrInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(OptionsRegistrInfo record);

	int updateByPrimaryKey(OptionsRegistrInfo record);

	// 查询填报信息
	OptionsRegistrInfo selectOptionsRegistrByUIdAndOId(OptionsRegistrInfo record);

	// 根据员工名、填报日期查询数据 ZED
	// List<OptionsRegistrInfo> selectByDateAndUserId(
	// @Param("realtime") String realtime, @Param("name") String name,
	// @Param("optionsid") Long optionsid);

	// 根据员工名、月份查询每天、每条选项数据ZED
	List<Map<String, Object>> selecOptionsByMonth(
			@Param("begintime") String begintime,
			@Param("endtime") String endtime,
			@Param("optionsid") Long optionsid,
			@Param("foruserid") Long foruserid);

	// 查询所有选项某一天的总数据得分ZED
	List<Map<String, Object>> selecOptionsScoresByDay(
			@Param("begintime") String begintime,
			@Param("endtime") String endtime,
			@Param("deptypeid") Long deptypeid,
			@Param("foruserid") Long foruserid);

	// 查询所有选项某一天的数据次数ZED
	List<Map<String, Object>> selecOptionsScoreByOption(
			@Param("begintime") String begintime,
			@Param("endtime") String endtime,
			@Param("deptypeid") Long deptypeid,
			@Param("foruserid") Long foruserid);

	// 查询某个选项所有天的总数据ZED
	List<Map<String, Object>> selecOptionsScoresByOption(
			@Param("begintime") String begintime,
			@Param("endtime") String endtime,
			@Param("deptypeid") Long deptypeid,
			@Param("foruserid") Long foruserid);

	// 查询总分数ZED
	List<Map<String, Object>> selecOptionsTotleScores(
			@Param("begintime") String begintime,
			@Param("endtime") String endtime,
			@Param("deptypeid") Long deptypeid,
			@Param("foruserid") Long foruserid);
	
	//更新选项记录
	int  updateByForUserIdRealTimeOptionsId(OptionsRegistrInfo ori);
	//查询是否记录已存在
	OptionsRegistrInfo selectByForUserIdRealTimeOptionsId(OptionsRegistrInfo ori);
}
