package cn.qhjys.mall.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.service.CategoryService;

@Controller
@RequestMapping("/readExcle")
public class ReadExcleController {
	
	@Autowired
	private CategoryService categoryService;
	
	public Map<String, Long>  map = new HashMap<String, Long>();
   
	@RequestMapping("/changeStoreExcel")
	@ResponseBody
	public String changeStoreExcel() throws Exception{
		List<CategoryInfo> list  = categoryService.queryAll();
		for (int i = 0; i < list.size(); i++) {
			CategoryInfo category = list.get(i);
			map.put(category.getName(), category.getId());
		}
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("f://bank.txt"), true)));
		InputStream is = new FileInputStream(new File("f://bankinfo.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum() + 1; 
		System.out.println(rowNum);
		for (int i = 0; i < rowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			
			XSSFCell cell1 = row.getCell(1); 
			String cellvalue1 = cell1.getStringCellValue();
			XSSFCell cell2 = row.getCell(3); 
			String cellvalue2 = cell2.getStringCellValue();
			XSSFCell cell3 = row.getCell(9); 
			String cellvalue3 = cell3.getStringCellValue();
			
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE bank_info SET cardholder='");
			sb.append(cellvalue1);
			sb.append("',cark_num='");
			sb.append(cellvalue2);
			sb.append("' WHERE id=");
			sb.append(cellvalue3);
			sb.append(";");
			if (StringUtils.isEmpty(cellvalue1)||StringUtils.isEmpty(cellvalue2)||StringUtils.isEmpty(cellvalue3)) {
				System.out.println(cellvalue1+"--"+cellvalue2+"--"+cellvalue3);
			}
			out.write(sb.toString()+"\r\n");
		}
		out.close();
		return "1";
	}
}
