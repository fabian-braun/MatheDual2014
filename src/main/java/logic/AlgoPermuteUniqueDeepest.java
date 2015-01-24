package logic;

import io.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Area;
import error.SolverException;

public class AlgoPermuteUniqueDeepest extends AbstractAlgo implements Runnable {
	private static Logger log = LoggerFactory
			.getLogger(AlgoPermuteUniqueDeepest.class);

	private int xLength;
	private int yLength;
	private Task t;

	public AlgoPermuteUniqueDeepest(Task t, int xLength, int yLength) {
		this.xLength = xLength;
		this.yLength = yLength;
		this.t = t;
	}

	public void run() {
		int numSquares = 0;
		List<Stack<Byte>> stacks = new ArrayList<Stack<Byte>>();
		for (byte i = 9; i > 0; i--) {
			numSquares += t.get(i);
			Stack<Byte> stack = new Stack<Byte>();
			stacks.add(stack);
			for (int j = 0; j < t.get(i); j++) {
				stack.push(i);
			}
		}
		byte[] initialorder = new byte[numSquares];
		checkPermutationsRec(initialorder, stacks, 0);
	}

	private void checkPermutationsRec(byte[] order,
			List<Stack<Byte>> remaining, int index) {
		if (order[order.length - 1] != 0) {
			try {
				propagateSolution(getArea(order.clone()),
						AlgoPermuteUniqueDeepest.class);
			} catch (SolverException e) {
				log.error("error", e);
			}
		}
		for (Stack<Byte> stack : remaining) {
			if (stack.empty()) {
				// nothing
			} else {
				order[index] = stack.pop();
				checkPermutationsRec(order, remaining, index + 1);
				stack.push(order[index]);
				order[index] = 0;
			}
		}

	}

	private Area getArea(byte[] order) {
		log.debug("try order " + Arrays.toString(order));
		Area area = new Area(xLength, yLength);
		int left = 0;
		for (byte b : order) {
			left = area.findDeapestDrop(b);
			if (!area.dropTo(left, b)) {
				String msg = "square [" + b + "] doesnt fit in area:"
						+ area.toString() + " at " + left;
				throw new SolverException(msg);
			}
		}
		return area;
	}

}
