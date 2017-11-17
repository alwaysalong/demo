///**
// * struts2框架
// */
//package com.sh.pri.controller;
//
//import java.io.OutputStream;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.struts2.ServletActionContext;
//
//import com.ibm.icu.util.Calendar;
//import com.opensymphony.xwork2.ModelDriven;
//
//public class AccountOrderAction extends PageAction implements ModelDriven<TWAccountOrderDto>{
//
//	private static final long serialVersionUID = 3455950370328129725L;
//
//	private Log log = LogFactory.getLog(AccountOrderAction.class);
//	private PageBO pagination; // 分页bean
//	private TWAccountOrderDto dto = new TWAccountOrderDto();//页面显示数据DTO
//	
//	@Override
//	public TWAccountOrderDto getModel() {
//		return null;
//	}
//	/*
//	 * 初始化页面
//	 */
//	public String queryAccountOrder(){
//		setInitTime();
//		return "queryAccountOrder";
//	}
//	
//	/**
//	 * 
//	 * @Title: setInitTime 
//	 * @Description: 初始化页面时间插件默认统计时间
//	 * @param 
//	 * @return void 
//	 * @throws
//	 */
//	private void setInitTime(){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(new Date());
//		calendar.add(Calendar.DAY_OF_MONTH, -31);
//		//按特定格式显示刚设置的时间
//		dto.setCreateStartTime(sdf.format(calendar.getTime()));;
//		dto.setCreateEndTime(sdf.format(new Date()));
//	}
//	
//	/**
//	 * 
//	 * @Title: queryAccountOrderList 
//	 * @Description: 分页查询账户充值订单列表
//	 * @param @return
//	 * @return String 
//	 * @throws
//	 */
//	public String queryAccountOrderList(){
//		if(null == pagination){
//			pagination = this.pageBO;
//		}
//		paginationSetting();
//		try {
//			pagination = this.getClient().queryAccountOrderList(pagination, dto);
//		} catch (Exception e) {
//			log.error("查询账户充值订单列表异常", e);
//			return "error";
//		}
//		paginationData();
//		return "queryAccountOrder";
//	}
//	
//	/**
//	 * 设定分页数据
//	 *	return void
//	 */
//	public void paginationSetting(){
//		 
//		if (pagination.getCpage() == null) {
//			pagination.setCpage(this.getCpage());
//		}
//		if (pagination.getPerpage() == null) {
//			pagination.setPerpage("10");
//		}
//	}
//	
//	/**
//	 * 
//	 * @Title: downLoadAccountOrderInfo 
//	 * @Description: 下载账户充值订单信息
//	 * @param @return
//	 * @return String 
//	 * @throws
//	 */
//	public String downLoadAccountOrderInfo(){
//		try{
//			HttpServletRequest request = ServletActionContext.getRequest();
//			HttpServletResponse response = ServletActionContext.getResponse();
//			String sheetname="账户充值订单";//excle文件名及标题名
//			String fileName = MyFileUtils.encodeDownloadFilename(sheetname, request.getHeader("USER-AGENT"))+System.currentTimeMillis()+".xlsx";
//			XSSFWorkbook workbook = new XSSFWorkbook();
//			//生成sheet页 带标题
//			Sheet sheet = workbook.createSheet(sheetname);
//			//创建首行标题
//			Row row= sheet.createRow(0);
//			row.createCell(0).setCellValue("用户ID");
//			row.createCell(1).setCellValue("订单号");
//			row.createCell(2).setCellValue("请求流水号");
//			row.createCell(3).setCellValue("支付流水号");
//			row.createCell(4).setCellValue("状态");
//			row.createCell(5).setCellValue("订单金额");
//			row.createCell(6).setCellValue("订单创建时间");
//			row.createCell(7).setCellValue("交易完成时间");
//			row.createCell(8).setCellValue("支付方式");
//			List<TWAccountOrderDto> list = this.getClient().downLoadAccountOrderInfo(dto);
//			//往excel中写数据
//			if(list != null && list.size() > 0){
//				for(int i = 0; i < list.size(); i++){
//					TWAccountOrderDto dto = list.get(i);
//					row = sheet.createRow(i+1);
//					row.createCell(0).setCellValue(dto.getUserOnlyId());
//					row.createCell(1).setCellValue(dto.getOrderId());
//					row.createCell(2).setCellValue(dto.getReqNo());
//					row.createCell(3).setCellValue(dto.getPayId());
//					row.createCell(4).setCellValue(dto.getStatus());
//					row.createCell(5).setCellValue(dto.getOrderMoney());
//					row.createCell(6).setCellValue(dto.getOrderCreateTime());
//					row.createCell(7).setCellValue(dto.getPayTime());
//					row.createCell(8).setCellValue(dto.getPayType());
//				}
//			}
//			OutputStream out = response.getOutputStream();
//			response.reset();
//			response.setContentType("multipart/form-data");
//			response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gb2312"),"iso8859-1"));
//			response.setContentType("UTF-8");
//			workbook.write(out);
//			out.flush();
//			out.close();
//		}catch(Exception e){
//			log.info("下载账户充值订单报表异常", e);
//			return "error";
//		}
//		return NONE;
//	}
//	
//	public void paginationData(){
//		this.setTotalRecord(pagination.getTotal());
//		this.setPerpage(pagination.getPerpage());
//	}
//	
//	private IAccountClient getClient(){
//		return new AccountClientImpl();
//	}
//
//	public PageBO getPagination() {
//		return pagination;
//	}
//
//	public void setPagination(PageBO pagination) {
//		this.pagination = pagination;
//	}
//
//	public TWAccountOrderDto getDto() {
//		return dto;
//	}
//
//	public void setDto(TWAccountOrderDto dto) {
//		this.dto = dto;
//	}
//
//
//
//
//
//	
//
//}
