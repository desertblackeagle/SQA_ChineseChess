package ui;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WinFrame extends ParentFrame {
	JLabel background, lodingGifLabel;
	ImageIcon backgroundPhoto, lodingGif;

	public WinFrame() {
		// TODO Auto-generated constructor stub
		initLabel();
		initBounds();
	}

	private void initLabel() {
		java.net.URL imUrl = getClass().getResource("/image/");
		String path = imUrl.toString() + "win.png";
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backgroundPhoto = new ImageIcon(url);
		backgroundPhoto.setImage(backgroundPhoto.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
		background = new JLabel(backgroundPhoto);
		add(background);
	}

	private void initBounds() {
		background.setBounds(0, 0, getWidth(), getHeight());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WinFrame l = new WinFrame();
	}
}
