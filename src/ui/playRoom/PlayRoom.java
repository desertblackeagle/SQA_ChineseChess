package ui.playRoom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import ui.ChatPanel;
import ui.LodingFrame;
import ui.ParentFrame;
import ui.playRoom.chessBoard.ChineseChessBoard;
import ui.playRoom.viewpanel.ViewPanel;
import ui.playRoom.viewpanel.chatAndPlayInfoPanel.ChatAndPlayInfo;
import ui.playRoom.viewpanel.chatAndPlayInfoPanel.playInfo.PlayInfo;
import control.GameObservable;
import control.net.Connecter;

public class PlayRoom extends ParentFrame implements Observer {
	private JLabel background, exit, narrow, logo;
	private ViewPanel viewPanel;
	private MouseAdapter mouseForExitNarrow;
	private ImageIcon logoIcon;
	private ChatAndPlayInfo chatAndPlayInfo;
	private ChatPanel chatPanel;
	private PlayInfo playerAInfo, playerBInfo;
	private ChineseChessBoard chessBoard;
	private GameObservable obs;
	private int playerTeam = 0;// red 0 black 1 block 3
	private LodingFrame loadingFrame;

	public PlayRoom(String APIToken, String userToken, String playerAName, String playerPhoto) {
		// TODO Auto-generated constructor stub
		setVisible(false);
		loadingFrame = new LodingFrame();
		initMouseAdapter();
		initFrame();
		initBackground();
		initBound();

		obs = new GameObservable();

		revalidate();
		repaint();
		getAndSetAllComponent();
//		testDrive();
		setPlayerAInfoName(playerAName);
		setPlayerAPhoto(playerPhoto);
	}

	private void testDrive() {
		changePlay();
		appendChatArea("Rose > Hello !");

		setPlayerAPhoto("http://img.kanzhongguo.com/dat/media/15/2010/12/06/20101206021518227.jpg");
		setPlayerAInfoWin(51);
		setPlayerAInfoLose(0);
		setPlayerAInfoName("Rose");

		setPlayerBPhoto("http://img.kanzhongguo.com/dat/media/15/2010/12/06/20101206021518227.jpg");
		setPlayerBInfoWin(1000);
		setPlayerBInfoLose(0);
		setPlayerBInfoName("God");
		setPlayerTeam(1);
	}

	// 設置關閉和縮小的listener
	private void initMouseAdapter() {
		mouseForExitNarrow = new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if (((JLabel) e.getComponent()).getText().equals("X")) {
					System.exit(0);
				} else {
					narrowFrame();
				}
			}

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

		};
	}

	private void narrowFrame() {
		// 縮小視窗
		this.setExtendedState(JFrame.ICONIFIED);
	}

	// init Component //

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

		logoIcon = new ImageIcon("c:/sqa/wallpaper/logo.png");
		logo = new JLabel(logoIcon);
		logo.setSize(140, 120);
		logo.setLocation(0, 0);
		logoIcon.setImage(logoIcon.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_DEFAULT));
		add(logo);
	}

	private void initBackground() {
		java.net.URL imUrl = getClass().getResource("/image/");
		String path = imUrl.toString() + "nubero.jpg";
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		background = new JLabel(new ImageIcon(url));
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
			viewPanel = new ViewPanel(10, getHeight() / 10 + 10, getWidth() - 20, getHeight() * 9 / 10 - 20);
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

	// init Component end //

	private void getAndSetAllComponent() {
		chatAndPlayInfo = viewPanel.getChatAndPlayInfo();
		chatPanel = viewPanel.getChatAndPlayInfo().getChatPanel();
		playerAInfo = viewPanel.getChatAndPlayInfo().getPlayerAInfo();
		playerBInfo = viewPanel.getChatAndPlayInfo().getPlayerBInfo();
		chessBoard = viewPanel.getChessBoard();
	}

	// API //

	public void changePlay() {
		chatAndPlayInfo.changePlay();
	}

	public void setWhichTeam(int team) {
		chatAndPlayInfo.setWhichTeam(team);
	}

	public void appendChatArea(String chatString) {
		chatPanel.appendChatArea(chatString);
	}

	public void setPlayerAPhoto(String photoUrl) {
		playerAInfo.setPlayerPhoto(photoUrl);
	}

	public void setPlayerBPhoto(String photoUrl) {
		playerBInfo.setPlayerPhoto(photoUrl);
	}

	public void setPlayerAInfoWin(int win) {
		playerAInfo.getPlayerIndividualInfo().setWin(win);
	}

	public void setPlayerAInfoLose(int lose) {
		playerAInfo.getPlayerIndividualInfo().setLose(lose);
	}

	public void setPlayerAInfoName(String name) {
		chatPanel.setLocalPlayerName(name);
		playerAInfo.getPlayerIndividualInfo().setName(name);
	}

	public void setPlayerBInfoWin(int win) {
		playerBInfo.getPlayerIndividualInfo().setWin(win);
	}

	public void setPlayerBInfoLose(int lose) {
		playerBInfo.getPlayerIndividualInfo().setLose(lose);
	}

	public void setPlayerBInfoName(String name) {
		playerBInfo.getPlayerIndividualInfo().setName(name);
	}

	public ViewPanel getViewPanel() {
		return viewPanel;
	}

	public ChatAndPlayInfo getChatAndPlayInfo() {
		return chatAndPlayInfo;
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	public PlayInfo getPlayerAInfo() {
		return playerAInfo;
	}

	public PlayInfo getPlayerBInfo() {
		return playerBInfo;
	}

	public ChineseChessBoard getChessBoard() {
		return chessBoard;
	}

	public LodingFrame getLoadingFrame() {
		return loadingFrame;
	}

	public void setPlayerTeam(int playerTeam) {
		chessBoard.setPlayerTeam(playerTeam);
		this.playerTeam = playerTeam;
	}

	// observable //

	public void setChanged() {
		obs.setChanged();
	}

	public void addObserver(Observer observer) {
		obs.addObserver(observer);
	}

	public int countObservers() {
		return obs.countObservers();
	}

	public void deleteObserver(Observer observer) {
		obs.deleteObserver(observer);
	}

	public void deleteObservers() {
		obs.deleteObservers();
	}

	public void notifyObservers() {
		obs.notifyObservers();
	}

	public void notifyObservers(Object o) {
		obs.notifyObservers(o);
	}

	public void notifyObservers(Observer observer) {
		obs.notifyObservers(observer);
	}

	// observable end //

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg0 instanceof Connecter) {
			if (arg1 instanceof String) {
				appendChatArea((String) arg1);
			}
		}
	}

	// API end //

//	public static void main(String[] args) {
//		PlayRoom p = new PlayRoom();
//	}

}
