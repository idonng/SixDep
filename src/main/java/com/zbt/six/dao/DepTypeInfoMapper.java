package com.zbt.six.dao;

import java.util.List;

import com.zbt.six.pojo.DepTypeInfo;

public interface DepTypeInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DepTypeInfo record);

    int insertSelective(DepTypeInfo record);


    int updateByPrimaryKeySelective(DepTypeInfo record);

    int updateByPrimaryKey(DepTypeInfo record);
    
    
    //根据职位ID获取名称 ZED
    DepTypeInfo selectByPrimaryKey(Long deptypeid);
    //获取所有职位信息（不包括管理员）ZED
    List<DepTypeInfo> selectAll();
}