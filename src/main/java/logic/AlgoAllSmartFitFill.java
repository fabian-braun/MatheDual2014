package logic;

import io.Task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlgoAllSmartFitFill extends AbstractAlgo implements Runnable {
	private static Logger log = LoggerFactory.getLogger(AlgoAllSmartFitFill.class);

	private int numSquares;
	private int minFlank;
	private int maxFlank;
	private byte[] order;
	private Task task;

	public AlgoAllSmartFitFill(Task t) {
		this.task = t;
		int surfaceArea = 0;
		for (byte i = 1; i < 10; i++) {
			numSquares += t.get(i);
			surfaceArea += t.get(i) * i * i;
		}
		minFlank = (int) (Math.sqrt(surfaceArea) * 0.9);

		maxFlank = (1 + minFlank) * 2;
		log.info("x range = [" + minFlank + "..." + maxFlank + "]");
		order = new byte[numSquares];
		int index = 0;
		for (byte i = 1; i < 10; i++) {
			int to = t.get(i) + index;
			while (index < to) {
				order[index] = i;
				index++;
			}
		}
	}

	public void run() {
		List<Thread> threads = new ArrayList<Thread>();
		for (int x = minFlank, diff = 0; x <= maxFlank; x++, diff++) {
			log.debug("span Thread for length(x)=" + x + " and length(y)="
					+ (maxFlank - diff));
			AlgoLargeFitSmallFill ap = new AlgoLargeFitSmallFill(task, x,
					maxFlank, (byte) 3);
			ap.setSolutionManager(sm);
			Thread t = new Thread(ap);
			threads.add(t);
			t.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
