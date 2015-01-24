package system;

import java.util.Arrays;

import logic.AlgoPermuteHeap;

import org.junit.Test;

public class HeapPerm {

	// @Test
	public void test() {

		byte[] array = new byte[4];
		array[0] = 0;
		array[1] = 1;
		array[2] = 2;
		array[3] = 3;
		generate(array);
	}

	@Test
	public void area4x6order1321() {
		AlgoPermuteHeap aph = new AlgoPermuteHeap(new byte[] { 1, 1, 2, 3 }, 4,
				6);
		aph.setSolutionManager(new SolutionManagerMock());
		aph.run();

	}

	@Test
	public void area5x5order1321() {
		AlgoPermuteHeap aph = new AlgoPermuteHeap(new byte[] { 1, 1, 2, 3 }, 5,
				5);
		aph.setSolutionManager(new SolutionManagerMock());
		aph.run();

	}

	public void generate(byte[] array) {
		generateRec(array, array.length - 1);
	}

	private void generateRec(byte[] array, int n) {
		if (n == 0) {
			System.out.println(Arrays.toString(array));
			return;
		}
		for (int i = 0; i <= n; i++) {
			generateRec(array, n - 1);
			if (n % 2 > 0) {
				swap(array, 0, n);
			} else {
				swap(array, i, n);
			}
		}
	}

	public void swap(byte[] array, int i1, int i2) {
		byte x = array[i1];
		array[i1] = array[i2];
		array[i2] = x;
	}

}
