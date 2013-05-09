package org.adligo.i.db;

import javax.persistence.EntityManagerFactory;

/**
 * note if there were 2 or more read only databases (which will happen in large deployments)
 * the connectionName of the ModifyRequest and or SelectRequest would be used
 * in conjunction with the StorageWrapper methods that allow for selection of a database
 * allowing the client code to determine how to delegate read requests (round robin, weighted exc)
 * 
 * @author scott
 *
 */
public class EntityManagerFactoryContainer {
	private String name;
	private EntityManagerFactory readOnlyEntityManagerFactory;
	private EntityManagerFactory readWriteEntityManagerFactory;
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
	 * @return the readOnlyEntityManagerFactory
	 */
	public EntityManagerFactory getReadOnlyEntityManagerFactory() {
		return readOnlyEntityManagerFactory;
	}
	/**
	 * @param readOnlyEntityManagerFactory the readOnlyEntityManagerFactory to set
	 */
	public void setReadOnlyEntityManagerFactory(
			EntityManagerFactory p) {
		readOnlyEntityManagerFactory = p;
	}
	/**
	 * @return the readWriteEntityManagerFactory
	 */
	public EntityManagerFactory getReadWriteEntityManagerFactory() {
		return readWriteEntityManagerFactory;
	}
	/**
	 * @param readWriteEntityManagerFactory the readWriteEntityManagerFactory to set
	 */
	public void setReadWriteEntityManagerFactory(
			EntityManagerFactory p) {
		readWriteEntityManagerFactory = p;
	}
	
	public void validate() {
		if (name == null) {
			throw new NullPointerException("EntityManagerFactoryContainer requires a name");
		}
		if (readWriteEntityManagerFactory == null) {
			throw new NullPointerException("EntityManagerFactoryContainer requires a readWriteEntityManagerFactory");
		}
		if (readOnlyEntityManagerFactory == null) {
			throw new NullPointerException("EntityManagerFactoryContainer requires a readOnlyEntityManagerFactory");
		}
	}
}
