package org.adligo.i.db;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.persistence.Parameter;
import javax.persistence.TemporalType;

public interface I_ParameterContainer {

	public abstract <T> Parameter<T> getParameter(int arg0, Class<T> arg1);

	public abstract Parameter<?> getParameter(int arg0);

	public abstract <T> Parameter<T> getParameter(String arg0, Class<T> arg1);

	public abstract Parameter<?> getParameter(String arg0);

	public abstract Object getParameterValue(int arg0);

	public abstract <T> T getParameterValue(Parameter<T> arg0);

	public abstract Object getParameterValue(String arg0);

	public abstract Set<Parameter<?>> getParameters();

	public abstract void setParameter(int arg0, Calendar arg1, TemporalType arg2);

	public abstract void setParameter(int arg0, Date arg1, TemporalType arg2);

	public abstract void setParameter(int arg0, Object arg1);

	public abstract void setParameter(Parameter<Calendar> arg0, Calendar arg1,
			TemporalType arg2);

	public abstract void setParameter(Parameter<Date> arg0, Date arg1,
			TemporalType arg2);

	public abstract <T> void setParameter(Parameter<T> arg0, T arg1);

	public abstract void setParameter(String arg0, Calendar arg1,
			TemporalType arg2);

	public abstract void setParameter(String arg0, Date arg1, TemporalType arg2);

	public abstract void setParameter(String arg0, Object arg1);
	
	
	public abstract int getFirstResult();

	public abstract Map<String, Object> getHints();

	public abstract int getMaxResults();
	
	public abstract void setFirstResult(int arg0);

	public abstract void setHint(String arg0, Object arg1);

	public abstract void setMaxResults(int arg0);


}