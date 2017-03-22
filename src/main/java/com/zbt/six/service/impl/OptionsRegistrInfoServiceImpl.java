package com.zbt.six.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.zbt.six.dao.OptionsRegistrInfoMapper;
import com.zbt.six.pojo.OptionsRegistrInfo;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.OptionsRegistrInfoService;

@Service("optionsRegistrInfoService")
public class OptionsRegistrInfoServiceImpl implements OptionsRegistrInfoService {
	@Resource
	private OptionsRegistrInfoMapper optionsRegistrInfoDao;

	// @Override
	// public List<OptionsRegistrInfo> selectByDateAndUserId(String realtime,
	// String name,Long optionsid) {
	// // TODO Auto-generated method stub
	// return this.optionsRegistrInfoDao.selectByDateAndUserId(realtime,
	// name,optionsid);
	// }

	@Override
	public List<Map<String, Object>> selecOptionsByMonth(String begintime,
			String endtime, Long optionsid, Long foruserid) {
		// TODO Auto-generated method stub
		return this.optionsRegistrInfoDao.selecOptionsByMonth(begintime,
				endtime, optionsid, foruserid);
	}

	@Override
	public List<Map<String, Object>> selecOptionsScoresByDay(String begintime,
			String endtime, Long deptypeid, Long foruserid) {
		// TODO Auto-generated method stub
		return this.optionsRegistrInfoDao.selecOptionsScoresByDay(begintime,
				endtime, deptypeid, foruserid);
	}

	@Override
	public List<Map<String, Object>> selecOptionsScoresByOption(
			String begintime, String endtime, Long deptypeid, Long foruserid) {
		// TODO Auto-generated method stub
		return this.optionsRegistrInfoDao.selecOptionsScoresByOption(begintime,
				endtime, deptypeid, foruserid);
	}

	@Override
	public List<Map<String, Object>> selecOptionsTotleScores(String begintime,
			String endtime, Long deptypeid, Long foruserid) {
		// TODO Auto-generated method stub
		return this.optionsRegistrInfoDao.selecOptionsTotleScores(begintime,
				endtime, deptypeid, foruserid);
	}

	@Override
	public List<Map<String, Object>> selecOptionsScoreByOption(
			String begintime, String endtime, Long deptypeid, Long foruserid) {
		// TODO Auto-generated method stub
		return this.optionsRegistrInfoDao.selecOptionsScoreByOption(begintime,
				endtime, deptypeid, foruserid);
	}

	@Override
	public int insertOrUpdateOptions(Long foruserid, String realTime,
			Long optionsid, int num,HttpServletRequest req) {
		// TODO Auto-generated method stub
		UserInfo user = (UserInfo) req.getSession().getAttribute("user");
		Long adduseId = (long) 44;
		if (user != null) {
			adduseId = user.getId();
		}
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		int i = 0;
		try {
			OptionsRegistrInfo ori = new OptionsRegistrInfo();
			date = simpleFormate.parse(realTime);
			ori.setRealtime(date);
			ori.setForuserid(foruserid);
			ori.setOptionsid(optionsid);
			ori = this.optionsRegistrInfoDao
					.selectByForUserIdRealTimeOptionsId(ori);
			if (ori != null) {
				OptionsRegistrInfo oriUpdate = new OptionsRegistrInfo();
				oriUpdate.setRealtime(date);
				oriUpdate.setForuserid(foruserid);
				oriUpdate.setOptionsid(optionsid);
				oriUpdate.setUpdateuserid(adduseId);
				oriUpdate.setUpdatetime(new Date());
				oriUpdate.setNum(num);
				i = this.optionsRegistrInfoDao
						.updateByForUserIdRealTimeOptionsId(oriUpdate);
			} else {
				OptionsRegistrInfo oriInsert = new OptionsRegistrInfo();
				oriInsert.setRealtime(date);
				oriInsert.setForuserid(foruserid);
				oriInsert.setOptionsid(optionsid);
				oriInsert.setAdduserid(adduseId);
				oriInsert.setAddtime(new Date());
				oriInsert.setIsenable(1);
				oriInsert.setNum(num);
				i = this.optionsRegistrInfoDao.insert(oriInsert);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
