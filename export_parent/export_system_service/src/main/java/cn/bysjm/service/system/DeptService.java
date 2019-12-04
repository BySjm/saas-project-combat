package cn.bysjm.service.system;

import cn.bysjm.domain.system.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DeptService {

    List<Dept> findAll(String companyId);

    void save(Dept dept);

    Dept toUpdate(String id);

    void update(Dept dept);

    void delete(String id);

    PageInfo findPage(Integer page, Integer pageSize,String companyId);
    
}
