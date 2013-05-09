package org.adligo.i.db;

import java.util.List;
import java.util.Map;

import javax.persistence.Parameter;

/**
 * these methods are all from the JPA I_Query class,
 * simply separated here to  remove update methods for
 * replicated databases
 * 
 * @author scott
 *
 */
public interface I_Query extends I_ParameterContainer {

	public abstract int getFirstResult();

	public abstract Map<String, Object> getHints();

	public abstract int getMaxResults();

	public abstract List getResultList();

	public abstract Object getSingleResult();


}