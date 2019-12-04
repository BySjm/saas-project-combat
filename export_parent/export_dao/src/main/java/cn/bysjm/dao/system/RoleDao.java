package cn.bysjm.dao.system;

import cn.bysjm.domain.system.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    List<Role> findAll(String companyId);

    Role findById(String id);

    void save(Role role);

    void update(Role role);

    void delete(String id);

    Role findRoleIdsByRoleId(String roleid);

    void updateRoleModule(@Param("roleid") String roleid,@Param("moduleId") String moduleId);

    void deleteRoleModuleByRoleId(String roleid);
}
