package org.adligo.i.db;

import org.adligo.models.core.client.ids.I_StorageIdentifier;

/**
 * This class encapsulates a request for a entity by a Long, and 
 * creates the convention <ParameterType>EntityRequest.
 * 
 * @author scott
 *
 */
public class IDSelectRequest extends SelectRequest {
	private I_StorageIdentifier id;

	/**
	 * @return the id
	 */
	public I_StorageIdentifier getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(I_StorageIdentifier id) {
		this.id = id;
	}
	
}
