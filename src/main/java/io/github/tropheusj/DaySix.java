package io.github.tropheusj;

import java.util.ArrayList;
import java.util.List;

public class DaySix {
	public static void main(String[] args) {
		String data = Data.get(6);
		Outer:
		for (int i = 3; i < data.length(); i++) {
			List<Character> chars = new ArrayList<>();
			for (int offsetBack = 0; offsetBack < 14; offsetBack++) {
				char c = data.charAt(i - offsetBack);
				if (chars.contains(c)) {
					if (offsetBack == 3) {
						// packet start
						System.out.println("Found packet marker end at " + (i + 1));
					}
					continue Outer;
				}
				chars.add(c);
			}
			System.out.println("Found message marker end at " + (i + 1));
			break;
		}
	}
}
