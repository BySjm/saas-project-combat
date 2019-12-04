package cn.bysjm.dao.system;

import cn.bysjm.domain.system.SysLog;

import java.util.List;

public interface SysLogDao {

    List<SysLog> findAll(String companyId);

    void save(SysLog sysLog);
}
