package com.zbt.six.common;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.zbt.six.pojo.OptionsInfo;

/**
 * 报表生成类.
 * 
 * @author zhued
 * @version
 */
public class ComplexExportExcelClient {

	private   HSSFWorkbook wb = new HSSFWorkbook();

	private   HSSFSheet sheet = wb.createSheet();

	/**
	 * 
	 * @param optionNames
	 *            选项名称 创建序号列、项目列
	 * @param area
	 *            区域名称
	 * @param name
	 *            人员名称
	 * @param month
	 *            所选月份
	 */
	@SuppressWarnings("unchecked")
	public HSSFWorkbook getFixCell(String depName, String month, String area,
			String userName, List<OptionsInfo> ois,
			Map<Long, Map<Object, Object>> mapNums, List<String> rowScores,
			List<String> cellScores, String totleScore,
			HttpServletResponse response) {

		ExportExcel exportExcel = new ExportExcel(wb, sheet);

		// 创建列标头日期LIST
		int days = Common.JudgeDateAmount(month);
		List<String> fialList = new ArrayList<String>();
		for (int i = 1; i < days + 1; i++) {
			fialList.add(String.valueOf(i));
		}

		// 计算该报表的列数
		int number = fialList.size() + 5;

		// 给工作表列定义列宽(实际应用自己更改列数)
		for (int i = 0; i < number; i++) {
			sheet.setColumnWidth(i, 1000);

		}

		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 2500);
		sheet.setColumnWidth(number - 1, 2500);
		// 创建单元格样式
		HSSFCellStyle cellStyle = wb.createCellStyle();

		// 指定单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 指定当单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);

		// 设置单元格字体
		HSSFFont font = wb.createFont();
		//font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);
		String headString = "事业六部" + depName + "月度积分表（总表）";
		// 创建报表头部
		exportExcel.createNormalHead(headString, number);

		// 设置第二行
		exportExcel.createNormalTwoRow(area, userName, month);

		// 设置列头
		HSSFRow row2 = sheet.createRow(2); // 第三行插入

		HSSFCell cell0 = row2.createCell(0);
		cell0.setCellStyle(cellStyle);
		cell0.setCellValue(new HSSFRichTextString("序号"));

		HSSFCell cell1 = row2.createCell(1);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue(new HSSFRichTextString("项目\\日期"));

		HSSFCell cell2 = row2.createCell(2);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue(new HSSFRichTextString("分值"));

		for (int i = 1; i < fialList.size() + 1; i++) {
			HSSFCell cell3 = row2.createCell(2 + i);
			cell3.setCellStyle(cellStyle);
			cell3.setCellValue(fialList.get(i - 1));
		}

		HSSFCell cell4 = row2.createCell(number - 2);
		cell4.setCellStyle(cellStyle);
		cell4.setCellValue(new HSSFRichTextString("各项汇总"));

		HSSFCell cell5 = row2.createCell(number - 1);
		cell5.setCellStyle(cellStyle);
		cell5.setCellValue(new HSSFRichTextString("填报规则"));

		for (int i = 0; i < ois.size(); i++) {
			Map<Object, Object> mapNum = new HashMap<Object, Object>();
			mapNum = mapNums.get(ois.get(i).getId());
			HSSFRow row3 = sheet.createRow(i + 3); // 第4行插入
			// 插入序号
			HSSFCell cell6 = row3.createCell(0);
			cell6.setCellStyle(cellStyle);
			cell6.setCellValue(new HSSFRichTextString(String.valueOf(i + 1)));
			// 插入选项
			HSSFCell cell7 = row3.createCell(1);
			cell7.setCellStyle(cellStyle);
			cell7.setCellValue(new HSSFRichTextString((String) mapNum
					.get("OptionName")));
			// 插入分值
			HSSFCell cell8 = row3.createCell(2);
			cell8.setCellStyle(cellStyle);
			cell8.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell8.setCellValue(new HSSFRichTextString((String) mapNum
					.get("ScoreUnit")));

			HSSFCell cell9 = row3.createCell(number - 1);
			cell9.setCellStyle(cellStyle);
			cell9.setCellValue(new HSSFRichTextString((Integer) mapNum
					.get("Frequency") == 1 ? "每天一次" : "每月一次"));

			List<String> listNums = new ArrayList<String>();
			listNums = (List<String>) mapNum.get("nums");
			// 循环创建中间的单元格的各项的值
			for (int j = 0; j < listNums.size(); j++) {
				exportExcel.cteateContentCell(wb, row3, (short) j + 3,
						cellStyle,  listNums.get(j));
			}
			// 创建倒数第二列的合计列
			HSSFCell cell10 = row3.createCell(number - 2);
			cell10.setCellStyle(cellStyle);
			cell10.setCellValue(new HSSFRichTextString(cellScores.get(i)));
		}

		// 创建最后一行的合计行
		exportExcel.createLastSumRow(2, rowScores, totleScore,ois.size(),cellStyle);

		return exportExcel.getWb();
	}
	
	public void  exportExcel(String depName, String month, String area,
			String userName, List<OptionsInfo> ois,
			Map<Long, Map<Object, Object>> mapNums, List<String> rowScores,
			List<String> cellScores, String totleScore,
			HttpServletResponse response){
		wb=getFixCell(depName, month, area, userName, ois, mapNums, rowScores, cellScores, totleScore, response);
		ExportExcel exportExcel = new ExportExcel(wb, sheet);
		
		String fileNameString = "事业六部" + depName + "月度积分表（总表）_" + month + "_"
				+ userName;
		exportExcel.outputExcel(fileNameString, response);
	}
	public HSSFWorkbook  showExcel(String depName, String month, String area,
			String userName, List<OptionsInfo> ois,
			Map<Long, Map<Object, Object>> mapNums, List<String> rowScores,
			List<String> cellScores, String totleScore,
			HttpServletResponse response){
		
		wb=getFixCell(depName, month, area, userName, ois, mapNums, rowScores, cellScores, totleScore, response);
		 return wb;
	}
}