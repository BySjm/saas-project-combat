package cn.bysjm.dao.system;

import cn.bysjm.domain.system.Module;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ModuleDao {
    List<Module> findAll();

    void save(Module module);

    Module findById(String id);

    void update(Module module);

    void delete(String id);

    List<String> findModulesByRoleId(String roleid);

    List<Module> findByBelong(int belong);

    List<Module> findModuleListByUserId(String userId);
}
