package data;

import java.util.Stack;

/**
 * Stackcontainer which wraps 9 Stacks for the different possible sizes
 * 
 * @author Fabian
 * 
 */
public class SizeStack implements Cloneable {

	private byte smallUpTo = -1;
	private int numSmallSquares = 0;
	private int numLargeSquares = 0;

	private Stack<Byte> s1 = new Stack<Byte>();
	private Stack<Byte> s2 = new Stack<Byte>();
	private Stack<Byte> s3 = new Stack<Byte>();
	private Stack<Byte> s4 = new Stack<Byte>();
	private Stack<Byte> s5 = new Stack<Byte>();
	private Stack<Byte> s6 = new Stack<Byte>();
	private Stack<Byte> s7 = new Stack<Byte>();
	private Stack<Byte> s8 = new Stack<Byte>();
	private Stack<Byte> s9 = new Stack<Byte>();

	public SizeStack() {

	}

	@Override
	public SizeStack clone() {
		SizeStack other = new SizeStack();
		other.setSmallUpTo(this.smallUpTo);
		for (byte i = 1; i < 10; i++) {
			int j = getAvailable(i);
			for (int j2 = 0; j2 < j; j2++) {
				other.push(i);
			}
		}
		return other;

	}

	public boolean largeAvailable() {
		return numLargeSquares > 0;
	}

	public boolean smallAvailable() {
		return numSmallSquares > 0;
	}

	public void push(byte size) {
		if (size < 1 || size > 9) {
			return;
		}
		if (size > smallUpTo) {
			numLargeSquares++;
		} else {
			numSmallSquares++;
		}
		switch (size) {
		case 1:
			s1.push(size);
			break;
		case 2:
			s2.push(size);
			break;
		case 3:
			s3.push(size);
			break;
		case 4:
			s4.push(size);
			break;
		case 5:
			s5.push(size);
			break;
		case 6:
			s6.push(size);
			break;
		case 7:
			s7.push(size);
			break;
		case 8:
			s8.push(size);
			break;
		case 9:
			s9.push(size);
			break;
		}
	}

	public void setSmallUpTo(byte smallUpTo) {
		this.smallUpTo = smallUpTo;
	}

	public int getAvailable(byte size) {
		switch (size) {
		case 1:
			return s1.size();
		case 2:
			return s2.size();
		case 3:
			return s3.size();
		case 4:
			return s4.size();
		case 5:
			return s5.size();
		case 6:
			return s6.size();
		case 7:
			return s7.size();
		case 8:
			return s8.size();
		case 9:
			return s9.size();
		default:
			return 0;
		}
	}

	public byte pop(byte size) {
		if (size > smallUpTo) {
			numLargeSquares--;
		} else {
			numSmallSquares--;
		}
		switch (size) {
		case 1:
			return s1.pop();
		case 2:
			return s2.pop();
		case 3:
			return s3.pop();
		case 4:
			return s4.pop();
		case 5:
			return s5.pop();
		case 6:
			return s6.pop();
		case 7:
			return s7.pop();
		case 8:
			return s8.pop();
		case 9:
			return s9.pop();
		default:
			return 0;
		}
	}

	public byte getLargest() {
		if (!s9.empty()) {
			if (9 > smallUpTo) {
				numLargeSquares--;
			} else {
				numSmallSquares--;
			}
			return s9.pop();
		} else if (!s8.empty()) {
			if (8 > smallUpTo) {
				numLargeSquares--;
			} else {
				numSmallSquares--;
			}
			return s8.pop();
		} else if (!s7.empty()) {
			if (7 > smallUpTo) {
				numLargeSquares--;
			} else {
				numSmallSquares--;
			}
			return s7.pop();
		} else if (!s6.empty()) {
			if (6 > smallUpTo) {
				numLargeSquares--;
			} else {
				numSmallSquares--;
			}
			return s6.pop();
		} else if (!s5.empty()) {
			if (5 > smallUpTo) {
				numLargeSquares--;
			} else {
				numSmallSquares--;
			}
			return s5.pop();
		} else if (!s4.empty()) {
			if (4 > smallUpTo) {
				numLargeSquares--;
			} else {
				numSmallSquares--;
			}
			return s4.pop();
		} else if (!s3.empty()) {
			if (3 > smallUpTo) {
				numLargeSquares--;
			} else {
				numSmallSquares--;
			}
			return s3.pop();
		} else if (!s2.empty()) {
			if (2 > smallUpTo) {
				numLargeSquares--;
			} else {
				numSmallSquares--;
			}
			return s2.pop();
		} else if (!s1.empty()) {
			if (1 > smallUpTo) {
				numLargeSquares--;
			} else {
				numSmallSquares--;
			}
			return s1.pop();
		}
		return -1;
	}

}
