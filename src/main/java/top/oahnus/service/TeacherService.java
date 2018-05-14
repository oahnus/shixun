package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.domain.Teacher;
import top.oahnus.domain.UserAuth;
import top.oahnus.exception.DataExistedException;
import top.oahnus.exception.NotFoundException;
import top.oahnus.repository.TeacherRepo;
import top.oahnus.repository.UserAuthRepo;

import java.util.Arrays;
import java.util.Date;
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
    private UserAuthRepo authRepo;

    @Transactional
    public void save(Teacher teacher) {
        String workerId = teacher.getWorkerId();

        checkExisted(workerId);

        UserAuth auth;
        auth = UserAuth.buildByWorkerId(workerId);
        auth = authRepo.save(auth);
        teacher.setAuthId(auth.getId());
        teacherRepo.save(teacher);
    }

    public void update(Teacher teacher) {
        String workerId = teacher.getWorkerId();
        checkExisted(workerId);

        teacher.setUpdateTime(new Date());

        teacherRepo.save(teacher);
    }

    public void delete(Long teacherId) {
        Teacher teacher = teacherRepo.findOne(teacherId);
        teacher.setDelFlag(true);
        teacherRepo.save(teacher);
    }

    private void checkExisted(String workerId) {
        UserAuth auth = authRepo.findFirstByUsername(workerId);
        if (auth != null) {
            throw new DataExistedException("");
        }
    }
}
