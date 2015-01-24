package system;

import io.Task;
import logic.AlgoLargeFitSmallFill;

import org.junit.BeforeClass;
import org.junit.Test;

import data.Area;

public class SmartFill {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		SolutionManagerMock sm = new SolutionManagerMock();
		Task t = new Task();
		t.set(1, 20);
		t.set(2, 5);
		t.set(3, 1);
		t.set(4, 1);
		t.set(5, 1);
		t.set(6, 1);
		t.set(7, 1);
		t.set(8, 1);
		t.set(9, 1);
		AlgoLargeFitSmallFill algo = new AlgoLargeFitSmallFill(t, 20, 50,
				(byte) 3);
		algo.setSolutionManager(sm);
		algo.run();
	}

	@Test
	public void testFilling() {
		Area area = new Area(12, 4);
		area.dropTo(0, (byte) 3);
		area.dropTo(4, (byte) 3);
		area.dropTo(9, (byte) 3);
		area.fillIn((byte) 1);
		area.fillIn((byte) 2);
	}
}
