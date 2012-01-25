package org.refact4j.util.param;

public class ParametersBuilder {
	private Parameters parameters;

	private ParametersBuilder() {
		this.parameters = new Parameters();
	}

	public static ParametersBuilder init() {
		return new ParametersBuilder();
	}

	public ParametersBuilder add(String key, Object value) {
		parameters.add(key, value);
		return this;
	}

	public ParametersBuilder add(ParameterType parameterType, Object value) {
		parameters.add(parameterType, value);
		return this;
	}

	public Parameters getParameters() {
		return this.parameters;
	}

}
