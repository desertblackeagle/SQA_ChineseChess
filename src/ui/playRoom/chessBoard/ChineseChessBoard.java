package ui.playRoom.chessBoard;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import control.GameObservable;
import data.chess.Chess;
import data.chess.ChessBox;

public class ChineseChessBoard extends JPanel implements MouseMotionListener, MouseListener, Observer {

	private int gridLength;
	private int weightFromPanelEdge, heightFromPanelEdge;
	private int chessBoardWidth, chessBoardHeight;
	private GameObservable obs;
	private int playerTeam = 3;
	private ArrayList<Chess> chessBox;

	public ChineseChessBoard(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);

		chessBox = new ArrayList<Chess>();
		obs = new GameObservable();

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
		java.net.URL imUrl = getClass().getResource("/image/");
//		ImageIcon icon = new ImageIcon("c:/sqa/b.jpg");
		String path = imUrl.toString() + "chessBoard.jpg";
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		float lineWidth = 3.25f;
		((Graphics2D) g).setStroke(new BasicStroke(lineWidth));

		drawBoardWeight(g);
		drawBoardHeight(g);
		drawXLine(g);
	}

	public void setChessListener(int playerTeam) {
		if (playerTeam == 0) {
			playerTeam = 0;
		} else {
			playerTeam = 1;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (((Chess) e.getSource()).getColor() == playerTeam) {
			int locX = (e.getX() + ((JComponent) e.getSource()).getLocation().x - ((JComponent) e.getSource()).getWidth() / 2);
			int locY = (e.getY() + ((JComponent) e.getSource()).getLocation().y - ((JComponent) e.getSource()).getHeight() / 2);
			setComponentZOrder(((JComponent) e.getSource()), 0);
			((JComponent) e.getSource()).setLocation(locX, locY);
//			System.out.println(locX + " " + locY);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
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
		if (((Chess) e.getSource()).getColor() == playerTeam) {
			int locX = (e.getX() + ((JComponent) e.getSource()).getLocation().x);
			int locY = (e.getY() + ((JComponent) e.getSource()).getLocation().y);
			setChanged();
			notifyObservers(((JComponent) e.getSource()));
//		System.out.println(locX + " : " + locY);
		}
	}

	// observer //

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof ChessBox) {
			if (arg instanceof ArrayList<?>) {
				for (Chess chess : (ArrayList<Chess>) arg) {
					add(chess);
					chess.addMouseListener(this);
					chess.addMouseMotionListener(this);
					chessBox.add(chess);
				}
				repaint();
				revalidate();
			}
		}
	}

	public void removeChessListener() {
		for (Chess chess : chessBox) {
			chess.removeMouseListener(this);
			chess.removeMouseMotionListener(this);
		}
	}

	public void setChanged() {
		obs.setChanged();
	}

	public void addObserver(Observer observer) {
		obs.addObserver(observer);
	}

	public int countObservers() {
		return obs.countObservers();
	}

	public void deleteObserver(Observer observer) {
		obs.deleteObserver(observer);
	}

	public void deleteObservers() {
		obs.deleteObservers();
	}

	public void notifyObservers() {
		obs.notifyObservers();
	}

	public void notifyObservers(Object o) {
		obs.notifyObservers(o);
	}

	public void notifyObservers(Observer observer) {
		obs.notifyObservers(observer);
	}

	public int getChessBoardWidth() {
		return chessBoardWidth;
	}

	public int getChessBoardHeight() {
		return chessBoardHeight;
	}

	public void setPlayerTeam(int playerTeam) {
		this.playerTeam = playerTeam;
	}

}
