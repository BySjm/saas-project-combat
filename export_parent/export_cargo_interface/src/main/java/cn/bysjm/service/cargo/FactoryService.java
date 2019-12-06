package cn.bysjm.service.cargo;



import cn.bysjm.domain.cargo.Factory;
import cn.bysjm.domain.cargo.FactoryExample;

import java.util.List;
import java.util.Map;

/**
 */
public interface FactoryService {

	/**
	 * 保存
	 */
	void save(Factory factory);

	/**
	 * 更新
	 */
	void update(Factory factory);

	/**
	 * 删除
	 */
	void delete(String id);

	/**
	 * 根据id查询
	 */
	Factory findById(String id);

	//查询所有
	List<Factory> findAll(FactoryExample example);
}
