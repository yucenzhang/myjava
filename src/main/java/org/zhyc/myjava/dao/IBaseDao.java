package org.zhyc.myjava.dao;

import java.io.Serializable;
import java.util.List;

import org.zhyc.myjava.dao.query.QueryProperty;

public interface IBaseDao<T> extends Serializable {
	
	boolean update(T t) throws Exception;

	boolean delete(T t) throws Exception;

	boolean insert(T t) throws Exception;

	List<T> list(Class<T> c) throws Exception;

	List<T> listPage(Class<T> c, int firstResult, int MasResults) throws Exception;

	List<T> listPage(QueryProperty<T> properties, int firstResult, int maxResults) throws Exception;
}
