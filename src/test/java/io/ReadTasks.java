package io;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadTasks {

	public static final String example01 = "src/test/resources/example01.in";

	private static Logger log = LoggerFactory.getLogger(ReadTasks.class);

	@Test
	public void test() throws FileNotFoundException {
		TaskReader tr = new TaskReader();
		Task t = tr.readTask(new File(example01));
		byte[][] sol = new byte[5][3];
		sol[0][0] = 3;
		sol[0][1] = 3;
		sol[0][2] = 3;
		sol[1][0] = 3;
		sol[1][1] = 3;
		sol[1][2] = 3;
		sol[2][0] = 3;
		sol[2][1] = 3;
		sol[2][2] = 3;
		sol[3][0] = 1;
		sol[3][1] = 2;
		sol[3][2] = 2;
		sol[4][0] = 1;
		sol[4][1] = 2;
		sol[4][2] = 2;
		log.debug(t.toString());
	}
}
