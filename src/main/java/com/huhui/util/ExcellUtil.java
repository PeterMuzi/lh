package com.huhui.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.huhui.util.excell.AddColumnHander;
import com.huhui.util.excell.BaseColumnHander;
import com.huhui.util.excell.DirectSetColumnHander;
import com.huhui.util.excell.MapDataUtil;

public class ExcellUtil {
	public static void main(String[] arg) {
		try {
			
			String from="D:\\其他\\李西凉\\需求-FD1.xls";
			String to="D:\\其他\\李西凉\\政策_to.xls";
			if(arg!=null){
				from=System.getProperty("user.dir")+ File.separator+arg[arg.length-2];
				to=System.getProperty("user.dir")+ File.separator+arg[arg.length-1];
			}
			
			System.out.println(from);
			System.out.println(to);
			List<Map> datas=toMaps(from);
			
			//去掉重复的
			List<List<Map>> cf=MapUtil.groupBy(datas, new String[]{"城市","舱位"}, 2);
			if(cf!=null&&cf.size()>0){
				List<Map> remove=new ArrayList();
				for(int i=0;i<cf.size();i++){
					List<Map> temp=cf.get(i);
					BeanComparator compare=new BeanComparator("",BeanComparator.asc);
					Collections.sort(temp, compare);
					for(int j=0;j<temp.size()-1;j++){
						remove.add(temp.get(j));
					}
				}
				datas.removeAll(remove);
			}
			
			//6条数据变12条往返数据
			MapDataUtil.mul(datas, "舱位",new String[]{"M","B","H"}, true, "回程航班适用舱位");
			
			
			List<BaseColumnHander> handlers=new ArrayList();
			DirectSetColumnHander column1=new DirectSetColumnHander("政策代码","往返");//第一列直接设置默认值
			handlers.add(column1);
			BaseColumnHander column2=new BaseColumnHander("航空公司","航空公司");//第二列复制原文件的"航空公司"列
			handlers.add(column2);
			BaseColumnHander column3=new BaseColumnHander("起飞城市","航线");
			handlers.add(column3);
			BaseColumnHander column4=new BaseColumnHander("到达城市","城市");
			handlers.add(column4);
			AddColumnHander column5=new AddColumnHander("票面价","单程票价",new String[]{"航线","城市","回程航班适用舱位"},new String[]{"航线","城市","舱位"});
			handlers.add(column5);
			DirectSetColumnHander column6=new DirectSetColumnHander("返点","0");
			handlers.add(column6);
			DirectSetColumnHander column7=new DirectSetColumnHander("留钱","0");
			handlers.add(column7);
			DirectSetColumnHander column8=new DirectSetColumnHander("是否允许直接支付","是");
			handlers.add(column8);
			DirectSetColumnHander column9=new DirectSetColumnHander("是否生成PNR","是");
			handlers.add(column9);
			DirectSetColumnHander column10=new DirectSetColumnHander("进行PAT:A校验","是");
			handlers.add(column10);
			DirectSetColumnHander column11=new DirectSetColumnHander("去程有效航班起始时间","2014/5/8");
			handlers.add(column11);
			DirectSetColumnHander column12=new DirectSetColumnHander("去程有效航班结束时间","2014/5/8");
			handlers.add(column12);
			DirectSetColumnHander column13=new DirectSetColumnHander("去程有效航班适用","所有");
			handlers.add(column13);
			DirectSetColumnHander column14=new DirectSetColumnHander("去程有效航班号","");
			handlers.add(column14);
			BaseColumnHander column15=new BaseColumnHander("去程航班适用舱位","舱位");
			handlers.add(column15);
			DirectSetColumnHander column16=new DirectSetColumnHander("去程航班限制","1234567");
			handlers.add(column16);
			DirectSetColumnHander column17=new DirectSetColumnHander("回程有效航班起始时间","2014/5/8");
			handlers.add(column17);
			DirectSetColumnHander column18=new DirectSetColumnHander("回程有效航班结束时间","2014/5/8");
			handlers.add(column18);
			DirectSetColumnHander column19=new DirectSetColumnHander("回程有效航班适用","所有");
			handlers.add(column19);
			DirectSetColumnHander column20=new DirectSetColumnHander("回程有效航班号","");
			handlers.add(column20);
			BaseColumnHander column21=new BaseColumnHander("回程航班适用舱位","回程航班适用舱位");
			handlers.add(column21);
			DirectSetColumnHander column22=new DirectSetColumnHander("回程航班限制","1234567");
			handlers.add(column22);
			DirectSetColumnHander column23=new DirectSetColumnHander("提前出票时限","3");
			handlers.add(column23);
			DirectSetColumnHander column24=new DirectSetColumnHander("最短停留时间","2");
			handlers.add(column24);
			DirectSetColumnHander column25=new DirectSetColumnHander("最长停留时间","100");
			handlers.add(column25);
			DirectSetColumnHander column26=new DirectSetColumnHander("销售起始日期","2014/5/8");
			handlers.add(column26);
			DirectSetColumnHander column27=new DirectSetColumnHander("销售结束日期","2014/5/8");
			handlers.add(column27);
			DirectSetColumnHander column28=new DirectSetColumnHander("退改签说明","特价往返机票，不能改期，不能退票.出票价格可能与实际支付价格不一致，差价不提供行程单");
			handlers.add(column28);
			DirectSetColumnHander column29=new DirectSetColumnHander("舱位说明","特价往返机票，不能改期，不能退票.出票价格可能与实际支付价格不一致，差价不提供行程单");
			handlers.add(column29);
			DirectSetColumnHander column30=new DirectSetColumnHander("搭桥office号","CAN658");
			handlers.add(column30);
			DirectSetColumnHander column31=new DirectSetColumnHander("是否提供行程单","是");
			handlers.add(column31);
			creat2003Excel(datas,handlers,to);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	 /**  
     * 创建2003版本的Excel文件  
     */  
    public static void creat2003Excel(List<Map> data,List<BaseColumnHander> handers,String file) throws Exception{  
    	if(data==null||data.size()==0){
    		return;
    	}
        HSSFWorkbook workBook = new HSSFWorkbook();// 创建 一个excel文档对象  
  
        HSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象  
  
        HSSFRow row =null;
        HSSFCell cell=null;
        row= sheet.createRow(0);
        for(int j=0;j<handers.size();j++){
    		cell = row.createCell(j);// 创建单元格
    		cell.setCellValue(handers.get(j).columnName);
    	}
        
        for(int i=1;i<=data.size();i++){
        	row= sheet.createRow(i);
        	for(int j=0;j<handers.size();j++){
        		cell = row.createCell(j);// 创建单元格
        		cell.setCellValue(handers.get(j).hand(data,data.get(i-1)));
        	}
        }
        // 文件输出流  
        FileOutputStream os = new FileOutputStream(file);  
        workBook.write(os);// 将文档对象写入文件输出流  
        os.close();// 关闭文件输出流  
        System.out.println("创建成功 office 2003 excel");  
    }
    
	public	static List<Map> toMaps(String fileName) throws Exception{
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName  
                .substring(fileName.lastIndexOf(".") + 1);  
        if ("xls".equals(extension)) {  
            return read2003Excel(new File(fileName));  
        } else if ("xlsx".equals(extension)) {  
            return read2007Excel(new File(fileName));  
        } else {  
            throw new IOException("不支持的文件类型");  
        }
	}
	private static List<String> getColumns(XSSFSheet sheet){
		List<String> columns=new ArrayList();
        int i= sheet.getFirstRowNum();
        XSSFRow row = sheet.getRow(i);  
        if (row == null) {
            return null;  
        }
        XSSFCell cell = null; 
        for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {  
            cell = row.getCell(j);  
            if (cell == null||cell.toString()==null) {  
            	columns.add("");  
            }else{
            	columns.add(cell.toString());  
            }
        }  
        return columns;
	}
	private static List<String> getColumns(HSSFSheet sheet){
		List<String> columns=new ArrayList();
        int i= sheet.getFirstRowNum();
        HSSFRow row = sheet.getRow(i);  
        if (row == null) {
            return null;  
        }
        HSSFCell cell = null; 
        for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {  
            cell = row.getCell(j);  
            if (cell == null||cell.toString()==null) {  
            	columns.add("");  
            }else{
            	columns.add(cell.toString());  
            }
        }  
        return columns;
	}
	
    /**  
     * 读取 office 2003 excel  
     *   
     * @throws IOException  
     * @throws FileNotFoundException  
     */  
	
    private static List<Map> read2003Excel(File file)  
            throws IOException {
        List<Map> list = new ArrayList<Map>();  
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));  
        HSSFSheet sheet = hwb.getSheetAt(0);  
        Object value = null;  
        HSSFRow row = null;  
        HSSFCell cell = null;  
        Map map=null;
        List<String> columns=getColumns(sheet);
        if(columns==null){
        	System.out.println("读取列名称失败");
        	return null;
        }
        System.out.println("读取office 2003 excel内容如下(从第2行开始取)：");  
        for (int i = sheet.getFirstRowNum()+1; i <= sheet.getPhysicalNumberOfRows(); i++) {  
            row = sheet.getRow(i);
            if (row == null) {
                continue;  
            }  
            map=new HashMap();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {  
                cell = row.getCell(j);  
                if (cell == null||cell.toString()==null) {  
                	map.put(columns.get(j),"");  
                }else{
                	map.put(columns.get(j), cell.toString());  
                }
            }  
            System.out.println("");
            list.add(map);  
        } 
  
        return list;  
    }  
  
