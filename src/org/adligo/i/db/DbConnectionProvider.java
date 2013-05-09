package org.adligo.i.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.adligo.i.adi.client.InvocationException;
import org.adligo.i.adig.client.BaseGInvoker;
import org.adligo.i.adig.client.I_GCheckedInvoker;
import org.adligo.i.log.client.Log;
import org.adligo.i.log.client.LogFactory;

/**
 * a api that allows for getting a connection
 * in a way that is compatible with Replicated Databases (Master Slave)
 * 
 * @author scott
 *
 */
public class DbConnectionProvider extends BaseGInvoker implements I_GCheckedInvoker<DbConnectionRequest, I_DbConnection> {
	public static final String STORAGE_CONNECTION_PROVIDER_REQUIRES_VALID_CONTAINERS = "StorageConnectionProvider requires valid containers";

	public static final String STORAGE_CONNECTION_PROVIDER_DOES_NOT_ALLOW_NULL_CONNECTION_NAMES = "StorageConnectionProvider does NOT allow null connection names.";

	public static final String STORAGE_CONNECTION_PROVIDER_REQUIRES_A_ENTITY_MANAGER_FACTORY_CONTAINER = "StorageConnectionProvider requires a EntityManagerFactoryContainer";

	public static final String POSSIBLE_VALUES_ARE = " possible values are ";

	public static final String NO_CONNECTION_FOUND_WITH_NAME = "No connection found with name ";

	public static final String STORAGE_CONNECTION_PROVIDER_REQUIRES_A_STORAGE_CONNECTION_REQUEST = "StorageConnectionProvider requires a StorageConnectionRequest";

	private static final Log log = LogFactory.getLog(DbConnectionProvider.class);
	
	private Map<String, EntityManagerFactoryContainer> entityManagers =
		new HashMap<String, EntityManagerFactoryContainer>();

	public DbConnectionProvider(EntityManagerFactoryContainer container) {
		super(DbConnectionRequest.class, I_DbConnection.class);
		if (container == null) {
			throw new NullPointerException(STORAGE_CONNECTION_PROVIDER_REQUIRES_A_ENTITY_MANAGER_FACTORY_CONTAINER);
		}
		addContainer(container);
	}

	public void addContainer(EntityManagerFactoryContainer container) {
		container.validate();
		String name = container.getName();
		entityManagers.put(name, container);
	}
	
	public DbConnectionProvider(Collection<EntityManagerFactoryContainer> containers) {
		super(DbConnectionRequest.class, I_DbConnection.class);
		if (containers == null) {
			throw new NullPointerException(STORAGE_CONNECTION_PROVIDER_REQUIRES_VALID_CONTAINERS);
		}
		for (EntityManagerFactoryContainer emfc: containers) {
			addContainer(emfc);
		}
	}
	
	@Override
	public I_DbConnection invoke(DbConnectionRequest valueObject) throws InvocationException {
		if (valueObject == null) {
			throw new NullPointerException(STORAGE_CONNECTION_PROVIDER_REQUIRES_A_STORAGE_CONNECTION_REQUEST);
		}
		try {
			String name = valueObject.getName();
			EntityManagerFactoryContainer container = entityManagers.get(name);
			if (container == null) {
				throw new IllegalStateException(NO_CONNECTION_FOUND_WITH_NAME + name + 
						POSSIBLE_VALUES_ARE +
						entityManagers.keySet());
			}
			
			if (valueObject.isWriteable()) {
				EntityManagerFactory emf = container.getReadWriteEntityManagerFactory();
				EntityManager em = emf.createEntityManager();
				return new ReadWriteStorageConnection(em);
			}
			EntityManagerFactory emf = container.getReadWriteEntityManagerFactory();
			EntityManager em = emf.createEntityManager();
			return new DbConnection(em);
		} catch (Exception x) {
			log.error(x.getMessage(), x);
			ExceptionThrower.rethrow(x);
		}
		return null;
	}
	
	/**
	 *  A simple method for creating a connection provider that only provides connections
	 *  to a single database.
	 *  
	 * @param reader
	 * @param writer
	 * @param name
	 * @return
	 */
	public static DbConnectionProvider create(EntityManagerFactory reader, EntityManagerFactory writer, 
			String name) {
		EntityManagerFactoryContainer container = new EntityManagerFactoryContainer();
		container.setName(name);
		container.setReadOnlyEntityManagerFactory(reader);
		container.setReadWriteEntityManagerFactory(writer);
		DbConnectionProvider provider = new DbConnectionProvider(container);
		return provider;
	}
}
