package org.zhyc.myjava.dao.query;

import org.zhyc.myjava.dao.IBaseDao;

public interface ICommand<T> {
	T execute(IBaseDao<T> baseDao);
}
