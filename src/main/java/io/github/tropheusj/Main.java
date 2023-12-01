package io.github.tropheusj;

import java.io.FileNotFoundException;
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
			Solution instance = (Solution) code.getConstructor().newInstance();
			Method run = code.getDeclaredMethod("run", String.class);
			String data = Data.get(year, day);
			run.invoke(instance, data);
		} catch (FileNotFoundException e) {
			System.out.println("No data found for this day.");
		} catch (ClassCastException e) {
			System.out.println("Class does not implement Solution");
		} catch (InstantiationException e) {
			System.out.println("Constructor is invalid");
		} catch (ClassNotFoundException e) {
			System.out.println("No code found for this day.");
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
