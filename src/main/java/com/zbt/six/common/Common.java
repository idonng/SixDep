package com.zbt.six.common;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common {
	/**
	 * 
	 * @param specifiedDay  字符串日期精确到月 如：2017-02
	 * @return  int 天数如：28
	 * 功能: 判断某年某月多少天
	 * 时间:  2017.2.20
	 */
	public static int JudgeDateAmount(String specifiedDay) {
		specifiedDay=specifiedDay+"-01";
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(date);
		int maxDate = cal.getActualMaximum(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 功能: 判断年月
	 * 参数: 字符串日期 specifiedDay
	 * 返回值类型: String
	 * 时间:  2017.2.20
	 */
	public static String JudgeYearMonth(String specifiedDay) {
		specifiedDay=specifiedDay+"-01";
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			//logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd失败！", e);
		}
		c.setTime(date);

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		return year + "-" + month;
	}
	/**
	 * 功能: 获得指定日期的下个月第一天
	 * 参数: 字符串日期 specifiedDay
	 * 参数: N number
	 * 返回值类型: String
	 * 时间:  2017.2.20
	 */
	public static String getDayAfter(String specifiedDay, int number) {
		specifiedDay=specifiedDay+"-01";
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			//logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd失败！", e);
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + number);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}
	/**
	 * 功能: 获得指定日期的后N天
	 * 参数: 字符串日期 specifiedDay
	 * 参数: N number
	 * 返回值类型: String
	 * 时间:  2017.2.20
	 */
	public static String getDayAfter1(String specifiedDay, int number) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			//logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd失败！", e);
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + number);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}
	  //获取当前月天数
//    public int getDateOfCurrentMonth(){
//    	Calendar cal = Calendar.getInstance(); 
//    	int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
//    	return dateOfMonth;
//    }
    public static void main(String[] args) {
    	int i=JudgeDateAmount("2017-02");
    	System.out.println(i);
	}
}
