package org.adligo.i.db;

import javax.persistence.EntityTransaction;

/**
 * it is intended that you extend this class
 * to pass values to storage (for updates, deletes) exc
 * ie see ModifyEntitiesRequest, ModifySingleEntityRequest
 * 
 * This is the basic change something request
 * 
 * @author scott
 *
 */
public class ModifyRequest {
	private I_ReadWriteConnection readWriteConnection;
	private EntityTransaction transaction;
	private String connectionName;
	
	/**
	 * @return the readWriteConnection
	 */
	public final I_ReadWriteConnection getReadWriteConnection() {
		return readWriteConnection;
	}
	/**
	 * @param readWriteConnection the readWriteConnection to set
	 */
	final void setReadWriteConnection(I_ReadWriteConnection p) {
		readWriteConnection = p;
	}
	/**
	 * kept package private to avoid impl error which causes connection leaks
	 * @return the transaction
	 */
	final EntityTransaction getTransaction() {
		return transaction;
	}
	/**
	 * kept package private to avoid impl error which causes connection leaks
	 * @param transaction the transaction to set
	 */
	final void setTransaction(EntityTransaction transaction) {
		this.transaction = transaction;
	}
	
	public void passValues(ModifyRequest other) {
		readWriteConnection = other.getReadWriteConnection();
		transaction = other.getTransaction();
		connectionName = other.getConnectionName();
	}
	public String getConnectionName() {
		return connectionName;
	}
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
}
