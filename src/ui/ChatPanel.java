package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import control.GameObservable;

public class ChatPanel extends JPanel {

	private JTextArea chatInputArea, chatTextArea;
	private JScrollPane chatScrollPanel;
	private int width, height;
	private String localPlayerName = "";
	private GameObservable obs;

	public ChatPanel(int locationX, int locationY, int width, int height) {
		// TODO Auto-generated constructor stub
		setSize(width, height);
		setLocation(locationX, locationY);
		setLayout(null);
		this.width = width;
		this.height = height;
		obs = new GameObservable();
		initJTextArea();
		initScrollPane();
		setComponentFont();
	}

	// init Component //

	private void setComponentFont() {
		// 設定元件字體格式
		chatInputArea.setFont(new Font(Font.DIALOG, Font.BOLD, 17));

	}

	private void initJTextArea() {
		// 聊天室顯示框
		chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);
		chatTextArea.setLineWrap(true);
		// 聊天室輸入框
		chatInputArea = new JTextArea();
		chatInputArea.setLineWrap(true);
		LineBorder tt = new LineBorder(Color.BLACK);
		chatInputArea.setBorder(tt);
		chatInputArea.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					// call socket to send the message
					System.out.println("chat : " + chatInputArea.getText().replace("\n", ""));
					if (!(chatInputArea.getText().replace("\n", "").length() == 0)) {
						chatTextArea.append(localPlayerName + " >" + chatInputArea.getText().replace("\n", "") + "\n");
						chatTextArea.setCaretPosition(chatTextArea.getText().length());
						setChanged();
						notifyObservers(localPlayerName + " >" + chatInputArea.getText().replace("\n", "") + "\n");
						chatInputArea.setText("");
					} else {
						chatInputArea.setText("");
					}
				}
			}
		});
		chatInputArea.setBounds(0, getHeight() * 6 / 7, getWidth(), getHeight() - getHeight() * 6 / 7);
		add(chatInputArea);
	}

	private void initScrollPane() {
		chatScrollPanel = new JScrollPane();
		chatScrollPanel.setBounds(0, 0, getWidth(), getHeight() * 6 / 7);
		chatScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatScrollPanel.getViewport().setView(chatTextArea);
		add(chatScrollPanel);
	}

	// init Component end //

	// API //

	public void appendChatArea(String chatString) {
		chatTextArea.append(chatString.replace('\n', ' ') + "\n");
		chatTextArea.setCaretPosition(chatTextArea.getText().length());
	}

	public String getLocalPlayerName() {
		return localPlayerName;
	}

	public void setLocalPlayerName(String localPlayerName) {
		this.localPlayerName = localPlayerName;
	}

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

	// API end //

}
