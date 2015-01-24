package data;

import io.Task;

import java.io.Serializable;

import logic.LogicUtil;

/**
 * Class, which represents the solution for the given {@link Task}
 * 
 * @author Fabian
 * 
 */
public class Solution implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 908401117773011973L;
	private Area area;
	private int perimeter;

	public Solution(Area area) {
		this.area = area.clone();
		perimeter = LogicUtil.getPerimeter(area.getSystem());
	}

	@Override
	public Solution clone() {
		Solution other = new Solution(area);
		other.perimeter = this.perimeter;
		return other;
	}

	@Override
	public String toString() {
		String result = "# total perimeter = " + perimeter + "\n# "
				+ area.toString();
		return result;
	}

	public int getPerimeter() {
		return perimeter;
	}

}
