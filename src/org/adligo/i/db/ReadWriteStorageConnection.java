package org.adligo.i.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;

/**
 * this class is not threadsafe (due to the entity manager it self not being thread save,
 * which is due to hibernates original impl not being thread safe). 
 * This comment should be added to the JPA EntityMangers documentation.
 * 
 * @author scott
 *
 */
public class ReadWriteStorageConnection implements I_DbConnection {
	private static final Log log = LogFactory.getLog(ReadWriteStorageConnection.class);
	
	private EntityManager em;
	private ReadWriteConnection modifier;
	private ReadOnlyConnection obtainer;
	
	ReadWriteStorageConnection(EntityManager p) {
		em = p;
	}
	/**
	 * This will close the jpa (usally jdbc connection)
	 * or put it back in the pool (if it came from a jdbc pool)
	 * rolling back transactions that are still open
	 * 
	 */
	public void returnToPool() {
		EntityTransaction tran = em.getTransaction();
		if (tran.isActive()) {
			tran.rollback();
			Exception e = new Exception("All EntityTransactions should be commited or rolled back before " +
					"the entity manager instance is closed");
			log.error(e.getMessage(), e);
		}
		em.close();
	}

	public boolean isOpen() {
		return em.isOpen();
	}

	public EntityTransaction getTransaction() {
		return em.getTransaction();
	}
	/**
	 * @return the modifier
	 */
	public I_ReadWriteConnection getModifier() {
		if (modifier == null) {
			modifier = new ReadWriteConnection(em);
		}
		return modifier;
	}
	/**
	 * @return the obtainer
	 */
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
	
	
}
