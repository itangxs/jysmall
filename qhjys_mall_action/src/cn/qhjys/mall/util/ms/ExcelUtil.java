package cn.qhjys.mall.util.ms;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.model.io.HWPFOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-8
 * Time: 9:30:51
 * To change this template use File | Settings | File Templates.
 */
public class ExcelUtil{
    private static String NUMBER_FORMAT = "#,##0.00";

    /**
     * 创建一个空的excel文件
     *
     * @return
     */
    public HSSFWorkbook createWorkbook() {
        return new HSSFWorkbook();
    }

    /**
     * 根据不同的数据类型,设置单元格数据
     *
     * @param row
     * @param index
     * @param type
     * @param value
     * @param dateStyle
     * @param wb
     */
    public void setRowCellValue(HSSFRow row, Short index, Class<?> type,
                                Object value, HSSFCellStyle dateStyle, HSSFWorkbook wb) {
        short i = index.shortValue();

        if (Date.class.isAssignableFrom(type)) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue((Date) value);
            cell.setCellStyle(dateStyle);
        } else if (String.class.isAssignableFrom(type)) {
            row.createCell(i).setCellValue(new HSSFRichTextString((String) value));
        } else if (BigDecimal.class.isAssignableFrom(type)) {
            BigDecimal bigDecimalValue = (BigDecimal) value;
            if (bigDecimalValue.abs().doubleValue() > BigDecimal.ZERO.doubleValue()) {
                HSSFCellStyle cellStyle = wb.createCellStyle();
                cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(NUMBER_FORMAT));
                row.createCell(i).setCellStyle(cellStyle);
                row.createCell(i).setCellValue(bigDecimalValue.doubleValue());
            }
        } else if (Long.class.isAssignableFrom(type)) {
            Long longValue = (Long) value;
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(longValue.longValue());
        } else if (Double.class.isAssignableFrom(type)) {
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(NUMBER_FORMAT));
            Double doubleValue = (Double) value;
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(doubleValue.doubleValue());
        } else if (Float.class.isAssignableFrom(type)) {
            HSSFCellStyle cellStyle = wb.createCellStyle();
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(NUMBER_FORMAT));
            Float floatValue = (Float) value;
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(floatValue.floatValue());
        }
    }

    /**
     * 往excel工作簿中写入内容
     *
     * @param wb
     * @param list
     * @param modelNames
     * @param beanNames
     * @param <T>
     * @throws Exception
     */
    public <T> void addWorksheet(HSSFWorkbook wb, List<T> list, List<String> modelNames, List<String> beanNames, HashMap con) throws Exception {
        assert list != null;

        String sheetName = "New Sheet";
        if(StringUtils.isNotBlank(String.valueOf(con.get("sheetName")))) {
            sheetName = String.valueOf(con.get("sheetName"));
        }

        //创建一个工作簿
        HSSFSheet sheet = wb.createSheet(sheetName);


        // 设置xls头行样式 粗体
        HSSFCellStyle headerStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(font);

        //创建一个日期样式
        HSSFCellStyle dateStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        dateStyle.setDataFormat(format.getFormat("yyyy-mm-dd HH:mm:ss"));

        //创建xls头行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < modelNames.size(); i++) {
            sheet.setColumnWidth(i, (short) 5200);
            String header = modelNames.get(i);
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(new HSSFRichTextString(header));
            cell.setCellStyle(headerStyle);
        }

        //创建excel的每一行
        List<String> columns = beanNames;
        short counter = 1;
        for (Object item : list) {
            row = sheet.createRow(counter++);
            for (short i = 0; i < columns.size(); i++) {
                String header = columns.get(i);
                Object value = null;
                String[] jParms = null;
                if (header.indexOf("#") != -1) {
                   jParms = header.split("#");
                   String target_field = jParms[0];
                   String logic_rule = jParms[1];
                   value = this.propAccess(item, target_field);
                   if(value!=null&&String.valueOf(value).indexOf(logic_rule)==-1) {
                       //目标字段不含规定的预留字符
                       continue;
                   }
                   String orig_formula = jParms[2];
                   header = orig_formula;
                }
                if(header.indexOf("@")!=-1) {
                    header = header.replace("@", "");
                    String f1 = header.substring(0, header.indexOf("*"));
                    String f2 = header.substring(header.indexOf("*") + 1);
                    value = this.propAccess(item, f1);
                    if(value==null){
                        value = Long.valueOf(0);
                    }
                    Double num;
                    java.text.DecimalFormat myformat = new java.text.DecimalFormat("#0.0");
                    num = Double.parseDouble(f2);//装换为double类型
                    num = Double.parseDouble(myformat.format(num));//
                    try{
                        value = Long.valueOf(String.valueOf(value)) * num;
                    } catch(Exception e) {
                    }
                } else {
                    value = this.propAccess(item, header);
                }
                if (value == null) {
                    row.createCell(i);
                    continue;
                }

                Class<?> type = value.getClass();
                Short index = Short.valueOf(i);
                this.setRowCellValue(row, index, type, value, dateStyle, wb);
            }
        }
    }


    /**
     * 往excel工作簿中写入内容
     *
     * @param wb
     * @param list
     * @param modelNames
     * @param beanNames
     * @param <T>
     * @throws Exception
     */
    public <T> void addWorksheet(String sheetName,HSSFWorkbook wb, List<T> list, List<List> modelNames1, List<String> beanNames) throws Exception {
        assert list != null;

        //创建一个工作簿
        HSSFSheet sheet = wb.createSheet(sheetName);


        // 设置xls头行样式 粗体
        HSSFCellStyle headerStyle = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(font);

        //创建一个日期样式
        HSSFCellStyle dateStyle = wb.createCellStyle();
        HSSFDataFormat format = wb.createDataFormat();
        dateStyle.setDataFormat(format.getFormat("yyyy-mm-dd HH:mm:ss"));

        //创建xls头行
        HSSFRow row = null;
        short i1 =0;
        for (i1 = 0; i1 < modelNames1.size(); i1++){
        	row = sheet.createRow(i1);
        	List<String>modelNames = modelNames1.get(i1);
        	for (short i = 0; i < modelNames.size(); i++) {
                sheet.setColumnWidth(i, (short) 5200);
                String header = modelNames.get(i);
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(header);
                cell.setCellStyle(headerStyle);
            }
        }
        

        //创建excel的每一行
        List<String> columns = beanNames;
        short counter = i1;
        for (Object item : list) {
            row = sheet.createRow(counter++);
            for (short i = 0; i < columns.size(); i++) {
                String header = columns.get(i);
                Object value = this.propAccess(item, header);
                if (value == null) {
                    row.createCell(i);
                    continue;
                }

                Class<?> type = value.getClass();
                Short index = Short.valueOf(i);
                this.setRowCellValue(row, index, type, value, dateStyle, wb);
            }
        }
    }

    /**
     * 根据实例和列名获得属性值
     *
     * @param instance
     * @param fieldName
     * @return
     * @throws Exception
     */
    public static Object propAccess(Object instance, String fieldName) throws Exception {
        if(instance ==null) {
            return null;
        } else {
            //System.out.println(1);
        }
        Class clazz = instance.getClass();
        if (fieldName.contains(".")) {
            Field field = clazz.getDeclaredField(fieldName.split("\\.")[0]);
            field.setAccessible(true);
            return propAccess(field.get(instance), fieldName.split("\\.")[1]);
        } else {
            Field field = clazz.getDeclaredField(fieldName);
            // 参数值为true，禁用访问控制检查
            field.setAccessible(true);
            return field.get(instance);
        }
    }

    public InputStream export(HSSFWorkbook wb, String fileName) throws Exception {
        HWPFOutputStream out = new HWPFOutputStream();
        wb.write(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return in;
    }

    public static void main(String[] aa) throws Exception {
        List virgins = new ArrayList();
        News news = null;
        for (int i = 0; i < 10; i++) {
            news = new News();
            news.setId(new Long(i));
            news.setTitle("标题" + i);
            news.setKeyword("关键字" + i);
            news.setCount(1000000000000000000L);
            news.setPublishDate(new Date());
            virgins.add(news);
        }

        List modelNames = new ArrayList();
        modelNames.add("id");
        modelNames.add("标题");
        modelNames.add("关键字");
        modelNames.add("点击数");
        modelNames.add("发布日期");

        List beanNames = new ArrayList();
        beanNames.add("id");
        beanNames.add("title");
        beanNames.add("keyword");
        beanNames.add("count");
        beanNames.add("publishDate");

        ExcelUtil excelUtil = new ExcelUtil();
        HSSFWorkbook hssfWorkbook = excelUtil.createWorkbook();
        excelUtil.addWorksheet(hssfWorkbook, virgins, modelNames, beanNames,new HashMap());

        HWPFOutputStream out = new HWPFOutputStream();
        hssfWorkbook.write(out);
        //ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        OutputStream os = new FileOutputStream(new File("d:/myxls.xls"));
        os.write(out.toByteArray());
        os.flush();

        out.close();
        os.close();

    }

}
class News{
	private Long id;
	private String title;
	private String keyword;
	private Long count;
	private Date publishDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
}