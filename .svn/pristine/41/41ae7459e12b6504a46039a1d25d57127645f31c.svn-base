package com.zbt.six.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zbt.six.dao.DepTypeInfoMapper;
import com.zbt.six.pojo.DepTypeInfo;
import com.zbt.six.service.DepTypeInfoService;
 
@Service("depTypeInfoService")
public class DepTypeInfoServiceImpl  implements DepTypeInfoService{
	@Resource
	private DepTypeInfoMapper depTypeInfoMapper;

	@Override
	public DepTypeInfo selectByPrimaryKey(Long deptypeid) {
		// TODO Auto-generated method stub
		return this.depTypeInfoMapper.selectByPrimaryKey(deptypeid);
	}

	@Override
	public List<DepTypeInfo> selectAll() {
		// TODO Auto-generated method stub
		return this.depTypeInfoMapper.selectAll();
	}
 
	 

	
}
