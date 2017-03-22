package com.zbt.six.common;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传处理类
 * 
 * @author lenovo
 *
 */
public class FileUploadUtil {
	/**
	 * 文件上传处理
	 * 
	 * @param file
	 *            上传的文件
	 * @param request
	 *            请求
	 * @return
	 * @throws Exception
	 */
	public static String upload(MultipartFile file, HttpServletRequest request)
			throws Exception {
		// 日期，用于按日生成文件夹
		String dateStr = DateFormatUtils.format(new Date(), "yyyyMMdd");
		// 文件名
		String filename = file.getOriginalFilename();
		// 利用文件名和时间生成UUID
		UUID uuid = UUID
				.nameUUIDFromBytes((System.currentTimeMillis() + filename)
						.getBytes());
		// 文件保存位置
		String url = "uploads\\" + dateStr + "\\" + uuid.toString()
				+ filename.substring(filename.lastIndexOf("."));
		// 请求URL路径 +文件保存位置
		String filePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ url;
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
				filePath));
		return url;
	}

	public static String upload(MultipartFile file, String suffix,
			HttpServletRequest request) throws Exception {
		// 日期，用于按日生成文件夹
		String dateStr = DateFormatUtils.format(new Date(), "yyyyMMdd");
		// 文件名
		String filename = file.getOriginalFilename();
		// 利用文件名和时间生成UUID
		UUID uuid = UUID
				.nameUUIDFromBytes((System.currentTimeMillis() + filename)
						.getBytes());
		// 文件保存位置
		String url = "/uploads/" + dateStr + "/"
				+ MD5.getMD5Code(dateStr + suffix) + "/" + uuid.toString()
				+ filename.substring(filename.lastIndexOf("."));
		// 请求URL路径 +文件保存位置
		String filePath = request.getSession().getServletContext()
				.getRealPath("/")
				+ url;
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
				filePath));
		return url;
	}
}
