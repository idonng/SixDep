package com.zbt.six.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zbt.six.common.FileUploadUtil;
import com.zbt.six.pojo.OptionsRegistrImgPath;
import com.zbt.six.pojo.Result;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.OptionsRegistrImgPathService;
import com.zbt.six.service.OptionsRegistrInfoService;

@Controller
@RequestMapping("/page")
public class OptionsRegistrInfoController {
	private static final Logger logger = Logger
			.getLogger(OptionsRegistrInfoController.class);
	@Resource
	private OptionsRegistrInfoService optionsRegistrInfoService;

	@Resource
	private OptionsRegistrImgPathService optionsRegistrImgPathService;

	@ResponseBody
	@RequestMapping("/insertOrUpdateOptions.do")
	public String insertOrUpdateOptions(String realTime,
			@RequestParam("foruserid") long foruserid,
			@RequestParam("optionsIds") String optionsIds,
			@RequestParam("nums") String nums, HttpServletRequest request,
			HttpServletResponse response) {
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		if (user != null && user.getRealTime() != null) {
			realTime = user.getRealTime();
		} else {
			realTime = (String) request.getParameter("realTime");
		}
		String[] optionsIdss = optionsIds.split("##");
		String[] numss = nums.split("##");
		int optionNum = 0;
		for (int i = 0; i < optionsIdss.length; i++) {
			Long optionsid = Long.valueOf(optionsIdss[i]);
			int num = Integer.valueOf(numss[i]);
			logger.info("员工ID:" + foruserid + "选项ID：" + optionsid + "选项值："
					+ num + "时间：" + realTime + "管理员提交入库");
			optionNum += this.optionsRegistrInfoService.insertOrUpdateOptions(
					foruserid, realTime, optionsid, num, request);
		}
		if (optionNum == optionsIdss.length) {
			logger.info("员工ID:" + foruserid + "选项ID：" + "时间：" + realTime
					+ "所有选项值管理员提交入库成功");
			return "1";
		} else {
			logger.info("员工ID:" + foruserid + "选项ID：" + "时间：" + realTime
					+ "所有选项值管理员提交入库失败");
			return "0";
		}

	}

	@RequestMapping(value = "uploadImage.do", method = RequestMethod.POST)
	@ResponseBody
	public Result upload(@RequestParam("files") MultipartFile[] files,
			@RequestParam("foruserid") Long foruserid, String realTime,
			HttpServletRequest request) {
		// real时间格式化
		SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
		Result returnValue = new Result();
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		if (user != null && user.getRealTime() != null) {
			realTime = user.getRealTime();
		} else {
			realTime = (String) request.getParameter("realTime");
		}
		OptionsRegistrImgPath img = new OptionsRegistrImgPath();
		for (MultipartFile file : files) {
			try {
				String path = FileUploadUtil.upload(file, request);
				img.setImgpath(path);
				img.setForuserid(foruserid);
				img.setRealtime(simpleFormate.parse(realTime));
				img.setAddtime(new Date());
				img.setAdduserid(foruserid);
				optionsRegistrImgPathService.insert(img);
				logger.info("图片上传成功!");
				returnValue.setMessage("图片上传成功，路径为" + path);
				returnValue.setStatus(1);
				returnValue.setSuccess(true);

			} catch (Exception e) {
				logger.error("图片上传失败", e);
				returnValue.setMessage("图片上传失败");
				returnValue.setStatus(0);
				returnValue.setSuccess(false);
			}
		}
		return returnValue;
	}

	@RequestMapping("/deleteImage.do")
	@ResponseBody
	public Result deleteImageByPrimaryKey(HttpServletRequest req,
			@RequestParam("imgId") String imgId, 
			HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		Long id = Long.parseLong(imgId);
		Result returnValue = new Result();
		String currentPath = req.getSession().getServletContext()
				.getRealPath("/");
		try {
			OptionsRegistrImgPath record = optionsRegistrImgPathService.selectByPrimaryKey(id);
			// 删除数据库图片信息
			optionsRegistrImgPathService.deleteByPrimaryKey(id);
			// 删除图片文件
			deleteImg(currentPath, record.getImgpath());
			returnValue.setMessage(id + "-图片路径删除成功！");
			returnValue.setStatus(1);
			returnValue.setSuccess(true);
			logger.info(id + "-图片路径删除成功！");
		} catch (Exception e) {
			logger.error("网络异常，请稍后再试！", e);
			returnValue.setMessage("网络异常，请稍后再试！");
			returnValue.setStatus(0);
			returnValue.setSuccess(false);
		}
		System.out.println(JSONObject.fromObject(returnValue));
		return returnValue;
	}

	private void deleteImg(String path, String ImgName) {
		path = path.replace("%20", " ");
		File file = new File(path + ImgName);
		if (file.exists()) {
			file.delete();
			logger.info(ImgName + "-图片文件删除成功！");
		}
	}
}
