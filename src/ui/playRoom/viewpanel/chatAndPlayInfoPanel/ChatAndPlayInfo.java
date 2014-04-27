package ui.playRoom.viewpanel.chatAndPlayInfoPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private ActionListener btnListener;

	public ChatAndPlayInfo(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);

		initListener();
		initPanel();
		initButton();
		initLabel();
		initBound();
	}

	private void initListener() {
		btnListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getActionCommand().equals("離開")) {
					System.exit(0);
				}
			}
		};
	}

	// init Component //

	private void initPanel() {
		componentWidth = getWidth();
		componentHeight = (getHeight() - 2 * gap) / 8;

		playerAInfo = new PlayInfo(0, 0, getWidth(), componentHeight * 2);
		add(playerAInfo);

		playerBInfo = new PlayInfo(0, componentHeight * 6 + 2 * gap, getWidth(), componentHeight * 2);
		add(playerBInfo);

		chatPanel = new ChatPanel(0, gap + 2 * componentHeight, (getWidth() - gap) / 2, componentHeight * 4);
		add(chatPanel);
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
		exit.addActionListener(btnListener);
		add(exit);
	}

	// init Component end //

	// API //

	public void changePlay() {
		if (forWhoToPlay.getText().equals("輪到你下")) {
			forWhoToPlay.setText("輪到對方下");
		} else {
			forWhoToPlay.setText("輪到你下");
		}
	}

	public PlayInfo getPlayerAInfo() {
		return playerAInfo;
	}

	public PlayInfo getPlayerBInfo() {
		return playerBInfo;
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}
	// API end //
}
