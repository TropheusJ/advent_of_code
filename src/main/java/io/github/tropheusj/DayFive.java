package io.github.tropheusj;

import java.util.*;

public class DayFive {
	public static void main(String[] args) {
		String data = Data.get(5);
		String[] split = data.split("\n\n");
		Stack<Character>[] singleMoveStacks = parseStacks(split[0]);
		Stack<Character>[] multiMoveStacks = parseStacks(split[0]);
		Move[] moves = parseMoves(split[1]);
		for (Move move : moves) {
			Stack<Character> fromSingle = singleMoveStacks[move.from - 1];
			Stack<Character> fromMulti = multiMoveStacks[move.from - 1];
			Stack<Character> toSingle = singleMoveStacks[move.to - 1];
			Stack<Character> toMulti = multiMoveStacks[move.to - 1];
			Stack<Character> tempMultiStack = new Stack<>();
			for (int i = 0; i < move.amount; i++) {
				toSingle.push(fromSingle.pop());
				tempMultiStack.push(fromMulti.pop());
			}
			while (!tempMultiStack.empty()) {
				toMulti.push(tempMultiStack.pop());
			}
		}
		StringBuilder out = new StringBuilder("Tops single: ");
		for (Stack<Character> stack : singleMoveStacks) {
			out.append(stack.peek());
		}
		System.out.println(out);
		out = new StringBuilder("Tops multi: ");
		for (Stack<Character> stack : multiMoveStacks) {
			out.append(stack.peek());
		}
		System.out.println(out);
	}

	private static Stack<Character>[] parseStacks(String stackData) {
		String[] rawRows = stackData.split("\n");
		// final row is indexes
		List<String> rows = new ArrayList<>(List.of(rawRows)).subList(0, rawRows.length - 1);
		// get top box on top of stack by reversing here
		Collections.reverse(rows);
		int stackCount = (rawRows[0].length() + 1) / 4;
		Stack<Character>[] stacks = new Stack[stackCount];
		for (int i = 0; i < stacks.length; i++) {
			stacks[i] = new Stack<>();
		}
		for (String row : rows) {
			//[T]     [Q]
			// ^   ^   ^
			// |   |   |
			// 0   1   2
			for (int i = 1; i < row.length(); i += 4) {
				char c = row.charAt(i);
				if (Character.isWhitespace(c))
					continue;
				int stack = i / 4;
				stacks[stack].push(c);
			}
		}
		System.out.println("Stacks:");
		for (Stack<Character> stack : stacks) {
			System.out.println(stack + " // top: " + stack.peek());
		}
		return stacks;
	}

	private static Move[] parseMoves(String moveData) {
		String[] rawMoves = moveData.split("\n");
		Move[] moves = new Move[rawMoves.length];
		for (int i = 0; i < rawMoves.length; i++) {
			// move 5 from 4 to 9
			String[] rawMove = rawMoves[i].split(" ");
			int count = Integer.parseInt(rawMove[1]);
			int from = Integer.parseInt(rawMove[3]);
			int to = Integer.parseInt(rawMove[5]);
			moves[i] = new Move(from, to, count);
		}
		System.out.println("Moves:");
		for (Move move : moves) {
			System.out.println(move);
		}
		return moves;
	}

	record Move(int from, int to, int amount) {
		@Override
		public String toString() {
			return "Move %s (%s -> %s)".formatted(amount, from, to);
		}
	}
}
