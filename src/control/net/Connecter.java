package control.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;

import ui.playRoom.PlayRoom;
import control.TransferAbsoluteToXY;
import data.chess.Chess;
import data.chess.ChessXYLocOnChessBoard;

public class Connecter extends Observable implements Observer {
	private Socket client;
	private BufferedReader serverReader;
	private PrintStream serverWriter;
	private ChessXYLocOnChessBoard chessXYLocOnChessBoard;
	private String APIToken_C;
	private String userToken_C;
	private String playerAName_C;
	private String playerPhoto_C;
	private PlayRoom playRoom;

	public Connecter(PlayRoom playRoom, ChessXYLocOnChessBoard chessXYLocOnChessBoard, String APIToken, String userToken, String playerAName, String playerPhoto) {
		// TODO Auto-generated constructor stub
		this.chessXYLocOnChessBoard = chessXYLocOnChessBoard;
		this.playRoom = playRoom;
		APIToken_C = APIToken;
		userToken_C = userToken;
		playerAName_C = playerAName;
		playerPhoto_C = playerPhoto;
		new Thread(new Runnable() {
			public void run() {
				try {
//					client = new Socket("123.204.84.144", 56);
//					client = new Socket("127.0.0.1", 56);
//					client = new Socket("192.168.1.239", 56);
//					client = new Socket("192.168.1.109", 56);
					client = connectToLocal();
					serverReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
					serverWriter = new PrintStream(client.getOutputStream(), true, "utf-8");
					System.out.println("client io set up complete ");
					JSONObject check = new JSONObject();
					check.put("action", "check");
					check.put("API Token", APIToken_C);
					check.put("User Token", userToken_C);
					serverWriter.println(check.toString());
					System.out.println("send check info to server : " + check);
					connectToServer();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	private Socket connectToLocal() {
		try {
			return new Socket("127.0.0.1", 56);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("無法連線至local 改成連線至遠端");
			return connectToRemote();
		}
		return null;
	}

	private Socket connectToRemote() {
		try {
			return new Socket("123.204.84.144", 56);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("所有Server接連線失敗");
			JOptionPane.showMessageDialog(null, "無法連上Server", "提醒", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	private void connectToServer() {
		String serverInput;
		try {
			while ((serverInput = serverReader.readLine()) != null) {
				Thread.interrupted();
				System.out.println("message from server : " + serverInput);
				JSONObject serverMsg = new JSONObject(serverInput);
				if (serverMsg.get("action").equals("move")) {
					int x = (Integer) serverMsg.get("chess X");
					int y = (Integer) serverMsg.get("chess Y");
					int toX = (Integer) serverMsg.get("chess toX");
					int toY = (Integer) serverMsg.get("chess toY");
					if (x == toX && y == toY) {
						System.out.println("move chess : " + x + " " + y + " to " + toX + " " + toY);
						chessXYLocOnChessBoard.getChess(x, y).setChessToXY(toX, toY);
						JOptionPane.showMessageDialog(null, "不能這樣下", "錯誤下法", JOptionPane.WARNING_MESSAGE);
					} else {
						System.out.println("move chess : " + x + " " + y + " to " + toX + " " + toY);
						chessXYLocOnChessBoard.getChess(x, y).setChessToXY(toX, toY);
						chessXYLocOnChessBoard.getChess(x, y).setChessLocX(toX);
						chessXYLocOnChessBoard.getChess(x, y).setChessLocY(toY);
						chessXYLocOnChessBoard.setChess(x, y, toX, toY);
						playRoom.changePlay();
					}
				} else if (serverMsg.get("action").equals("chat")) {
					String chat = (String) serverMsg.get("chat msg");
					playRoom.appendChatArea(chat);
				} else if (serverMsg.get("action").equals("sayBye")) {
					playRoom.getChessBoard().removeChessListener();
					JOptionPane.showMessageDialog(null, "對方已經離開", "遊戲資訊", JOptionPane.INFORMATION_MESSAGE);
				} else if (serverMsg.get("action").equals("get info")) {
					JSONObject sendToServer = new JSONObject();
					sendToServer.put("action", "give info");
					sendToServer.put("API Token", APIToken_C);
					sendToServer.put("User Token", userToken_C);
					sendToServer.put("player name", playerAName_C);
					sendToServer.put("player photo", playerPhoto_C);
					serverWriter.println(sendToServer.toString());
					System.out.println("client send info to server : " + sendToServer);
				} else if (serverMsg.get("action").equals("rival info")) {
					System.out.println("get rival info from server");
					System.out.println(serverMsg.get("player name").toString());
					System.out.println(serverMsg.get("player photo").toString());
					System.out.println(serverMsg.get("player win").toString());
					System.out.println(serverMsg.get("player lose").toString());
					System.out.println(serverMsg.get("team").toString() + "\n");
					playRoom.setPlayerBInfoName(serverMsg.get("player name").toString());
					playRoom.setPlayerBPhoto(serverMsg.get("player photo").toString());
					playRoom.setPlayerTeam((Integer) serverMsg.get("team"));
					playRoom.setPlayerBInfoLose(Integer.valueOf(serverMsg.get("player lose").toString()));
					playRoom.setPlayerBInfoWin(Integer.valueOf(serverMsg.get("player win").toString()));
					if (serverMsg.get("team").toString().equals("1")) {
						playRoom.changePlay();
						playRoom.setWhichTeam(1);
					}
					playRoom.getLoadingFrame().setVisible(false);
					playRoom.getLoadingFrame().dispose();
					playRoom.setVisible(true);
					playRoom.getPlayTooLong().startTimer(3600);
				} else if (serverMsg.get("action").equals("winAndLose")) {
					System.out.println("get player win and lose info from server");
					System.out.println(serverMsg.get("player win").toString());
					System.out.println(serverMsg.get("player lose").toString() + "\n");
					playRoom.setPlayerAInfoLose(Integer.valueOf(serverMsg.get("player lose").toString()));
					playRoom.setPlayerAInfoWin(Integer.valueOf(serverMsg.get("player win").toString()));
				} else if (serverMsg.get("action").equals("win")) {
					playRoom.getChessBoard().removeChessListener();
					JOptionPane.showMessageDialog(null, "您獲勝了", "遊戲資訊", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("you win");
				} else if (serverMsg.get("action").equals("lose")) {
					playRoom.getChessBoard().removeChessListener();
					JOptionPane.showMessageDialog(null, "您輸了", "遊戲資訊", JOptionPane.INFORMATION_MESSAGE);
					System.out.println("you lose");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof String) {
			JSONObject sendToServer = new JSONObject();
			sendToServer.put("action", "chat");
			sendToServer.put("chat msg", (String) arg);
			serverWriter.println(sendToServer.toString());
			System.out.println("client send chat to server : " + sendToServer);
		} else if (o instanceof TransferAbsoluteToXY) {
			if (arg instanceof Chess) {
				JSONObject sendToServer = new JSONObject();
				sendToServer.put("action", "move");
				sendToServer.put("chess name", ((Chess) arg).getChessName());
				sendToServer.put("chess X", ((Chess) arg).getChessLocX());
				sendToServer.put("chess Y", ((Chess) arg).getChessLocY());
				sendToServer.put("chess toX", ((Chess) arg).getChessToX());
				sendToServer.put("chess toY", ((Chess) arg).getChessToY());
				sendToServer.put("chess color", ((Chess) arg).getColor());
				serverWriter.println(sendToServer);
				System.out.println("client send chess move detail to server : " + sendToServer);
			}
		}
	}
}
