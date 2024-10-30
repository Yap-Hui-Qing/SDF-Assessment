package vttp.batch5.sdf.task02;

import java.io.*;

public class board {
    
    private final String tttFile;
	private char[][] board = null;
    private int width = 3;
    private int height = 3;

	public board (String ttt){
        tttFile = ttt;
    }

    // read a ttt file
    public char[][] readFile() throws Exception{
        board = initializeBoard(3, 3);
        Reader fis = new FileReader(tttFile);
        BufferedReader br = new BufferedReader(fis);
        return populateBoard(br);
    }
        
    private char[][] initializeBoard(int width, int height){
        char[][] board = new char[height][width];
        for (int y = 0; y<height; y++){
            board[y] = Constants.blank.substring(0,width).toCharArray();
        }
        return board;
    }

	// populate the board
	private char[][] populateBoard(BufferedReader br) throws Exception {
		String line;
        int y = 0;
		while ((line = br.readLine()) != null) {
			char[] data = line.toCharArray();
            board[y] = data;
            y++;
		}
        return board;
	}

    // print the board
    public void printBoard(){
        System.out.println("Board: ");
        for (int i = 0; i < height; i++){
            System.out.printf("%s\n", new String(board[i]));
        }
    }

}
