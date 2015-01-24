package runnable;

import logic.AbstractAlgo;
import data.Solution;

public interface SolutionManager {

	public void solve();

	public int getCurrentBestPerimeter();

	public void addSolution(Solution sol,
			Class<? extends AbstractAlgo> algorithm);

}
