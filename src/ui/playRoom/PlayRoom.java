package ui.playRoom;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import ui.ParentFrame;
import ui.playRoom.viewpanel.ViewPanel;

public class PlayRoom extends ParentFrame {

	private JLabel background, exit, narrow;
	private ViewPanel viewPanel;
	private MouseAdapter mouseForExitNarrow;

	public PlayRoom() {
		// TODO Auto-generated constructor stub
		initMouseAdapter();
		initFrame();
		initBackground();
		initBound();

		revalidate();
		repaint();
	}

	private void initMouseAdapter() {
		mouseForExitNarrow = new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseEntered(e);
				e.getComponent().setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				e.getComponent().setForeground(Color.white);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (((JLabel) e.getComponent()).getText().equals("X")) {
					System.exit(0);
				} else {
					narrowFrame();
				}

			}
		};
	}

	private void narrowFrame() {
		this.setExtendedState(JFrame.ICONIFIED);
	}

	private void initLabel() {
		exit = new JLabel("X", JLabel.CENTER);
		add(exit);
//		exit.setOpaque(true);
		exit.setFont(new Font("標楷體", Font.BOLD, 18));
		exit.setForeground(Color.white);
		exit.addMouseListener(mouseForExitNarrow);

		narrow = new JLabel("＿", JLabel.CENTER);
		add(narrow);
//		narrow.setOpaque(true);
		narrow.setForeground(Color.white);
		narrow.addMouseListener(mouseForExitNarrow);
	}

	private void initBackground() {
		background = new JLabel(new ImageIcon("c:/sqa/wallpaper/nubero.jpg"));
		add(background);
	}

	private void initFrame() {
		initPanel();
		initLabel();
	}

	private void initPanel() {
		try {
			// //////////////////////---------------------------------- select Look and Feel(下面就是要改变的地方了)
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
			// //////////////////////---------------------------------- start application;
			viewPanel = new ViewPanel(10, getHeight() / 7 + 10, getWidth() - 20, getHeight() * 6 / 7 - 20);
			viewPanel.addMouseListener(new MouseAdapter() {
			});
			add(viewPanel);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void initBound() {
		background.setBounds(0, 0, getWidth(), getHeight());
		exit.setBounds(getWidth() - 30, 15, 20, 20);
		narrow.setBounds(getWidth() - 60, 15, 20, 20);
	}

	public static void main(String[] args) {
		PlayRoom p = new PlayRoom();
	}

}
