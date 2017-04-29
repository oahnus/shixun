package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.UserAuthDto;
import top.oahnus.dto.UserDto;
import top.oahnus.entity.*;
import top.oahnus.enums.AuthType;
import top.oahnus.exception.LoginFailedException;
import top.oahnus.mapper.*;
import top.oahnus.util.MD5Util;

import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * 0:23
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserMenuMapper userMenuMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public UserAuth getUserAuth(UserAuthDto userAuthDto) {
        UserAuth userAuth = userAuthMapper.login(
                userAuthDto.getUsername(),
                MD5Util.getMD5(userAuthDto.getPassword()),
                AuthType.valueOf(userAuthDto.getAuthType()).ordinal());
        if(userAuth == null){
            throw new LoginFailedException("用户名或密码错误");
        } else {
            int type = userAuth.getType().ordinal();
            switch (type) {
                case 0:
                    break;
                case 1:
                    Company company = companyMapper.selectCompanyByName(userAuthDto.getUsername());
                    userAuth.setUser(company);
                    break;
                case 2:
                    Teacher teacher = teacherMapper.selectTeacherByWorkerId(userAuthDto.getUsername());
                    userAuth.setUser(teacher);
                    break;
                case 3:
                    Student student = studentMapper.selectStudentByStudentNum(userAuthDto.getUsername());
                    userAuth.setUser(student);
                    break;
            }
            List<UserMenu> userMenus = userMenuMapper.selectRootUserMenuByAuthType(userAuth.getType().ordinal());
            userMenus.forEach(userMenu -> userMenu.setChild(userMenuMapper.selectChildUserMenuByParentId(userMenu.getId())));
            userAuth.setUserMenus(userMenus);
            return userAuth;
        }
    }

    @Override
    public Integer resetPassword(UserDto userDto) {
        Integer count = userAuthMapper.resetPassword(userDto.getUsername(),
                MD5Util.getMD5(userDto.getOldPassword()),
                MD5Util.getMD5(userDto.getNewPassword()),
                userDto.getAuthType().ordinal());
        if (count <= 0) {
            throw new LoginFailedException("用户名或密码错误");
        } else {
            return count;
        }
    }
}
