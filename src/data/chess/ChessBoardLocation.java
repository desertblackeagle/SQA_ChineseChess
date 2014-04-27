package data.chess;

import java.awt.Point;

public class ChessBoardLocation {
	private Point[][] location;
	private int gridLength;
	private int weightFromPanelEdge, heightFromPanelEdge;

	public ChessBoardLocation(int width, int height) {
//		 TODO Auto-generated constructor stub
		location = new Point[10][9];
		allocationSpace(width - 60, height - 60);
		calcLocation();
	}

	private void allocationSpace(int width, int height) {
		gridLength = Math.min(height / 9, width / 8);
		weightFromPanelEdge = (width + 60 - 8 * gridLength) / 2;
		heightFromPanelEdge = (height + 60 - 9 * gridLength) / 2;
	}

	private void calcLocation() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				location[i][j] = new Point();
				location[i][j].x = weightFromPanelEdge + j * gridLength - gridLength * 2 / 5;
				location[i][j].y = heightFromPanelEdge + i * gridLength - gridLength * 2 / 5;
			}
		}

//		for (int i = 0; i < 10; i++) {
//			for (int j = 0; j < 9; j++) {
//				System.out.print("[" + location[i][j].x + "," + location[i][j].y + "]  \t  ");
//			}
//			System.out.println();
//		}
	}

	public int getGridLength() {
		return gridLength;
	}

	public Point getAbsLocOnBoard(int x, int y) {
		if (x < 0 || x > 8 || y < 0 || y > 9) {
			System.out.println("Error : ");
		}
		return location[y][x];
	}

//	public static void main(String[] args) {
//		ChessBoardLocation c = new ChessBoardLocation(848, 790);
//	}

}
