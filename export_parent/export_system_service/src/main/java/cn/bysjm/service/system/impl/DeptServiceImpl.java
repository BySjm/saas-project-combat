package cn.bysjm.service.system.impl;

import cn.bysjm.dao.system.DeptDao;
import cn.bysjm.domain.system.Dept;
import cn.bysjm.service.system.DeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> findAll(String companyId) {
        return deptDao.findAll(companyId);
    }

    @Override
    public void save(Dept dept) {
        deptDao.save(dept);
    }

    @Override
    public Dept toUpdate(String id) {
        return deptDao.toUpdate(id);
    }

    @Override
    public void update(Dept dept) {
        deptDao.update(dept);
    }

    @Override
    public void delete(String id) {
        deptDao.delete(id);
    }

    @Override
    public PageInfo findPage(Integer page, Integer pageSize,String companyId) {
        PageHelper.startPage(page, pageSize);
        List<Dept> list = deptDao.findAll(companyId);
        return new PageInfo(list,5);
    }
}
