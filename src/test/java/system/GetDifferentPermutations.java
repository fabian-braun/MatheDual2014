package system;

import io.Task;

import java.util.Arrays;
import java.util.List;

import logic.LogicUtil;

import org.junit.BeforeClass;
import org.junit.Test;

public class GetDifferentPermutations {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		Task t = new Task();
		t.set(1, 3);
		t.set(2, 2);
		t.set(3, 5);
		List<byte[]> perm = LogicUtil.getPermutations(t);
		for (byte[] bs : perm) {
			System.out.println(Arrays.toString(bs));
		}

	}

}
