package vttp.batch5.sdf.task02;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {

		if (args.length <= 0) {
			System.err.print("No TTT configuration file is provided");
			System.exit(1);
		}

		board b = new board("task02/TTT/" + args[0]);
		char[][] tttboard = b.readFile();
		b.printBoard();

		TicTacToe.possibleMoves(tttboard);
	}

	
}
