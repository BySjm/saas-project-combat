package cn.bysjm.service.system.impl;

import cn.bysjm.dao.system.ModuleDao;
import cn.bysjm.domain.system.Module;
import cn.bysjm.domain.system.User;
import cn.bysjm.service.system.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleDao moduleDao;

    @Override
    public List<Module> findAll() {
        return moduleDao.findAll();
    }

    @Override
    public void save(Module module) {
        moduleDao.save(module);
    }

    @Override
    public Module toUpdate(String id) {
        return moduleDao.findById(id);
    }

    @Override
    public void update(Module module) {
        moduleDao.update(module);
    }

    @Override
    public void delete(String id) {
        moduleDao.delete(id);
    }

    @Override
    public PageInfo findPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Module> list = moduleDao.findAll();
        return new PageInfo(list, 5);
    }

    @Override
    public List<String> findModulesByRoleId(String roleid) {
        return moduleDao.findModulesByRoleId(roleid);
    }

    @Override
    public List<Module> findModuleListByUser(User user) {
        Integer degree = user.getDegree();
        List<Module> moduleList = null;
        if (degree == 0){
            moduleList = moduleDao.findByBelong(0);
        }else if (degree == 1){
            moduleList = moduleDao.findByBelong(1);
        }else {
            moduleList = moduleDao.findModuleListByUserId(user.getId());
        }
        return moduleList;
    }
}
