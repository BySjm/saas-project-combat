package cn.bysjm.service.system;

import cn.bysjm.domain.system.User;
import cn.bysjm.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> notifyList(String date);

    List<User> findAll(String companyId);

    PageInfo findPage(Integer page, Integer pageSize, String companyId);

    void save(User user);

    User toUpdate(String id);

    void update(User user);

    void delete(String id);

    List<String> findRoleIdsByUserId(String id);

    void changeRole(String userid, String[] roleIds);

    User findByEmail(String email);
}
