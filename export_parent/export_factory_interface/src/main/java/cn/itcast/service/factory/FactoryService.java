package cn.itcast.service.factory;



import cn.bysjm.domain.cargo.Factory;
import cn.bysjm.domain.cargo.FactoryExample;
import com.github.pagehelper.PageInfo;

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
//	PageInfo<Factory> findPage(FactoryExample example, Integer page, Integer pageSize);
	//分页查询
	PageInfo findPage(FactoryExample example, int page, int size);
	//查询所有
	public List<Factory> findAll(FactoryExample example);
	public List<Factory> findByState(String ctype);

}
