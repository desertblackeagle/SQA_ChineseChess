package net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import control.TransferAbsoluteToXY;
import data.chess.Chess;
import data.chess.ChessXYLocOnChessBoard;

public class Connecter extends Observable implements Observer {
	private Socket client;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ChessXYLocOnChessBoard chessXYLocOnChessBoard;

	public Connecter(ChessXYLocOnChessBoard chessXYLocOnChessBoard) {
		// TODO Auto-generated constructor stub
		this.chessXYLocOnChessBoard = chessXYLocOnChessBoard;
		new Thread(new Runnable() {
			public void run() {
				System.out.println("thread start");
				try {
					System.out.println("new start");
//					client = new Socket("123.204.84.144", 56);
					client = new Socket("127.0.0.1", 56);
					System.out.println("socket end");
					oos = new ObjectOutputStream(client.getOutputStream());
					System.out.println("oos complete");
					ois = new ObjectInputStream(client.getInputStream());
					System.out.println("ois complete");
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connectToServer();
			}
		}).start();
	}

	private void connectToServer() {
		try {
			MessagePack msgPack;
			while ((msgPack = (MessagePack) ois.readObject()) != null) {
				System.out.println("client : " + msgPack);
				if (msgPack.getAction().equals("move")) {
					int x = (int) msgPack.getObjectHashMap().get("chess X");
					int y = (int) msgPack.getObjectHashMap().get("chess Y");
					int toX = (int) msgPack.getObjectHashMap().get("chess toX");
					int toY = (int) msgPack.getObjectHashMap().get("chess toY");
					System.out.println("socket move chess : " + x + " " + y + " " + toX + " " + toY);
					chessXYLocOnChessBoard.getChess(x, y).setChessToXY(toX, toY);
					chessXYLocOnChessBoard.getChess(x, y).setChessLocX(toX);
					chessXYLocOnChessBoard.getChess(x, y).setChessLocY(toY);
					chessXYLocOnChessBoard.setChess(x, y, toX, toY);
				} else if (msgPack.getAction().equals("chat")) {
					String chess = (String) msgPack.getObjectHashMap().get("chat msg");
					setChanged();
					notifyObservers(chess);
				} else if (msgPack.getAction().equals("sayBye")) {

				} else if (msgPack.getAction().equals("start game")) {

				}
			}
		} catch (ClassNotFoundException e) {
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
			MessagePack sendToServer = new MessagePack("chat");
			sendToServer.addData("chat msg", (String) arg);
			try {
				oos.writeObject(sendToServer);
				System.out.println("Obj " + oos + " Pack " + sendToServer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (o instanceof TransferAbsoluteToXY) {
			if (arg instanceof Chess) {
				MessagePack sendToServer = new MessagePack("move");
				sendToServer.addData("chess name", ((Chess) arg).getChessName());
				sendToServer.addData("chess X", ((Chess) arg).getChessLocX());
				sendToServer.addData("chess Y", ((Chess) arg).getChessLocY());
				sendToServer.addData("chess toX", ((Chess) arg).getChessToX());
				sendToServer.addData("chess toY", ((Chess) arg).getChessToY());
				sendToServer.addData("chess color", ((Chess) arg).getColor());
				try {
					oos.writeObject(sendToServer);
					System.out.println("Obj " + oos + " Pack " + sendToServer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
