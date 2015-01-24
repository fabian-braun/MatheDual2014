package ext;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class EvaluationFunction {
	public abstract int evaluate(int[][] a, int[] squares) throws Exception;

	public static boolean check(int[][] a, int[] squares) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] > 0) {
					if (!checkSquare(a, i, j, squares)) {
						return false;
					}
				}
			}
		}

		for (int i = 0; i < squares.length; i++) {
			if (squares[i] != 0) {
				return false;
			}
		}

		return true;
	}

	public static boolean checkSquare(int[][] a, int row, int col, int[] squares) {
		try {
			int value = a[row][col];

			squares[value - 1]--;

			for (int i = 0; i < value; i++) {
				for (int j = 0; j < value; j++) {
					if (a[row + i][col + j] != value) {
						return false;
					} else {
						a[row + i][col + j] = -1;
					}

				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}

		return true;
	}

	public static int[][] createBoundingBox(int[][] a) {
		try {
			int startRow = 0, endRow = a.length - 1, startCol = 0, endCol = a[0].length - 1;
			int i;

			for (i = 0; i < a.length; i++) {
				if (!checkRowForSquare(a, i)) {
					startRow++;
				} else {
					break;
				}
			}

			for (i = a.length - 1; i >= 0; i--) {
				if (!checkRowForSquare(a, i)) {
					endRow--;
				} else {
					break;
				}
			}

			for (i = 0; i < a[0].length; i++) {
				if (!checkColForSquare(a, i)) {
					startCol++;
				} else {
					break;
				}
			}

			for (i = a[0].length - 1; i >= 0; i--) {
				if (!checkColForSquare(a, i)) {
					endCol--;
				} else {
					break;
				}
			}

			int[][] boundingBox = new int[endRow - startRow + 1][endCol
					- startCol + 1];

			for (i = 0; i < boundingBox.length; i++) {
				for (int j = 0; j < boundingBox[i].length; j++) {
					boundingBox[i][j] = a[startRow + i][startCol + j];
				}
			}

			return boundingBox;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	public static boolean checkColForSquare(int[][] a, int col) {
		try {
			for (int i = 0; i < a.length; i++) {
				if (a[i][col] != 0) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public static boolean checkRowForSquare(int[][] a, int row) {
		try {
			for (int i = 0; i < a[row].length; i++) {
				if (a[row][i] != 0) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public static int[][] loadOutputFile(String path) {
		ArrayList<String> rows = new ArrayList<String>();

		try {
			Scanner sc = new Scanner(new File(path));

			String line;
			while (sc.hasNextLine()) {
				line = sc.nextLine();

				if (!line.startsWith("#")) {
					rows.add(line);
				}
			}

			sc.close();

			int[][] retVal = new int[rows.size()][rows.get(0).length()];

			String row;
			for (int i = 0; i < rows.size(); i++) {
				row = rows.get(i);

				for (int j = 0; j < row.length(); j++) {
					retVal[i][j] = row.charAt(j) - '0';
				}
			}

			return retVal;
		} catch (Exception e) {
			return null;
		}
	}

	public static int[] loadInputFile(String path) {
		ArrayList<Integer> rows = new ArrayList<Integer>();

		try {
			Scanner sc = new Scanner(new File(path));

			String line;
			while (sc.hasNextLine()) {
				line = sc.nextLine();

				if (!line.startsWith("#")) {
					rows.add(Integer.parseInt(line));
				}
			}

			sc.close();

			int[] retVal = new int[rows.size()];

			for (int i = 0; i < rows.size(); i++) {
				retVal[i] = rows.get(i);
			}

			return retVal;
		} catch (Exception e) {
			return null;
		}
	}

}
