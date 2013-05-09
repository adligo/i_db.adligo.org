package org.adligo.i.db;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

/**
 * these methods are the same as there JPA EntityManager 
 * methods, it was added to narrow the scope of what client code 
 * would be allowed to do, since some databases are write only (aka replication)
 * 
 * @author scott
 *
 */
public interface I_ReadOnlyConnection {
	public void clear();
	
	public boolean contains(Object arg0);

	public void detach(Object p);
	
	public <T> I_TypedQuery<T> createNamedQuery(String arg0, Class<T> arg1);
	
	public I_ParameterContainer createNamedQuery(String arg0);
	
	public <T> I_TypedQuery<T> createQuery(CriteriaQuery<T> arg0);

	public <T> I_TypedQuery<T> createQuery(String arg0, Class<T> arg1);

	public I_ParameterContainer createQuery(String arg0);
	
	public I_Query createNativeQuery(String arg0, Class<?> arg1);

	public I_ParameterContainer createNativeQuery(String arg0, String arg1);

	public I_Query createNativeQuery(String arg0);
	
	public <T> T find(Class<T> arg0, Object arg1);
	
	public CriteriaBuilder getCriteriaBuilder();
	
	public Metamodel getMetamodel();

	public <T> T getReference(Class<T> arg0, Object arg1);
	
	public void refresh(Object arg0, Map<String, Object> arg1);
	
	public void refresh(Object arg0);
}