package cn.bysjm.dao.factory;

import cn.bysjm.domain.cargo.Factory;
import cn.bysjm.domain.cargo.FactoryExample;

import java.util.List;

public interface FactoryDao {
	
	//删除
    int deleteByPrimaryKey(String id);

	//保存
    int insertSelective(Factory record);

	//条件查询
    List<Factory> selectByExample(FactoryExample example);

	//id查询
    Factory selectByPrimaryKey(String id);

	//更新
    int updateByPrimaryKeySelective(Factory record);
    //查询所有
//    List<Factory> findAll(FactoryExample example);

    List<Factory> findByState(String ctype);

}