package cn.bysjm.service.system;

import cn.bysjm.domain.system.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysLogService {

    List<SysLog> findAll(String companyId);

    void save(SysLog sysLog);

    PageInfo findPage(Integer page, Integer pageSize, String companyId);
}
