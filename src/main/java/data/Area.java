package data;

import io.IoUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import error.SolverException;

/**
 * this class represents a two-dimensional coordinate-system and contains some
 * methods to fill it with squares
 * 
 * @author Fabian
 * 
 */
public class Area implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4461204975797350322L;
	private byte[][] system;
	private int _x;
	private int _y;

	public Area(int x, int y) {
		system = new byte[y][x];
		_x = x;
		_y = y;
	}

	/**
	 * fills a square in the area with the given size:<br>
	 * xxx<br>
	 * xxx<br>
	 * oxx<br>
	 * 
	 * @param downLeft
	 *            at which coordinates the corner of the square should end up
	 * @param size
	 *            the size of the square inserted
	 */
	private void fillSquare(Coord downLeft, byte size) {
		for (int y = downLeft.y - size + 1; y <= downLeft.y; y++) {
			for (int x = downLeft.x; x < downLeft.x + size; x++) {
				system[y][x] = size;
			}
		}
	}

	/**
	 * inserts a square into the area the tetris way:<br>
	 * xxx<br>
	 * xxx<br>
	 * oxx<br>
	 * o refers to the referencepoint of the square and will end up in the
	 * "left" column
	 * 
	 * @param left
	 * @param size
	 * @return if the square could be dropped
	 */
	public boolean dropTo(int left, byte size) {
		if (left + size > _x) {
			return false;
		}
		int x = left;
		int y;
		outer: for (y = 0; y < _y; y++) {
			for (x = left; x < left + size; x++) {
				if (system[y][x] != 0) {
					break outer;
				}
			}
		}
		// don't fill in line of collision
		y--;
		if (y - size > -2) {
			fillSquare(new Coord(left, y), size);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * finds the index where a square can be dropped the deepest into the system
	 * 
	 * @param size
	 *            of the square to drop
	 * @return index of left corner
	 */
	public int findDeapestDrop(byte size) {
		int deepestX = -1;
		int deepestY = -1;
		for (int currentX = 0; currentX <= _x - size; currentX++) {
			int currentY = getDropY(currentX, size);
			if (currentY > deepestY) {
				deepestY = currentY;
				deepestX = currentX;
			}
		}
		return deepestX;
	}

	/**
	 * looks for the deepest free line in this area<br>
	 * 
	 * <pre>
	 * --0-1-2-3-4-5-6-<br>
	 * 0|2 2 # # # # #<br>
	 * 1|2 2 # # # # #<br>
	 * 2|3 3 3 # # # #<br>
	 * 3|3 3 3 # # 2 2<br>
	 * 4|3 3 3 1 1 2 2<br>
	 * </pre>
	 * 
	 * in this case Line(leftIndex=3,length=2) would be returned
	 * 
	 * @param ignoreFromBottom
	 *            how many lines should be ignored for the search counted from
	 *            the bottom
	 * @return deepest {@link Line}
	 */
	public Line findDeapestLine(int ignoreFromBottom) {
		int left = -1;
		for (int y = _y - ignoreFromBottom - 2; y >= 0; y--) {
			int deltaX = 0;
			for (int x = 0; x < _x; x++) {
				if (system[y][x] != 0 && left > 0) {
					break;
				}
				if (system[y][x] == 0) {
					if (left < 0) {
						left = x;
					}
					deltaX++;
				}
			}
			if (deltaX > 0) {
				return new Line(left, deltaX);
			}
		}
		throw new SolverException(
				"findDeepestLine failed. Maybe 'above' too large?");
	}

	/**
	 * checks, how deep a square can be dropped at a certain position
	 * 
	 * @param left
	 *            x-coordinate where left corner should be dropped
	 * @param size
	 *            of the square
	 * @return y-coordinate where left corner would end up
	 */
	private int getDropY(int left, byte size) {
		if (left + size > _x) {
			return -1;
		}
		int x = left;
		// assume no squares in area at all
		int yFinal = _y;
		// look for squares
		outer: for (int y = 0; y < _y; y++) {
			for (x = left; x < left + size; x++) {
				if (system[y][x] != 0) {
					yFinal = y;
					break outer;
				}
			}
		}
		return yFinal;
	}

	@Override
	public Area clone() {
		Area other = new Area(_x, _y);
		other.system = this.system.clone();
		return other;
	}

	/**
	 * Do not modify return value!
	 * 
	 * @return a reference to the internally used coordsystem
	 */
	public byte[][] getSystem() {
		return system;
	}

	public String toString() {
		return IoUtil.toString(system);
	}

	/**
	 * analyzes the area from bottom to top. If it finds an empty area
	 * surrounded by other squares, the method fills it up with the given
	 * square. Falls back to deepest drop in the case that there is no "hole"
	 * example:<br>
	 * area is in following state, method is executed with square of size 2
	 * 
	 * <pre>
	 * --0-1-2-3-4-5-6-<br>
	 * 0|2 2 2 2 2 2 #<br>
	 * 1|2 2 2 2 2 2 #<br>
	 * 2|3 3 3 # # 1 #<br>
	 * 3|3 3 3 # # 2 2<br>
	 * 4|3 3 3 1 1 2 2<br>
	 * </pre>
	 * 
	 * --> insert square of size 2
	 * 
	 * <pre>
	 * --0-1-2-3-4-5-6-<br>
	 * 0|2 2 2 2 2 2 #<br>
	 * 1|2 2 2 2 2 2 #<br>
	 * 2|3 3 3 2 2 1 #<br>
	 * 3|3 3 3 2 2 2 2<br>
	 * 4|3 3 3 1 1 2 2<br>
	 * </pre>
	 * 
	 * @param size
	 */
	public void fillIn(byte size) {
		boolean matchFound = false;
		List<Coord> leftlows = new ArrayList<Coord>();
		for (int y = _y - 1; y > -1; y--) {
			for (int x = 0; x < _x; x++) {
				if (system[y][x] != 0) {
					// a left margin is found
					for (int x2 = x + 1; x2 < _x; x2++) {
						if (system[y][x2] == 0) {
							// free space after left margin found
							for (int x3 = x2 + 1; x3 < _x; x3++) {
								if (system[y][x3] != 0) {
									// right margin after free space found
									// enclosed space with left corner (x2,y)
									leftlows.add(new Coord(x2, y));
									break;
								}
							}
						}
					}
					break;
				}
			}
		}
		for (Coord c : leftlows) {
			if (isFreeSpaceAt(c, size)) {
				fillSquare(c, size);
				matchFound = true;
				break;
			}
		}
		if (!matchFound) {
			int left = findDeapestDrop(size);
			dropTo(left, size);
		}
	}

	/**
	 * checks whether there is enough space in the area to insert the given
	 * square at the given position.
	 * 
	 * @param c
	 * @param size
	 * @return
	 */
	public boolean isFreeSpaceAt(Coord c, byte size) {
		for (int y = c.y; y > c.y - size; y--) {
			for (int x = c.x; x < c.x + size; x++) {
				if (system[y][x] != 0) {
					return false;
				}
			}
		}
		return true;
	}

}
