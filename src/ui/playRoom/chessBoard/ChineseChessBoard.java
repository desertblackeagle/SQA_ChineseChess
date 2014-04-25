package ui.playRoom.chessBoard;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import data.chess.ChessTest;

public class ChineseChessBoard extends JPanel implements MouseMotionListener, MouseListener, Observer {

	private int gridLength;
	private int weightFromPanelEdge, heightFromPanelEdge;
	private int chessBoardWidth, chessBoardHeight;

	public ChineseChessBoard(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);
		chessBoardWidth = width;
		chessBoardHeight = height;
		allocationSpace(width - 60, height - 60);
	}

	private void allocationSpace(int width, int height) {
		gridLength = Math.min(height / 9, width / 8);
		weightFromPanelEdge = (getWidth() - 8 * gridLength) / 2;
		heightFromPanelEdge = (getHeight() - 9 * gridLength) / 2;
	}

	private void drawBoardWeight(Graphics g) {
		for (int i = 0; i < 10; i++) {
			((Graphics2D) g).drawLine(weightFromPanelEdge, heightFromPanelEdge + gridLength * i, weightFromPanelEdge + gridLength * 8, heightFromPanelEdge + gridLength * i);
		}
	}

	private void drawBoardHeight(Graphics g) {
		((Graphics2D) g).drawLine(weightFromPanelEdge, heightFromPanelEdge + 4 * gridLength, weightFromPanelEdge, heightFromPanelEdge + 5 * gridLength);
		((Graphics2D) g).drawLine(weightFromPanelEdge + 8 * gridLength, heightFromPanelEdge + 4 * gridLength, weightFromPanelEdge + 8 * gridLength, heightFromPanelEdge + 5 * gridLength);
		for (int i = 0; i <= 8; i++) {
			((Graphics2D) g).drawLine(weightFromPanelEdge + gridLength * i, heightFromPanelEdge, weightFromPanelEdge + gridLength * i, heightFromPanelEdge + gridLength * 4);
		}

		for (int i = 0; i <= 8; i++) {
			((Graphics2D) g).drawLine(weightFromPanelEdge + gridLength * i, heightFromPanelEdge + gridLength * 5, weightFromPanelEdge + gridLength * i, heightFromPanelEdge + gridLength * 9);
		}
	}

	private void drawXLine(Graphics g) {
		((Graphics2D) g).drawLine(weightFromPanelEdge + 3 * gridLength + 1, heightFromPanelEdge + 1, weightFromPanelEdge + 5 * gridLength, heightFromPanelEdge + 2 * gridLength);
		((Graphics2D) g).drawLine(weightFromPanelEdge + 3 * gridLength, heightFromPanelEdge + 2 * gridLength, weightFromPanelEdge + 5 * gridLength - 1, heightFromPanelEdge + 1);
		((Graphics2D) g).drawLine(weightFromPanelEdge + 3 * gridLength + 1, heightFromPanelEdge + 1, weightFromPanelEdge + 5 * gridLength, heightFromPanelEdge + 2 * gridLength);
		((Graphics2D) g).drawLine(weightFromPanelEdge + 3 * gridLength + 1, heightFromPanelEdge + 1, weightFromPanelEdge + 5 * gridLength, heightFromPanelEdge + 2 * gridLength);
		((Graphics2D) g).drawLine(weightFromPanelEdge + 3 * gridLength + 1, heightFromPanelEdge + 7 * gridLength + 1, weightFromPanelEdge + 5 * gridLength, heightFromPanelEdge + 9 * gridLength);
		((Graphics2D) g).drawLine(weightFromPanelEdge + 3 * gridLength, heightFromPanelEdge + 9 * gridLength, weightFromPanelEdge + 5 * gridLength - 1, heightFromPanelEdge + 1 + 7 * gridLength);
		((Graphics2D) g).drawLine(weightFromPanelEdge + 3 * gridLength + 1, heightFromPanelEdge + 1 + 7 * gridLength, weightFromPanelEdge + 5 * gridLength, heightFromPanelEdge + 9 * gridLength);
		((Graphics2D) g).drawLine(weightFromPanelEdge + 3 * gridLength + 1, heightFromPanelEdge + 1 + 7 * gridLength, weightFromPanelEdge + 5 * gridLength, heightFromPanelEdge + 9 * gridLength);
	}

	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("c:/sqa/b.jpg");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		float lineWidth = 3.25f;
		((Graphics2D) g).setStroke(new BasicStroke(lineWidth));

		drawBoardWeight(g);
		drawBoardHeight(g);
		drawXLine(g);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int locX = (e.getX() + ((JComponent) e.getSource()).getLocation().x - ((JComponent) e.getSource()).getWidth() / 2);
		int locY = (e.getY() + ((JComponent) e.getSource()).getLocation().y - ((JComponent) e.getSource()).getHeight() / 2);
		((JComponent) e.getSource()).setLocation(locX, locY);
//		System.out.println(locX + " " + locY);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + " " + e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// observer //

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public int getChessBoardWidth() {
		return chessBoardWidth;
	}

	public int getChessBoardHeight() {
		return chessBoardHeight;
	}
}
