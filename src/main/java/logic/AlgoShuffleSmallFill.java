package logic;

import io.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Area;
import data.SizeStack;
import error.SolverException;

public class AlgoShuffleSmallFill extends AbstractAlgo implements Runnable {
	private static Logger log = LoggerFactory
			.getLogger(AlgoShuffleSmallFill.class);

	private int xLength;
	private int yLength;
	private SizeStack sStack = new SizeStack();
	private List<Byte> order;

	public AlgoShuffleSmallFill(Task t, int xLength, int yLength, byte smallUpTo) {
		this.order = new ArrayList<Byte>();
		this.xLength = xLength;
		this.yLength = yLength;
		sStack.setSmallUpTo(smallUpTo);
		for (byte i = 9; i > smallUpTo; i--) {
			for (int j = 0; j < t.get(i); j++) {
				order.add(i);
			}
		}
		for (byte i = smallUpTo; i > 0; i--) {
			for (int j = 0; j < t.get(i); j++) {
				sStack.push(i);
			}
		}
	}

	public void run() {
		while (true) {
			Collections.shuffle(order);
			propagateSolution(getArea(order.toArray(new Byte[0])),
					AlgoShuffleSmallFill.class);
		}
	}

	private Area getArea(Byte[] order) {
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
