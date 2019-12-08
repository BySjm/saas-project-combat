package cn.bysjm.dao.system;

import cn.bysjm.domain.system.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    List<User> findAll(String companyId);

    User findById(String id);

    void save(User user);

    void update(User user);

    void delete(String id);

    List<String> findRoleIdsByUserId(String id);

    void deleteRoleById(String id);

    void updateRole(@Param("userid") String userid, @Param("roleId") String roleId);

    User findByEmail(String email);

    List<User> notifyList(String date);

    User findByUnionid(String unionid);

    void updateByEmail(@Param("email") String email,@Param("unionid") String unionid);
}
