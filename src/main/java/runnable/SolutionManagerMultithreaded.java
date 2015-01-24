package runnable;

import io.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import logic.AbstractAlgo;
import logic.AlgoAllPermuteUnique;
import logic.AlgoAllPermuteUniqueSmallFill;
import logic.AlgoAllShuffleSmallFill;
import logic.AlgoAllSmartFitFill;
import logic.AlgoBasicSolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Solution;

public class SolutionManagerMultithreaded implements SolutionManager {
	private static Logger log = LoggerFactory
			.getLogger(SolutionManagerMultithreaded.class);

	AtomicInteger currentBestPerimeter = new AtomicInteger(Integer.MAX_VALUE);
	Solution currentBestSolution = null;
	int lowBoundery = 0;
	boolean squarePossible = false;
	Task task;
	long startTime;
	int count;

	public SolutionManagerMultithreaded(Task t) {
		task = t;
		calculateLowBoundary(task);
		count = task.getTotalSquareCount();
	}

	public void solve() {
		log.debug("init Solvers");
		log.info("start clock");
		startTime = System.currentTimeMillis();
		// find basic solution to start off
		AlgoBasicSolution algoBasicSolution = new AlgoBasicSolution(task);
		algoBasicSolution.setSolutionManager(this);
		algoBasicSolution.run();
		// now the real action start
		List<AbstractAlgo> algorithms = new ArrayList<AbstractAlgo>();
		List<Thread> threads = new ArrayList<Thread>();

		// init algorithms depending on problem size
		if (count < 100) {
			algorithms.add(new AlgoAllPermuteUnique(task));
		}
		algorithms.add(new AlgoAllPermuteUniqueSmallFill(task));
		if (count > 140) {
			algorithms.add(new AlgoAllShuffleSmallFill(task));
		}
		algorithms.add(new AlgoAllSmartFitFill(task));

		// start threads
		for (AbstractAlgo algo : algorithms) {
			algo.setSolutionManager(this);
			Thread t1 = new Thread(algo);
			threads.add(t1);
			t1.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("Programm terminated! Exercise was too easy!");
	}

	public int getCurrentBestPerimeter() {
		return currentBestPerimeter.get();
	}

	public synchronized void setCurrentBestPerimeter(int currentBestPerimeter) {
		this.currentBestPerimeter.getAndSet(currentBestPerimeter);
	}

	public synchronized void addSolution(Solution s,
			Class<? extends AbstractAlgo> algorithm) {
		if (getCurrentBestPerimeter() > s.getPerimeter()) {
			setCurrentBestPerimeter(s.getPerimeter());
			long milliTimeDiff = System.currentTimeMillis() - startTime;
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(milliTimeDiff);
			Date time = cal.getTime();
			SimpleDateFormat ft = new SimpleDateFormat("mm:ss.SSS");
			currentBestSolution = s;
			log.info("\n# current best solution:\n# algorithm: "
					+ algorithm.getSimpleName()
					+ " \n# calculated in time: "
					+ ft.format(time)
					+ "\n"
					+ currentBestSolution
					+ "\n############### end of solution #####################################");
		}
	}

	private void calculateLowBoundary(Task t) {
		int area = 0;
		for (int i = 1; i < 10; i++) {
			area += (t.get(i) * i * i);
		}
		log.debug("total area = " + area);
		double flank = Math.sqrt(area);
		if (((int) flank) * ((int) flank) == area) {
			squarePossible = true;
			lowBoundery = 4 * ((int) flank);
			log.debug("the lowestBoundery for the solution is " + lowBoundery);
			log.debug("it is possible to form a square");
		} else {
			squarePossible = false;
			lowBoundery = 1 + ((int) (flank * 4));
			log.debug("the lowestBoundery for the solution is " + lowBoundery);
			log.debug("it is not possible to form a square");
		}
	}

}
