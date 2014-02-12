package org.adligo.i.db;

import javax.persistence.PersistenceException;

import org.adligo.i.adi.shared.InvocationException;

public class ExceptionThrower {

	public static void rethrow(Exception ix) throws InvocationException {
		if (ix instanceof PersistenceException) {
			throw new InvocationException(ix.getMessage(), ix);
		} else if (ix instanceof RuntimeException) {
			//re throw OutOfMemoryException exc so container 
			// can deal with it correctly
			throw (RuntimeException) ix;
		} else {
			//it's some other kind of checked exception
			// that was added to JPA or a adligo InvocationException
			// or something else
			throw new InvocationException(ix.getMessage(), ix);
		}
	}
}
