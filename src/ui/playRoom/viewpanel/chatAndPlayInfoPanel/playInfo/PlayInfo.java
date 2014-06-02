package ui.playRoom.viewpanel.chatAndPlayInfoPanel.playInfo;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.net.ImageDownload;

public class PlayInfo extends JPanel {
	private JLabel playerPhoto;
	private ImageIcon photo;
	private LineBorder border;
	private IndividualInfo playerIndividualInfo;
	private ImageDownload imgDown;

	public PlayInfo(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);

		initBoarder();
		initPanel();
		initImageIcon();
		initLabel();
		initBound();
	}

	private void initLabel() {
		playerPhoto = new JLabel(photo);
		playerPhoto.setSize(getHeight(), getHeight());
		playerPhoto.setBorder(border);
		photo.setImage(photo.getImage().getScaledInstance(playerPhoto.getWidth(), playerPhoto.getHeight(), Image.SCALE_DEFAULT));
		add(playerPhoto);

	}

	private void initBound() {
		playerPhoto.setBounds(0, 0, getHeight(), getHeight());
	}

	private void initBoarder() {
		border = new LineBorder(Color.black);
	}

	private void initPanel() {
		playerIndividualInfo = new IndividualInfo(getHeight(), 0, getWidth() - getHeight(), getHeight());
		playerIndividualInfo.setBorder(border);
		add(playerIndividualInfo);

	}

	private void initImageIcon() {
		java.net.URL imUrl = getClass().getResource("/image/");
		String path = imUrl.toString() + "noPlayerPhoto.jpg";
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		photo = new ImageIcon(url);
	}

	// API //

	public void setPlayerPhoto(String photoUrl) {
		ImageIcon photo = null;
		if (photoUrl.substring(0, 5).equals("https")) {
			try {
				imgDown = new ImageDownload();
				photo = new ImageIcon(imgDown.download(photoUrl));
			} catch (ConnectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				photo = new ImageIcon(new URL(photoUrl));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (photo == null) {
			java.net.URL imUrl = getClass().getResource("/image/");
			String path = imUrl.toString() + "noPlayerPhoto.jpg";
			URL url = null;
			try {
				url = new URL(path);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			photo = new ImageIcon(url);
		}
		photo.setImage(photo.getImage().getScaledInstance(playerPhoto.getWidth(), playerPhoto.getHeight(), Image.SCALE_DEFAULT));
		playerPhoto.setIcon(photo);
	}

	public IndividualInfo getPlayerIndividualInfo() {
		return playerIndividualInfo;
	}

	// API end //
}
