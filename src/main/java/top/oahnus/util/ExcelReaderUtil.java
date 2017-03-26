package top.oahnus.util;

import javafx.application.Application;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.oahnus.config.ExcelReaderConfig;
import top.oahnus.entity.Company;
import top.oahnus.entity.Student;
import top.oahnus.entity.Teacher;
import top.oahnus.enums.AuthType;
import top.oahnus.exception.DataFormatException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Excel解析工具类,可以根据Excel中的标题导入数据
 */
@Component
public class ExcelReaderUtil {
    private static final String EXCEL_2003_EXTENSION = ".xls";
    private static final String EXCEL_2007_EXTENSION = ".xlsx";

    @Autowired
    private ExcelReaderConfig config;

    /**
     * 从Cell中获取对应类型的值的字符串
     * @param cell cell
     * @return Excel中的值
     */
    private static String getCellFormatValue(Cell cell) {
        String cellValue;
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_NUMERIC:
                    cellValue = String.valueOf(Math.round(cell.getNumericCellValue()));
                    break;
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 方法2：这样子的data格式是不带带时分秒的：2011-10-12
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = sdf.format(date);
                    } else {
                        // 如果是纯数字取得当前Cell的数值
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case Cell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                default:
                    // 默认的Cell值
                    cellValue = " ";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }

    /**
     * 从Excel中读取数据
     * @param file  Excel文件
     * @param type 角色，根据角色读取数据返回
     * @return Excel中对象的列表
     */
    public static List readExcelFile(File file, AuthType type) {
        Workbook wb = null;
        Sheet sheet;
        try {
            String filename = file.getName();
            String extension = filename.substring(filename.lastIndexOf('.'));
            InputStream is = new FileInputStream(file);

            switch (extension) {
                case EXCEL_2003_EXTENSION:
                    // Excel 2003
                    wb = new HSSFWorkbook(new POIFSFileSystem(is));
                    break;
                case EXCEL_2007_EXTENSION:
                    wb = new XSSFWorkbook(is);
                    break;
                default:
                    throw new DataFormatException("不支持的文件格式");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = wb.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        Row row;
        switch (type.ordinal()) {
            case 1:
                List<Company> companyList = new ArrayList<>();

                for (int i = 1; i <= rowNum; i++) {
                    row = sheet.getRow(i);
//                    companyList.add(new Company(
//                            getCellFormatValue(row.getCell(1)),//企业名称
//                            getCellFormatValue(row.getCell(2)),//企业联系人
//                            getCellFormatValue(row.getCell(3)) //企业地址
//                    ));
                }
                return companyList;
            case 2:
                List<Teacher> teacherList = new ArrayList<>();
                for (int i = 1; i <= rowNum; i++) {
                    row = sheet.getRow(i);
//                    Teacher teacher = new Teacher(
//                            getCellFormatValue(row.getCell(1)),//工号
//                            getCellFormatValue(row.getCell(2)),//姓名
//                            getCellFormatValue(row.getCell(3)),//专业
//                            getCellFormatValue(row.getCell(4)) //学院
//                            );
//                    teacherList.add(teacher);
                }
                return teacherList;
            case 3:
                List<Student> studentList = new ArrayList<>();
                for(int i = 1; i <= rowNum; i++){
                    row = sheet.getRow(i);
//                    Student student = new Student(
//                            getCellFormatValue(row.getCell(1)),//学号
//                            getCellFormatValue(row.getCell(2)),//姓名
//                            getCellFormatValue(row.getCell(3)),//专业
//                            getCellFormatValue(row.getCell(4)) //学院
//                    );
//                    studentList.add(student);
                }
                return studentList;
        }
        return null;
    }
}
