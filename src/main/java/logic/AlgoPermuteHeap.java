package logic;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Area;
import error.SolverException;

public class AlgoPermuteHeap extends AbstractAlgo implements Runnable {
	private static Logger log = LoggerFactory.getLogger(AlgoPermuteHeap.class);

	private int xLength;
	private int yLength;
	private byte[] order;
	Area area = null;

	public AlgoPermuteHeap(byte[] order, int xLength, int yLength) {
		this.xLength = xLength;
		this.yLength = yLength;
		this.order = order.clone();
	}

	public void run() {
		generateRec(order, order.length - 1);
	}

	private void generateRec(byte[] array, int n) {
		if (n == 0) {
			propagateSolution(getArea(array), AlgoPermuteHeap.class);
			return;
		}
		for (int i = 0; i <= n; i++) {
			generateRec(array, n - 1);
			if (n % 2 > 0) {
				LogicUtil.swap(array, 0, n);
			} else {
				LogicUtil.swap(array, i, n);
			}
		}
	}

	private Area getArea(byte[] order) {
		log.debug("try order " + Arrays.toString(order));
		area = new Area(xLength, yLength);
		int left = 0;
		for (byte b : order) {
			if (area.dropTo(left, b)) {
				left += b;
			} else {
				left = 0;
				if (!area.dropTo(left, b)) {
					String msg = "square [" + b + "] doesnt fit in area:"
							+ area.toString() + " at " + left;
					throw new SolverException(msg);
				}
				left += b;
			}
		}
		return area;
	}
}
