package org.refact4j.util.param;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Parameters represents a list of parameters.
 * 
 * @author vdupain
 * 
 */
public class Parameters implements Iterable<String> {

	public final static Parameters EMPTY_PARAMETERS = new Parameters();

	private SortedMap<String, Object> map = new TreeMap<String, Object>();

	public void add(String key, Object value) {
		map.put(key, value);
	}

	public void add(ParameterType parameterType, Object value) {
		checkClassValue(parameterType.getName(), parameterType.getType(), value);
		add(parameterType.getName(), value);
	}

	public Object get(ParameterType parameterType) {
		return this.get(parameterType.getName());
	}

	public Object get(String key) {
		return map.get(key);
	}

	public Integer getInteger(String key) {
		return (Integer) getObject(key, Integer.class);
	}

	public Integer getInteger(ParameterType parameterType) {
		return (Integer) getObject(parameterType.getName(), Integer.class);
	}

	public Long getLong(String key) {
		return (Long) getObject(key, Long.class);
	}

	public Long getLong(ParameterType parameterType) {
		return (Long) getObject(parameterType.getName(), Long.class);
	}

	public Date getDate(String key) {
		return (Date) getObject(key, Date.class);
	}

	public Date getDate(ParameterType parameterType) {
		return (Date) getObject(parameterType.getName(), Date.class);
	}

	public String getString(String key) {
		return (String) getObject(key, String.class);
	}

	public String getString(ParameterType parameterType) {
		return (String) getObject(parameterType.getName(), String.class);
	}

	public Boolean getBoolean(String key) {
		return (Boolean) getObject(key, Boolean.class);
	}

	public Boolean getBoolean(ParameterType parameterType) {
		return (Boolean) getObject(parameterType.getName(), Boolean.class);
	}

	private Object getObject(String key, Class<?> expectedClass) {
		Object value = get(key);
		checkClassValue(key, expectedClass, value);
		return value;
	}

	private void checkClassValue(String key, Class<?> expectedClass, Object value) {
		if ((value != null) && !expectedClass.isAssignableFrom(value.getClass())) {
			throw new RuntimeException("Value of parameter '" + key + "' must be instance of "
					+ expectedClass.getName() + " but was " + value.getClass());
		}
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append('[');
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
			String key = iterator.next();
			buffer.append(key).append('=').append(map.get(key));
			if (iterator.hasNext()) {
				buffer.append(", ");
			}
		}
		buffer.append(']');
		return buffer.toString();
	}

	public Iterator<String> iterator() {
		return this.map.keySet().iterator();
	}

	public Collection<Object> getParamValues() {
		return this.map.values();
	}
}
