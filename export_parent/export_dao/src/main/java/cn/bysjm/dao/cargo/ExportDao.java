package cn.bysjm.dao.cargo;

import cn.bysjm.domain.cargo.Export;
import cn.bysjm.domain.cargo.ExportExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExportDao {
    /**
     * 根据id删除
     */
    int deleteByPrimaryKey(String id);


    /**
     * 保存
     */
    int insertSelective(Export record);

    /**
     * 条件查询
     */
    List<Export> selectByExample(ExportExample example);

    /**
     * 根据id查询
     */
    Export selectByPrimaryKey(String id);

    /**
     * 更新
     */
    int updateByPrimaryKeySelective(Export record);

    List<Export> findByState(@Param("state") Integer state, @Param("companyId") String companyId);
}