package com.huhui.util;
import java.io.File;  
    import java.io.FileInputStream;  
    import java.io.FileNotFoundException;  
    import java.io.FileOutputStream;  
    import java.io.IOException;  
    import java.text.DecimalFormat;  
    import java.text.SimpleDateFormat;  
    import java.util.Date;  
    import java.util.LinkedList;  
    import java.util.List;  
      
    import org.apache.poi.hssf.usermodel.HSSFCell;  
    import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
    import org.apache.poi.hssf.usermodel.HSSFDataFormat;  
    import org.apache.poi.hssf.usermodel.HSSFDateUtil;  
    import org.apache.poi.hssf.usermodel.HSSFFont;  
    import org.apache.poi.hssf.usermodel.HSSFRow;  
    import org.apache.poi.hssf.usermodel.HSSFSheet;  
    import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
    import org.apache.poi.hssf.util.HSSFColor;  
    import org.apache.poi.xssf.usermodel.XSSFCell;  
    import org.apache.poi.xssf.usermodel.XSSFCellStyle;  
    import org.apache.poi.xssf.usermodel.XSSFFont;  
    import org.apache.poi.xssf.usermodel.XSSFRow;  
    import org.apache.poi.xssf.usermodel.XSSFSheet;  
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
      
    /**  
     * 可以从http://poi.apache.org/ 这里下载到POI的jar包 POI   
    创建和读取2003-2007版本Excel文件  
     *   
     */  
      
    public class Ex {  
      
        
      
        /**  
         * 创建2007版Excel文件  
         *   
         * @throws FileNotFoundException  
         * @throws IOException  
         */  
        private static void creat2007Excel() throws FileNotFoundException,  
                IOException {  
            // HSSFWorkbook workBook = new HSSFWorkbook();// 创建 一个excel文档对象  
            XSSFWorkbook workBook = new XSSFWorkbook();  
            XSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象  
      
            sheet.setColumnWidth(1, 10000);// 设置第二列的宽度为  
      
            XSSFRow row = sheet.createRow(1);// 创建一个行对象  
      
            row.setHeightInPoints(23);// 设置行高23像素  
      
            XSSFCellStyle style = workBook.createCellStyle();// 创建样式对象  
      
            // 设置字体  
      
            XSSFFont font = workBook.createFont();// 创建字体对象  
      
            font.setFontHeightInPoints((short) 15);// 设置字体大小  
      
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体  
      
            font.setFontName("黑体");// 设置为黑体字  
      
            style.setFont(font);// 将字体加入到样式对象  
      
            // 设置对齐方式  
      
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中  
      
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中  
      
            // 设置边框  
      
            style.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线  
      
            style.setTopBorderColor(HSSFColor.RED.index);// 设置为红色  
      
            style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// 底部边框双线  
      
            style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框  
      
            style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框  
      
            // 格式化日期  
      
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));  
      
            XSSFCell cell = row.createCell(1);// 创建单元格  
      
            cell.setCellValue(new Date());// 写入当前日期  
      
            cell.setCellStyle(style);// 应用样式对象  
      
            // 文件输出流  
      
            FileOutputStream os = new FileOutputStream("style_2007.xlsx");  
      
            workBook.write(os);// 将文档对象写入文件输出流  
      
            os.close();// 关闭文件输出流  
            System.out.println("创建成功 office 2007 excel");  
        }  
      
        /**  
         * 创建2003版本的Excel文件  
         */  
        private static void creat2003Excel() throws FileNotFoundException,  
                IOException {  
            HSSFWorkbook workBook = new HSSFWorkbook();// 创建 一个excel文档对象  
      
            HSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象  
      
            sheet.setColumnWidth(1, 10000);// 设置第二列的宽度为  
      
            HSSFRow row = sheet.createRow(1);// 创建一个行对象  
      
            row.setHeightInPoints(23);// 设置行高23像素  
      
            HSSFCellStyle style = workBook.createCellStyle();// 创建样式对象  
      
            // 设置字体  
      
            HSSFFont font = workBook.createFont();// 创建字体对象  
      
            font.setFontHeightInPoints((short) 15);// 设置字体大小  
      
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体  
      
            font.setFontName("黑体");// 设置为黑体字  
      
            style.setFont(font);// 将字体加入到样式对象  
      
            // 设置对齐方式  
      
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中  
      
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中  
      
            // 设置边框  
      
            style.setBorderTop(HSSFCellStyle.BORDER_THICK);// 顶部边框粗线  
      
            style.setTopBorderColor(HSSFColor.RED.index);// 设置为红色  
      
            style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);// 底部边框双线  
      
            style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框  
      
            style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框  
      
            // 格式化日期  
      
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));  
      
            HSSFCell cell = row.createCell(1);// 创建单元格  
      
            cell.setCellValue(new Date());// 写入当前日期  
      
            cell.setCellStyle(style);// 应用样式对象  
      
            // 文件输出流  
      
            FileOutputStream os = new FileOutputStream("style_2003.xls");  
      
            workBook.write(os);// 将文档对象写入文件输出流  
      
            os.close();// 关闭文件输出流  
            System.out.println("创建成功 office 2003 excel");  
        }  
       
      
  
    }  