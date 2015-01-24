package io;


public class IoUtil {

	public static String toString(byte[][] field) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		int yFrom = 0;
		// look for first line that contains square
		outer: for (int y = 0; y < field.length; y++) {
			for (int x = 0; x < field[0].length; x++) {
				if (field[y][x] != 0) {
					yFrom = y;
					break outer;
				}
			}
		}
		for (int y = yFrom; y < field.length; y++) {
			for (int x = 0; x < field[0].length; x++) {
				sb.append(field[y][x]);
			}
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
