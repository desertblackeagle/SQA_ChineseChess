package game;

import net.Connecter;
import ui.playRoom.PlayRoom;
import control.Controler;
import data.MainData;

public class StartGame {

	public StartGame(String APIToken, String userToken, String playerAName, String playerPhoto) {
		// TODO Auto-generated constructor stub

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
		String userToken = args[1];
		String playerAName = args[2];
		String playerPhoto = args[3];

		new StartGame(APIToken, userToken, playerAName, playerPhoto);

	}
}
