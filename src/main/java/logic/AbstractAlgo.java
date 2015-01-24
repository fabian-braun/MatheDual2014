package logic;

import runnable.SolutionManager;
import data.Area;
import data.Solution;

public abstract class AbstractAlgo implements Runnable {

	protected SolutionManager sm;
	private int cacheCounter = 100;
	private int cachePerimeter;

	public void setSolutionManager(SolutionManager sm) {
		this.sm = sm;
	}

	private final int getCurrMinPerimeter() {
		cacheCounter++;
		if (cacheCounter > 99) {
			cacheCounter = 0;
			cachePerimeter = sm.getCurrentBestPerimeter();
		}
		return cachePerimeter;
	}

	protected final void propagateSolution(Area area,
			Class<? extends AbstractAlgo> algorithm) {
		Solution sol = new Solution(area);
		if (getCurrMinPerimeter() > sol.getPerimeter()) {
			cachePerimeter = sol.getPerimeter();
			sm.addSolution(sol, algorithm);
		}
	}
}
