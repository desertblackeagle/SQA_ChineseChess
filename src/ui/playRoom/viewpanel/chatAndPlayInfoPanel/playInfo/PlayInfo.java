package ui.playRoom.viewpanel.chatAndPlayInfoPanel.playInfo;

import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PlayInfo extends JPanel {
	private JLabel playerPhoto;
	private ImageIcon photo;
	private LineBorder border;
	private IndividualInfo playerIndividualInfo;

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
		photo = new ImageIcon("c:/sqa/playerAPhoto.jpg");
	}

	// API //

	public void setPlayerPhoto(String photoUrl) {
		ImageIcon photo = null;
		try {
			photo = new ImageIcon(new URL(photoUrl));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (photo == null) {
			photo = new ImageIcon("C:/Users/rose/Pictures/Penguins.jpg");
		}
		photo.setImage(photo.getImage().getScaledInstance(playerPhoto.getWidth(), playerPhoto.getHeight(), Image.SCALE_DEFAULT));
		playerPhoto.setIcon(photo);
	}

	public IndividualInfo getPlayerIndividualInfo() {
		return playerIndividualInfo;
	}

	// API end //
}
