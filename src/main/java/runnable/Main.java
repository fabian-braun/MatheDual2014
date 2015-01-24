package runnable;

import io.Task;
import io.TaskReader;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		if (args.length < 1) {
			log.error("Program was not executed correctly:\nPlease execute the program via 'java -jar solver.jar [file containing exercise to solve]'");
			System.exit(-1);
		}

		File f = new File(args[0]);
		TaskReader tr = new TaskReader();
		Task t = null;
		try {
			t = tr.readTask(f);
		} catch (Exception e) {
			log.error(e.getMessage());
			System.exit(-1);
		}
		SolutionManager sm = new SolutionManagerMultithreaded(t);
		sm.solve();
	}
}
