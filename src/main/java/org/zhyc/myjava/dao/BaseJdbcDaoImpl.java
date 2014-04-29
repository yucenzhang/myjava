package org.zhyc.myjava.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseJdbcDaoImpl<T> implements IBaseDao<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(BaseJdbcDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	@Override
	public boolean update(T t) throws Exception {
		try {
			jdbcTemplate.update("");
		} catch (Exception e) {
			log.error("", e);
			throw new Exception(e);
		}
		return false;
	}

	@Override
	public boolean delete(T t) throws Exception {
		try {
			jdbcTemplate.update("");
		} catch (Exception e) {
			log.error("", e);
			throw new Exception(e);
		}
		return false;
	}

	@Override
	public boolean insert(T t) throws Exception {
		try {
			jdbcTemplate.update("");
		} catch (Exception e) {
			log.error("", e);
			throw new Exception(e);
		}
		return false;
	}

}
