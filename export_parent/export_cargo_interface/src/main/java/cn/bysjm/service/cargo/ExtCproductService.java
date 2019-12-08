package cn.bysjm.service.cargo;



import cn.bysjm.domain.cargo.ExtCproduct;
import cn.bysjm.domain.cargo.ExtCproductExample;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**

 */
public interface ExtCproductService {
	/**
	 * 保存
	 */
	void save(ExtCproduct ExtCproduct);

	/**
	 * 更新
	 */
	void update(ExtCproduct ExtCproduct);

	/**
	 * 删除
	 */
	void delete(String id);

	/**
	 * 根据id查询
	 */
	ExtCproduct findById(String id);

	/**
	 * 分页查询
	 */
	PageInfo findAll(ExtCproductExample example, int page, int size);

	PageInfo findPage(String contractProductId, int page, int size);

    void baoCun(ExtCproduct extCproduct);

    void xiuGai(ExtCproduct extCproduct);

    void shanChu(String id);
}
