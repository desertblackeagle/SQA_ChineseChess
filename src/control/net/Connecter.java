package control.net;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import log.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import ui.LoseFrame;
import ui.WinFrame;
import ui.playRoom.PlayRoom;
import control.ConfigGen;
import control.TransferAbsoluteToXY;
import data.chess.Chess;
import data.chess.ChessXYLocOnChessBoard;

public class Connecter extends Observable implements Observer {
	private Socket client;
	private BufferedReader serverReader;
	private PrintStream serverWriter;
	private ChessXYLocOnChessBoard chessXYLocOnChessBoard;
	private String APIToken_C;
	private String secreatToken_C;
	private String playerAName_C;
	private String playerPhoto_C;
	private PlayRoom playRoom;
	private String serverIp = "127.0.0.1";
	private Logger logger;

	public Connecter(PlayRoom playRoom, ChessXYLocOnChessBoard chessXYLocOnChessBoard, String APIToken, String secreatToken, String playerAName, String playerPhoto) {
		// TODO Auto-generated constructor stub
		this.chessXYLocOnChessBoard = chessXYLocOnChessBoard;
		this.playRoom = playRoom;
		logger = new Logger("client_connecter");
		ConfigGen config = new ConfigGen();
		serverIp = config.getConfig("serverIP");
		APIToken_C = APIToken;
		secreatToken_C = secreatToken;
		playerAName_C = playerAName;
		playerPhoto_C = playerPhoto;
		new Thread(new Runnable() {
			public void run() {
				try {
					client = connectToLocal();
					serverReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
					serverWriter = new PrintStream(client.getOutputStream(), true, "utf-8");
					System.out.println("client io set up complete ");
					JSONObject check = new JSONObject();
					check.put("action", "check");
					check.put("API Token", APIToken_C);
					check.put("secreatToken", secreatToken_C);
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
			return new Socket(serverIp, 56);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("無法連線至伺服器");
			logger.log("無法連線至伺服器");
		}
		return null;
	}

	private void connectToServer() {
		String serverInput;
		try {
			while ((serverInput = serverReader.readLine()) != null) {
				try {
					Thread.interrupted();
					System.out.println("message from server : " + serverInput);
					logger.log("message from server : " + serverInput);
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
						sendToServer.put("secreatToken", secreatToken_C);
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
						WinFrame winFrame = new WinFrame();
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						winFrame.dispose();
						System.out.println("you win");
					} else if (serverMsg.get("action").equals("lose")) {
						playRoom.getChessBoard().removeChessListener();
						LoseFrame loseFrame = new LoseFrame();
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						loseFrame.dispose();
						System.out.println("you lose");
					} else if (serverMsg.get("action").equals("check fail")) {
						JOptionPane.showMessageDialog(null, "非法登入，五秒後自動關閉!!!!", "遊戲資訊", JOptionPane.ERROR_MESSAGE);
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.exit(1);
					} else if (serverMsg.get("action").equals("is alive")) {
						serverWriter.println("i am alive");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.log(e.getMessage());
					logger.log(e.getStackTrace().toString());
				}
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(e.getMessage());
			logger.log(e.getStackTrace().toString());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.log(e.getMessage());
			logger.log(e.getStackTrace().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("server is shutdown");
			logger.log("server is shutdown");
			JOptionPane.showMessageDialog(null, "與伺服器斷線 將在五秒後自動關閉!!!!", "遊戲資訊", JOptionPane.ERROR_MESSAGE);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				logger.log(e1.getMessage());
				logger.log(e1.getStackTrace().toString());
			}
			System.exit(1);
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
			logger.log("client send chat to server : " + sendToServer);
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
