package org.adligo.i.db;

import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.adligo.i.adi.client.InvocationException;
import org.adligo.i.adig.client.GRegistry;
import org.adligo.i.adig.client.I_GCheckedInvoker;
import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;

public class DbMethodWrappers {
	
	public static final String TRANSACTION_WRAPPERS_ARE_REQUIRED_TO_BE_WRAPPED_INSIDE_OF_A_READ_WRITE_CONNECTION_WRAPPER = "TransactionWrappers are required to be wrapped inside of a ReadWriteConnectionWrapper.";
	public static final String TRANSACTION_WRAPPERS_REQUIRE_A_MODIFY_ENTITY_REQUEST_PARAMETER = "transactionWrappers require a ModifyEntityRequest parameter.";
	public static final String THE_PLUGIN_S_PARAMETER_CLASS_MUST_BE_ASSIGNABLE_FROM_MODIFY_ENTITY_REQUEST_TO_CREATE_A_TRANSACTION_WRAPPER = "The plugin's parameter class must be assignable from ModifyEntityRequest to create a TransactionWrapper";
	public static final String PLUGIN_IS_REQUIRED_FOR_TRANSACTION_WRAPPERS = "plugin is required for transactionWrappers";
	public static final String THE_PLUGIN_S_PARAMETER_CLASS_MUST_BE_ASSIGNABLE_FROM_MODIFY_ENTITY_REQUEST = "the plugin's parameter class must be assignable from ModifyEntityRequest";
	public static final String READ_WRITE_CONNECTION_WRAPPERS_REQUIRE_MODIFY_ENTITY_REQUESTS = "readWriteConnectionWrappers require ModifyEntityRequests'";
	public static final String CONNECTION_WRAPPERS_REQUIRE_ENTITY_REQUEST_PARAMETERS = "connectionWrappers require EntityRequest parameters, or should this code be configured to use a readWrite connection wrapper?";
	public static final String PLUGIN_IS_REQUIRED_FOR_CREATE_CONNECTION_WRAPPER = "plugin is required for createConnectionWrapper";
	public static final String THE_PLUGIN_S_PARAMETER_CLASS_MUST_EXTEND_FROM_ENTITY_REQUEST = "the plugin's parameter class must extend from EntityRequest";
	private static final I_GCheckedInvoker<DbConnectionRequest, I_DbConnection> 
		CONNECTION_PROVIDER = GRegistry.getCheckedInvoker(
				DbCheckedInvokerNames.STORAGE_CONNECTION_PROVIDER,
				DbConnectionRequest.class, I_DbConnection.class);
		
