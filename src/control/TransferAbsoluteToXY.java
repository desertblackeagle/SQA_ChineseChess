package control;

import java.util.Observable;
import java.util.Observer;

import data.chess.Chess;

public class TransferAbsoluteToXY extends Observable implements Observer {
	private int width, height;

	public TransferAbsoluteToXY(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof Chess) {
			int locX = (((Chess) arg).getAbsLocationX() + ((Chess) arg).getWidth() / 2);
			int locY = (((Chess) arg).getAbsLocationY() + ((Chess) arg).getWidth() / 2);
//			System.out.println("Tran : " + (((Chess) arg).getAbsLocationX() + ((Chess) arg).getWidth() / 2) + " : " + (((Chess) arg).getAbsLocationY() + ((Chess) arg).getWidth() / 2));
			transfer(locX, locY, ((Chess) arg));
		}
	}

	private void transfer(int locX, int locY, Chess chess) {
//		System.out.println((locX - chess.getChessBoardLoc().getAbsLocOnBoard(0, 0).x - (chess.getGrid() * 2 / 5) + (chess.getGrid() / 2)) + " :: " + (locY - chess.getChessBoardLoc().getAbsLocOnBoard(0, 0).y - (chess.getGrid() * 2 / 5) + (chess.getGrid() / 2)));
		locX = (locX - chess.getChessBoardLoc().getAbsLocOnBoard(0, 0).x - (chess.getGrid() * 2 / 5) + (chess.getGrid() / 2));
		locY = (locY - chess.getChessBoardLoc().getAbsLocOnBoard(0, 0).y - (chess.getGrid() * 2 / 5) + (chess.getGrid() / 2));
		int x = locX / chess.getGrid();
		int y = locY / chess.getGrid();

//		System.out.println(x+" ::: "+y);
		if (locX >= 0 && locY >= 0) {
			if (x < 0 || x > 8 || y < 0 || y > 9) {
				chess.goBack();
				System.out.println("Tran error x y out of index");
			} else {
				chess.setChessToXY(x, y);
				chess.setChessToX(x);
				chess.setChessToY(y);
				if (chess.getChessLocX() == x && chess.getChessLocY() == y) {
					System.out.println("TRAN X and Y" + x + " : " + y + " but in the same way");
				} else {
					setChanged();
					notifyObservers(chess);
					System.out.println("TRAN X and Y" + x + " : " + y);
				}
			}
		} else {
			chess.goBack();
			System.out.println("Tran error x y out of index");
		}

	}
}
