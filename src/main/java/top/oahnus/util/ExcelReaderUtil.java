package top.oahnus.util;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.oahnus.config.ExcelReaderConfig;
import top.oahnus.entity.Company;
import top.oahnus.entity.Student;
import top.oahnus.entity.Teacher;
import top.oahnus.enums.AuthType;
import top.oahnus.exception.DataFormatException;

import javax.annotation.PostConstruct;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel解析工具类,可以根据Excel中的标题导入数据
 */
@Component
public class ExcelReaderUtil {
    private static final String EXCEL_2003_EXTENSION = ".xls";
    private static final String EXCEL_2007_EXTENSION = ".xlsx";

    @Autowired
    private ExcelReaderConfig tConfig;

    private static ExcelReaderConfig config;

    @PostConstruct
    public void init() {
        ExcelReaderUtil.config = tConfig;
    }

    @Autowired
    public static void setExcelReaderConfig(ExcelReaderConfig config) {
        ExcelReaderUtil.config = config;
    }

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

        sheet = wb != null ? wb.getSheetAt(0) : null;
        int rowNum = sheet != null ? sheet.getLastRowNum() : 0;

        // 获取表头
        Row rowHeader = sheet != null ? sheet.getRow(0) : null;
        Row row;

        Map<Object,Integer> headerMap = new HashMap<>();
        int index = 0;
        if (rowHeader != null) {
            switch (type.ordinal()) {
                case 1:
                    List<Company> companyList = new ArrayList<>();

                    while (index < rowHeader.getPhysicalNumberOfCells()) {
                        Cell cell = rowHeader.getCell(index);
                        if (getCellFormatValue(cell).equals(config.getCompany().getName())) {
                            headerMap.put("name", index);
                        } else if (getCellFormatValue(cell).equals(config.getCompany().getContact())) {
                            headerMap.put("contact", index);
                        } else if (getCellFormatValue(cell).equals(config.getCompany().getContactPhone())) {
                            headerMap.put("contact_phone", index);
                        } else if (getCellFormatValue(cell).equals(config.getCompany().getAddress())) {
                            headerMap.put("address", index);
                        } else if (getCellFormatValue(cell).equals(config.getCompany().getEmail())) {
                            headerMap.put("email", index);
                        }
                        index++;
                    }

                    for (int i = 1; i <= rowNum; i++) {
                        row = sheet.getRow(i);
                        companyList.add(new Company(
                                getCellFormatValue(row.getCell(headerMap.get("name"))),// 企业名称
                                getCellFormatValue(row.getCell(headerMap.get("contact"))),// 企业联系人
                                getCellFormatValue(row.getCell(headerMap.get("contact_phone"))), // 联系人电话
                                getCellFormatValue(row.getCell(headerMap.get("address"))), // 企业地址
                                getCellFormatValue(row.getCell(headerMap.get("email"))) // 邮箱
                        ));
                    }
                    return companyList;
                case 2:
                    List<Teacher> teacherList = new ArrayList<>();

                    // TODO 修改读取方式，通过获取Teacher类中的属性名来选取
                    while (index < rowHeader.getPhysicalNumberOfCells()) {
                        Cell cell = rowHeader.getCell(index);
                        if (getCellFormatValue(cell).equals(config.getTeacher().getName())) {
                            headerMap.put("name", index);
                        } else if (getCellFormatValue(cell).equals(config.getTeacher().getWorkerId())) {
                            headerMap.put("worker_id", index);
                        } else if (getCellFormatValue(cell).equals(config.getTeacher().getSex())) {
                            headerMap.put("sex", index);
                        } else if (getCellFormatValue(cell).equals(config.getTeacher().getJobTitle())) {
                            headerMap.put("job_title", index);
                        } else if (getCellFormatValue(cell).equals(config.getTeacher().getDepart())) {
                            headerMap.put("depart", index);
                        } else if (getCellFormatValue(cell).equals(config.getTeacher().getProfession())) {
                            headerMap.put("profession", index);
                        } else if (getCellFormatValue(cell).equals(config.getTeacher().getEmail())) {
                            headerMap.put("email", index);
                        }
                        index++;
                    }

                    for (int i = 1; i <= rowNum; i++) {
                        row = sheet.getRow(i);
                        teacherList.add(new Teacher(
                                getCellFormatValue(row.getCell(headerMap.get("worker_id"))),// 工号
                                getCellFormatValue(row.getCell(headerMap.get("name"))),// 姓名
                                getCellFormatValue(row.getCell(headerMap.get("profession"))), // 专业
                                getCellFormatValue(row.getCell(headerMap.get("depart"))), // 学院
                                getCellFormatValue(row.getCell(headerMap.get("sex"))), // 性别
                                getCellFormatValue(row.getCell(headerMap.get("job_title"))), // 征程
                                getCellFormatValue(row.getCell(headerMap.get("email"))) // 邮箱
                        ));
                    }
                    return teacherList;
                case 3:
                    List<Student> studentList = new ArrayList<>();
                    while (index < rowHeader.getPhysicalNumberOfCells()) {
                        Cell cell = rowHeader.getCell(index);
                        if (getCellFormatValue(cell).equals(config.getStudent().getName())) {
                            headerMap.put("name", index);
                        } else if (getCellFormatValue(cell).equals(config.getStudent().getStudentNum())) {
                            headerMap.put("student_num", index);
                        } else if (getCellFormatValue(cell).equals(config.getStudent().getSex())) {
                            headerMap.put("sex", index);
                        } else if (getCellFormatValue(cell).equals(config.getStudent().getDepart())) {
                            headerMap.put("depart", index);
                        } else if (getCellFormatValue(cell).equals(config.getStudent().getProfession())) {
                            headerMap.put("profession", index);
                        } else if (getCellFormatValue(cell).equals(config.getStudent().getEmail())) {
                            headerMap.put("email", index);
                        }
                        index++;
                    }

                    for (int i = 1; i <= rowNum; i++) {
                        row = sheet.getRow(i);
                        studentList.add(new Student(
                                getCellFormatValue(row.getCell(headerMap.get("student_num"))),// 学号
                                getCellFormatValue(row.getCell(headerMap.get("name"))),// 姓名
                                getCellFormatValue(row.getCell(headerMap.get("profession"))), // 专业
                                getCellFormatValue(row.getCell(headerMap.get("depart"))), // 学院
                                getCellFormatValue(row.getCell(headerMap.get("sex"))), // 性别
                                getCellFormatValue(row.getCell(headerMap.get("email"))) // 邮箱
                        ));
                    }
                    return studentList;
            }
        }
        return null;
    }
}
