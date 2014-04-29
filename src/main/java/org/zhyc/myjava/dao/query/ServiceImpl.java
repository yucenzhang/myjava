package org.zhyc.myjava.dao.query;

import org.zhyc.myjava.dao.IBaseDao;

public class ServiceImpl {
	private IBaseDao<?> baseDao;

	/**
	 * @return the baseDao
	 */
	public IBaseDao<?> getBaseDao() {
		return baseDao;
	}

	/**
	 * @param baseDao
	 *            the baseDao to set
	 */
	public void setBaseDao(IBaseDao<?> baseDao) {
		this.baseDao = baseDao;
	}
}
