package com.zbt.six.service;

import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.zbt.six.pojo.OptionsInfo;

public interface OptionsInfoService {
	
	  List<OptionsInfo> selectByDepTypeId(Long deptypeid);
	  
	  public int updateByPrimaryKeySelective(OptionsInfo record);
	  
	  public int insertSelective(OptionsInfo record);
}
