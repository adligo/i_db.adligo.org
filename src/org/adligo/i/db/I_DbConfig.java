package org.adligo.i.db;

import java.io.InputStream;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.adligo.i.adi.shared.InvocationException;

public interface I_DbConfig {

	/**
	 * Used to inject a datasource object as the connection provider.
	 * If used, be sure to <b>not override</b> the hibernate.connection.provider_class
	 * property
	 */
	public abstract void setDataSource(DataSource ds);

	public abstract EntityManagerFactory buildEntityManagerFactory();

	public abstract I_DbConfig setProperties(Properties properties);

	public abstract I_DbConfig addInputStream(
			InputStream xmlInputStream) throws InvocationException;

}