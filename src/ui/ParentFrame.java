package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

import org.json.JSONObject;

import control.ConfigGen;

public class ParentFrame extends JFrame {
	private int pressX, pressY;

	public ParentFrame() {
//		 TODO Auto-generated constructor stub
		ConfigGen configGen = new ConfigGen();
		String configData = "";
		JSONObject display = null;
		int x, y;
		configData = configGen.getConfig("display");
		display = new JSONObject(configData);
		x = display.getInt("display size x");
		y = display.getInt("display size y");
		assert x < 0 : "螢幕寬度小於0";
		assert y < 0 : "螢幕高度小於0";
		setSize(x, y);
		setLayout(null);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;
		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		// 可以拖曳視窗
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
