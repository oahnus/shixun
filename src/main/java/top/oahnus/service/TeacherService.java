package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.repository.TeacherRepo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oahnus on 2017/3/28
 * 18:37.
 */
@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private DepartAndProfessionService dapService;

    private List<String> teacherTitles = Arrays.asList(
            "工号",
            "姓名",
            "性别",
            "学院",
            "专业",
            "职称");
    private List<String> companyTitles = Arrays.asList("");
    private List<String> studentTitles = Arrays.asList("");

    public void importTeacher() {

    }
}
