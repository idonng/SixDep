package com.zbt.six.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;

import org.codehaus.jackson.map.ObjectMapper;

public class TransType {

	// 将List<String>转换成JSONArray
	public JSONArray getJsonByListString(List<String> lists) {
		JSONArray jsonString = JSONArray.fromObject(lists);
		return jsonString;
	}

	// 实体类、List、Map等类型转成json类型的String
	public static String objectToJson(Object obj) throws JSONException,
			IOException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(obj);
		return jsonStr;
	}

	//判断当天是否是本月最后一天
	public static int checkDateLastDay(String today){
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date;
		try {
			date = simpleFormate.parse(today);
			calendar.setTime(date);
			today=simpleFormate.format(calendar.getTime());
			calendar.set(Calendar.DATE, 1);// 把日期设置为当月第一天
			calendar.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
			String monthEnd = simpleFormate.format(calendar.getTime());
			if (today.equals(monthEnd)) {
				return 1;
			}
			else{
				return 0;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	 
	}
}
