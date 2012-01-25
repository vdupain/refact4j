package org.refact4j.functor;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.refact4j.functor.arithmetic.Divides;
import org.refact4j.functor.arithmetic.Minus;
import org.refact4j.functor.arithmetic.Multiplies;
import org.refact4j.functor.arithmetic.Negate;
import org.refact4j.functor.arithmetic.Plus;

public class ArithmeticFunctorsTest {
	double d1 = 12345.;
	double d2 = 6789.;
	int i1 = 12345;
	int i2 = 6789;

	@Test
	public void testMultiplies() {
		assertEquals((Double) (d1 * d2), new Multiplies<Double>().eval(d1, d2));
		assertEquals((Integer) (i1 * i2),
				new Multiplies<Integer>().eval(i1, i2));
	}

	@Test
	public void testDivides() {
		assertEquals((Double) (d1 / d2), new Divides<Double>().eval(d1, d2));
		assertEquals((Integer) (i1 / i2), new Divides<Integer>().eval(i1, i2));
	}

	@Test
	public void testMinus() {
		assertEquals((Double) (d1 - d2), new Minus<Double>().eval(d1, d2));
		assertEquals((Integer) (i1 - i2), new Minus<Integer>().eval(i1, i2));
	}

	@Test
	public void testPlus() {
		assertEquals((Double) (d1 + d2), new Plus<Double>().eval(d1, d2));
		assertEquals((Integer) (i1 + i2), new Plus<Integer>().eval(i1, i2));
	}

	@Test
	public void testNegate() {
		assertEquals((Double) (-d1), new Negate<Double>().eval(d1));
		assertEquals((Integer) (-i1), new Negate<Integer>().eval(i1));
	}

}
