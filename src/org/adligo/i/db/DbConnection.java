package org.adligo.i.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * this class is not threadsafe (due to the entity manager it self not being thread save,
 * which is due to hibernates original impl not being thread safe). 
 * This comment should be added to the JPA EntityMangers documentation.
 * 
 * @author scott
 *
 */
public class DbConnection implements I_DbConnection {
	
	private EntityManager em;
	private ReadOnlyConnection obtainer;
	
	DbConnection(EntityManager p) {
		em = p;
	}
	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_StorageConnection#closeOrPutbackInPool()
	 */
	@Override
	public void returnToPool() {
		em.close();
	}



	/* (non-Javadoc)
	 * @see org.adligo.i.storage.I_StorageConnection#getObtainer()
	 */
	@Override
	public I_ReadOnlyConnection getObtainer() {
		if (obtainer == null) {
			obtainer = new ReadOnlyConnection(em);
		}
		return obtainer;
	}
	
	@Override
	public boolean isReadOnly() {
		return true;
	}
	
	@Override
	public I_ReadWriteConnection getModifier() {
		throw new RuntimeException("method not implemented see results from isReadOnly()");
	}
	
	@Override
	public EntityTransaction getTransaction() {
		throw new RuntimeException("method not implemented see results from isReadOnly()");
	}
	
}
