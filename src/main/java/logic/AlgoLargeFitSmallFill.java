package logic;

import io.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Area;
import data.Line;
import data.SizeStack;

public class AlgoLargeFitSmallFill extends AbstractAlgo implements Runnable {
	private static Logger log = LoggerFactory
			.getLogger(AlgoLargeFitSmallFill.class);

	private int xLength;
	private int yLength;
	private SizeStack sStack = new SizeStack();
	private Task t;
	private byte smallUpTo;

	public AlgoLargeFitSmallFill(Task t, int xLength, int yLength,
			byte smallUpTo) {
		this.xLength = xLength;
		this.yLength = yLength;
		this.t = t;
		this.smallUpTo = smallUpTo;
		sStack.setSmallUpTo(smallUpTo);
	}

	public void run() {
		for (byte i = 1; i < 10; i++) {
			for (int j = 0; j < t.get(i); j++) {
				sStack.push(i);
			}
		}
		propagateSolution(findGoodSolution(), AlgoLargeFitSmallFill.class);
	}

	private Area findGoodSolution() {
		Area area = new Area(xLength, yLength);
		int above = -1;
		while (sStack.largeAvailable()) {
			Line l = area.findDeapestLine(above);
			if (l.deltaX > smallUpTo) {
				switch (l.deltaX) {
				case 1:
					if (sStack.getAvailable((byte) 1) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 1));
					} else {
						above++;
					}
					break;
				case 2:
					if (sStack.getAvailable((byte) 2) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 2));
					} else if (sStack.getAvailable((byte) 1) > 1) {
						area.dropTo(l.left, sStack.pop((byte) 1));
						area.dropTo(l.left + 1, sStack.pop((byte) 1));
					} else {
						above++;
					}
					break;
				case 3:
					if (sStack.getAvailable((byte) 3) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 3));
					} else {
						above++;
					}
					break;
				case 4:
					if (sStack.getAvailable((byte) 4) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 4));
					} else {
						above++;
					}
					break;
				case 5:
					if (sStack.getAvailable((byte) 5) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 5));
					} else if (sStack.getAvailable((byte) 2) > 0
							&& sStack.getAvailable((byte) 3) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 2));
						area.dropTo(l.left + 2, sStack.pop((byte) 3));
					} else if (sStack.getAvailable((byte) 4) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 4));
					} else {
						above++;
					}
					break;
				case 6:
					if (sStack.getAvailable((byte) 6) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 6));
					} else if (sStack.getAvailable((byte) 3) > 1) {
						area.dropTo(l.left, sStack.pop((byte) 3));
						area.dropTo(l.left + 3, sStack.pop((byte) 3));
					} else if (sStack.getAvailable((byte) 5) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 5));
					} else if (sStack.getAvailable((byte) 4) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 4));
					} else {
						above++;
					}
					break;
				case 7:
					if (sStack.getAvailable((byte) 7) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 7));
					} else if (sStack.getAvailable((byte) 3) > 0
							&& sStack.getAvailable((byte) 4) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 3));
						area.dropTo(l.left + 3, sStack.pop((byte) 4));
					} else if (sStack.getAvailable((byte) 6) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 6));
					} else if (sStack.getAvailable((byte) 5) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 5));
					} else {
						above++;
					}
					break;
				case 8:
					if (sStack.getAvailable((byte) 8) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 8));
					} else if (sStack.getAvailable((byte) 4) > 1) {
						area.dropTo(l.left, sStack.pop((byte) 4));
						area.dropTo(l.left + 4, sStack.pop((byte) 4));
					} else if (sStack.getAvailable((byte) 7) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 7));
					} else if (sStack.getAvailable((byte) 6) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 6));
					} else {
						above++;
					}
					break;
				case 9:
					if (sStack.getAvailable((byte) 9) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 9));
					} else if (sStack.getAvailable((byte) 5) > 0
							&& sStack.getAvailable((byte) 4) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 5));
						area.dropTo(l.left + 5, sStack.pop((byte) 4));
					} else {
						area.dropTo(l.left, sStack.getLargest());
					}
					break;
				case 10:
					if (sStack.getAvailable((byte) 9) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 9));
					} else if (sStack.getAvailable((byte) 5) > 1) {
						area.dropTo(l.left, sStack.pop((byte) 5));
						area.dropTo(l.left + 5, sStack.pop((byte) 5));
					} else {
						area.dropTo(l.left, sStack.getLargest());
					}
					break;
				case 11:
					if (sStack.getAvailable((byte) 9) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 9));
					} else if (sStack.getAvailable((byte) 5) > 0
							&& sStack.getAvailable((byte) 6) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 5));
						area.dropTo(l.left + 5, sStack.pop((byte) 6));
					} else if (sStack.getAvailable((byte) 4) > 0
							&& sStack.getAvailable((byte) 7) > 0) {
						area.dropTo(l.left, sStack.pop((byte) 7));
						area.dropTo(l.left + 7, sStack.pop((byte) 4));
					} else {
						area.dropTo(l.left, sStack.getLargest());
					}
					break;
				default:
					area.dropTo(l.left, sStack.getLargest());
				}
			} else {
				above++;
			}
		}
		while (sStack.smallAvailable()) {
			if (sStack.getAvailable((byte) 9) > 0) {
				area.fillIn(sStack.pop((byte) 9));
			} else if (sStack.getAvailable((byte) 8) > 0) {
				area.fillIn(sStack.pop((byte) 8));
			} else if (sStack.getAvailable((byte) 7) > 0) {
				area.fillIn(sStack.pop((byte) 7));
			} else if (sStack.getAvailable((byte) 6) > 0) {
				area.fillIn(sStack.pop((byte) 6));
			} else if (sStack.getAvailable((byte) 5) > 0) {
				area.fillIn(sStack.pop((byte) 5));
			} else if (sStack.getAvailable((byte) 4) > 0) {
				area.fillIn(sStack.pop((byte) 4));
			} else if (sStack.getAvailable((byte) 3) > 0) {
				area.fillIn(sStack.pop((byte) 3));
			} else if (sStack.getAvailable((byte) 2) > 0) {
				area.fillIn(sStack.pop((byte) 2));
			} else if (sStack.getAvailable((byte) 1) > 0) {
				area.fillIn(sStack.pop((byte) 1));
			} else {
				log.warn("wrong counting in findGoodSolution: "
						+ " small squares remaining, but Stack empty");
			}
		}
		return area;
	}

}
