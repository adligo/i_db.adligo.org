package org.adligo.i.db;

/**
 * This class encapsulates a request for a entity by a Long, and 
 * creates the convention <ParameterType>EntityRequest.
 * 
 * @author scott
 *
 */
public class LongSelectRequest extends SelectRequest {
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}
