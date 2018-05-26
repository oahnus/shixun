package top.oahnus.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.oahnus.domain.Company;
import top.oahnus.domain.Student;
import top.oahnus.domain.Teacher;
import top.oahnus.domain.UserAuth;
import top.oahnus.enums.RoleEnum;
import top.oahnus.enums.SexEnum;
import top.oahnus.exception.BadRequestParamException;
import top.oahnus.exception.DataFormatException;
import top.oahnus.exception.ExcelFormatException;
import top.oahnus.repository.CompanyRepo;
import top.oahnus.repository.StudentRepo;
import top.oahnus.repository.TeacherRepo;
import top.oahnus.repository.UserAuthRepo;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class ExcelImportService {
    private final String EXCEL_2003_EXTENSION = ".xls";
    private final String EXCEL_2007_EXTENSION = ".xlsx";

    private final List<String> TEACHER_TITLES = Arrays.asList(
            "工号",
            "姓名",
            "专业",
            "学院",
            "性别",
            "职称",
            "邮箱"
            );
    private final List<String> COMPANY_TITLES = Arrays.asList(
            "邮箱",
            "公司名称",
            "地址",
            "电话"
        );
    private final List<String> STUDENT_TITLES = Arrays.asList(
            "学号",
            "姓名",
            "性别",
            "学院",
            "专业",
            "邮箱"
    );

    private DecimalFormat DF = new DecimalFormat("#");

    @Autowired
    private DepartAndProfessionService dapService;
    @Autowired
    private UserAuthRepo authRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private StudentRepo studentRepo;

    public void importData(MultipartFile multipartFile, RoleEnum role) {

        try {
            File tempFile = File.createTempFile("temp", "d");
            FileUtils.copyToFile(multipartFile.getInputStream(), tempFile);
            switch (role) {
                case TEACHER:
                    readTeacherFromExcel(tempFile);
                    break;
                case STUDENT:
                    readStudentFromExcel(tempFile);
                    break;
                case COMPANY:
                    readCompanyFromExcel(tempFile);
                    break;
                default:
                    throw new BadRequestParamException("");
            }
        } catch (IOException e) {
            log.error("EXCEL文件读取失败");
        }
    }

    @SuppressWarnings({"unckecked", "unchecked"})
    private <T> T getCellValue(Cell cell, Class cls) {
        if (cell == null) {
            return null;
        }
        int type = cell.getCellType();
        switch (type) {
            case Cell.CELL_TYPE_STRING:
            case Cell.CELL_TYPE_NUMERIC:
                Double value = cell.getNumericCellValue();
                if (cls == Date.class) {
                    return (T) cell.getDateCellValue();
                } else if (cls == Integer.class) {
                    int c = value.intValue();
                    return (T) Integer.valueOf(c);
                } else if (cls == Long.class) {
                    int c = value.intValue();
                    return (T) Long.valueOf(c);
                } else if (cls == Double.class) {
                    return (T) value;
                } else if (cls == String.class) {
                    return (T) DF.format(value);
                } else {
                    return null;
                }
            case Cell.CELL_TYPE_BOOLEAN:
                Boolean value1 = cell.getBooleanCellValue();
                return (T) value1;
            case Cell.CELL_TYPE_FORMULA:
                return (T) cell.getArrayFormulaRange().formatAsString();
            case Cell.CELL_TYPE_BLANK:
                return null;
            default:
                return null;
        }
    }

    private Sheet getSheet(File file) {
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
        if (sheet == null) {
            throw new ExcelFormatException();
        }
        return sheet;
    }

    public void readStudentFromExcel(File file) {
        Sheet sheet = getSheet(file);
        if (sheet != null) {
            int maxRowNum = sheet.getLastRowNum();
            List<Integer> rows = IntStream.range(1, maxRowNum)
                    .boxed()
                    .collect(Collectors.toList());
            List<List<Integer>> rowGroups = ListUtils.partition(rows, 50);

            rowGroups.forEach(_rows -> {
                List<Student> studentList = _rows.stream()
                        .map(row -> wrapperStudent(sheet, row))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                saveStudentList(studentList);
            });
        }
    }

    private Student wrapperStudent(Sheet sheet, Integer rowNum) {
        Student student = new Student();
        try {
            Row row = sheet.getRow(rowNum);
            String stuNumber = getCellValue(row.getCell(STUDENT_TITLES.indexOf("学号")), String.class);
            String name = getCellValue(row.getCell(STUDENT_TITLES.indexOf("姓名")), String.class);
            String sexName = getCellValue(row.getCell(STUDENT_TITLES.indexOf("性别")), String.class);
            String proName = getCellValue(row.getCell(STUDENT_TITLES.indexOf("专业")), String.class);
            String departName = getCellValue(row.getCell(STUDENT_TITLES.indexOf("学院")), String.class);
            String email = getCellValue(row.getCell(STUDENT_TITLES.indexOf("邮箱")), String.class);

            student.setStuNumber(stuNumber);
            student.setSex(SexEnum.convert(sexName));
            student.setProfession(dapService.getProByName(proName));
            student.setDepartment(dapService.getDepartByName(departName));
            student.setEmail(email);
            student.setName(name);
            student.setCreateTime(new Date());
            student.setUpdateTime(new Date());
            student.setDelFlag(false);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return student;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveStudentList(List<Student> studentList) {
        List<String> stuNumbers = studentList.stream()
                .map(Student::getStuNumber)
                .distinct()
                .collect(Collectors.toList());

        List<String> existedAuthList = authRepo.findByRoleAndUsernameIn(RoleEnum.STUDENT, stuNumbers)
                .stream().map(UserAuth::getUsername)
                .collect(Collectors.toList());

        List<Student> needSaveStudents = studentList.stream()
                .filter(student -> !existedAuthList.contains(student.getStuNumber()))
                .distinct()
                .collect(Collectors.toList());

        needSaveStudents.forEach(student -> {
            UserAuth auth = UserAuth.buildByEmail(student.getStuNumber());
            UserAuth _auth = authRepo.save(auth);
            student.setAuthId(_auth.getId());
        });

        studentRepo.save(needSaveStudents);
    }

    public void readCompanyFromExcel(File file) {
        Sheet sheet = getSheet(file);
        if (sheet != null) {
            int maxRowNum = sheet.getLastRowNum();
            List<Integer> rows = IntStream.range(1, maxRowNum)
                    .boxed()
                    .collect(Collectors.toList());
            List<List<Integer>> rowGroups = ListUtils.partition(rows, 50);

            rowGroups.forEach(_rows -> {
                List<Company> companyList = _rows.stream()
                        .map(row -> wrapperCompany(sheet, row))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                saveCompanyList(companyList);
            });
        }
    }

    private Company wrapperCompany(Sheet sheet, Integer rowNum) {
        Company company = new Company();
        try {
            Row row = sheet.getRow(rowNum);
            String email = getCellValue(row.getCell(COMPANY_TITLES.indexOf("邮箱")), String.class);
            String name = getCellValue(row.getCell(COMPANY_TITLES.indexOf("公司名称")), String.class);
            String address = getCellValue(row.getCell(COMPANY_TITLES.indexOf("地址")), String.class);
            String telephone = getCellValue(row.getCell(COMPANY_TITLES.indexOf("电话")), String.class);

            company.setEmail(email);
            company.setName(name);
            company.setAddress(address);
            company.setTelephone(telephone);
            company.setCreateTime(new Date());
            company.setUpdateTime(new Date());
            company.setDelFlag(false);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return company;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCompanyList(List<Company> companyList) {
        List<String> emails = companyList.stream()
                .map(Company::getEmail)
                .distinct()
                .collect(Collectors.toList());

        List<String> existedAuthList = authRepo.findByRoleAndUsernameIn(RoleEnum.COMPANY, emails)
                .stream().map(UserAuth::getUsername)
                .collect(Collectors.toList());

        List<Company> needSaveCompanies = companyList.stream()
                .filter(company -> !existedAuthList.contains(company.getEmail()))
                .distinct()
                .collect(Collectors.toList());

        needSaveCompanies.forEach(company -> {
            UserAuth auth = UserAuth.buildByEmail(company.getEmail());
            UserAuth _auth = authRepo.save(auth);
            company.setAuthId(_auth.getId());
        });

        companyRepo.save(needSaveCompanies);
    }

    public void readTeacherFromExcel(File file) {
        Sheet sheet = getSheet(file);
        if (sheet != null) {
            int maxRowNum = sheet.getLastRowNum();
            List<Integer> rows = IntStream.range(1, maxRowNum)
                    .boxed()
                    .collect(Collectors.toList());
                List<List<Integer>> rowGroups = ListUtils.partition(rows, 50);

            rowGroups.forEach(_rows -> {
                List<Teacher> _teacherList = _rows.stream()
                        .map(row -> wrapperTeacher(sheet, row))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                saveTeacherList(_teacherList);
//                System.out.println(_teacherList);
            });
        }
    }

    private Teacher wrapperTeacher(Sheet sheet, Integer rowNum) {
        Teacher teacher = new Teacher();
        try {
            Row row = sheet.getRow(rowNum);
            String workerId = getCellValue(row.getCell(TEACHER_TITLES.indexOf("工号")), String.class);
            String name = getCellValue(row.getCell(TEACHER_TITLES.indexOf("姓名")), String.class);
            String sexName = getCellValue(row.getCell(TEACHER_TITLES.indexOf("性别")), String.class);
            String departName = getCellValue(row.getCell(TEACHER_TITLES.indexOf("学院")), String.class);
            String proName = getCellValue(row.getCell(TEACHER_TITLES.indexOf("专业")), String.class);
            String jobTitle = getCellValue(row.getCell(TEACHER_TITLES.indexOf("职称")), String.class);
            String email = getCellValue(row.getCell(TEACHER_TITLES.indexOf("邮箱")), String.class);

            teacher.setWorkerId(workerId);
            teacher.setName(name);
            teacher.setSex(SexEnum.convert(sexName));
            teacher.setDepartment(dapService.getDepartByName(departName));
            teacher.setProfession(dapService.getProByName(proName));
            teacher.setJobTitle(jobTitle);
            teacher.setEmail(email);
            teacher.setCreateTime(new Date());
            teacher.setUpdateTime(new Date());
            teacher.setDelFlag(false);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return teacher;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveTeacherList(List<Teacher> teacherList) {
        List<String> workerIds = teacherList.stream()
                .map(Teacher::getWorkerId)
                .distinct()
                .collect(Collectors.toList());

        List<String> existedAuthList = authRepo.findByRoleAndUsernameIn(RoleEnum.TEACHER, workerIds)
                .stream().map(UserAuth::getUsername)
                .collect(Collectors.toList());

        List<Teacher> needSaveTeachers = teacherList.stream()
                .filter(teacher -> !existedAuthList.contains(teacher.getWorkerId()))
                .collect(Collectors.toList());

        needSaveTeachers.forEach(teacher -> {
            UserAuth auth = UserAuth.buildByWorkerId(teacher.getWorkerId());
            UserAuth _auth = authRepo.save(auth);
            teacher.setAuthId(_auth.getId());
        });

        teacherRepo.save(needSaveTeachers);
    }
}
