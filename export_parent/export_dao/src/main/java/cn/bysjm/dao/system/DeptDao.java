package cn.bysjm.dao.system;

import cn.bysjm.domain.system.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptDao {

    List<Dept> findAll(String companyId);

    Dept findById(String id);

    void save(Dept dept);


    Dept toUpdate(String id);

    void update(Dept dept);

    void delete(String id);
}
