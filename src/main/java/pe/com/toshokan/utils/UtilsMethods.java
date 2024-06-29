package pe.com.toshokan.utils;

import java.util.ArrayList;
import java.util.List;

public class UtilsMethods {

	public static List<Integer> random5NumbersLib() {

		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			int number = (int) (Math.random() * ((45 - 2) + 1)) + 5;
			numbers.add(number);
		}
		return numbers;
	}

}
