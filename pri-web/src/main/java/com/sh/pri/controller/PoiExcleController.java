package com.sh.pri.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;















import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sh.pri.pojo.TUserInfo;
import com.sh.pri.service.IUserInfoService;

@Controller
@RequestMapping("/poiExcle")
public class PoiExcleController {

	public static final String outPutFile = "D:\\用户信息.xlsx";
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@RequestMapping("/jump")
	public String downLoadUserInfo(){
		return "poiExcle";
	}
	
	@RequestMapping("/download")
	 public void downloadExcle(HttpServletRequest req, HttpServletResponse resp) {
	        try {
	        	List<TUserInfo> userList = userInfoService.selectUserAll();
	            XSSFWorkbook workbook=new XSSFWorkbook();
	            req.setCharacterEncoding("UTF-8");
	            resp.setCharacterEncoding("UTF-8");
	            resp.setContentType("application/x-download");

	            String filedisplay = "用户信息.xlsx";
	            filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
	            resp.addHeader("Content-Disposition", "attachment;filename="+ filedisplay);
//	            XSSFSheet sheet = workbook.createSheet("未匹配产品");
//	            sheet.setColumnWidth(0, 50*160);
//	            XSSFFont font = workbook.createFont();
//	            font.setFontName("宋体");
//	            font.setFontHeightInPoints((short) 16);
	            
	            XSSFSheet sheet=workbook.createSheet("countryDB");
	            XSSFRow row = sheet.createRow((short)0);
	            XSSFCell cell=null;
	            cell=row.createCell((short)0);
	            cell.setCellValue("id");
	            cell=row.createCell((short)1);
	            cell.setCellValue("userName");
	            cell=row.createCell((short)2);
	            cell.setCellValue("email");
	            cell=row.createCell((short)3);
	            cell.setCellValue("mobile");
	            int i=1;
	            for (TUserInfo userInfo : userList) {
	            	 row=sheet.createRow(i);
		                cell=row.createCell(0);
		                cell.setCellValue(userInfo.getId());
		                cell=row.createCell(1);
		                cell.setCellValue(userInfo.getUserName());
		                cell=row.createCell(2);
		                cell.setCellValue(userInfo.getEmail());
		                cell=row.createCell(3);
		                cell.setCellValue(userInfo.getMobile());
		                i++;
				}
//	            FileOutputStream FOut = new FileOutputStream(outPutFile);
	            OutputStream FOut = resp.getOutputStream();
	            workbook.write(FOut);
	            FOut.flush();
	            FOut.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
