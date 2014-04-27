package data.chess;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;

import ui.playRoom.chessBoard.ChineseChessBoard;

public class ChessBox extends Observable {
	private Chess redHorse1;
	private Chess redHorse2;
	private Chess blackHorse1;
	private Chess blackHorse2;
	private Chess redRook1;
	private Chess redRook2;
	private Chess blackRook1;
	private Chess blackRook2;
	private Chess redCannon1;
	private Chess redCannon2;
	private Chess blackCannon1;
	private Chess blackCannon2;
	private Chess redPawn1;
	private Chess redPawn2;
	private Chess redPawn3;
	private Chess redPawn4;
	private Chess redPawn5;
	private Chess blackPawn1;
	private Chess blackPawn2;
	private Chess blackPawn3;
	private Chess blackPawn4;
	private Chess blackPawn5;
	private Chess redKing;
	private Chess blackKing;
	private Chess redElephant1;
	private Chess redElephant2;
	private Chess blackElephant1;
	private Chess blackElephant2;
	private Chess redWarrior1;
	private Chess redWarrior2;
	private Chess blackWarrior1;
	private Chess blackWarrior2;
	private ChessBoardLocation chessBoardLoc;
	private String photoSubPath = "C:/sqa/chess/";
	private ArrayList<Chess> chessBoxList;

	public ChessBox(ChessBoardLocation chessBoardLoc) {
		this.chessBoardLoc = chessBoardLoc;
		chessBoxList = new ArrayList<Chess>();
		// TODO Auto-generated constructor stub
	}

