package org.adligo.i.db;

import javax.persistence.EntityTransaction;

public interface I_DbConnection {

	/**
	 * @return the obtainer
	 */
	public I_ReadOnlyConnection getObtainer();
	
	public I_ReadWriteConnection getModifier();

	public EntityTransaction getTransaction();
	

	/**
	 * This will put it back in the pool 
	 */
	public abstract void returnToPool();

	/**
	 * if false this connection can write to storage
	 * @return
	 */
	public abstract boolean isReadOnly();
}