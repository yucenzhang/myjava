package org.zhyc.myjava.dao;

import java.util.List;

public class BaseDao<T> extends BaseJdbcDaoImpl<T> implements IBaseDao<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public List<T> list(Class<T> c) throws Exception {
		return null;
	}

	@Override
	public List<T> listPage(Class<T> c, int firstResult, int MasResults)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
