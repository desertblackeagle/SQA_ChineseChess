package ui.playRoom.viewpanel.chatAndPlayInfoPanel;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import ui.ChatPanel;
import ui.playRoom.viewpanel.chatAndPlayInfoPanel.playInfo.PlayInfo;

public class ChatAndPlayInfo extends JPanel {
	private ChatPanel chatPanel;
	private int gap = 10;
	private int componentWidth, componentHeight;
	private JButton exit;
	private PlayInfo playerAInfo, playerBInfo;
	private JLabel forWhoToPlay;

	public ChatAndPlayInfo(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);

		initPanel();
		initButton();
		initLabel();
		initBound();
	}

	private void initPanel() {
		componentWidth = getWidth();
		componentHeight = (getHeight() - 2 * gap) / 8;

		chatPanel = new ChatPanel(0, gap + 2 * componentHeight, (getWidth() - gap) / 2, componentHeight * 4);
		add(chatPanel);

		playerAInfo = new PlayInfo(0, 0, getWidth(), componentHeight * 2);
		add(playerAInfo);

		playerBInfo = new PlayInfo(0, componentHeight * 6 + 2 * gap, getWidth(), componentHeight * 2);
		add(playerBInfo);
	}

	private void initLabel() {
		forWhoToPlay = new JLabel("輪到你下", JLabel.CENTER);
		forWhoToPlay.setBorder(new LineBorder(Color.black));
		add(forWhoToPlay);
	}

	private void initBound() {
		forWhoToPlay.setBounds(gap + (getWidth() - gap) / 2, gap + 2 * componentHeight, (getWidth() - gap) / 2, componentHeight);
		exit.setBounds(gap + (getWidth() - gap) / 2, gap * 2 + 3 * componentHeight, (getWidth() - gap) / 2, componentHeight);
	}

	private void initButton() {
		exit = new JButton("離開");
		add(exit);
	}
}
