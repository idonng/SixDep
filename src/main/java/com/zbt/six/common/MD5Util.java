package com.zbt.six.common;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

	/*用户自定义的工具类*/
	public class MD5Util {
	  
		private final static String[] hexArray = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" }; // 存储十六进制值的数组
	
	/** 根据指定的字符串，创建加密后的字符串 */
	public static String createEncrypPassword(String string) {
		return encrypByMD5(string);
	}
	
	/** 检验输入的密码是否正确 */
	public static boolean verificationPassword(String password, String string) {
		if (password.equals(encrypByMD5(string))) {
			return true;
		} else {
			return false;
		}
	}
	
	/** 对指定的字符串进行MD5加密 */
	private static String encrypByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有MD5算法的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes("UTF-8"));
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHex(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/** 将字节数组转换成16进制，并以字符串的形式返回 */
	private static String byteArrayToHex(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHex(b[i]));
		}
		return resultSb.toString();
	}
	
	/** 将一个字节转换成16进制，并以字符串的形式返回 */
	private static String byteToHex(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexArray[d1] + hexArray[d2];
	}


	
	public static ArrayList<String> stringToArrayList(String s,String regex)
	{
		ArrayList<String> list = new ArrayList<String>();
		String[] arr = s.split(regex);
		for(String temp: arr)
		{
			list.add(temp);
		}
		return list;
	}
	
	//如果存在，先删除再追加，如果不存在，则直接屁股后面追加
	public static ArrayList<String> addItemsIdToArrayList(ArrayList<String> list,String id)
	{
		if(list.contains(id))
		{
			list.remove(id);
		}
		list.add(id);
		return list;
	}
	
	//把ArrayList转成String,使用逗号分隔
	public static String arrayListToString(ArrayList<String> list)
	{
		String s="";
		for(String temp:list)
		{
			s+=temp+",";
		}
		return s;
	}
	
	//把util.Date转为sql.Date
	public static java.sql.Date toSqlDate(Date date)
	{
		return new java.sql.Date(date.getTime());
	}
	
	//把sql.Date转为util.Date
	public static Date toUtilDate(java.sql.Date date)
	{
		return  new Date(date.getTime());
	}
	
	//把字符串日期(yyyy-MM-dd)类型转成util.Date类型
	public static Date stringToUitlDate(String date) throws Exception
	{
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}
	
	//把字符串数组转为字符串指定分隔符号
	public static String stringArrayToString(String[] arr,String split)
	{
		StringBuffer sb = new StringBuffer();
		for(String s:arr)
		{
			sb.append(s+split);
		}
		return sb.toString();
	}
	
	
	 
}
