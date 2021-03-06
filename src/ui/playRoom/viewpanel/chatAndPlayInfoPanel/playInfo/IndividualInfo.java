package ui.playRoom.viewpanel.chatAndPlayInfoPanel.playInfo;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class IndividualInfo extends JPanel {
	private JLabel win, lose, name;
	private int labelNum = 3;

	public IndividualInfo(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);

		initLabel();
		setComponentFont(width);
		initBound();
	}

	private void setComponentFont(int width) {
		// 寬度大於一定大小則放大字體
		if (width > 300) {
			win.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
			lose.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
			name.setFont(new Font(Font.DIALOG, Font.BOLD, 22));
		}
	}

	private void initLabel() {
		win = new JLabel("勝 : ");
		add(win);
		lose = new JLabel("敗 : ");
		add(lose);
		name = new JLabel("玩家名字 : ");
		add(name);
	}

	private void initBound() {
		win.setBounds(10, 0, getHeight(), getHeight() / labelNum);
		lose.setBounds(10, getHeight() / labelNum, getWidth(), getHeight() / labelNum);
		name.setBounds(10, getHeight() * 2 / labelNum, getWidth(), getHeight() / labelNum);
	}

	// API //

	public void setWin(int win) {
		this.win.setText("勝 : " + win);
	}

	public void setLose(int lose) {
		this.lose.setText("敗 : " + lose);
	}

	public void setName(String name) {
		this.name.setText("玩家名字 : " + name);
	}

	public JLabel getWin() {
		return win;
	}

	public JLabel getLose() {
		return lose;
	}

	public JLabel getPlayerName() {
		return name;
	}

	// API end //
}