	/**
	 * his will obtain a read only connection from the pool,
	 * call the plugin, and then put the connection back in the pool.
	 * 
	 * @param plugin
	 * @param connectionName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static I_GCheckedInvoker createConnectionWrapper(
			final I_GCheckedInvoker plugin,
			final String connectionName) {
		if (plugin == null) {
			throw new NullPointerException(PLUGIN_IS_REQUIRED_FOR_CREATE_CONNECTION_WRAPPER);
		}
		Class paramClass = plugin.getParameterClass();
		if (!(SelectRequest.class.isAssignableFrom(paramClass))) {
			throw new NullPointerException(THE_PLUGIN_S_PARAMETER_CLASS_MUST_EXTEND_FROM_ENTITY_REQUEST);
		}
		
		return new I_GCheckedInvoker() {

			@Override
			public Class getReturnClass() {
				return plugin.getReturnClass();
			}

			@Override
			public Class getParameterClass() {
				return plugin.getParameterClass();
			}

			@Override
			public Object invoke(Object valueObject) throws InvocationException {
				if (!(valueObject instanceof SelectRequest)) {
					throw new IllegalArgumentException(CONNECTION_WRAPPERS_REQUIRE_ENTITY_REQUEST_PARAMETERS);
				}
				SelectRequest er = (SelectRequest) valueObject;
				
				DbConnectionRequest scr = new DbConnectionRequest();
				scr.setName(connectionName);
				scr.setWriteable(false);
				
				I_DbConnection connection = CONNECTION_PROVIDER.invoke(scr);
				I_ReadOnlyConnection eo = null;
				try {
					eo = connection.getObtainer();
					er.setReadOnlyConnection(eo);
					return plugin.invoke(er);
				} catch (PersistenceException x) { 
					throw new InvocationException(x.getMessage(), x);
				} finally {
					if (connection != null) {
						connection.returnToPool();
					}
					if (eo != null) {
						((ReadOnlyConnection) eo).cleanup();
					}
					er.setReadOnlyConnection(null);
				}
			}
			
		};
	}
	
	
	/**
	 * This will obtain a read write connection from the pool,
	 * call the plugin, and then put the connection back in the pool.
	 * 
	 * @param plugin
	 * @param connectionName
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static I_GCheckedInvoker createReadWriteConnectionWrapper(
			final I_GCheckedInvoker plugin,
			final String connectionName) {
		
		if (plugin == null) {
			throw new NullPointerException(PLUGIN_IS_REQUIRED_FOR_CREATE_CONNECTION_WRAPPER);
		}
		Class paramClass = plugin.getParameterClass();
		if (!ModifyRequest.class.isAssignableFrom(paramClass)) {
			throw new NullPointerException(
					THE_PLUGIN_S_PARAMETER_CLASS_MUST_BE_ASSIGNABLE_FROM_MODIFY_ENTITY_REQUEST);
		}
		
		return new I_GCheckedInvoker() {

			@Override
			public Class getReturnClass() {
				return plugin.getReturnClass();
			}

			@Override
			public Class getParameterClass() {
				return plugin.getParameterClass();
			}

			@Override
			public Object invoke(Object valueObject) throws InvocationException {
				if (!(valueObject instanceof ModifyRequest)) {
					throw new IllegalArgumentException(READ_WRITE_CONNECTION_WRAPPERS_REQUIRE_MODIFY_ENTITY_REQUESTS);
				}
				ModifyRequest er = (ModifyRequest) valueObject;
				
				DbConnectionRequest scr = new DbConnectionRequest();
				scr.setName(connectionName);
				scr.setWriteable(true);
				
				I_DbConnection connection = CONNECTION_PROVIDER.invoke(scr);
				I_ReadWriteConnection em = null;
				try {
					em = connection.getModifier();
					er.setReadWriteConnection(em);
					EntityTransaction tran = connection.getTransaction();
					er.setTransaction(tran);
					return plugin.invoke(er);
				} finally {
					if (connection != null) {
						connection.returnToPool();
					}
					if (em != null) {
						((ReadWriteConnection) em).cleanup();
					}
					er.setReadWriteConnection(null);
				}
			}
			
		};
	}
	
	/**
	 * 
	 * @param plugin
	 * @param connectionName
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static I_GCheckedInvoker createTransactionAndConnectionWrapper(
			final I_GCheckedInvoker plugin,
			final String connectionName) {
		
		if (plugin == null) {
			throw new NullPointerException(PLUGIN_IS_REQUIRED_FOR_CREATE_CONNECTION_WRAPPER);
		}
		Class paramClass = plugin.getParameterClass();
		if (!ModifyRequest.class.isAssignableFrom(paramClass)) {
			throw new NullPointerException(
					THE_PLUGIN_S_PARAMETER_CLASS_MUST_BE_ASSIGNABLE_FROM_MODIFY_ENTITY_REQUEST);
		}
		
		return new I_GCheckedInvoker() {

			@Override
			public Class getReturnClass() {
				return plugin.getReturnClass();
			}

			@Override
			public Class getParameterClass() {
				return plugin.getParameterClass();
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object valueObject) throws InvocationException {
				if (!(valueObject instanceof ModifyRequest)) {
					throw new IllegalArgumentException(READ_WRITE_CONNECTION_WRAPPERS_REQUIRE_MODIFY_ENTITY_REQUESTS);
				}
				ModifyRequest er = (ModifyRequest) valueObject;
				
				DbConnectionRequest scr = new DbConnectionRequest();
				scr.setName(connectionName);
				scr.setWriteable(true);
				
				I_DbConnection connection = CONNECTION_PROVIDER.invoke(scr);
				EntityTransaction tran = connection.getTransaction();
				I_ReadWriteConnection em = null;
				try {
					tran.begin();
					em = connection.getModifier();
					er.setReadWriteConnection(em);
					Object result =  plugin.invoke(er);
					tran.commit();
					return result;
				} catch(PersistenceException ix) {
					ExceptionThrower.rethrow(ix);
				} finally {
					if (tran != null) {
						if (tran.isActive()) {
							tran.rollback();
						}
					}
					connection.returnToPool();
					if (em != null) {
						((ReadWriteConnection) em).cleanup();
					}
					er.setReadWriteConnection(null);
				}
				return null;
			}
			
		};
	}
	
	/**
	 * creates a transaction inside of a open connection
	 *   (aka inside of one of the other wrappers)
	 *   for when two or more transactions are required
	 *   
	 * @param plugin
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static I_GCheckedInvoker createTransactionWrapper(
		 final I_GCheckedInvoker plugin) {
		
		if (plugin == null) {
			throw new NullPointerException(PLUGIN_IS_REQUIRED_FOR_TRANSACTION_WRAPPERS);
		}
		Class paramClass = plugin.getParameterClass();
		if (!ModifyRequest.class.isAssignableFrom(paramClass)) {
			throw new NullPointerException(
					THE_PLUGIN_S_PARAMETER_CLASS_MUST_BE_ASSIGNABLE_FROM_MODIFY_ENTITY_REQUEST_TO_CREATE_A_TRANSACTION_WRAPPER);
		}
		return new I_GCheckedInvoker<Object,Object>() {

			@Override
			public Class<?> getReturnClass() {
				return plugin.getReturnClass();
			}

			@Override
			public Class<?> getParameterClass() {
				return plugin.getParameterClass();
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object valueObject) throws InvocationException {
				if (!(valueObject instanceof ModifyRequest)) {
					throw new IllegalArgumentException(
							TRANSACTION_WRAPPERS_REQUIRE_A_MODIFY_ENTITY_REQUEST_PARAMETER);
				}
				ModifyRequest er = (ModifyRequest) valueObject;
				
				EntityTransaction tran = er.getTransaction();
				if (tran == null) {
					throw new InvocationException(
							TRANSACTION_WRAPPERS_ARE_REQUIRED_TO_BE_WRAPPED_INSIDE_OF_A_READ_WRITE_CONNECTION_WRAPPER);
				}
				try {
					tran.begin();
					Object result =  plugin.invoke(er);
					tran.commit();
					return result;
				} catch (PersistenceException x) {
					ExceptionThrower.rethrow(x);
				} finally {
					if (tran != null) {
						if (tran.isActive()) {
							tran.rollback();
						}
					}
				} 
				return null;
			}
		};
	}

	/**
	 * his will obtain a read only connection from the pool,
	 * call the plugin, and then put the connection back in the pool.
	 * 
	 * @param plugin
	 * @param connectionName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static I_GCheckedInvoker createConnectionWrapper(
			final I_GCheckedInvoker plugin) {
		if (plugin == null) {
			throw new NullPointerException(PLUGIN_IS_REQUIRED_FOR_CREATE_CONNECTION_WRAPPER);
		}
		Class paramClass = plugin.getParameterClass();
		if (!(SelectRequest.class.isAssignableFrom(paramClass))) {
			throw new NullPointerException(THE_PLUGIN_S_PARAMETER_CLASS_MUST_EXTEND_FROM_ENTITY_REQUEST);
		}
		
		return new I_GCheckedInvoker() {

			@Override
			public Class getReturnClass() {
				return plugin.getReturnClass();
			}

			@Override
			public Class getParameterClass() {
				return plugin.getParameterClass();
			}

			@Override
			public Object invoke(Object valueObject) throws InvocationException {
				if (!(valueObject instanceof SelectRequest)) {
					throw new IllegalArgumentException(CONNECTION_WRAPPERS_REQUIRE_ENTITY_REQUEST_PARAMETERS);
				}
				SelectRequest er = (SelectRequest) valueObject;
				
				DbConnectionRequest scr = new DbConnectionRequest();
				String connectionName = er.getConnectionName();
				scr.setName(connectionName);
				scr.setWriteable(false);
				
				I_DbConnection connection = CONNECTION_PROVIDER.invoke(scr);
				I_ReadOnlyConnection eo = null;
				try {
					eo = connection.getObtainer();
					er.setReadOnlyConnection(eo);
					return plugin.invoke(er);
				} catch (PersistenceException x) { 
					throw new InvocationException(x.getMessage(), x);
				} finally {
					if (connection != null) {
						connection.returnToPool();
					}
					if (eo != null) {
						((ReadOnlyConnection) eo).cleanup();
					}
					er.setReadOnlyConnection(null);
				}
			}
			
		};
	}
	
	
	/**
	 * This will obtain a read write connection from the pool,
	 * call the plugin, and then put the connection back in the pool.
	 * 
	 * @param plugin
	 * @param connectionName
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static I_GCheckedInvoker createReadWriteConnectionWrapper(
			final I_GCheckedInvoker plugin) {
		
		if (plugin == null) {
			throw new NullPointerException(PLUGIN_IS_REQUIRED_FOR_CREATE_CONNECTION_WRAPPER);
		}
		Class paramClass = plugin.getParameterClass();
		if (!ModifyRequest.class.isAssignableFrom(paramClass)) {
			throw new NullPointerException(
					THE_PLUGIN_S_PARAMETER_CLASS_MUST_BE_ASSIGNABLE_FROM_MODIFY_ENTITY_REQUEST);
		}
		
		return new I_GCheckedInvoker() {

			@Override
			public Class getReturnClass() {
				return plugin.getReturnClass();
			}

			@Override
			public Class getParameterClass() {
				return plugin.getParameterClass();
			}

			@Override
			public Object invoke(Object valueObject) throws InvocationException {
				if (!(valueObject instanceof ModifyRequest)) {
					throw new IllegalArgumentException(READ_WRITE_CONNECTION_WRAPPERS_REQUIRE_MODIFY_ENTITY_REQUESTS);
				}
				ModifyRequest er = (ModifyRequest) valueObject;
				
				DbConnectionRequest scr = new DbConnectionRequest();
				String connectionName = er.getConnectionName();
				scr.setName(connectionName);
				scr.setWriteable(true);
				
				I_DbConnection connection = CONNECTION_PROVIDER.invoke(scr);
				I_ReadWriteConnection em = null;
				try {
					em = connection.getModifier();
					er.setReadWriteConnection(em);
					EntityTransaction tran = connection.getTransaction();
					er.setTransaction(tran);
					return plugin.invoke(er);
				} finally {
					if (connection != null) {
						connection.returnToPool();
					}
					if (em != null) {
						((ReadWriteConnection) em).cleanup();
					}
					er.setReadWriteConnection(null);
				}
			}
			
		};
	}
	
	/**
	 * 
	 * @param plugin
	 * @param connectionName
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static I_GCheckedInvoker createTransactionAndConnectionWrapper(
			final I_GCheckedInvoker plugin) {
		
		if (plugin == null) {
			throw new NullPointerException(PLUGIN_IS_REQUIRED_FOR_CREATE_CONNECTION_WRAPPER);
		}
		Class paramClass = plugin.getParameterClass();
		if (!ModifyRequest.class.isAssignableFrom(paramClass)) {
			throw new NullPointerException(
					THE_PLUGIN_S_PARAMETER_CLASS_MUST_BE_ASSIGNABLE_FROM_MODIFY_ENTITY_REQUEST);
		}
		
		return new I_GCheckedInvoker() {

			@Override
			public Class getReturnClass() {
				return plugin.getReturnClass();
			}

			@Override
			public Class getParameterClass() {
				return plugin.getParameterClass();
			}

			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object valueObject) throws InvocationException {
				if (!(valueObject instanceof ModifyRequest)) {
					throw new IllegalArgumentException(READ_WRITE_CONNECTION_WRAPPERS_REQUIRE_MODIFY_ENTITY_REQUESTS);
				}
				ModifyRequest er = (ModifyRequest) valueObject;
				
				DbConnectionRequest scr = new DbConnectionRequest();
				String connectionName = er.getConnectionName();
				scr.setName(connectionName);
				scr.setWriteable(true);
				
				I_DbConnection connection = CONNECTION_PROVIDER.invoke(scr);
				EntityTransaction tran = connection.getTransaction();
				I_ReadWriteConnection em = null;
				try {
					tran.begin();
					em = connection.getModifier();
					er.setReadWriteConnection(em);
					Object result =  plugin.invoke(er);
					tran.commit();
					return result;
				} catch(PersistenceException ix) {
					ExceptionThrower.rethrow(ix);
				} finally {
					if (tran != null) {
						if (tran.isActive()) {
							tran.rollback();
						}
					}
					connection.returnToPool();
					if (em != null) {
						((ReadWriteConnection) em).cleanup();
					}
					er.setReadWriteConnection(null);
				}
				return null;
			}
			
		};
	}
	

}
