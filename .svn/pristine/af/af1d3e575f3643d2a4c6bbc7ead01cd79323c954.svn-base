package com.zbt.six.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Service;
import com.zbt.six.dao.OptionsInfoMapper;
import com.zbt.six.pojo.OptionsInfo;
import com.zbt.six.service.OptionsInfoService;
 
@Service("optionsInfoService")
public class OptionsInfoServiceImpl  implements OptionsInfoService{
	@Resource
	private OptionsInfoMapper optionsInfoMapper;

	@Override
	public List<OptionsInfo> selectByDepTypeId(Long deptypeid) {
		// TODO Auto-generated method stub
		return this.optionsInfoMapper.selectByDepTypeId(deptypeid);
	}
	
	@Override
	public int updateByPrimaryKeySelective(OptionsInfo record){
		// TODO Auto-generated method stub
				return this.optionsInfoMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int insertSelective(OptionsInfo record){
		// TODO Auto-generated method stub
		return this.optionsInfoMapper.insertSelective(record);
	}
}
