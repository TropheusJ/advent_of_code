package io.github.tropheusj;

public class DayFour {
	public static void main(String[] args) {
		String data = Data.get(4);
		String[] pairs = data.split("\n");
		int fullDuplicates = 0;
		int overlaps = 0;
		for (String pair : pairs) {
			String[] ranges = pair.split(",");
			Range one = Range.get(ranges[0]);
			Range two = Range.get(ranges[1]);
			if (one.contains(two) || two.contains(one)) {
				fullDuplicates++;
			}
			if (one.overlaps(two) || two.overlaps(one)) {
				overlaps++;
			}
		}
		System.out.println("full duplicates: " + fullDuplicates);
		System.out.println("overlaps: " + overlaps);
	}

	record Range(int lower, int upper) {
		public static Range get(String range) {
			String[] split = range.split("-");
			int lower = Integer.parseInt(split[0]);
			int upper = Integer.parseInt(split[1]);
			return new Range(lower, upper);
		}

		public boolean contains(Range other) {
			return this.lower <= other.lower && this.upper >= other.upper;
		}

		public boolean overlaps(Range other) {
			if (this.lower <= other.lower) {
				return this.upper >= other.lower;
			} else {
				return this.lower <= other.upper;
			}
		}
	}
}
