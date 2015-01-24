package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import error.SolverException;

/**
 * IO-Service. Can read a {@link Task} from an input file.
 * 
 * @author Fabian
 * 
 */
public class TaskReader {

	private static Logger log = LoggerFactory.getLogger(TaskReader.class);

	public Task readTask(File input) throws FileNotFoundException {
		Scanner sc = new Scanner(input);
		Task task = new Task();
		int i = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine().trim().toLowerCase();
			if (line.contains("#")) {
				continue;
			}
			try {
				int num = Integer.parseInt(line);
				i++;
				task.set(i, num);
			} catch (NumberFormatException e) {
				log.warn("Can not parse line in input file: " + line);
			}
		}
		sc.close();

		// check if at least one value is set
		for (int i1 = 1; i1 < 10; i1++) {
			if (task.get(i1) != 0) {
				log.debug("Input file read with success");
				log.info(task.toString());
				return task;
			}
		}

		throw new SolverException("No valid task could be read from input file");

	}
}
