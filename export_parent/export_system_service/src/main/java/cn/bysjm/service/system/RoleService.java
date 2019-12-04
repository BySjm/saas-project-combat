package cn.bysjm.service.system;

import cn.bysjm.domain.system.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    List<Role> findAll(String companyId);

    void save(Role role);

    Role toUpdate(String id);

    void update(Role role);

    void delete(String id);

    PageInfo findPage(Integer page, Integer pageSize, String companyId);

    Role findRoleIdsByRoleId(String roleid);

    void updateRoleModule(String roleid, String moduleIds);
}
