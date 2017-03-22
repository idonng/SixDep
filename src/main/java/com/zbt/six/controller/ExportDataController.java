package com.zbt.six.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zbt.six.common.Common;
import com.zbt.six.common.ComplexExportExcelClient;
import com.zbt.six.common.TransType;
import com.zbt.six.pojo.DepTypeInfo;
import com.zbt.six.pojo.OptionsInfo;
import com.zbt.six.pojo.OptionsRegistrImgPath;
import com.zbt.six.pojo.UserInfo;
import com.zbt.six.service.DepTypeInfoService;
import com.zbt.six.service.IUserService;
import com.zbt.six.service.OptionsInfoService;
import com.zbt.six.service.OptionsRegistrImgPathService;
import com.zbt.six.service.OptionsRegistrInfoService;

@Controller
@RequestMapping("/page")
public class ExportDataController {
	private static final Logger logger = Logger.getLogger(ExportDataController.class);
	@Resource
	private OptionsRegistrInfoService optionsRegistrInfoService;
	@Resource
	private OptionsRegistrImgPathService optionsRegistrImgPathService;
	@Resource
	private OptionsInfoService optionsInfoService;
	@Resource
	private IUserService userService;
	@Resource
	private DepTypeInfoService depTypeInfoService;
	 

	@ResponseBody
	@RequestMapping("/exportExcel.do")
	public void exportExcel(@RequestParam("userId") long userId,
			@RequestParam("month") String month, HttpServletRequest request,
			HttpServletResponse response) {
		exportDate(userId, month, response);
	}
	
	@ResponseBody
	@RequestMapping("/selectDepType.do")
	public String selectDepType( HttpServletRequest request,
			HttpServletResponse response) {
		List<DepTypeInfo> dti= this.depTypeInfoService.selectAll();
		try {
			String	json = TransType.objectToJson(dti);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "";
	}
	@ResponseBody
	@RequestMapping("/selectUsersByDepType.do")
	public String selectUsersByDepType(@RequestParam("depTypeId") long depTypeId, HttpServletRequest request,
			HttpServletResponse response) {
		List<UserInfo> ui= this.userService.selectUsersByDepType(depTypeId);
		try {
			String	json = TransType.objectToJson(ui);
			return json;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "";
	}
	@ResponseBody
	@RequestMapping("/queryOptionsScoreByOption.do")
	public String queryOptionsScoreByOption(@RequestParam("userId") long userId,
			@RequestParam("date") String date, HttpServletRequest request,
			HttpServletResponse response) {

		 return queryOptionsScore(userId, date);

	}

	@ResponseBody
	@RequestMapping("/showExcel.do")
	public String showExcel(@RequestParam("userId") long userId,
			@RequestParam("month") String month, HttpServletRequest request,
			HttpServletResponse response) {

		HSSFSheet sheet = null;
		StringBuilder lsb = new StringBuilder();
		try {
			HSSFWorkbook workbook = showDate(userId, month, response);
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				sheet = workbook.getSheetAt(sheetIndex);
				if (sheet != null) {
					int firstRowNum = sheet.getFirstRowNum() + 2; // 第一行
					int lastRowNum = sheet.getLastRowNum(); // 最后一行
					lsb.append("<table class='table table-striped table-bordered table-hover'>");
					for (int rowNum = firstRowNum; rowNum <= lastRowNum - 1; rowNum++) {
						if (sheet.getRow(rowNum) != null) { // 如果行不为空
							HSSFRow row = sheet.getRow(rowNum);
							int firstCellNum = row.getFirstCellNum();// 该行的第一个单元格
							int lastCellNum = row.getLastCellNum();// 该行的最后一个单元格
							int height = (int) (row.getHeight() / 15.625); // 行的高度
							lsb.append("<tr height='" + height + "'>");
							for (int cellNum = firstCellNum; cellNum <= lastCellNum-3; cellNum++) { // 循环每一个单元格
								HSSFCell cell = row.getCell(cellNum);
								if (cell != null) {
									lsb.append("<td style='text-align: center;'>" + getCellValue(cell)
											+ "</td>");
								}
							}
							for (int cellNum = lastCellNum-2; cellNum <= lastCellNum; cellNum++) { // 循环每一个单元格
								HSSFCell cell = row.getCell(cellNum);
								if (cell != null) {
									lsb.append("<td style='width:50px;text-align: center;'>" + getCellValue(cell)
											+ "</td>");
								}
							}
						}
						lsb.append("</tr>");
					}
					if (sheet.getRow(lastRowNum) != null) { // 如果行不为空
						HSSFRow row = sheet.getRow(lastRowNum);
						int firstCellNum = row.getFirstCellNum();// 该行的第一个单元格
						int lastCellNum = row.getLastCellNum();// 该行的最后一个单元格
						int height = (int) (row.getHeight() / 15.625); // 行的高度
						lsb.append("<tr height='" + height + "'>");
						//最后一行第一格
						lsb.append("<td colspan='3' style='text-align: center;'>" + getCellValue(row.getCell(firstCellNum))
								+ "</td>");
						for (int cellNum = firstCellNum+1; cellNum <= lastCellNum; cellNum++) { // 循环每一个单元格
							HSSFCell cell = row.getCell(cellNum);
							if (cell != null) {
								lsb.append("<td style='text-align: center;' >" + getCellValue(cell)
										+ "</td>");
							}
						}
					}
					lsb.append("</tr>");
					lsb.append("</table>");
					lsb.append("<br>");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lsb.toString();
	}

	/**
	 * 获取excel每列的值
	 * 
	 * @param cell
	 * @return
	 * @throws IOException
	 */
	private static Object getCellValue(HSSFCell cell) throws IOException {
		Object value = "";
		if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
			value = cell.getRichStringCellValue().toString();
		} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				value = sdf.format(date);
			} else {
				double value_temp = (double) cell.getNumericCellValue();
				BigDecimal bd = new BigDecimal(value_temp);
				BigDecimal bd1 = bd.setScale(3, BigDecimal.ROUND_HALF_UP);
				value = bd1.doubleValue();
			}
		}
		if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			value = "";
		}
		return value;
	}

