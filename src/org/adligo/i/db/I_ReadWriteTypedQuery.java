package org.adligo.i.db;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;


public interface I_ReadWriteTypedQuery<X> extends I_TypedQuery<X> {

	public abstract int executeUpdate();
	
	public abstract FlushModeType getFlushMode();
	
	public void setFlushMode(FlushModeType arg0);

}