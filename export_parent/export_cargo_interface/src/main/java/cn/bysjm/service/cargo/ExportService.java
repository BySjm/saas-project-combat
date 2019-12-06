package cn.bysjm.service.cargo;

import cn.bysjm.domain.Vo.ExportResult;
import cn.bysjm.domain.cargo.Export;
import cn.bysjm.domain.cargo.ExportExample;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ExportService {

    Export findById(String id);

    void save(Export export);

    void update(Export export);

    void delete(String id);

	PageInfo findAll(ExportExample example, int page, int size);

    void updateE(ExportResult exportResult);

    PageInfo findByState(Integer page,Integer size,Integer state,String companyId);
}
