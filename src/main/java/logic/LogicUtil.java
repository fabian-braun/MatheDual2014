package logic;

import io.Task;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LogicUtil {

	/**
	 * calculates the total perimeter of the form in the given system
	 * 
	 * @param system
	 * @return
	 */
	public static int getPerimeter(byte[][] system) {
		int sum = 0;
		for (int y = 0; y < system.length; y++) {
			boolean lastNull = true;
			for (int x = 0; x < system[y].length; x++) {
				if (lastNull && system[y][x] != 0) {
					sum++;
					lastNull = false;
				} else if (!lastNull && system[y][x] == 0) {
					sum++;
					lastNull = true;
				}
			}
			if (!lastNull) {
				sum++;
			}
		}
		for (int x = 0; x < system[0].length; x++) {
			boolean lastNull = true;
			for (int y = 0; y < system.length; y++) {
				if (lastNull && system[y][x] != 0) {
					sum++;
					lastNull = false;
				} else if (!lastNull && system[y][x] == 0) {
					sum++;
					lastNull = true;
				}
			}
			if (!lastNull) {
				sum++;
			}
		}
		return sum;
	}

	public static List<byte[]> getPermutations(Task t) {
		int numSquares = 0;
		List<Stack<Byte>> stacks = new ArrayList<Stack<Byte>>();
		for (byte i = 1; i < 10; i++) {
			numSquares += t.get(i);
			Stack<Byte> stack = new Stack<Byte>();
			stacks.add(stack);
			for (int j = 0; j < t.get(i); j++) {
				stack.push(i);
			}
		}
		byte[] initialorder = new byte[numSquares];
		List<byte[]> permutations = new ArrayList<byte[]>();
		getPermutationsRec(permutations, initialorder, stacks, 0);
		return permutations;

	}

	private static void getPermutationsRec(List<byte[]> permutations,
			byte[] order, List<Stack<Byte>> remaining, int index) {
		if (order[order.length - 1] != 0) {
			permutations.add(order.clone());
		}
		for (Stack<Byte> stack : remaining) {
			if (stack.empty()) {
				// nothing
			} else {
				order[index] = stack.pop();
				getPermutationsRec(permutations, order, remaining, index + 1);
				stack.push(order[index]);
				order[index] = 0;
			}
		}

	}

	private static BigInteger factorial(long i) {
		BigInteger b = BigInteger.valueOf(i);
		i--;
		for (; i > 1; i--) {
			b = BigInteger.valueOf(i).multiply(b);
		}
		return b;
	}

	/**
	 * calculates a fraction of factorials
	 * 
	 * 
	 * @param numerator
	 * @param denominator
	 * @return numerator! / (denominator[0]! * denominator[1]! *...)
	 */
	public static BigInteger divideFactorials(long numerator, long[] denominator) {
		BigInteger numeratorB = factorial(numerator);
		for (long l : denominator) {
			numeratorB.divide(factorial(l));
		}
		return numeratorB;
	}

	public static void swap(byte[] array, int i1, int i2) {
		byte x = array[i1];
		array[i1] = array[i2];
		array[i2] = x;
	}
}
