package cn.qhjys.mall.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;

@Service
public class ExportExcelExtUtil<T> {

	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	SimpleDateFormat formaty = new SimpleDateFormat("yyyy年MM月dd日");

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出
	 * 
	 * @param title
	 *            表格标题名
	 * @param headersName
	 *            表格属性列名数组
	 * @param headersId
	 *            表格属性列名对应的字段
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headersName, String[] headersId, List dtoList,
			HttpServletResponse response) {
		// 表头
		Map map = new HashMap();
		int key = 0;
		for (int i = 0; i < headersName.length; i++) {
			if (!headersName[i].equals(null)) {
				map.put(key, headersName[i]);
				key++;
			}
		}
		// 字段
		Map zdMap = new HashMap();
		int value = 0;
		for (int i = 0; i < headersId.length; i++) {
			if (!headersId[i].equals(null)) {
				zdMap.put(value, headersId[i]);
				value++;
			}
		}
		// 声明一个工作薄
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(title);
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		HSSFRow row = sheet.createRow(0);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		HSSFCell cell;
		Collection c = map.values();
		Iterator it = c.iterator();
		// 根据选择的字段生成表头
		short size = 0;
		while (it.hasNext()) {
			cell = row.createCell(size);
			cell.setCellValue(it.next().toString());
			cell.setCellStyle(style);
			size++;
		}
		// 字段
		Collection zdC = zdMap.values();
		Iterator labIt = dtoList.iterator();
		int zdRow = 0;
		while (labIt.hasNext()) {
			int zdCell = 0;
			zdRow++;
			row = sheet.createRow(zdRow);
			T l = (T) labIt.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = l.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				// System.out.println(fieldName);
				Iterator zdIt = zdC.iterator();
				while (zdIt.hasNext()) {
					if (zdIt.next().equals(fieldName)) {
						String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
						Class tCls = l.getClass();
						try {
							Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
							Object val = getMethod.invoke(l, new Object[] {});
							/*
							 * System.out.println("字段名 :"+fields[i].getName());
							 * System.out.println("字段值"+val);
							 * System.out.println(tCls.getSimpleName());
							 */
							String textVal = null;
							if (val != null) {
								if("FqSellerStatement".equals(tCls.getSimpleName())){
									if (fields[i].getName().toString().equals("state")) {
										// 0未结算，1已结算
										if ("0".equals(val.toString())) {
											textVal = "未结算";
										}else if ("1".equals(val.toString())) {
											textVal = "已结算";
										}
									}else if (fields[i].getName().toString().equals("statementDate")) {
										textVal = formaty.format((Date) val);
									}else {
										textVal = val.toString();
									}
								} else if (val instanceof Date) {
									textVal = format.format((Date) val);
								}else {
									textVal = val.toString();
								}
							}else {
								textVal = null;
							}
							row.createCell((short) zdCell).setCellValue(textVal);
							zdCell++;
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}

			}
		}

		try {

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			// 将工作簿内容写入响应输出流
			wb.write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String((title + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
				if (os != null)
					// 将输出流对象关闭
					os.close();
			}

			/* JOptionPane.showMessageDialog(null, "导出成功!"); */
		} catch (FileNotFoundException e) {
			// JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		} catch (IOException e) {
			// JOptionPane.showMessageDialog(null, "导出失败!");
			e.printStackTrace();
		}
	}
}