package ext;

public class EvaluationStandard extends EvaluationFunction {

	@Override
	public int evaluate(int[][] a, int[] squares) throws Exception {

		if (!check(a, squares)) {
			throw new Exception("Fehlerhafte Ausgabedatei.");
		}

		int[][] boundingBox;

		try {
			boundingBox = createBoundingBoxWithZeros(a);
		} catch (Exception e) {
			throw new Exception("Fehlerhafte Ausgabedatei.");
		}

		int edges = 0;
		int sum, row, col;

		for (row = 1; row < boundingBox.length; row++) {
			for (col = 1; col < boundingBox[row].length; col++) {
				sum = boundingBox[row][col - 1] + boundingBox[row][col];

				if (sum != 0
						&& (sum == boundingBox[row][col - 1] || sum == boundingBox[row][col])) {
					edges++;
				}

				sum = boundingBox[row - 1][col] + boundingBox[row][col];

				if (sum != 0
						&& (sum == boundingBox[row - 1][col] || sum == boundingBox[row][col])) {
					edges++;
				}

			}
		}

		return edges;
	}

	public int[][] createBoundingBoxWithZeros(int[][] a) {
		int[][] val = super.createBoundingBox(a);

		int[][] retVal = new int[val.length + 2][val[0].length + 2];

		for (int i = 0; i < retVal.length; i++) {
			for (int j = 0; j < retVal[i].length; j++) {
				if (i == 0 || i + 1 == retVal.length || j == 0
						|| j + 1 == retVal[i].length) {
					retVal[i][j] = 0;
				} else {
					retVal[i][j] = val[i - 1][j - 1];
				}

			}
		}

		return retVal;
	}
}