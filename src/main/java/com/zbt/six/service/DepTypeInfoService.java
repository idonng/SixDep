package com.zbt.six.service;

import java.util.List;

import com.zbt.six.pojo.DepTypeInfo;

public interface DepTypeInfoService {

	 DepTypeInfo selectByPrimaryKey(Long deptypeid);
	
	
	
	 List<DepTypeInfo> selectAll();
		

	
	
}
