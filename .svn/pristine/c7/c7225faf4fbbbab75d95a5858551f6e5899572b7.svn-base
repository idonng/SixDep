package com.zbt.six.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zbt.six.pojo.OptionsRegistrImgPath;

public interface OptionsRegistrImgPathMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OptionsRegistrImgPath record);

    int insertSelective(OptionsRegistrImgPath record);

    OptionsRegistrImgPath selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OptionsRegistrImgPath record);

    int updateByPrimaryKey(OptionsRegistrImgPath record);
    
    List<OptionsRegistrImgPath> selectImageListByUserIdAndRealTime(@Param("forUserId") Long forUserId,@Param("realTime") String realTime);
}