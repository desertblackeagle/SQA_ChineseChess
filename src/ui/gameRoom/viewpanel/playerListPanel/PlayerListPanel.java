package ui.gameRoom.viewpanel.playerListPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PlayerListPanel extends JPanel {

	private JLabel waitingPlayerLabel;
	private JScrollPane waitingPlayerList;
	private JList<String> waitingPlayerJlist;
	private String selectedPlayer;

	public PlayerListPanel(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);

		initJLabel();
		initJPanel();
		initJList();
		initJScrollPane();
		setComponentFont();
		testDrive();
	}

	private void testDrive() {
		String[] waitingPlayerList = new String[5];
		for (int i = 0; i < 5; i++) {
			waitingPlayerList[i] = String.valueOf(i + 1);
		}

		waitingPlayerJlist.setListData(waitingPlayerList);

	}

	// init Component //

	private void setComponentFont() {
		waitingPlayerLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
		waitingPlayerJlist.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
	}

	private void initJLabel() {
		waitingPlayerLabel = new JLabel("等待中玩家",JLabel.CENTER);
		waitingPlayerLabel.setBounds(0, 0, getWidth(), getHeight() / 8);
		add(waitingPlayerLabel);
	}

	private void initJList() {
		waitingPlayerJlist = new JList<String>();
		waitingPlayerJlist.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (e.getValueIsAdjusting()) {
					int index = waitingPlayerJlist.getSelectedIndex();
					System.out.println("index: " + index);
					String selectedItem = waitingPlayerJlist.getSelectedValue().toString();
					System.out.println("選擇了:" + selectedItem);
					selectedPlayer = selectedItem;
				}
			}
		});
	}

	private void initJScrollPane() {
		waitingPlayerList = new JScrollPane();
		waitingPlayerList.setBackground(Color.black);
		waitingPlayerList.getViewport().setView(waitingPlayerJlist);
		waitingPlayerList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		waitingPlayerList.setBounds(0, waitingPlayerLabel.getHeight(), getWidth() / 2, getHeight() - waitingPlayerLabel.getHeight());
		add(waitingPlayerList);
	}

	private void initJPanel() {
	}

	private boolean checkSelectedPlayerExistInWaitingList(String[] waitingPlayerList, String selectedPlayer) {
		for (String player : waitingPlayerList) {
			if (player.equals(selectedPlayer)) {
				return true;
			}
		}
		return false;
	}

	// init Component end //

	// API //

	public void updateWaitingPlayerList(String[] waitingPlayerList) {
		waitingPlayerJlist.setListData(waitingPlayerList);
	}

	// API end //

}
