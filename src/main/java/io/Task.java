package io;

import java.io.Serializable;

import data.Solution;
import error.SolverException;

/**
 * class which represents a Task. Optionally contains a solution for this task.
 * 
 * @author Fabian
 * 
 */
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6396642877863629703L;

	private int[] squares = new int[9];
	private Solution solution = null;

	public Task() {

	}

	public void set(int size, int num) {
		if (size > 9 || size < 1) {
			throw new SolverException("size of square can not exceed [1..9]");
		}
		squares[size - 1] = num;
	}

	public int get(int size) {
		if (size > 9 || size < 1) {
			throw new SolverException("size of square can not exceed [1..9]");
		}
		return squares[size - 1];
	}

	public void setSolution(Solution solution) {
		this.solution = solution.clone();
	}

	public Solution getSolution() {
		return solution.clone();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Task is");
		for (int i = 1; i < 10; i++) {
			sb.append(" " + get(i));
		}
		if (solution != null) {
			sb.append("# Solution is\n");
			sb.append(solution.toString());
		}
		return sb.toString();
	}

	public int getTotalSquareCount() {
		int result = 0;
		for (int count : squares) {
			result += count;
		}
		return result;
	}
}
