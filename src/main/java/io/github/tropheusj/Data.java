package io.github.tropheusj;

import java.io.IOException;
import java.io.InputStream;

public class Data {
	public static String get(int year, int day) {
		ClassLoader loader = Data.class.getClassLoader();
		String name = "%s/day_%s.txt".formatted(year, day);
		try (InputStream stream = loader.getResourceAsStream(name)) {
			if (stream == null) {
				throw new IOException("File not found: " + name);
			}
			return new String(stream.readAllBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
