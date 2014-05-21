package game;

import java.util.Random;

import javax.swing.JOptionPane;

import ui.playRoom.PlayRoom;
import control.Controler;
import control.GameTimeListener;
import control.GameTimer;
import control.net.Connecter;
import data.MainData;

public class StartGame {

	public StartGame(String APIToken, String userToken, String playerAName, String playerPhoto) {
		// TODO Auto-generated constructor stub
		GameTimer playTooLong = new GameTimer();
		playTooLong.addTimeListener(new GameTimeListener() {

			@Override
			public void timeOut() {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "已經過了一小時，你玩太久囉!!!!", "提醒", JOptionPane.INFORMATION_MESSAGE);
			}

			@Override
			public void onChange(long sec) {
				// TODO Auto-generated method stub

			}
		});
		playTooLong.startTimer(10);
		PlayRoom playRoom = new PlayRoom(APIToken, userToken, playerAName, playerPhoto);
		int chessBoardWidth = playRoom.getChessBoard().getChessBoardWidth(), chessBoardHeight = playRoom.getChessBoard().getChessBoardHeight();
		MainData data = new MainData(chessBoardWidth, chessBoardHeight);
		Controler controler = new Controler(chessBoardWidth, chessBoardHeight);
		Connecter connecter = new Connecter(playRoom, data.getChessXYLoc(), APIToken, userToken, playerAName, playerPhoto);

		// set observer observable
		controler.getTransferAbsoluteToXY().addObserver(connecter);
		connecter.addObserver(playRoom);
		playRoom.getChatPanel().addObserver(connecter);
		data.getChessBox().addObserver(playRoom.getChessBoard());
		data.getChessBox().addObserver(data.getChessXYLoc());
		playRoom.getChessBoard().addObserver(controler.getTransferAbsoluteToXY());
		// set observer observable end
		data.getChessBox().initChess();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String APIToken = args[0];
		APIToken = APIToken + String.valueOf((new Random().nextInt(1000)));
		String userToken = args[1];
		String playerAName = args[2];
		String playerPhoto = args[3];

//		new StartGame(APIToken, userToken, playerAName, playerPhoto);

		APIToken = "83d25eaeb2cebf405adc604f9262c660b6ce2d8d83687dfb01d4226ff027a049dd15d102fda255f9d5c810f83f63b81aaa66";
		userToken = "1";
		playerAName = "fcuA";
		playerPhoto = "http://simswiki.info/images/3/30/Windows_Logo.png";

		new StartGame(APIToken, userToken, playerAName, playerPhoto);

//		APIToken = "b123456789123456789123456788";
//		userToken = "2";
//		playerAName = "fcuB";
//		playerPhoto = "https://upload.wikimedia.org/wikipedia/en/0/0c/ITunes_11_Logo.png";
//
//		new StartGame(APIToken, userToken, playerAName, playerPhoto);
	}
}