	public void initChess() {
		redHorse1 = new Chess(chessBoardLoc.getAbsLocOnBoard(1, 9), 1, 9, chessBoardLoc.getGridLength(), photoSubPath + "redHorse.png", "redHorse1");
		redHorse2 = new Chess(chessBoardLoc.getAbsLocOnBoard(7, 9), 7, 9, chessBoardLoc.getGridLength(), photoSubPath + "redHorse.png", "redHorse2");
		blackHorse1 = new Chess(chessBoardLoc.getAbsLocOnBoard(1, 0), 1, 0, chessBoardLoc.getGridLength(), photoSubPath + "blackHorse.png", "blackHorse1");
		blackHorse2 = new Chess(chessBoardLoc.getAbsLocOnBoard(7, 0), 7, 0, chessBoardLoc.getGridLength(), photoSubPath + "blackHorse.png", "blackHorse2");
		redRook1 = new Chess(chessBoardLoc.getAbsLocOnBoard(0, 9), 0, 9, chessBoardLoc.getGridLength(), photoSubPath + "redRook.png", "redRook1");
		redRook2 = new Chess(chessBoardLoc.getAbsLocOnBoard(8, 9), 8, 9, chessBoardLoc.getGridLength(), photoSubPath + "redRook.png", "redRook2");
		blackRook1 = new Chess(chessBoardLoc.getAbsLocOnBoard(0, 0), 0, 0, chessBoardLoc.getGridLength(), photoSubPath + "blackRook.png", "blackRook1");
		blackRook2 = new Chess(chessBoardLoc.getAbsLocOnBoard(8, 0), 8, 0, chessBoardLoc.getGridLength(), photoSubPath + "blackRook.png", "blackRook2");
		redCannon1 = new Chess(chessBoardLoc.getAbsLocOnBoard(1, 7), 1, 7, chessBoardLoc.getGridLength(), photoSubPath + "redCannon.png", "redCannon1");
		redCannon2 = new Chess(chessBoardLoc.getAbsLocOnBoard(7, 7), 7, 7, chessBoardLoc.getGridLength(), photoSubPath + "redCannon.png", "redCannon2");
		blackCannon1 = new Chess(chessBoardLoc.getAbsLocOnBoard(1, 2), 1, 2, chessBoardLoc.getGridLength(), photoSubPath + "blackCannon.png", "blackCannon1");
		blackCannon2 = new Chess(chessBoardLoc.getAbsLocOnBoard(7, 2), 7, 2, chessBoardLoc.getGridLength(), photoSubPath + "blackCannon.png", "blackCannon2");
		redPawn1 = new Chess(chessBoardLoc.getAbsLocOnBoard(0, 6), 0, 6, chessBoardLoc.getGridLength(), photoSubPath + "redPawn.png", "redPawn1");
		redPawn2 = new Chess(chessBoardLoc.getAbsLocOnBoard(2, 6), 2, 6, chessBoardLoc.getGridLength(), photoSubPath + "redPawn.png", "redPawn2");
		redPawn3 = new Chess(chessBoardLoc.getAbsLocOnBoard(4, 6), 4, 6, chessBoardLoc.getGridLength(), photoSubPath + "redPawn.png", "redPawn3");
		redPawn4 = new Chess(chessBoardLoc.getAbsLocOnBoard(6, 6), 6, 6, chessBoardLoc.getGridLength(), photoSubPath + "redPawn.png", "redPawn4");
		redPawn5 = new Chess(chessBoardLoc.getAbsLocOnBoard(8, 6), 8, 6, chessBoardLoc.getGridLength(), photoSubPath + "redPawn.png", "redPawn5");
		blackPawn1 = new Chess(chessBoardLoc.getAbsLocOnBoard(0, 3), 0, 3, chessBoardLoc.getGridLength(), photoSubPath + "blackPawn.png", "blackPawn1");
		blackPawn2 = new Chess(chessBoardLoc.getAbsLocOnBoard(2, 3), 2, 3, chessBoardLoc.getGridLength(), photoSubPath + "blackPawn.png", "blackPawn2");
		blackPawn3 = new Chess(chessBoardLoc.getAbsLocOnBoard(4, 3), 4, 3, chessBoardLoc.getGridLength(), photoSubPath + "blackPawn.png", "blackPawn3");
		blackPawn4 = new Chess(chessBoardLoc.getAbsLocOnBoard(6, 3), 6, 3, chessBoardLoc.getGridLength(), photoSubPath + "blackPawn.png", "blackPawn4");
		blackPawn5 = new Chess(chessBoardLoc.getAbsLocOnBoard(8, 3), 8, 3, chessBoardLoc.getGridLength(), photoSubPath + "blackPawn.png", "blackPawn5");
		redKing = new Chess(chessBoardLoc.getAbsLocOnBoard(4, 9), 4, 9, chessBoardLoc.getGridLength(), photoSubPath + "redKing.png", "redKing");
		blackKing = new Chess(chessBoardLoc.getAbsLocOnBoard(4, 0), 4, 0, chessBoardLoc.getGridLength(), photoSubPath + "blackKing.png", "blackKing");
		redElephant1 = new Chess(chessBoardLoc.getAbsLocOnBoard(2, 9), 2, 9, chessBoardLoc.getGridLength(), photoSubPath + "redElephant.png", "redElephant1");
		redElephant2 = new Chess(chessBoardLoc.getAbsLocOnBoard(6, 9), 6, 9, chessBoardLoc.getGridLength(), photoSubPath + "redElephant.png", "redElephant2");
		blackElephant1 = new Chess(chessBoardLoc.getAbsLocOnBoard(2, 0), 2, 0, chessBoardLoc.getGridLength(), photoSubPath + "blackElephant.png", "blackElephant1");
		blackElephant2 = new Chess(chessBoardLoc.getAbsLocOnBoard(6, 0), 6, 0, chessBoardLoc.getGridLength(), photoSubPath + "blackElephant.png", "blackElephant2");
		redWarrior1 = new Chess(chessBoardLoc.getAbsLocOnBoard(3, 9), 3, 9, chessBoardLoc.getGridLength(), photoSubPath + "redWarrior.png", "redWarrior1");
		redWarrior2 = new Chess(chessBoardLoc.getAbsLocOnBoard(5, 9), 5, 9, chessBoardLoc.getGridLength(), photoSubPath + "redWarrior.png", "redWarrior2");
		blackWarrior1 = new Chess(chessBoardLoc.getAbsLocOnBoard(3, 0), 3, 0, chessBoardLoc.getGridLength(), photoSubPath + "blackWarrior.png", "blackWarrior1");
		blackWarrior2 = new Chess(chessBoardLoc.getAbsLocOnBoard(5, 0), 5, 0, chessBoardLoc.getGridLength(), photoSubPath + "blackWarrior.png", "blackWarrior2");

		chessBoxList.add(redHorse1);
		chessBoxList.add(redHorse2);
		chessBoxList.add(blackHorse1);
		chessBoxList.add(blackHorse2);
		chessBoxList.add(redRook1);
		chessBoxList.add(redRook2);
		chessBoxList.add(blackRook1);
		chessBoxList.add(blackRook2);
		chessBoxList.add(redCannon1);
		chessBoxList.add(redCannon2);
		chessBoxList.add(blackCannon1);
		chessBoxList.add(blackCannon2);
		chessBoxList.add(redPawn1);
		chessBoxList.add(redPawn2);
		chessBoxList.add(redPawn3);
		chessBoxList.add(redPawn4);
		chessBoxList.add(redPawn5);
		chessBoxList.add(blackPawn1);
		chessBoxList.add(blackPawn2);
		chessBoxList.add(blackPawn3);
		chessBoxList.add(blackPawn4);
		chessBoxList.add(blackPawn5);
		chessBoxList.add(redKing);
		chessBoxList.add(blackKing);
		chessBoxList.add(redElephant1);
		chessBoxList.add(redElephant2);
		chessBoxList.add(blackElephant1);
		chessBoxList.add(blackElephant2);
		chessBoxList.add(redWarrior1);
		chessBoxList.add(redWarrior2);
		chessBoxList.add(blackWarrior1);
		chessBoxList.add(blackWarrior2);

		setChanged();
		notifyObservers(chessBoxList);
	}

	public ArrayList<Chess> getChessBox() {
		return chessBoxList;
	}
}
