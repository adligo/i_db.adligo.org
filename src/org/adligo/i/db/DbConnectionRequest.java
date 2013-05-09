package org.adligo.i.db;

public class DbConnectionRequest {
	/**
	 * the logical name of the storage connection (accouting_db) or something
	 */
	private String name;
	/**
	 * if you need a writeable StorageConnection
	 * (that can save to the storage)
	 */
	private boolean writeable;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the writeable
	 */
	public boolean isWriteable() {
		return writeable;
	}
	/**
	 * @param writeable the writeable to set
	 */
	public void setWriteable(boolean writeable) {
		this.writeable = writeable;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StorageConnectionRequest [name=" + name + ", writeable="
				+ writeable + "]";
	}
	
}
