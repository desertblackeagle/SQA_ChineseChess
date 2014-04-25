package data;

import data.chess.ChessBoardLocation;
import data.chess.ChessTable;

public class MainData {
	private ChessBoardLocation chessBoardLoc;
	private ChessTable chessTable;

	public MainData(int width, int height) {
		// TODO Auto-generated constructor stub
		chessBoardLoc = new ChessBoardLocation(width, height);
		chessTable = new ChessTable(chessBoardLoc);
	}

	public ChessBoardLocation getChessBoardLoc() {
		return chessBoardLoc;
	}

	public ChessTable getChessTable() {
		return chessTable;
	}

}