	/**
	 * 根据用户、日期查询当天选项
	 * 
	 * @param userId
	 *            用户id
	 * @param date
	 *            查询日期（年月日）
	 */
	public  String queryOptionsScore(long userId, String date) {
		UserInfo ui = this.userService.selectByPrimaryKey(userId);
		long deptypeid = ui.getDeptypeid();
		long foruserid = userId;
		String begintime = date;
		String endtime = Common.getDayAfter1(date, 1);
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		listMaps = this.optionsRegistrInfoService.selecOptionsScoreByOption(
				begintime, endtime, deptypeid, foruserid);
		List<OptionsInfo> ois = new ArrayList<OptionsInfo>();
		ois = getOptionInfos(deptypeid);
		List<String> listJson=new ArrayList<String>();
		List<OptionsRegistrImgPath> listPath =this.optionsRegistrImgPathService.selectImageListByUserIdAndRealTime(foruserid, begintime);
		
		int lastDayOfMonth=TransType.checkDateLastDay(date);
		
		for (int i = 0; i < ois.size(); i++) {
			String json = "{\"id\":\"" + ois.get(i).getId() + 
					"\",\"name\":\""+ ois.get(i).getName() +
					"\",\"score\":\""+ ois.get(i).getScore() + 
					"\",\"unit\":\""+ ois.get(i).getUnit() + 
					"\",\"frequency\":\""+ ois.get(i).getFrequency() + 
					"\",\"lastDayOfMonth\":\""+ lastDayOfMonth + 
					"\",\"sumNums\":\""+ (Integer) (listMaps.get(i).get("sumNums") == null ? 0: listMaps.get(i).get("sumNums")) + 
					"\"}";
			listJson.add(json);
		}
		List<String> pathJson=new ArrayList<String>();
		for (int i = 0; i < listPath.size(); i++) {
			String json = "{\"imgid\":\"" + listPath.get(i).getId() + 
					"\",\"imgpath\":\""+ listPath.get(i).getImgpath().replace("\\", "/") +
					"\"}";
			pathJson.add(json);
		}
		
		List<List<String>> t=new ArrayList<List<String>>();
		t.add(listJson);
		t.add(pathJson);
		try {
			return TransType.objectToJson(t);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public List<OptionsInfo> getOptionInfos(long deptypeid) {
		List<OptionsInfo> ois = this.optionsInfoService
				.selectByDepTypeId(deptypeid);
		return ois;
	}

	/**
	 * 获取所有数据集合
	 * 
	 * @param begintime
	 *            开始日期
	 * @param endtime
	 *            结束日期
	 * @param foruserid
	 *            用户id
	 * @param deptypeid
	 *            部门类型id
	 * @return
	 */
	public Map<Long, Map<Object, Object>> getNum(String begintime,
			String endtime, long foruserid, long deptypeid) {

		Map<Long, Map<Object, Object>> mapNums = new HashMap<Long, Map<Object, Object>>();

		List<Long> optionIds = new ArrayList<Long>();
		List<OptionsInfo> ois = this.optionsInfoService
				.selectByDepTypeId(deptypeid);
		for (OptionsInfo oi : ois) {
			Map<Object, Object> mapNum = new HashMap<Object, Object>();
			// 数量list
			List<String> nums = new ArrayList<String>();
			optionIds.add(oi.getId());
			// 根据选项ID、起止日期、分类Id，查询对应填的数字
			// 返回map<时间，数量>的list
			List<Map<String, Object>> listMaps = this.optionsRegistrInfoService
					.selecOptionsByMonth(begintime, endtime, oi.getId(),
							foruserid);
			for (Map<String, Object> listMap : listMaps) {
				nums.add((Integer) (listMap.get("num") == null ? 0 : listMap
						.get("num")) * oi.getScore() + "");

			}
			mapNum.put("nums", nums);
			mapNum.put("OptionName", oi.getName());
			mapNum.put("ScoreUnit", oi.getScore() + oi.getUnit());
			mapNum.put("Frequency", oi.getFrequency());
			mapNums.put(oi.getId(), mapNum);
		}
		return mapNums;
	}

	/**
	 * 获取表格横向数据
	 * 
	 * @param begintime
	 *            开始日期
	 * @param endtime
	 *            结束日期
	 * @param foruserid
	 *            用户id
	 * @param deptypeid
	 *            部门类型id
	 * @return
	 */
	public List<String> getScoresRow(String begintime, String endtime,
			Long deptypeid, Long foruserid) {
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		listMaps = this.optionsRegistrInfoService.selecOptionsScoresByDay(
				begintime, endtime, deptypeid, foruserid);
		List<String> listScores = new ArrayList<String>();
		for (Map<String, Object> map : listMaps) {
			listScores.add((Integer) (map.get("sumNums") == null ? 0 : map
					.get("sumNums")) + "");
		}
		return listScores;
	}

	/**
	 * 获取表格纵向数据
	 * 
	 * @param begintime
	 *            开始日期
	 * @param endtime
	 *            结束日期
	 * @param foruserid
	 *            用户id
	 * @param deptypeid
	 *            部门类型id
	 * @return
	 */
	public List<String> getScoresCell(String begintime, String endtime,
			Long deptypeid, Long foruserid) {
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		listMaps = this.optionsRegistrInfoService.selecOptionsScoresByOption(
				begintime, endtime, deptypeid, foruserid);
		List<String> listScores = new ArrayList<String>();
		for (Map<String, Object> map : listMaps) {
			listScores.add((Integer) (map.get("sumNums") == null ? 0 : map
					.get("sumNums")) + "");
		}
		return listScores;
	}

	/**
	 * 获取合计数据
	 * 
	 * @param begintime
	 *            开始日期
	 * @param endtime
	 *            结束日期
	 * @param foruserid
	 *            用户id
	 * @param deptypeid
	 *            部门类型id
	 * @return
	 */
	public String getTotleScores(String begintime, String endtime,
			Long deptypeid, Long foruserid) {
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		listMaps = this.optionsRegistrInfoService.selecOptionsTotleScores(
				begintime, endtime, deptypeid, foruserid);
		if (null != listMaps.get(0)) {
			return (Integer) (listMaps.get(0).get("sumNums") == null ? 0
					: listMaps.get(0).get("sumNums")) + "";
		} else {
			return "0";
		}

	}

	/**
	 * 导出数据到excel
	 * 
	 * @param userId
	 *            人员ID
	 * @param month
	 *            所选月份
	 */
	public void exportDate(long userId, String month,
			HttpServletResponse response) {
		// 通过月份获取填报信息
		UserInfo ui = this.userService.selectByPrimaryKey(userId);
		String area = ui.getArea();
		String name = ui.getName();
		long deptypeid = ui.getDeptypeid();
		DepTypeInfo dti = new DepTypeInfo();
		dti = this.depTypeInfoService.selectByPrimaryKey(deptypeid);
		String depName = dti.getName();
		long foruserid = userId;
		String begintime = Common.JudgeYearMonth(month) + "-1";
		String endtime = Common.getDayAfter(
				(Common.JudgeYearMonth(month) + "-" + Common
						.JudgeDateAmount(month)), 1);

		Map<Long, Map<Object, Object>> mapNums = new HashMap<Long, Map<Object, Object>>();
		mapNums = getNum(begintime, endtime, foruserid, deptypeid);

		List<OptionsInfo> ois = new ArrayList<OptionsInfo>();
		ois = getOptionInfos(deptypeid);
		for (int i = 0; i < ois.size(); i++) {
			Map<Object, Object> mapNum = new HashMap<Object, Object>();
			mapNum = mapNums.get(ois.get(i).getId());
//			System.out.println(mapNum.get("OptionName") + "-----"
//					+ mapNum.get("ScoreUnit") + mapNum.get("nums").toString());
		}

		List<String> rowScores = getScoresRow(begintime, endtime, deptypeid,
				foruserid);
		List<String> cellScores = getScoresCell(begintime, endtime, deptypeid,
				foruserid);
		String totleScore = getTotleScores(begintime, endtime, deptypeid,
				foruserid);

//		System.out.println(cellScores.toString());
		logger.info("开始导出:姓名为："+name+" 月份为："+month+"的数据。");
		new ComplexExportExcelClient().exportExcel(depName, month, area, name,
				ois, mapNums, rowScores, cellScores, totleScore, response);
		logger.info("姓名为："+name+" 月份为："+month+"的数据导出成功"); 
	}

	/**
	 * 展示数据到页面
	 * 
	 * @param userId
	 *            人员ID
	 * @param month
	 *            所选月份
	 * @return
	 */
	public HSSFWorkbook showDate(long userId, String month,
			HttpServletResponse response) {
		// 通过月份获取填报信息
		UserInfo ui = this.userService.selectByPrimaryKey(userId);
		String area = ui.getArea();
		String name = ui.getName();
		long deptypeid = ui.getDeptypeid();
		DepTypeInfo dti = new DepTypeInfo();
		dti = this.depTypeInfoService.selectByPrimaryKey(deptypeid);
		String depName = dti.getName();
		long foruserid = userId;
		String begintime = Common.JudgeYearMonth(month) + "-1";
		String endtime = Common.getDayAfter(
				(Common.JudgeYearMonth(month) + "-" + Common
						.JudgeDateAmount(month)), 1);

		Map<Long, Map<Object, Object>> mapNums = new HashMap<Long, Map<Object, Object>>();
		mapNums = getNum(begintime, endtime, foruserid, deptypeid);

		List<OptionsInfo> ois = new ArrayList<OptionsInfo>();
		ois = getOptionInfos(deptypeid);
		for (int i = 0; i < ois.size(); i++) {
			Map<Object, Object> mapNum = new HashMap<Object, Object>();
			mapNum = mapNums.get(ois.get(i).getId());
//			System.out.println(mapNum.get("OptionName") + "-----"
//					+ mapNum.get("ScoreUnit") + mapNum.get("nums").toString());
		}

		List<String> rowScores = getScoresRow(begintime, endtime, deptypeid,
				foruserid);
		List<String> cellScores = getScoresCell(begintime, endtime, deptypeid,
				foruserid);
		String totleScore = getTotleScores(begintime, endtime, deptypeid,
				foruserid);
		logger.info("查询:姓名为："+name+" 月份为："+month+"的数据。");
		return new ComplexExportExcelClient()
				.showExcel(depName, month, area, name, ois, mapNums, rowScores,
						cellScores, totleScore, response);
	}
}
