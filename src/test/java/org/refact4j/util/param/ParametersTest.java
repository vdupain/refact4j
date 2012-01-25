package org.refact4j.util.param;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class ParametersTest {

	@Test
	public void testSimpleCreate() {
		Date now = new Date();
		ParameterType parameterType = new ParameterType("double", Double.class);
		Parameters parameters = ParametersBuilder.init()
			.add("int", 1)
			.add("date", now)
			.add("string", "abc")
			.add("boolean", true)
			.add(parameterType, 1.2345)
			.getParameters();

		assertEquals(new Integer(1), parameters.getInteger("int"));
		assertEquals(now, parameters.getDate("date"));
		assertEquals("abc", parameters.getString("string"));
		assertEquals(Boolean.TRUE, parameters.getBoolean("boolean"));
		assertEquals(1.2345, parameters.get(parameterType));
	}

	@Test
	public void testAddParamWithBadClass() {
		try {
			ParametersBuilder.init()
			.add(new DoubleParameterType("param1"), "abcd")
			.getParameters();
			Assert.fail();
		} catch (Exception e) {
			assertEquals("Value of parameter 'param1' must be instance of java.lang.Double but was class java.lang.String", e.getMessage());
		}
		
	}
	
	@Test
	public void testToString() {
		Parameters parameters = ParametersBuilder.init().add("int", 1).add("string", "abc").add("boolean", true)
				.getParameters();
		assertEquals("[boolean=true, int=1, string=abc]", parameters.toString());
	}

}
