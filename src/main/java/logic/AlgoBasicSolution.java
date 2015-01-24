package logic;

import io.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Area;

public class AlgoBasicSolution extends AbstractAlgo {
	private static Logger log = LoggerFactory
			.getLogger(AlgoBasicSolution.class);

	private int numSquares;
	private int maxFlank;
	private byte[] order;
	Area area = null;

	public AlgoBasicSolution(Task t) {
		maxFlank = 0;
		for (byte i = 1; i < 10; i++) {
			maxFlank += t.get(i) * i;
			numSquares += t.get(i);
		}
		order = new byte[numSquares];
		int index = 0;
		for (byte i = 1; i < 10; i++) {
			int to = t.get(i) + index;
			while (index < to) {
				order[index] = i;
				index++;
			}
		}
		area = new Area(maxFlank, 9);
		int left = 0;
		for (byte b : order) {
			area.dropTo(left, b);
			left += b;
		}

	}

	public void run() {
		log.debug("calculate Basic Solution");
		propagateSolution(area, AlgoBasicSolution.class);
	}

}
