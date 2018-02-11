package com.sh.pri.controller;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sh.pri.commons.ExcelReadAndWrite;
import com.sh.pri.pojo.TUserInfo;
import com.sh.pri.service.IUserInfoService;

@Controller
@RequestMapping("/poiExcel")
public class PoiExcelController {

	private static Log log = LogFactory.getLog(PoiExcelController.class);
	public static final String outPutFile = "D:\\用户信息.xlsx";
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@RequestMapping("/jump")
	public String downLoadUserInfo(HttpServletRequest request){
		request.getSession().invalidate();
		return "poiExcel";
	}
	
	@RequestMapping("/download")
	 public void downloadExcel(HttpServletRequest req, HttpServletResponse resp) {
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
	
	@RequestMapping("/upload")
	public void uploadExcel(@RequestParam(value="uploadFile") MultipartFile Mfile, HttpServletRequest request, HttpServletResponse response) throws Exception{
		log.info("PoiExcelController.. uploadExcel()  start----");
		if (Mfile == null) {
			request.getSession().setAttribute("error", "上传文件不能为空!");
			request.getSession().invalidate();
			return;
		}
		//获取文件名
		String fileName = Mfile.getOriginalFilename();
		//MultipartFile转成file类型
		CommonsMultipartFile uploadFile = (CommonsMultipartFile)Mfile;
		DiskFileItem fi = (DiskFileItem)uploadFile.getFileItem();
		File file = fi.getStoreLocation();
		//解析excel文件
		List<List<String>> value = ExcelReadAndWrite.readExcel(fileName, (File)file);
		for (List<String> list : value) {
			list.remove(list.size()-1);
			for (String string : list) {
				System.out.println("=======excel:"+string);
			}
		}
		//删除缓存文件
		boolean delete = file.delete();
		System.out.println(delete);
		request.getSession().setAttribute("msg", "文件上传完毕!");
		request.getRequestDispatcher("/WEB-INF/jsp/poiExcel.jsp").forward(request, response);
		request.getSession().invalidate();
		return;
	}
	
}
