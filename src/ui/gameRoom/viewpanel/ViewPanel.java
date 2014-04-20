package ui.gameRoom.viewpanel;

import javax.swing.JPanel;

import ui.ChatPanel;
import ui.gameRoom.viewpanel.buttonPanel.ButtonPanel;
import ui.gameRoom.viewpanel.playerListPanel.PlayerListPanel;

public class ViewPanel extends JPanel {
	private PlayerListPanel playerList;
	private ButtonPanel buttonPanel;
	private ChatPanel chatPanel;
	private int gap = 50;
	private int LogoAreaHeight = getHeight() / 6;
	private int numberOfComponent = 3;
	private int componentWidth, componentHeight;

	public ViewPanel(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);

		initPanel();
	}

	private void initPanel() {
		componentWidth = (getWidth() - 4 * gap) / numberOfComponent;
		componentHeight = getHeight() - 2 * gap;
		playerList = new PlayerListPanel(gap, LogoAreaHeight + gap, componentWidth, componentHeight);
		add(playerList);

		buttonPanel = new ButtonPanel(2 * gap + componentWidth, LogoAreaHeight + gap, componentWidth, componentHeight);
		add(buttonPanel);

		chatPanel = new ChatPanel(3 * gap + componentWidth * 2, LogoAreaHeight + gap, componentWidth, componentHeight);
		add(chatPanel);
	}
}
