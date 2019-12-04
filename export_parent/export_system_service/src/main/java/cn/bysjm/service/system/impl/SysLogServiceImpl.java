package cn.bysjm.service.system.impl;

import cn.bysjm.dao.system.SysLogDao;
import cn.bysjm.domain.system.SysLog;
import cn.bysjm.service.system.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public List<SysLog> findAll(String companyId) {
        return sysLogDao.findAll(companyId);
    }

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public PageInfo findPage(Integer page, Integer pageSize, String companyId) {
        PageHelper.startPage(page,pageSize);
        List<SysLog> list = sysLogDao.findAll(companyId);
        return new PageInfo(list,5);
    }
}
