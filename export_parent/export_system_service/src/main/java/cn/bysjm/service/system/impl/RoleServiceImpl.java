package cn.bysjm.service.system.impl;

import cn.bysjm.dao.system.RoleDao;
import cn.bysjm.domain.system.Role;
import cn.bysjm.service.system.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll(String companyId) {
        return roleDao.findAll(companyId);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role toUpdate(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void delete(String id) {
        roleDao.delete(id);
    }

    @Override
    public PageInfo findPage(Integer page, Integer pageSize,String companyId) {
        PageHelper.startPage(page, pageSize);
        List<Role> list = roleDao.findAll(companyId);
        return new PageInfo(list,5);
    }

    @Override
    public Role findRoleIdsByRoleId(String roleid) {
        return roleDao.findRoleIdsByRoleId(roleid);
    }

    @Override
    public void updateRoleModule(String roleid, String moduleIds) {
        //先删除现有的权限
        roleDao.deleteRoleModuleByRoleId(roleid);
        //插入权限
        String[] split = moduleIds.split(",");
        for (String moduleId : split) {
            roleDao.updateRoleModule(roleid,moduleId);
        }
    }

}
