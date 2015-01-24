package system;

import java.math.BigInteger;

import org.junit.BeforeClass;
import org.junit.Test;

public class Factorial {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		System.out.println(factorial(50));
		System.out.println(divideFactorials(9, 1));
	}

	private BigInteger factorial(long i) {
		BigInteger b = BigInteger.valueOf(i);
		i--;
		for (; i > 1; i--) {
			b = BigInteger.valueOf(i).multiply(b);
		}
		return b;
	}

	private BigInteger divideFactorials(long numerator, long denominator) {
		return factorial(numerator).divide(factorial(denominator));
	}

}
