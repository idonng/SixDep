package com.zbt.six.common;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

@SuppressWarnings("deprecation")
public class ExportExcel {

	private HSSFWorkbook wb = null;

	private HSSFSheet sheet = null;

	/**
	 * @param wb
	 * @param sheet
	 */
	public ExportExcel(HSSFWorkbook wb, HSSFSheet sheet) {
		super();
		this.wb = wb;
		this.sheet = sheet;
	}

	/**
	 * @return the sheet
	 */
	public HSSFSheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet
	 *            the sheet to set
	 */
	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}
 
	/**
	 * @return the wb
	 */
	public HSSFWorkbook getWb() {
		return wb;
	}

	/**
	 * @param wb
	 *            the wb to set
	 */
	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	/**
	 * 创建通用EXCEL头部
	 * 
	 * @param headString
	 *            头部显示的字符
	 * @param colSum
	 *            该报表的总列数
	 */
	public void createNormalHead(String headString, int colSum) {

		HSSFRow row = sheet.createRow(0);

		// 设置第一行
		HSSFCell cell = row.createCell(0);
		row.setHeight((short) 400);

		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(new HSSFRichTextString(headString));

		// 指定合并区域
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));

		HSSFCellStyle cellStyle = wb.createCellStyle();

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行

		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 300);
		cellStyle.setFont(font);

		cell.setCellStyle(cellStyle);
	}

	 
	/**
	 * 创建通用报表第二行
	 * @param Area 区域
	 * @param name 姓名
	 * @param date 日期
	 */
	public void createNormalTwoRow(String Area, String name,String date) {
		

		HSSFCellStyle cellStyle = wb.createCellStyle();

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行

		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		cellStyle.setFont(font);

		HSSFRow row1 = sheet.createRow(1);

		// 设置第一行
		HSSFCell cell1 = row1.createCell(0);
		row1.setHeight((short) 300);

		// 定义单元格为字符串类型
		cell1.setCellType(HSSFCell.ENCODING_UTF_16);
		cell1.setCellValue(new HSSFRichTextString("大区：" ));
		cell1.setCellStyle(cellStyle);
		HSSFCell cell2 = row1.createCell(2);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue(new HSSFRichTextString( Area ));
		HSSFCell cell3 = row1.createCell(5);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue(new HSSFRichTextString("姓名："));
		HSSFCell cell4 = row1.createCell(7);
		cell4.setCellStyle(cellStyle);
		cell4.setCellValue(new HSSFRichTextString( name));
		HSSFCell cell5 = row1.createCell(20);
		cell5.setCellStyle(cellStyle);
		cell5.setCellValue(new HSSFRichTextString(date));
		// 指定合并区域
		sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) 1));
		sheet.addMergedRegion(new Region(1, (short) 5, 1, (short) 6));
		sheet.addMergedRegion(new Region(1, (short) 7, 1, (short) 8));
		sheet.addMergedRegion(new Region(1, (short) 20, 1, (short) 21));

	}

	/**
	 * 设置报表标题
	 * 
	 * @param columHeader
	 *            标题字符串数组
	 */
	public void createColumHeader(String[] columHeader) {

		// 设置列头
		HSSFRow row2 = sheet.createRow(2);

		// 指定行高
		row2.setHeight((short) 300);

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行

		// 单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 250);
		cellStyle.setFont(font);

		// 设置单元格背景色
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFCell cell3 = null;

		for (int i = 0; i < columHeader.length; i++) {
			cell3 = row2.createCell(i);
			cell3.setCellType(HSSFCell.ENCODING_UTF_16);
			cell3.setCellStyle(cellStyle);
			cell3.setCellValue(new HSSFRichTextString(columHeader[i]));
		}

	}

	/**
	 * 创建内容单元格
	 * 
	 * @param wb
	 *            HSSFWorkbook
	 * @param row
	 *            HSSFRow
	 * @param col
	 *            short型的列索引
	 * @param align
	 *            对齐方式
	 * @param val
	 *            列值
	 */
	public void cteateContentCell(HSSFWorkbook wb,HSSFRow row,  int col, HSSFCellStyle cellstyle,
			 String  num ) {
			HSSFCell cell = row.createCell(col);
			cell.setCellType(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(new HSSFRichTextString(num));
			cell.setCellStyle(cellstyle);
	}
	/**
	 * 创建合计行
	 * 
	 * @param colSum
	 *            需要合并到的列索引
	 * @param cellValue
	 */
	public void createLastSumRow(int colSum, List<String> rowScores,String totleScore,int rowNum,HSSFCellStyle cellStyle) {

		// 单元格字体
//		HSSFFont font = wb.createFont();
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		font.setFontName("宋体");
//		font.setFontHeight((short) 250);
//		cellStyle.setFont(font);

		HSSFRow lastRow = sheet.createRow((short) (rowNum + 3));
		HSSFCell sumCell = lastRow.createCell(0);

		sumCell.setCellValue(new HSSFRichTextString("每日汇总"));
		sumCell.setCellStyle(cellStyle);
		sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0,
				sheet.getLastRowNum(), (short) colSum));// 指定合并区域

		for (int i = 0; i < rowScores.size() ; i++) {
			sumCell = lastRow.createCell(i+3);
			sumCell.setCellStyle(cellStyle);
			sumCell.setCellValue(new HSSFRichTextString(rowScores.get(i)));
		}
		sumCell = lastRow.createCell(rowScores.size()+3);
		sumCell.setCellStyle(cellStyle);
		sumCell.setCellValue(new HSSFRichTextString(totleScore));
	}

	/**
	 * 输入EXCEL文件
	 * 
	 * @param fileName
	 *            文件名
	 */
	public void outputExcel(String fileName,HttpServletResponse response) {
		OutputStream fos = null;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		try {
			response.setHeader(
					"content-disposition",
					"attachment; filename="+URLEncoder.encode(fileName, "UTF-8")+".xls");
			try {
				fos =  response.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			wb.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}