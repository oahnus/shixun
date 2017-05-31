package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.payload.UserAuthPayload;
import top.oahnus.entity.*;
import top.oahnus.exception.LoginFailedException;
import top.oahnus.payload.UserPayload;
import top.oahnus.repository.*;
import top.oahnus.util.MD5Util;

import java.util.List;


/**
 * Created by oahnus on 2017/2/26.
 * 0:23
 */
@Service
public class UserAuthService {
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private UserMenuRepository userMenuRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    public UserAuth getUserAuth(UserAuthPayload userAuthPayload) {
        System.out.println(MD5Util.getMD5(userAuthPayload.getPassword()));
        UserAuth userAuth = userAuthRepository.findByUsernameAndPasswordAndType(
                userAuthPayload.getUsername(),
                MD5Util.getMD5(userAuthPayload.getPassword()),
                userAuthPayload.getAuthType());
        if(userAuth == null){
            throw new LoginFailedException("用户名或密码错误");
        } else {
            int type = userAuth.getType().ordinal();
            switch (type) {
                case 0:
                    break;
                case 1:
                    Company company = companyRepository.findFirstByName(userAuthPayload.getUsername());
                    userAuth.setUser(company);
                    break;
                case 2:
                    Teacher teacher = teacherRepository.findFirstByWorkerId(userAuthPayload.getUsername());
                    userAuth.setUser(teacher);
                    break;
                case 3:
                    Student student = studentRepository.findFirstByStudentNum(userAuthPayload.getUsername());
                    userAuth.setUser(student);
                    break;
            }
            List<UserMenu> userMenus = userMenuRepository.findByAuthTypeAndParentId(userAuth.getType(), 0L);
            userMenus.forEach(userMenu -> userMenu.setChild(userMenuRepository.findByParentId(userMenu.getId())));
            userAuth.setUserMenus(userMenus);
            return userAuth;
        }
    }

    @Transactional
    public Integer resetPassword(UserPayload userPayload) {
        Integer count = userAuthRepository.resetPassword(
                userPayload.getUsername(),
                userPayload.getOldPassword(),
                userPayload.getNewPassword(),
                userPayload.getAuthType()
        );
        if (count == 0) {
            throw new LoginFailedException("密码错误");
        }
        return count;
    }
}