    /**  
     * 读取Office 2007 excel  
     */  
    private static List<Map> read2007Excel(File file)  
    throws IOException { 
        List<Map> list = new ArrayList<Map>();  
        XSSFWorkbook hwb = new XSSFWorkbook(new FileInputStream(file));  
        XSSFSheet sheet = hwb.getSheetAt(0);   
        Object value = null;  
        XSSFRow row = null;  
        XSSFCell cell = null;  
        Map map=null;
        List<String> columns=getColumns(sheet);
        if(columns==null){
        	System.out.println("读取列名称失败");
        	return null;
        }
        System.out.println("读取office 2007 excel内容如下(从第2行开始取)：");  
        for (int i = sheet.getFirstRowNum()+1; i <= sheet.getPhysicalNumberOfRows(); i++) {  
            row = sheet.getRow(i);
            if (row == null) {
                continue;  
            }  
            map=new HashMap();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {  
                cell = row.getCell(j); 
                try{
                	if (cell == null||cell.toString()==null) {  
                    	map.put(columns.get(j),"");  
                    }else{
                    	map.put(columns.get(j), cell.toString());  
                    }
                }catch(Exception e){
                	
                }
            }  
            System.out.println("");
            list.add(map);  
        } 
  
        return list;  
    }
    /*private static List<Map> read2007Excel(File file)  
            throws IOException {  
  
    	List<Map> list = new ArrayList<Map>();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));  
  
        // 读取第一章表格内容  
        XSSFSheet sheet = xwb.getSheetAt(0);  
        Object value = null;  
        XSSFRow row = null;  
        XSSFCell cell = null;  
        System.out.println("读取office 2007 excel内容如下：");  
        for (int i = sheet.getFirstRowNum(); i <= sheet  
                .getPhysicalNumberOfRows(); i++) {  
            row = sheet.getRow(i);  
            if (row == null) {  
                continue;  
            }  
            List<Object> linked = new LinkedList<Object>();  
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {  
                cell = row.getCell(j);  
                if (cell == null) {  
                    continue;  
                }  
                DecimalFormat df = new DecimalFormat("0");// 格式化 number String  
                // 字符  
                SimpleDateFormat sdf = new SimpleDateFormat(  
                        "yyyy-MM-dd HH:mm:ss");// 格式化日期字符串  
                DecimalFormat nf = new DecimalFormat("0.00");// 格式化数字  
  
                switch (cell.getCellType()) {  
                case XSSFCell.CELL_TYPE_STRING:  
                    // System.out.println(i + "行" + j + " 列 is String type");  
                    value = cell.getStringCellValue();  
                    System.out.print("  " + value + "  ");  
                    break;  
                case XSSFCell.CELL_TYPE_NUMERIC:  
                    // System.out.println(i + "行" + j  
                    // + " 列 is Number type ; DateFormt:"  
                    // + cell.getCellStyle().getDataFormatString());  
                    if ("@".equals(cell.getCellStyle().getDataFormatString())) {  
                        value = df.format(cell.getNumericCellValue());  
  
                    } else if ("General".equals(cell.getCellStyle()  
                            .getDataFormatString())) {  
                        value = nf.format(cell.getNumericCellValue());  
                    } else {  
                        value = sdf.format(HSSFDateUtil.getJavaDate(cell  
                                .getNumericCellValue()));  
                    }  
                    System.out.print("  " + value + "  ");  
                    break;  
                case XSSFCell.CELL_TYPE_BOOLEAN:  
                    // System.out.println(i + "行" + j + " 列 is Boolean type");  
                    value = cell.getBooleanCellValue();  
                    System.out.print("  " + value + "  ");  
                    break;  
                case XSSFCell.CELL_TYPE_BLANK:  
                    // System.out.println(i + "行" + j + " 列 is Blank type");  
                    value = "";  
                    // System.out.println(value);  
                    break;  
                default:  
                    // System.out.println(i + "行" + j + " 列 is default type");  
                    value = cell.toString();  
                    System.out.print("  " + value + "  ");  
                }  
                if (value == null || "".equals(value)) {  
                    continue;  
                }  
                linked.add(value);  
            }  
            System.out.println("");  
            //list.add(linked);  
        }  
        return list;  
    }*/
}
