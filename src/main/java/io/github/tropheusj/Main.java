package io.github.tropheusj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Properties;

public class Main {
	public static void main(String[] args) {
		ClassLoader loader = Main.class.getClassLoader();
		Properties config = new Properties();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		config.setProperty("year", String.valueOf(year));
		config.setProperty("day", String.valueOf(day));
		try {
			config.load(loader.getResourceAsStream("config.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		year = Integer.parseInt(config.getProperty("year"));
		day = Integer.parseInt(config.getProperty("day"));
		System.out.printf("Running AOC %s day %s\n", year, day);
		System.out.println("(To run a different day, edit config.properties)");
		System.out.println();
		try {
			Class<?> code = Class.forName("io.github.tropheusj.aoc_%s.Day%s".formatted(year, day));
			Method run = code.getDeclaredMethod("main", String[].class);
			run.invoke(null, (Object) args);
		} catch (ClassNotFoundException e) {
			System.out.println("No code found for this day.");
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("No main method found for this day!", e);
		} catch (InvocationTargetException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
