package ui.gameRoom.viewpanel.buttonPanel;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	private int gap = 30;
	private int existComponentHeight = 0;
	private JButton leaveBtn;

	public ButtonPanel(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);
		setOpaque(false);
		
		intiButton();
		initBounds();
	}

	private void intiButton() {
		leaveBtn = new JButton("離開");
		add(leaveBtn);
	}

	private void initBounds() {
		leaveBtn.setBounds(gap, existComponentHeight + gap, getWidth() - 2 * gap, 100);
		existComponentHeight += 100;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
