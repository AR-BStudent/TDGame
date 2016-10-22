package com.utility;

import java.util.Random;

public class RandomUtil {

	public static int randIntRange(int start, int end) {
		Random r = new Random();
		return r.nextInt(end - start + 1) + end;
	}

	public static float randFloatRange(int start, int end) {
		Random r = new Random();
		return r.nextFloat() * (end - start) + start;
	}

}
