package org.adligo.i.db;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.Parameter;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

public class ReadWriteTypedQuery<X> implements I_ReadWriteTypedQuery<X> {
	private TypedQuery<X> query;
	
    public ReadWriteTypedQuery(TypedQuery<X> p) {
		query = p;
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#executeUpdate()
	 */
	@Override
	public int executeUpdate() {
		return query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getFirstResult()
	 */
	@Override
	public int getFirstResult() {
		return query.getFirstResult();
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getFlushMode()
	 */
	@Override
	public FlushModeType getFlushMode() {
		return query.getFlushMode();
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getHints()
	 */
	@Override
	public Map<String, Object> getHints() {
		return query.getHints();
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getMaxResults()
	 */
	@Override
	public int getMaxResults() {
		return query.getMaxResults();
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getParameter(int, java.lang.Class)
	 */
	@Override
	public <T> Parameter<T> getParameter(int arg0, Class<T> arg1) {
		return query.getParameter(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getParameter(int)
	 */
	@Override
	public Parameter<?> getParameter(int arg0) {
		return query.getParameter(arg0);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getParameter(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> Parameter<T> getParameter(String arg0, Class<T> arg1) {
		return query.getParameter(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getParameter(java.lang.String)
	 */
	@Override
	public Parameter<?> getParameter(String arg0) {
		return query.getParameter(arg0);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getParameterValue(int)
	 */
	@Override
	public Object getParameterValue(int arg0) {
		return query.getParameterValue(arg0);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getParameterValue(javax.persistence.Parameter)
	 */
	@Override
	public <T> T getParameterValue(Parameter<T> arg0) {
		return query.getParameterValue(arg0);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getParameterValue(java.lang.String)
	 */
	@Override
	public Object getParameterValue(String arg0) {
		return query.getParameterValue(arg0);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getParameters()
	 */
	@Override
	public Set<Parameter<?>> getParameters() {
		return query.getParameters();
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getResultList()
	 */
	@Override
	public List<X> getResultList() {
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#getSingleResult()
	 */
	@Override
	public X getSingleResult() {
		return query.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#isBound(javax.persistence.Parameter)
	 */
	@Override
	public boolean isBound(Parameter<?> arg0) {
		return query.isBound(arg0);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setFirstResult(int)
	 */
	@Override
	public void setFirstResult(int arg0) {
		 query.setFirstResult(arg0);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setFlushMode(javax.persistence.FlushModeType)
	 */
	@Override
	public void setFlushMode(FlushModeType arg0) {
		 query.setFlushMode(arg0);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setHint(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setHint(String arg0, Object arg1) {
		 query.setHint(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setMaxResults(int)
	 */
	@Override
	public void setMaxResults(int arg0) {
		 query.setMaxResults(arg0);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setParameter(int, java.util.Calendar, javax.persistence.TemporalType)
	 */
	@Override
	public void setParameter(int arg0, Calendar arg1, TemporalType arg2) {
		 query.setParameter(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setParameter(int, java.util.Date, javax.persistence.TemporalType)
	 */
	@Override
	public void setParameter(int arg0, Date arg1, TemporalType arg2) {
		 query.setParameter(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setParameter(int, java.lang.Object)
	 */
	@Override
	public void setParameter(int arg0, Object arg1) {
		 query.setParameter(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setParameter(javax.persistence.Parameter, java.util.Calendar, javax.persistence.TemporalType)
	 */
	@Override
	public void setParameter(Parameter<Calendar> arg0, Calendar arg1,
			TemporalType arg2) {
		 query.setParameter(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setParameter(javax.persistence.Parameter, java.util.Date, javax.persistence.TemporalType)
	 */
	@Override
	public void setParameter(Parameter<Date> arg0, Date arg1,
			TemporalType arg2) {
		 query.setParameter(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setParameter(javax.persistence.Parameter, T)
	 */
	@Override
	public <T> void setParameter(Parameter<T> arg0, T arg1) {
		 query.setParameter(arg0, arg1);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setParameter(java.lang.String, java.util.Calendar, javax.persistence.TemporalType)
	 */
	@Override
	public void setParameter(String arg0, Calendar arg1,
			TemporalType arg2) {
		 query.setParameter(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setParameter(java.lang.String, java.util.Date, javax.persistence.TemporalType)
	 */
	@Override
	public void setParameter(String arg0, Date arg1, TemporalType arg2) {
		 query.setParameter(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_ReadWriteTypedQuery#setParameter(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setParameter(String arg0, Object arg1) {
		 query.setParameter(arg0, arg1);
	}
    
    
}
