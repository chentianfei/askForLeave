package com.ctf.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ctf.bean.ExcelBean;
import com.ctf.bean.Person;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelToDatabaseUtils {
    private final static String excel2003L = ".xls"; // 2003- 版本的excel
    private final static String excel2007U = ".xlsx"; // 2007+ 版本的excel

    //读取excel数据，返回Workbook类对象
    public static Workbook readExcelToWorkbook(String filePath) throws IOException {
        // 1. 获取读取文件的输入流
        FileInputStream in = new FileInputStream(filePath);
        Workbook wb = null;
        String fileType = filePath
                .trim()
                .substring(filePath.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            wb = new HSSFWorkbook(in); // 2003-
        } else if (excel2007U.equals(fileType)) {
            wb = new XSSFWorkbook(in); // 2007+
        } 
        // 关闭流资源
        in.close();
        return wb;
    }

    //读取excel数据，封装为person类的list后返回
    public static List<Person> parseExcelToPersonListOBJ(String filePath) throws IOException, ParseException {
        Workbook workbook = readExcelToWorkbook(filePath);

        List<Person> personList = new ArrayList<>();
        boolean isFirstRow = true;

        if(null != workbook){
            Sheet sheet = workbook.getSheetAt(0);

            for(Row row : sheet){
                //第一行跳过，不封装为对象
                if(isFirstRow){
                    isFirstRow = false;
                    continue;
                }
                //从第二行开始解析数据
                if(row != null){
                    Person person = new Person();
                    for(Cell cell:row){
                        //读取数据，封装对象
                        switch (cell.getColumnIndex()){
                            case 0:
                                String name0 = cell.getStringCellValue();
                                person.setName(name0);
                                break;
                            case 1:
                                String sex1 = cell.getStringCellValue();
                                person.setSex(sex1);
                                break;
                            case 2:
                                String birthdate2 = cell.getStringCellValue();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                System.out.println(birthdate2);
                                person.setBirthDate(sdf.parse(birthdate2));
                                break;
                            case 3:
                                String area_class3 = cell.getStringCellValue();
                                person.setArea_class(area_class3);
                                break;
                            case 4:
                                String nation4 = cell.getStringCellValue();
                                person.setNation(nation4);
                                break;
                            case 5:
                                String nativeplace5 = cell.getStringCellValue();
                                person.setNativePlace(nativeplace5);
                                break;
                            case 6:
                                String office6 = cell.getStringCellValue();
                                person.setOffice(office6);
                                break;
                            case 7:
                                String post7 = cell.getStringCellValue();
                                person.setPost(post7);
                                break;
                            case 8:
                                String level8 = cell.getStringCellValue();
                                person.setLevel(level8);
                                break;
                            case 9:
                                String phone9 = new BigDecimal(
                                        cell.getNumericCellValue()
                                ).toPlainString();
                                person.setPhone(phone9);
                                break;
                            case 10:
                                int allow_leave_days10 = (int)cell.getNumericCellValue();
                                person.setAllow_Leave_Days(allow_leave_days10);
                                break;
                        }
                    }
                    personList.add(person);
                }
            }
        }
        return personList;
    }


    /**
     * 描述：对表格中数值进行格式化
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0"); // 格式化字符类型的数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式化
        DecimalFormat df2 = new DecimalFormat("0"); // 格式化数字

        if (cell.getCellType() == CellType.STRING) {
            value = cell.getRichStringCellValue().getString();
        }
        else if (cell.getCellType() == CellType.NUMERIC) {
            if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                value = df.format(cell.getNumericCellValue());
            } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                value = sdf.format(cell.getDateCellValue());
            } else {
                value = df2.format(cell.getNumericCellValue());
            }
        }
        else if (cell.getCellType() == CellType.BOOLEAN) {
            value = cell.getBooleanCellValue();
        }
        else if (cell.getCellType() == CellType.BLANK) {
            value = "";
        }
        return value;
    }

    /**
     * 导入Excel表结束 导出Excel表开始
     *
     * @param sheetName 工作簿名称
     * @param clazz 数据源model类型
     * @param objs excel标题列以及对应model字段名
     * @param map 标题列行数以及cell字体样式
     */
    public static XSSFWorkbook createExcelFile(Class clazz, List objs, Map<Integer, List<ExcelBean>> map,
                                               String sheetName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
            ClassNotFoundException, IntrospectionException {
        // 创建新的Excel工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 以下为excel的字体样式以及excel的标题与内容的创建，下面会具体分析;
        createTableHeader(sheet, map); //创建标题（头）
        createTableRows(sheet, map, objs, clazz); // 创建内容
        return workbook;
    }

    /**
     * @param sheet 工作簿
     * @param map 每行每个单元格对应的列头信息
     */
    public static final void createTableHeader(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map) {
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            XSSFRow row = sheet.createRow(entry.getKey());
            List<ExcelBean> excels = entry.getValue();
            for (int x = 0; x < excels.size(); x++) {
                XSSFCell cell = row.createCell(x);
                cell.setCellValue(excels.get(x).getHeadTextName());// 设置内容
            }
        }
    }

    public static void createTableRows(XSSFSheet sheet, Map<Integer, List<ExcelBean>> map, List objs, Class clazz)
            throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IntrospectionException{
        int rowindex = map.size();
        int maxKey = 0;
        List<ExcelBean> ems = new ArrayList<>();
        for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        ems = map.get(maxKey);
        for (Object obj : objs) {
            XSSFRow row = sheet.createRow(rowindex);
            for (int i = 0; i < ems.size(); i++) {
                ExcelBean em = ems.get(i);
                // 获得get方法
                PropertyDescriptor pd = new PropertyDescriptor(em.getPropertyName(), clazz);
                Method getMethod = pd.getReadMethod();
                Object rtn = getMethod.invoke(obj);
                String value = "";
                // 如果是日期类型进行转换
                if (rtn != null) {
                    if (rtn instanceof Date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date rtn_date = (Date) rtn;
                        value = simpleDateFormat.format(rtn_date);
                    } else {
                        value = rtn.toString();
                    }
                }
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(value);
                cell.setCellType(CellType.STRING);
            }
            rowindex++;
        }
    }
}
