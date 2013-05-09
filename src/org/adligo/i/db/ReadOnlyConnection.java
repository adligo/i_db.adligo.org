package org.adligo.i.db;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

public class ReadOnlyConnection implements I_ReadOnlyConnection {
	public static final String ENTITY_OBTAINER_REQUIRES_A_ENTITY_MANAGER = "EntityObtainer requires a EntityManager";
	private EntityManager em;
	
	public ReadOnlyConnection(EntityManager p) {
		if (p == null) {
			throw new NullPointerException(ENTITY_OBTAINER_REQUIRES_A_ENTITY_MANAGER);
		}
		em = p;
	}

	/**
	 * 
	 * @see javax.persistence.EntityManager#clear()
	 */
	public void clear() {
		em.clear();
	}

	/**
	 * @param arg0
	 * @return
	 * @see javax.persistence.EntityManager#contains(java.lang.Object)
	 */
	public boolean contains(Object arg0) {
		return em.contains(arg0);
	}

	/**
	 * @param <T>
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see javax.persistence.EntityManager#createNamedQuery(java.lang.String, java.lang.Class)
	 */
	public <T> I_TypedQuery<T> createNamedQuery(String arg0, Class<T> arg1) {
		return new ReadOnlyTypedQuery<T>(em.createNamedQuery(arg0, arg1));
	}

	/**
	 * @param arg0
	 * @return
	 * @see javax.persistence.EntityManager#createNamedQuery(java.lang.String)
	 */
	public I_Query createNamedQuery(String arg0) {
		return new ReadOnlyQuery(em.createNamedQuery(arg0));
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see javax.persistence.EntityManager#createNativeQuery(java.lang.String, java.lang.Class)
	 */
	public  I_Query createNativeQuery(String arg0, Class<?> arg1) {
		return new ReadOnlyQuery(em.createNativeQuery(arg0, arg1));
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see javax.persistence.EntityManager#createNativeQuery(java.lang.String, java.lang.String)
	 */
	public  I_ParameterContainer createNativeQuery(String arg0, String arg1) {
		return new ReadOnlyQuery(em.createNativeQuery(arg0, arg1));
	}

	/**
	 * @param arg0
	 * @return
	 * @see javax.persistence.EntityManager#createNativeQuery(java.lang.String)
	 */
	public I_Query createNativeQuery(String arg0) {
		return new ReadOnlyQuery(em.createNativeQuery(arg0));
	}

	/**
	 * @param <T>
	 * @param arg0
	 * @return
	 * @see javax.persistence.EntityManager#createQuery(javax.persistence.criteria.CriteriaQuery)
	 */
	public <T> I_TypedQuery<T> createQuery(CriteriaQuery<T> arg0) {
		return new ReadOnlyTypedQuery<T>(em.createQuery(arg0));
	}

	/**
	 * @param <T>
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see javax.persistence.EntityManager#createQuery(java.lang.String, java.lang.Class)
	 */
	public <T> I_TypedQuery<T> createQuery(String arg0, Class<T> arg1) {
		return new ReadOnlyTypedQuery<T>(em.createQuery(arg0, arg1));
	}

	/**
	 * @param arg0
	 * @return
	 * @see javax.persistence.EntityManager#createQuery(java.lang.String)
	 */
	public I_ParameterContainer createQuery(String arg0) {
		return new ReadOnlyQuery(em.createQuery(arg0));
	}

	/**
	 * @param arg0
	 * @see javax.persistence.EntityManager#detach(java.lang.Object)
	 */
	public void detach(Object arg0) {
		em.detach(arg0);
	}

	
	/**
	 * @param <T>
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @see javax.persistence.EntityManager#find(java.lang.Class, java.lang.Object, java.util.Map)
	 */
	public <T> T find(Class<T> arg0, Object arg1, Map<String, Object> arg2) {
		return em.find(arg0, arg1, arg2);
	}

	/**
	 * @param <T>
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see javax.persistence.EntityManager#find(java.lang.Class, java.lang.Object)
	 */
	public <T> T find(Class<T> arg0, Object arg1) {
		return em.find(arg0, arg1);
	}

	/**
	 * @return
	 * @see javax.persistence.EntityManager#getCriteriaBuilder()
	 */
	public CriteriaBuilder getCriteriaBuilder() {
		return em.getCriteriaBuilder();
	}

	/**
	 * @return
	 * @see javax.persistence.EntityManager#getMetamodel()
	 */
	public Metamodel getMetamodel() {
		return em.getMetamodel();
	}


	/**
	 * @param <T>
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see javax.persistence.EntityManager#getReference(java.lang.Class, java.lang.Object)
	 */
	public <T> T getReference(Class<T> arg0, Object arg1) {
		return em.getReference(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @see javax.persistence.EntityManager#refresh(java.lang.Object, java.util.Map)
	 */
	public void refresh(Object arg0, Map<String, Object> arg1) {
		em.refresh(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @see javax.persistence.EntityManager#refresh(java.lang.Object)
	 */
	public void refresh(Object arg0) {
		em.refresh(arg0);
	}
	
	public void cleanup() {
		em = null;
	}
}
