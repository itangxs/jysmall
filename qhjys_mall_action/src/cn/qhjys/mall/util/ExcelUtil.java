package cn.qhjys.mall.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ExcelUtil {
	private static final Log logger = LogFactory.getLog(ExcelUtil.class);

	/**
	 * 导出Excel对象集合
	 * 
	 * @param title
	 *            工作簿名称
	 * @param headers
	 *            工作簿标题
	 * @param dataset
	 *            原始数据
	 * @param out
	 *            OutputStream
	 * @throws Exception
	 */
	public static final void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out)
			throws Exception {
		logger.debug("开始生成并导出EXCEL......");
		if (headers == null || dataset == null)
			return;
		HSSFWorkbook wb = new HSSFWorkbook(); // 创建新的Excel工作簿
		HSSFSheet sheet;
		if (StringUtils.isEmpty(title)) // 在Excel工作簿中建一工作表
			sheet = wb.createSheet();
		else
			sheet = wb.createSheet(title);
		sheet.setDefaultColumnWidth(20); // 设置表格默认列宽度
		HSSFRow row = sheet.createRow(0); // 创建表格标题行
		HSSFFont font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFont(font);
		for (int i = 0; i < headers.length; i++) { // 创建标题行
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(headers[i]);
		}
		font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		style.setFont(font);
		int index = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Iterator<T> it = dataset.iterator(); it.hasNext();) { // 遍历集合数据，产生数据行
				T t = it.next();
				Class<? extends T> tCls = t.getClass();
				index++;
				row = sheet.createRow(index); // 创建行
				Field[] fields = t.getClass().getDeclaredFields(); // 利用JAVA反射，得到对象属性值
				for (int i = 0; i < fields.length; i++) {
					HSSFCell cell = row.createCell(i); // 创建单元格
					Field field = fields[i];
					String fieldName = field.getName(); // 获取属性名
					String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Method getMethod = tCls.getMethod(methodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = null;
					if (value instanceof Date) // 判断值的类型
						textValue = sdf.format((Date) value);
					else
						textValue = value.toString();
					Pattern p = Pattern.compile("^//d+(//.//d+)?$");
					Matcher matcher = p.matcher(textValue);
					cell.setCellStyle(style);
					if (matcher.matches()) // 是数字当作double处理
						cell.setCellValue(Double.parseDouble(textValue));
					else
						cell.setCellValue(textValue);
				}
			}
			wb.write(out);
		} catch (InvocationTargetException e) {
			logger.error("通过反射获取对象异常!", e);
			throw new Exception("通过反射获取对象异常!", e);
		} catch (IOException e) {
			logger.error("Excel写到输出流中出现异常!", e);
			throw new Exception("Excel写到输出流中出现异常!", e);
		} finally {
			out.flush();
			out.close();
			wb.close();
		}
	}

	/**
	 * 解析EXCEL 2003
	 * 
	 * @param input
	 *            输入流
	 * @param clas
	 *            实体类
	 * @param dataFormat
	 *            时间格式
	 * @param skipHeader
	 *            跳过表头
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public static final <T> List<T> importExcel03(InputStream input, Class<T> clas, String dataFormat,
			boolean skipHeader) throws Exception {
		logger.debug("开始解析EXCEL 2003......");
		List<T> list = new ArrayList<T>();
		JSONObject json = new JSONObject();
		HSSFWorkbook wb = new HSSFWorkbook(input);
		int sheetNum = wb.getNumberOfSheets(); // 获取工作簿的数量
		try {
			if (sheetNum == 0) {
				logger.warn("Excel 2003中不存在可解析的内容");
				throw new Exception("Excel文件中没有可用的工作簿!");
			}
			HSSFSheet sheet = wb.getSheetAt(0); // 获取当前工作簿(只读取第一页)
			int rowNum = sheet.getLastRowNum() + 1; // 获取所有的行
			if (rowNum <= (skipHeader ? 1 : 0)) // 判断是否空数据
				return list;
			Field[] fields = clas.getDeclaredFields(); // 得到对象属性值
			SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
			for (int i = (skipHeader ? 1 : 0); i < rowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				if (i == (skipHeader ? 1 : 0)) { // 判断列的长度是否与模板一致
					int cellNum = row.getLastCellNum();
					if (cellNum > fields.length)
						throw new Exception("Excel列格式不对，请参考导入模板");
				}
				for (int j = 0; j < fields.length; j++) { // 遍历每行的单元格
					Field field = fields[j]; // 获取属性
					HSSFCell cell = row.getCell(j); // 获取当前单元格
					Object value = null;
					if (cell != null) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) // 日期格式，自定义格式输出
								if (cell.getDateCellValue() != null)
									value = sdf.format(cell.getDateCellValue());
								else
									value = "";
							else if ("Generl".equals(cell.getCellStyle().getDataFormatString()))
								value = new DecimalFormat("0.00").format(cell.getNumericCellValue());
							else
								value = new DecimalFormat("0").format(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							value = String.valueOf(cell.getBooleanCellValue());
							break;
						default:
							value = "";
							break;
						}
					}
					json.put(field.getName(), value);
				}
				list.add(JSON.parseObject(json.toJSONString(), clas));
			}
		} catch (IllegalArgumentException e) {
			logger.error("填充实体参数异常：", e);
			throw new Exception("填充实体参数错误", e);
		} catch (ReflectiveOperationException e) {
			logger.error("生成实体对象异常：", e);
			throw new Exception("生成实体对象错误", e);
		} catch (Exception e) {
			logger.error("解析EXCEL 2003异常：", e);
			throw new Exception("解析EXCEL 2003错误", e);
		} finally {
			input.close();
			wb.close();
		}
		return list;
	}

	/**
	 * 解析EXCEL 2007
	 * 
	 * @param input
	 *            输入流
	 * @param clas
	 *            实体类
	 * @param dataFormat
	 *            时间格式
	 * @param skipHeader
	 *            跳过表头
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public static final <T> List<T> importExcel07(InputStream input, Class<T> clas, String dataFormat,
			boolean skipHeader) throws Exception {
		logger.debug("开始解析Excel 2007......");
		List<T> list = new ArrayList<T>();
		JSONObject json = new JSONObject();
		XSSFWorkbook xwb = new XSSFWorkbook(input);
		int sheetNum = xwb.getNumberOfSheets(); // 获取工作簿的数量
		try {
			if (sheetNum == 0) {
				logger.warn("Excel文件中不存在可解析的内容!");
				throw new Exception("Excel文件中不存在可解析的内容!");
			}
			XSSFSheet sheet = xwb.getSheetAt(0); // 获取当前工作簿(只读取第一页)
			int rowNum = sheet.getLastRowNum() + 1; // 获取所有的行
			if (rowNum <= (skipHeader ? 1 : 0)) // 判断是否空数据
				return list;
			Field[] fields = clas.getDeclaredFields(); // 得到对象属性值
			SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
			for (int i = (skipHeader ? 1 : 0); i < rowNum; i++) {
				XSSFRow row = sheet.getRow(i);
				if (i == (skipHeader ? 1 : 0)) { // 判断列的长度是否与模板一致
					int cellNum = row.getLastCellNum();
					if (cellNum > fields.length)
						throw new Exception("Excel列格式不对，请参考导入模板");
				}
				for (int j = 0; j < fields.length; j++) { // 遍历每行的单元格
					Field field = fields[j]; // 获取属性
					XSSFCell cell = row.getCell(j); // 获取当前单元格
					Object value = null;
					if (cell != null) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) // 日期格式，自定义格式输出
								if (cell.getDateCellValue() != null)
									value = sdf.format(cell.getDateCellValue());
								else
									value = "";
							else if ("Generl".equals(cell.getCellStyle().getDataFormatString()))
								value = new DecimalFormat("0.00").format(cell.getNumericCellValue());
							else
								value = new DecimalFormat("0").format(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							value = String.valueOf(cell.getBooleanCellValue());
							break;
						default:
							value = "";
							break;
						}
					}
					json.put(field.getName(), value);
				}
				list.add(JSON.parseObject(json.toJSONString(), clas));
			}
		} catch (IllegalArgumentException e) {
			logger.error("填充实体参数异常：", e);
			throw new Exception("填充实体参数错误", e);
		} catch (ReflectiveOperationException e) {
			logger.error("生成实体对象异常：", e);
			throw new Exception("生成实体对象错误", e);
		} catch (Exception e) {
			logger.error("解析EXCEL 2003异常：", e);
			throw new Exception("解析EXCEL 2003错误", e);
		} finally {
			input.close();
			xwb.close();
		}
		return list;
	}
}