package net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

public class Connecter extends Observable implements Observer {
	private Socket client;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public Connecter() {
		// TODO Auto-generated constructor stub
		new Thread(new Runnable() {
			public void run() {
				System.out.println("thread start");
				try {
					System.out.println("new start");
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

				} else if (msgPack.getAction().equals("chat")) {
					String chat = (String) msgPack.getObjectHashMap().get("chat");
					setChanged();
					notifyObservers(chat);
				} else if (msgPack.getAction().equals("sayBye")) {

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
			sendToServer.addData("chat", (String) arg);
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
