package org.aisino.core.base;

import java.util.List;
import com.github.pagehelper.PageInfo;


public interface IBaseService<T extends BaseDomain> {
	
	public int insert(T t);
	
	public int delete(String id);

	public int update(T t);

	public T get(T obj);

	public List<T> findList(T t);

	public PageInfo<T> findPage(T t);
}
