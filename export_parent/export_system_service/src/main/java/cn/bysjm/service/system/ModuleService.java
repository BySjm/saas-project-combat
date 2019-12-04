package cn.bysjm.service.system;

import cn.bysjm.domain.system.Module;
import cn.bysjm.domain.system.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ModuleService {

    List<Module> findAll();

    void save(Module module);

    Module toUpdate(String id);

    void update(Module module);

    void delete(String id);

    PageInfo findPage(Integer page, Integer pageSize);

    List<String> findModulesByRoleId(String roleid);

    List<Module> findModuleListByUser(User user);
}
