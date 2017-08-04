package org.aisino.core.base;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 基础服务
 *
 * @param <T>
 * @param <D>
 */
public abstract class BaseService<T extends BaseDomain, D extends BaseDao<T>> {

	@Autowired
	protected D dao;

	public int insert(T t) {
		int  res = dao.insert(t);
		return res;
	}

	public int delete(String id) {
		int  res = dao.delete(id);
		return res;
	}

	public int update(T t) {
		int res = dao.update(t);
		return res;
	}

	public T get(T obj) {
		return dao.get(obj);
	}

	public List<T> findList(T t) {
		return dao.findList(t);
	}

	public PageInfo<T> findPage(T t) {
		PageHelper.startPage(t);
		List<T> list = dao.findList(t);
		PageInfo<T> page = new PageInfo<T>(list);
		return page;
	}

}