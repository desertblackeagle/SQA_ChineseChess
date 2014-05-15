package data.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Chess extends JComponent implements Serializable {
	private String chessPath = "", chessName = "";
	private ImageIcon icon;
	private Image img;
	private int grid;
	private boolean dead = false;
	private int chessLocX, chessLocY;
	private int chessToX, chessToY;
	private ChessBoardLocation chessBoardLoc;
	private int color;

	public Chess(Point point, int chessLocX, int chessLocY, int grid, int color, String chessPath, String chessName, ChessBoardLocation chessBoardLoc) {
		// TODO Auto-generated constructor stub
		this.chessBoardLoc = chessBoardLoc;
		this.chessName = chessName;
		this.chessLocX = chessLocX;
		this.chessLocY = chessLocY;
		this.color = color;
		this.grid = grid;
		this.chessPath = chessPath;
		setBounds(((int) point.getX()), ((int) point.getY()), 60, 60);
		setBounds(((int) point.getX()), ((int) point.getY()), grid * 4 / 5, grid * 4 / 5);
		setBackground(Color.black);
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(227, 33, 34));
		java.net.URL imUrl = getClass().getResource("/image/chess/");
		String path = imUrl.toString() + chessPath;
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		icon = new ImageIcon(url);
		img = icon.getImage();
		g.drawImage(img, 0, 0, grid * 4 / 5, grid * 4 / 5, this);
	}

	public void goBack() {
		setLocation(chessBoardLoc.getAbsLocOnBoard(chessLocX, chessLocY));
	}

	public void setChessToXY(int x, int y) {
		setLocation(chessBoardLoc.getAbsLocOnBoard(x, y));
	}

	public int getGrid() {
		return grid;
	}

	public String getChessName() {
		return chessName;
	}

	public int getAbsLocationX() {
		return getLocation().x;
	}

	public int getAbsLocationY() {
		return getLocation().y;
	}

	public int getChessLocX() {
		return chessLocX;
	}

	public int getChessLocY() {
		return chessLocY;
	}

	public void setChessLocX(int chessLocX) {
		this.chessLocX = chessLocX;
	}

	public void setChessLocY(int chessLocY) {
		this.chessLocY = chessLocY;
	}

	public ChessBoardLocation getChessBoardLoc() {
		return chessBoardLoc;
	}

	public int getColor() {
		return color;
	}

	public int getChessToX() {
		return chessToX;
	}

	public void setChessToX(int chessToX) {
		this.chessToX = chessToX;
	}

	public int getChessToY() {
		return chessToY;
	}

	public void setChessToY(int chessToY) {
		this.chessToY = chessToY;
	}

}
