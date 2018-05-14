package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.domain.Student;
import top.oahnus.domain.UserAuth;
import top.oahnus.exception.DataExistedException;
import top.oahnus.repository.StudentRepo;
import top.oahnus.repository.UserAuthRepo;

import java.util.Date;

/**
 * Created by oahnus on 2018/5/13
 * 20:47.
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private UserAuthRepo authRepo;

    @Transactional
    public void save(Student student) {
        String stuNumber = student.getStuNumber();

        checkExisted(stuNumber);

        UserAuth auth;
        auth = UserAuth.buildByStuNumber(stuNumber);
        auth = authRepo.save(auth);
        student.setAuthId(auth.getId());
        studentRepo.save(student);
    }

    public void update(Student student) {
        String stuNumber = student.getStuNumber();
        checkExisted(stuNumber);

        student.setUpdateTime(new Date());

        studentRepo.save(student);
    }

    public void delete(Long studentId) {
        Student student = studentRepo.findOne(studentId);
        student.setDelFlag(true);
        studentRepo.save(student);
    }

    private void checkExisted(String stuNumber) {
        UserAuth auth = authRepo.findFirstByUsername(stuNumber);
        if (auth != null) {
            throw new DataExistedException("");
        }
    }
}
