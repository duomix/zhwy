package org.aisino.core.base;


import java.util.List;

/**
 * 基础dao
 *
 * @param <T>
 */
public interface BaseDao<T extends BaseDomain> {

	public int insert(T obj);
	
	public int delete(String id);
	
	public int update(T obj);

	public T get(T obj);

	public List<T> findList(T obj);
}
