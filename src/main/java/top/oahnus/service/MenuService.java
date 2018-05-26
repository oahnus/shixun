package top.oahnus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import top.oahnus.domain.UserMenu;
import top.oahnus.enums.RoleEnum;
import top.oahnus.repository.UserMenuRepo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by oahnus on 2018/5/24
 * 15:02.
 */
@Service
@Slf4j
public class MenuService {
    @Autowired
    private UserMenuRepo userMenuRepo;

    /**
     * 菜单最大深度为2
     * 顶级菜单parentid = 0
     * @param role role enum
     * @return 层级结构的 menu list
     */
    @Cacheable(value = "day")
    public List<UserMenu> getRoleMenuList(RoleEnum role) {
        log.debug("未命中Cache");
        List<UserMenu> list = userMenuRepo.findByRoleIdAndDelFlagFalse(role.getCode());
        Map<Long, List<UserMenu>> group = list.stream().collect(Collectors.groupingBy(UserMenu::getParentId));

        List<UserMenu> rootMenuList = group.get(0L);
        return rootMenuList.stream()
                .peek(root -> root.setChildren(group.get(root.getId())))
                .collect(Collectors.toList());
    }
}
