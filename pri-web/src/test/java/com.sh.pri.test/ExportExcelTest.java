package com.sh.pri.test;

import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by admin on 2018/5/17.
 */
public class ExportExcelTest {

    private static Log log = LogFactory.getLog(ExportExcelTest.class);


//    public static void Excel2007AboveOperate(String filePath) throws IOException {
//        Long startTime = System.currentTimeMillis();
//        XSSFWorkbook workbook1 = new XSSFWorkbook(new FileInputStream(new File(filePath)));
//        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook1, 100);
//        //            Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(filePath)));
//        Sheet first = sxssfWorkbook.getSheetAt(0);
//        for (int i = 0; i < 1; i++) {
//            Row row = first.createRow(i);
//            for (int j = 0; j < 11; j++) {
//                if (i == 0) {
//                    // 首行
//                    row.createCell(j).setCellValue("column" + j);
//                } else {
//                    // 数据
//                    if (j == 0) {
//                        CellUtil.createCell(row, j, String.valueOf(i));
//                    } else
//                        CellUtil.createCell(row, j, String.valueOf(Math.random()));
//                }
//            }
//        }
//        FileOutputStream out = new FileOutputStream("workbook.xlsx");
//        sxssfWorkbook.write(out);
//        out.close();
//        log.info("耗时:"+(System.currentTimeMillis() - startTime));
//    }

//    public static void main(String[] args) throws Exception{
//        Excel2007AboveOperate("D:\\workbook.xlsx");
//    }

    /**
     * 数据写入excel
     * 一万条数据4135毫秒, 1395k
     * 十万数据12000毫秒左右, 13976k
     * 一百万数据80000毫秒左右,140000k
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        Long startTime = System.currentTimeMillis();
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet();
        for (int i = 0; i < 1000000; i++) {
            Row row = sh.createRow(i);
            for (int j = 0; j < 11; j++) {
                if (i == 0) {
                    // 首行
                    row.createCell(j).setCellValue("column" + j);
                } else {
                    // 数据
                    if (j == 0) {
                        CellUtil.createCell(row, j, String.valueOf(i));
                    } else
                        CellUtil.createCell(row, j, String.valueOf(Math.random()));
                }
            }
        }
        FileOutputStream out = new FileOutputStream("D:\\workbook.xlsx");
        wb.write(out);
        out.close();
        log.info("耗时:"+(System.currentTimeMillis() - startTime));
    }

//    public static void main(String[] args) throws Throwable {
//        Long a = System.currentTimeMillis();
//        SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
//        Sheet sh = wb.createSheet();
//        for(int rownum = 0; rownum < 100000; rownum++){
//            Row row = sh.createRow(rownum);
//            for(int cellnum = 0; cellnum < 10; cellnum++){
//                Cell cell = row.createCell(cellnum);
//                String address = new CellReference(cell).formatAsString();
//                cell.setCellValue(address);
//            }
//        }
//        // Rows with rownum < 900 are flushed and not accessible
//        for(int rownum = 0; rownum < 900; rownum++){
//            Assert.assertNull(sh.getRow(rownum));
//        }
//        // ther last 100 rows are still in memory
//        for(int rownum = 900; rownum < 1000; rownum++){
//            Assert.assertNotNull(sh.getRow(rownum));
//        }
//        FileOutputStream out = new FileOutputStream("D:\\workbook.xlsx");
//        wb.write(out);
//        out.close();
//        // dispose of temporary files backing this workbook on disk
//        wb.dispose();
//        log.info("花费time:"+(System.currentTimeMillis() - a));
//    }
}
