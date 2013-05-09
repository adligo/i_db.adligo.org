package org.adligo.i.db;

/**
 * it is intended that you extend this class
 * to pass values to your entity request (see LongSelectRequest)
 * 
 * @author scott
 *
 */
public class SelectRequest {
	private I_ReadOnlyConnection readOnlyConnection;
	private String connectionName;
	
	public SelectRequest() {}
	
	public SelectRequest(ModifyRequest p) {
		readOnlyConnection = p.getReadWriteConnection();
	}
	/**
	 * @return the readOnlyConnection
	 */
	public final I_ReadOnlyConnection getReadOnlyConnection() {
		return readOnlyConnection;
	}
	/**
	 * @param readOnlyConnection the readOnlyConnection to set
	 */
	final void setReadOnlyConnection(I_ReadOnlyConnection p) {
		readOnlyConnection = p;
	}
	
	public void passValues(SelectRequest other) {
		readOnlyConnection = other.readOnlyConnection;
	}
	
	/**
	 * allows a select from a read write connection
	 * @param other
	 */
	public void passValues(ModifyRequest other) {
		readOnlyConnection = other.getReadWriteConnection();
		connectionName = other.getConnectionName();
	}
	public String getConnectionName() {
		return connectionName;
	}
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
}
