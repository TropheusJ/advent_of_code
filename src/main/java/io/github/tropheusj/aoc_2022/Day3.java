package io.github.tropheusj.aoc_2022;

import io.github.tropheusj.Solution;

import java.util.ArrayList;
import java.util.List;

public class Day3 implements Solution {
	public static final int LOWER_OFFSET = 'a' - 1;
	public static final int UPPER_OFFSET = ('A' - 1) - 26;
	public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	@Override
	public void run(String data) {
		int totalPriorities = 0;
		int totalBadgePriorities = 0;
		String[] split = data.split("\n");
		List<String[]> groups = new ArrayList<>();
		String[] currentGroup = null;
		for (int i = 0; i < split.length; i++) {
			int groupIndex = i % 3;
			if (groupIndex == 0) {
				currentGroup = new String[3];
				groups.add(currentGroup);
			}
			currentGroup[groupIndex] = split[i];
		}
		for (int i = 0; i < groups.size(); i++) {
			String[] group = groups.get(i);
			char groupCommon = ' ';
			String one = group[0];
			String two = group[1];
			String three = group[2];
			for (char c : CHARS) {
				if (one.indexOf(c) != -1
				&& two.indexOf(c) != -1
				&& three.indexOf(c) != -1) {
					groupCommon = c;
					break;
				}
			}
			int badgeOffset = Character.isUpperCase(groupCommon) ? UPPER_OFFSET : LOWER_OFFSET;
			int badgePriority = groupCommon - badgeOffset;
			totalBadgePriorities += badgePriority;
			for (String rucksack : group) {
				String compartment1 = rucksack.substring(0, rucksack.length() / 2);
				String compartment2 = rucksack.substring(rucksack.length() / 2);
				char compartmentCommon = ' ';
				for (int j = 0; j < compartment2.length(); j++) {
					char c = compartment2.charAt(j);
					int c1Index = compartment1.indexOf(c);
					if (c1Index != -1) {
						compartmentCommon = c;
						break;
					}
				}
				int offset = Character.isUpperCase(compartmentCommon) ? UPPER_OFFSET : LOWER_OFFSET;
				int priority = compartmentCommon - offset;
				System.out.printf("[group %s] (%s/%s) %s: %s (%s)\n", i, compartment1, compartment2, compartmentCommon, priority, offset);
				totalPriorities += priority;
			}
		}
		System.out.println("total: " + totalPriorities);
		System.out.println("total badge priorities: " + totalBadgePriorities);
	}
}
