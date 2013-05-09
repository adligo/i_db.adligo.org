package org.adligo.i.db;

import java.util.List;

import javax.persistence.Parameter;

public interface I_TypedQuery<X> extends I_ParameterContainer {

	public abstract List<X> getResultList();

	public abstract X getSingleResult();

	public abstract boolean isBound(Parameter<?> arg0);
}