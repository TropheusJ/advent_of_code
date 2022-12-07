package io.github.tropheusj;

public class DayTwo {
	public static final char DO_LOSE = 'X';
	public static final char DO_DRAW = 'Y';
	public static final char DO_WIN = 'Z';

	public static final char MY_ROCK = 'X';
	public static final char MY_PAPER = 'Y';
	public static final char MY_SCISSORS = 'Z';

	public static final char OPPONENT_ROCK = 'A';
	public static final char OPPONENT_PAPER = 'B';
	public static final char OPPONENT_SCISSORS = 'C';

	public static final int LOSS = 0;
	public static final int DRAW = 3;
	public static final int WIN = 6;

	public static final int ROCK_POINTS = 1;
	public static final int PAPER_POINTS = 2;
	public static final int SCISSORS_POINTS = 3;

	public static void main(String[] args) {
		String data = Data.get(2);
		String[] rounds = data.split("\n");
		int points = 0;
		for (String round : rounds) {
			char opponent = round.charAt(0);
			char result = round.charAt(2);
			char mine = getPlay(opponent, result);
			points += pointsOf(mine) + winPoints(opponent, mine);
		}
		System.out.println("total: " + points);
	}

	public static int winPoints(char opponent, char mine) {
		return switch (opponent) {
			case OPPONENT_ROCK -> switch (mine) {
				case MY_ROCK -> DRAW;
				case MY_PAPER -> WIN;
				case MY_SCISSORS -> LOSS;
				default -> throw new IllegalStateException("Unexpected value: " + mine);
			};
			case OPPONENT_PAPER -> switch (mine) {
				case MY_ROCK -> LOSS;
				case MY_PAPER -> DRAW;
				case MY_SCISSORS -> WIN;
				default -> throw new IllegalStateException("Unexpected value: " + mine);
			};
			case OPPONENT_SCISSORS -> switch (mine) {
				case MY_ROCK -> WIN;
				case MY_PAPER -> LOSS;
				case MY_SCISSORS -> DRAW;
				default -> throw new IllegalStateException("Unexpected value: " + mine);
			};
			default -> throw new IllegalStateException("Unexpected value: " + opponent);
		};
	}

	public static char getPlay(char opponent, char result) {
		return switch (result) {
			case DO_WIN -> switch (opponent) {
				case OPPONENT_ROCK -> MY_PAPER;
				case OPPONENT_PAPER -> MY_SCISSORS;
				case OPPONENT_SCISSORS -> MY_ROCK;
				default -> throw new IllegalStateException("Unexpected value: " + opponent);
			};
			case DO_DRAW -> switch (opponent) {
				case OPPONENT_ROCK -> MY_ROCK;
				case OPPONENT_PAPER -> MY_PAPER;
				case OPPONENT_SCISSORS -> MY_SCISSORS;
				default -> throw new IllegalStateException("Unexpected value: " + opponent);
			};
			case DO_LOSE -> switch (opponent) {
				case OPPONENT_ROCK -> MY_SCISSORS;
				case OPPONENT_PAPER -> MY_ROCK;
				case OPPONENT_SCISSORS -> MY_PAPER;
				default -> throw new IllegalStateException("Unexpected value: " + opponent);
			};
			default -> throw new IllegalStateException("Unexpected value: " + result);
		};
	}

	private static int pointsOf(char play) {
		return switch (play) {
			case MY_ROCK -> ROCK_POINTS;
			case MY_PAPER -> PAPER_POINTS;
			case MY_SCISSORS -> SCISSORS_POINTS;
			default -> throw new IllegalStateException("Unexpected value: " + play);
		};
	}
}
