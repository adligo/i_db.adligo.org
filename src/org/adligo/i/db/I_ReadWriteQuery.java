package org.adligo.i.db;

import javax.persistence.FlushModeType;

public interface I_ReadWriteQuery extends I_Query {

	public abstract int executeUpdate();

	public abstract FlushModeType getFlushMode();

	public abstract I_ParameterContainer setFlushMode(FlushModeType arg0);


}