package system;

import logic.AbstractAlgo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import runnable.SolutionManager;
import data.Solution;

public class SolutionManagerMock implements SolutionManager {

	private static Logger log = LoggerFactory
			.getLogger(SolutionManagerMock.class);

	public void solve() {
		// TODO Auto-generated method stub

	}

	public int getCurrentBestPerimeter() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	public void addSolution(Solution sol,
			Class<? extends AbstractAlgo> algorithm) {
		log.debug(sol.toString());
	}

}
