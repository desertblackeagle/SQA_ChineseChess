package data.chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class Chess extends JComponent {
	private String path = "", chessName = "";
	private ImageIcon icon;
	private Image img;
	private int grid;
	private boolean dead = false;
	private int chessLocX, chessLocY;

	public Chess(Point point, int chessLocX, int chessLocY, int grid, String path, String chessName) {
		// TODO Auto-generated constructor stub
		this.chessName = chessName;
		this.chessLocX = chessLocX;
		this.chessLocY = chessLocY;
		this.grid = grid;
		this.path = path;
		setBounds(((int) point.getX()), ((int) point.getY()), 60, 60);
		setBounds(((int) point.getX()), ((int) point.getY()), grid * 4 / 5, grid * 4 / 5);
		setBackground(Color.black);
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(227, 33, 34));
		icon = new ImageIcon(path);
		img = icon.getImage();
		g.drawImage(img, 0, 0, grid * 4 / 5, grid * 4 / 5, this);

	}

	public String getChessName() {
		return chessName;
	}

	public int getChessLocX() {
		return chessLocX;
	}

	public int getChessLocY() {
		return chessLocY;
	}

}
