package org.zhyc.myjava.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.zhyc.myjava.dao.query.QueryProperty;

public abstract class BaseHibernateDaoImpl<T> implements IBaseDao<T> {

	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(BaseHibernateDaoImpl.class);
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernataTemplate() {
		return this.hibernateTemplate;
	}

	@Override
	public boolean update(T t) throws Exception {
		try {
			hibernateTemplate.update(t);
		} catch (Exception e) {
			log.error("", e);
			throw new Exception(e);
		}
		return false;
	}

	@Override
	public boolean delete(T t) throws Exception {
		try {
			hibernateTemplate.delete(t);
		} catch (Exception e) {
			log.error("", e);
			throw new Exception(e);
		}
		return false;
	}

	@Override
	public boolean insert(T t) throws Exception {
		try {
			hibernateTemplate.save(t);
		} catch (Exception e) {
			log.error("", e);
			throw new Exception(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.zhyc.myjava.dao.IBaseDao#list(java.lang.Class)
	 */
	@Override
	public List<T> list(Class<T> c) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.zhyc.myjava.dao.IBaseDao#listPage(java.lang.Class, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> listPage(QueryProperty<T> property, int firstResult, int maxResults) throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(property.getEntityClass());
		Map<String, Object> mValues = property.getValues();
		Set<String> keys = mValues.keySet();
		for (String key : keys) {
			criteria.add(Restrictions.eq(key, mValues.get(key)));
		}
		return (List<T>) hibernateTemplate.findByCriteria(criteria, firstResult, maxResults);
	}
	
	
}
