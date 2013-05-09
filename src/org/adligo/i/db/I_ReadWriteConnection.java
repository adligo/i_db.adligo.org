package org.adligo.i.db;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaQuery;

/**
 * these methods are the same as there JPA EntityManager 
 * methods, it was added to narrow the scope of what client code 
 * would be allowed to do, since some databases are write only (aka replication).
 * 
 * @author scott
 *
 */
public interface I_ReadWriteConnection extends I_ReadOnlyConnection {

	
	public <T> I_ReadWriteTypedQuery<T> createNamedQueryForModify(String arg0, Class<T> arg1);
	
	public I_ReadWriteQuery createNamedQueryForModify(String arg0);
	
	public <T> I_ReadWriteTypedQuery<T> createQueryForModify(CriteriaQuery<T> arg0);

	public <T> I_ReadWriteTypedQuery<T> createQueryForModify(String arg0, Class<T> arg1);

	public I_ReadWriteQuery createQueryForModify(String arg0);
	
	public I_ReadWriteQuery createNativeQueryForModify(String arg0, Class<?> arg1);

	public I_ReadWriteQuery createNativeQueryForModify(String arg0, String arg1);

	public I_ReadWriteQuery createNativeQueryForModify(String arg0);

	public void persist(Object arg0);

	public void remove(Object arg0);
	
	public void flush();
	
	public FlushModeType getFlushMode();
	
	public <T> T merge(T arg0);
}