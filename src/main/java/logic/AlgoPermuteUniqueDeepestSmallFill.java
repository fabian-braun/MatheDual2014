package logic;

import io.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Area;
import data.SizeStack;
import error.SolverException;

public class AlgoPermuteUniqueDeepestSmallFill extends AbstractAlgo implements
		Runnable {
	private static Logger log = LoggerFactory
			.getLogger(AlgoPermuteUniqueDeepestSmallFill.class);

	private int xLength;
	private int yLength;
	private SizeStack sStack = new SizeStack();
	private Task t;
	private byte smallUpTo;

	public AlgoPermuteUniqueDeepestSmallFill(Task t, int xLength, int yLength,
			byte smallUpTo) {
		this.xLength = xLength;
		this.yLength = yLength;
		this.t = t;
		this.smallUpTo = smallUpTo;
		sStack.setSmallUpTo(smallUpTo);
	}

	public void run() {
		int largesquares = 0;
		List<Stack<Byte>> stacks = new ArrayList<Stack<Byte>>();
		for (byte i = 9; i > smallUpTo; i--) {
			largesquares += t.get(i);
			Stack<Byte> stack = new Stack<Byte>();
			stacks.add(stack);
			for (int j = 0; j < t.get(i); j++) {
				stack.push(i);
			}
		}
		for (byte i = smallUpTo; i > 0; i--) {
			for (int j = 0; j < t.get(i); j++) {
				sStack.push(i);
			}
		}
		byte[] initialorder = new byte[largesquares];
		checkPermutationsRec(initialorder, stacks, 0);
	}

	private void checkPermutationsRec(byte[] order,
			List<Stack<Byte>> remaining, int index) {
		if (order[order.length - 1] != 0) {
			try {
				propagateSolution(getArea(order.clone()),
						AlgoPermuteUniqueDeepestSmallFill.class);
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
		SizeStack tempStack = sStack.clone();
		while (tempStack.smallAvailable()) {
			if (tempStack.getAvailable((byte) 9) > 0) {
				area.fillIn(tempStack.pop((byte) 9));
			} else if (tempStack.getAvailable((byte) 8) > 0) {
				area.fillIn(tempStack.pop((byte) 8));
			} else if (tempStack.getAvailable((byte) 7) > 0) {
				area.fillIn(tempStack.pop((byte) 7));
			} else if (tempStack.getAvailable((byte) 6) > 0) {
				area.fillIn(tempStack.pop((byte) 6));
			} else if (tempStack.getAvailable((byte) 5) > 0) {
				area.fillIn(tempStack.pop((byte) 5));
			} else if (tempStack.getAvailable((byte) 4) > 0) {
				area.fillIn(tempStack.pop((byte) 4));
			} else if (tempStack.getAvailable((byte) 3) > 0) {
				area.fillIn(tempStack.pop((byte) 3));
			} else if (tempStack.getAvailable((byte) 2) > 0) {
				area.fillIn(tempStack.pop((byte) 2));
			} else if (tempStack.getAvailable((byte) 1) > 0) {
				area.fillIn(tempStack.pop((byte) 1));
			} else {
				log.warn("wrong counting in findGoodSolution: "
						+ " small squares remaining, but Stack empty");
			}
		}
		return area;
	}

}
