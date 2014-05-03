package data.chess;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ChessXYLocOnChessBoard implements Observer {
	private Chess[][] chessXYLocOnBoard;

	public ChessXYLocOnChessBoard() {
		// TODO Auto-generated constructor stub
		chessXYLocOnBoard = new Chess[10][9];
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof ChessBox) {
			if (arg instanceof ArrayList<?>) {
				for (Chess chess : (ArrayList<Chess>) arg) {
					chessXYLocOnBoard[chess.getChessLocY()][chess.getChessLocX()] = chess;
				}
				printXYLoc();
			}
		}
	}

	private void printXYLoc() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				if (chessXYLocOnBoard[i][j] != null) {
					System.out.print(chessXYLocOnBoard[i][j].getChessName() + "\t");
				} else {
					System.out.print("NULL" + "\t\t");
				}
			}
			System.out.println();
		}
	}

	public Chess getChess(int x, int y) {
		return chessXYLocOnBoard[y][x];
	}

	public void setChess(int x, int y, int toX, int toY) {

		if (chessXYLocOnBoard[toY][toX] != null) {
			System.out.println("eat : " + x + " " + y);
			chessXYLocOnBoard[toY][toX].setLocation(50000, 50000);
			chessXYLocOnBoard[toY][toX] = chessXYLocOnBoard[y][x];
			chessXYLocOnBoard[y][x] = null;
		} else {
			chessXYLocOnBoard[toY][toX] = chessXYLocOnBoard[y][x];
			chessXYLocOnBoard[y][x] = null;
		}
		printXYLoc();

	}
}
