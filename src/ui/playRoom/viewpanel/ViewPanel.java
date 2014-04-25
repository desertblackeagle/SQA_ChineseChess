package ui.playRoom.viewpanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import ui.playRoom.chessBoard.ChineseChessBoard;
import ui.playRoom.viewpanel.chatAndPlayInfoPanel.ChatAndPlayInfo;
import ui.playRoom.viewpanel.chatAndPlayInfoPanel.playInfo.IndividualInfo;

public class ViewPanel extends JPanel {
	private int gap = 20;
	private int componentWidth, componentHeight;
	private ChineseChessBoard chessBoard;
	private ChatAndPlayInfo chatAndPlayInfo;

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
		chessBoard = new ChineseChessBoard(0, 0, componentWidth * 4 + 2 * gap, componentHeight + 2 * gap);
		add(chessBoard);

		chatAndPlayInfo = new ChatAndPlayInfo(gap * 2 + componentWidth * 4, gap, componentWidth * 3, componentHeight);
		add(chatAndPlayInfo);

	}

	// API //

	public ChatAndPlayInfo getChatAndPlayInfo() {
		return chatAndPlayInfo;
	}

	public ChineseChessBoard getChessBoard() {
		return chessBoard;
	}

	// API end //
}
