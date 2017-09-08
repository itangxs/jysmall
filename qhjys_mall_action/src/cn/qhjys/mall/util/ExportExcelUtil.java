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
import org.springframework.util.StringUtils;

@Service
public class ExportExcelUtil<T> {

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
								if (fields[i].getName().toString().equals("tradingDate")) {
									textVal = format.format(val);
								} else if (fields[i].getName().toString().equals("date")
										&& "ShopSalesVo".equals(tCls.getSimpleName())) {
									textVal = formaty.format(val);
								} else if (fields[i].getName().toString().equals("createDate")
										&& "CashLogVo".equals(tCls.getSimpleName())) {
									textVal = format.format(val);
								} else if (fields[i].getName().toString().equals("payType")
										&& "CashLogVo".equals(tCls.getSimpleName())) {
									// 1会员充值、2会员提现、3卖家充值、4卖家提现、5购物、6退款
									if ("1".equals(val.toString())) {
										textVal = "会员充值";
									}
									if ("2".equals(val.toString())) {
										textVal = "会员提现";
									}
									if ("3".equals(val.toString())) {
										textVal = "卖家充值";
									}
									if ("4".equals(val.toString())) {
										textVal = "卖家提现";
									}
									if ("5".equals(val.toString())) {
										textVal = "购物";
									}
									if ("6".equals(val.toString())) {
										textVal = "退款";
									}
								}
								else if ("WithdrawsVo".equals(tCls.getSimpleName())) {
									if (fields[i].getName().toString().equals("status")) {
										// 0不成功，1成功,2未处理
										if ("1".equals(val.toString())) {
											textVal = "已审核";
										}else if ("2".equals(val.toString())) {
											textVal = "未审核";
										}else if ("0".equals(val.toString())) {
											textVal = "审核不通过";
										}else if ("3".equals(val.toString())) {
											textVal = "已出账";
										}
									}else if (fields[i].getName().toString().equals("createDate")) {
										textVal = format.format(val);
									}else {
										textVal = val.toString();
									}
								} else if(fields[i].getName().toString().equals("createTime")
										&& "RebateOrderVo".equals(tCls.getSimpleName())){
									textVal = format.format(val);
								} else if(fields[i].getName().toString().equals("createTime")
										&& "StoreCountVo".equals(tCls.getSimpleName())){
									textVal = formaty.format(val);
								} else if(fields[i].getName().toString().equals("payTime")
										&& "RebateOrderVo".equals(tCls.getSimpleName())){
									textVal = format.format(val);
								} else if(fields[i].getName().toString().equals("createDate")
										&& "WithdrawsVo".equals(tCls.getSimpleName())){
									textVal = formaty.format(val);
								}else if(fields[i].getName().toString().equals("createTime")
										&& "RebateOrder".equals(tCls.getSimpleName())){
									textVal = formaty.format(val);
								}else if(fields[i].getName().toString().equals("bankType")
										&& "RebateOrderVo".equals(tCls.getSimpleName())){
									if ("1".equals(val.toString())) {
										textVal = "零钱";
									}else if ("2".equals(val.toString())) {
										textVal = "信用卡";
									}else if ("3".equals(val.toString())) {
										textVal = "储蓄卡";
									}
								}else if(fields[i].getName().toString().equals("orderRate")
										&& "RebateOrderVo".equals(tCls.getSimpleName())){
									
										textVal = val+"%";
								}else if(fields[i].getName().toString().equals("type")
										&& "RebateOrderVo".equals(tCls.getSimpleName())){
									if ("1".equals(val.toString())) {
										textVal = "微信";
									}else if ("2".equals(val.toString())) {
										textVal = "支付宝";
									}else if ("3".equals(val.toString())) {
										textVal = "微信";
									}else if ("5".equals(val.toString())) {
										textVal = "微信";
									}else if ("6".equals(val.toString())) {
										textVal = "支付宝";
									}else if ("7".equals(val.toString())) {
										textVal = "QQ";
									}else if ("4".equals(val.toString())) {
										textVal = "POS";
									}
								}else if(fields[i].getName().toString().equals("couponId")){
									if ("0".equals(val.toString())) {
										textVal = "无";
									}
								}else if(fields[i].getName().toString().equals("isCash")){
									if ("0".equals(val.toString())) {
										textVal = "否";
									}else if ("1".equals(val.toString())) {
										textVal = "是";
									}
								}else if(fields[i].getName().toString().equals("isEffective")){
									if ("0".equals(val.toString())) {
										textVal = "否";
									}else if ("1".equals(val.toString())) {
										textVal = "是";
									}
								}else if(fields[i].getName().toString().equals("effectiveDate")){
									if (!StringUtils.isEmpty(val.toString())) {
										textVal = formaty.format(val);
									}
								}else if(fields[i].getName().toString().equals("countDate")){
										textVal = formaty.format(val);
								}else if(fields[i].getName().toString().equals("status")&& "RebateOrder".equals(tCls.getSimpleName())){
									if ("2".equals(val.toString())) {
										textVal = "销售入账";
									}else{
										textVal = "";
									}
								} else if (fields[i].getName().toString().equals("createDate")
										&& "MsWithdrawExportVo".equals(tCls.getSimpleName())) {
									textVal = format.format(val);
								} else if(fields[i].getName().toString().equals("state")&& "MsWithdrawExportVo".equals(tCls.getSimpleName())) {
									if ("0".equals(val.toString())) {
										textVal = "未审核";
									} else if ("1".equals(val.toString())) {
										textVal = "已审核";
									} else if ("2".equals(val.toString())) {
										textVal = "提现中";
									} else if ("3".equals(val.toString())) {
										textVal = "提现失败";
									} else if ("4".equals(val.toString())) {
										textVal = "提现成功";
									} 
								} else if (fields[i].getName().toString().equals("createDate")
										&& "MsWithdraw".equals(tCls.getSimpleName())) {
									textVal = format.format(val);
								} else if(fields[i].getName().toString().equals("state")&& "MsWithdraw".equals(tCls.getSimpleName())) {
									if ("0".equals(val.toString())) {
										textVal = "未审核";
									} else if ("1".equals(val.toString())) {
										textVal = "已审核";
									} else if ("2".equals(val.toString())) {
										textVal = "出账中";
									} else if ("3".equals(val.toString())) {
										textVal = "出账失败";
									} else if ("4".equals(val.toString())) {
										textVal = "出账成功";
									} 
								} else if(fields[i].getName().toString().equals("openOrder")&& "StorExporteVo".equals(tCls.getSimpleName())) {
									if ("0".equals(val.toString())) {
										textVal = "不开通";
									} else if ("1".equals(val.toString())) {
										textVal = "开通";
									}
								} else if(fields[i].getName().toString().equals("openCashier")&& "StorExporteVo".equals(tCls.getSimpleName())) {
									if ("0".equals(val.toString())) {
										textVal = "关闭";
									} else if ("1".equals(val.toString())) {
										textVal = "开启";
									}
								} else if ((fields[i].getName().toString().equals("wxAuthState") || fields[i].getName().toString().equals("zfbAuthState"))
										&& "StorExporteVo".equals(tCls.getSimpleName())) {
									if ("0".equals(val.toString())) {
										textVal = "未进件";
									} else if ("1".equals(val.toString())) {
										textVal = "进件中";
									} else if ("2".equals(val.toString())) {
										textVal = "进件成功";
									} else if ("3".equals(val.toString())) {
										textVal = "进件失败";
									}
								} else {
									textVal = val.toString();
								}
							}
							else {
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