package com.zbt.six.service;

import java.util.List;

import com.zbt.six.pojo.OptionsRegistrImgPath;

public interface OptionsRegistrImgPathService {
    int deleteByPrimaryKey(Long id);

    int insert(OptionsRegistrImgPath record);

    int insertSelective(OptionsRegistrImgPath record);

    //通过userid和时间查询图片List
    List<OptionsRegistrImgPath> selectImageListByUserIdAndRealTime(Long forUserId,String realTime);

    int updateByPrimaryKeySelective(OptionsRegistrImgPath record);

    int updateByPrimaryKey(OptionsRegistrImgPath record);
    
    OptionsRegistrImgPath selectByPrimaryKey(Long id);
    
    
}