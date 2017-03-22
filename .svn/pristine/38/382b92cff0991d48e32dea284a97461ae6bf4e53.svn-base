package com.zbt.six.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zbt.six.pojo.OptionsInfo;

public interface OptionsInfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(OptionsInfo record);

	int insertSelective(OptionsInfo record);

	OptionsInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(OptionsInfo record);

	int updateByPrimaryKey(OptionsInfo record);

	// 通过个人信息id和当前时间确定个人已填写积分项
	List<OptionsInfo> selectCheckOptionsByUser(@Param("userid") Long userid,
			@Param("deptypeid") Long deptypeid, @Param("frequency") int frequency,
			@Param("realTime") String realTime);

	// 通过个人信息和当前时间确定手机端展示所有积分项
	List<OptionsInfo> selectAllOptionsByUser(OptionsInfo record);
	
	//根据分类Id查询对应选项 1:大区经理2：推广经理3：管理员 ZED
    List<OptionsInfo> selectByDepTypeId(Long deptypeid);
}