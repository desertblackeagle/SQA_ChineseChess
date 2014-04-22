package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

public class ParentFrame extends JFrame {
	private int pressX, pressY;

	public ParentFrame() {
		// TODO Auto-generated constructor stub
		setSize(1500, 900);
//		setSize(1300, 900);
//		setSize(800, 600);
//		setSize(1200, 700);
//		setSize(1000, 600);
		setLayout(null);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				pressX = e.getX();
				pressY = e.getY();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int x = arg0.getX() - pressX;
				int y = arg0.getY() - pressY;
				((JFrame) arg0.getSource()).setLocation((int) ((JFrame) arg0.getSource()).getLocation().getX() + x, (int) ((JFrame) arg0.getSource()).getLocation().getY() + y);
			}
		});
	}

}
