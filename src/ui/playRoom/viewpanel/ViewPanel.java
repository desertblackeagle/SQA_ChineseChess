package ui.playRoom.viewpanel;

import javax.swing.JPanel;

import ui.playRoom.chessBoard.ChineseChessBoard;

public class ViewPanel extends JPanel {
	private int gap = 20;
	private int LogoAreaHeight = getHeight() / 6;
	private int numberOfComponent = 2;
	private int componentWidth, componentHeight;
	private ChineseChessBoard chessBoard;

	public ViewPanel(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);

		initPanel();
	}

	private void initPanel() {
		componentWidth = (getWidth() - 3 * gap) / 7;
		componentHeight = getHeight() - 2 * gap;

		chessBoard = new ChineseChessBoard(gap, gap, componentWidth * 4, componentHeight);
		add(chessBoard);
	}
}
