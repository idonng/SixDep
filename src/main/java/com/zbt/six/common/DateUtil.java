package com.zbt.six.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {
	
	private static final Logger logger = Logger.getLogger(DateUtil.class);

	/**
	 * 功能: 日期转字符串
	 * 参数: 日期 date
	 * 返回值类型: String
	 * 时间:  2016/12/4 11:35
	 */
	public static String parseStringByBoth(Date date) {
		if (date == null)
			return null;
		DateFormat formatParse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatParse.format(date);
	}
	
	/**
	 * 功能: 转换中文格式
	 * 参数: 字符串日期 specifiedDay
	 * 返回值类型: String
	 * 时间:  2016/12/4 11:35
	 */
	public static String parseString(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd失败！", e);
		}
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return year + "年" + month + "月" + day + "日";
	}

	/**
	 * 功能: 判断月日时
	 * 参数: 字符串日期 specifiedDay
	 * 返回值类型: String
	 * 时间:  2016/12/4 11:35
	 */
	public static String JudgeHouse(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd HH:mm失败！", e);
		}
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		int house = c.get(Calendar.HOUR_OF_DAY);
		return month + "/" + day + " " + house + "时";
	}

	/**
	 * 功能:  判断月日
	 * 参数: 字符串日期 specifiedDay
	 * 返回值类型: String
	 * 时间:  2016/12/4 11:35
	 */
	public static String JudgeDay(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd失败！", e);
		}
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return month + "/" + day;
	}

	/**
	 * 功能: 判断年月
	 * 参数: 字符串日期 specifiedDay
	 * 返回值类型: String
	 * 时间:  2016/12/4 11:35
	 */
	public static String JudgeYearMonth(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd失败！", e);
		}
		c.setTime(date);

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		//数字转字符串前面自动补0
		String str = String.format("%02d",month);
		return year + "-" + str;
	}

	/**
	 * 功能: 判断某年某月多少天
	 * 参数: 字符串日期 specifiedDay
	 * 返回值类型: int
	 * 时间:  2016/12/4 13:35
	 */
	public static int JudgeDateAmount(String specifiedDay) {
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd失败！", e);
		}
		cal.setTime(date);
		int maxDate = cal.getActualMaximum(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 功能: 获得指定日期的前N天
	 * 参数: 字符串日期 specifiedDay
	 * 参数: N number
	 * 返回值类型: String
	 * 时间:  2016/12/4 13:35
	 */
	public static String getDayBefore(String specifiedDay, int number) {// 可以用new
																		// Date().toLocalString()传递参数
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd HH:mm失败！", e);
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - number);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c
				.getTime());
		return dayBefore;
	}

	/**
	 * 功能: 获得指定日期的后N天
	 * 参数: 字符串日期 specifiedDay
	 * 参数: N number
	 * 返回值类型: String
	 * 时间:  2016/12/4 14:35
	 */
	public static String getDayAfter(String specifiedDay, int number) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd HH:mm失败！", e);
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + number);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd HH:mm")
				.format(c.getTime());
		return dayAfter;
	}

	/**
	 * 功能: 增加N小时
	 * 参数: 字符串日期 specifiedDay
	 * 参数: N number
	 * 返回值类型: String
	 * 时间:  2016/12/4 14:45
	 */
	public static String addHouse(String specifiedDay, int number) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(specifiedDay + "转换日期格式-yyyy-MM-dd失败！", e);
		}
		c.setTime(date);
		int house = c.get(Calendar.HOUR_OF_DAY);
		c.set(Calendar.HOUR_OF_DAY, house + number);

		String addHouse = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c
				.getTime());
		return addHouse;
	}

	/**
	 * 功能: 得到当前日期
	 * 返回值类型: String
	 * 时间:  2016/12/4 14:30
	 */
	public static String getCurrentDate() {
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(date);
	}

	/**
	 * 功能: 判断两个时间段之间多少天
	 * 参数: 开始时间 date1
	 * 参数: 结束时间 date2
	 * 返回值类型: int
	 * 时间:  2016/12/4 14:35
	 */
	public static int compareDate(String date1, String date2) {
		int n = 0;
		date1 = date1 == null ? getCurrentDate() : date1;
		date2 = date2 == null ? getCurrentDate() : date2;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(date1));
			c2.setTime(df.parse(date2));
		} catch (Exception e3) {
			logger.error(date1+"或"+date2+ "转换日期格式-yyyy-MM-dd失败！", e3);
		}
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			n++;
			c1.add(Calendar.DATE, 1);
		}
		return n;
	}
	/**
	 * 判断某一时间是否在一个区间内
	 * 
	 * @param sourceTime
	 *            时间区间,半闭合,如[10:00-20:00)
	 * @param curTime
	 *            需要判断的时间 如10:00
	 * @return 
	 * @throws IllegalArgumentException
	 */
	public static boolean isInTime(String sourceTime, String curTime) {
	    if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
	        throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
	    }
	    if (curTime == null || !curTime.contains(":")) {
	        throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
	    }
	    String[] args = sourceTime.split("-");
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    try {
	        long now = sdf.parse(curTime).getTime();
	        long start = sdf.parse(args[0]).getTime();
	        long end = sdf.parse(args[1]).getTime();
	        if (args[1].equals("00:00")) {
	            args[1] = "24:00";
	        }
	        if (end < start) {
	            if (now >= end && now < start) {
	                return false;
	            } else {
	                return true;
	            }
	        } 
	        else {
	            if (now >= start && now < end) {
	                return true;
	            } else {
	                return false;
	            }
	        }
	    } catch (ParseException e) {
	        e.printStackTrace();
	        throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
	    }

	}
	
	/**
	 * 功能: java比较两个日期大小
	 * 参数: @param DATE1
	 * 参数: @param DATE2
	 * 参数: @return   
	 * 返回值类型: int
	 * 时间: 2017-2-22 下午4:17:54
	 */
	public static int compare_date(String DATE1, String DATE2) {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	        try {
	            Date dt1 = df.parse(DATE1);
	            Date dt2 = df.parse(DATE2);
	            if (dt1.getTime() > dt2.getTime()) {
	                System.out.println("dt1 在dt2前");
	                return 1;
	            } else if (dt1.getTime() < dt2.getTime()) {
	                System.out.println("dt1在dt2后");
	                return -1;
	            } else {
	                return 0;
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        }
	        return 0;
	    }
	
	/**
	 * 功能: 判断某一时间12-次日12之间为当天
	 * 参数: @param DATE
	 * 参数: @param DATE1
	 * 参数: @param DATE2
	 * 参数: @return   
	 * 返回值类型: int
	 * 时间: 2017-2-22 下午4:21:24
	 */
	public static String getDateByInterval(String DATE,String DATE1) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
        	Date date = df.parse(DATE);
            if (date.getTime() < df.parse(DATE1).getTime()) {
            	return getDayBefore(DATE, 1).substring(0,10);
            }else{
            	return DATE.substring(0,10);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
	
	 /**
     * 
     * 功能: 判断是否是月末
     * @param 日期
     * @return true月末,false不是月末
     */
    public static boolean isLastDay(Date date){
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        if(cal.get(Calendar.DATE)==cal.getActualMaximum(Calendar.DAY_OF_MONTH))
            return true;
        else
            return false;
    }
}
