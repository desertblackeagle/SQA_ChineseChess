package ui.comboBox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

import control.ConfigGen;

public class DisplayComboBox extends JFrame {
	private String str[] = { "800x600", "1024x768", "1920x1080" };
	private JComboBox combo = null;
	private JPanel jp = null;
	private JLabel msg = null;
	private ConfigGen configGen;
	private JButton confirm;

	public DisplayComboBox() {
		// TODO Auto-generated constructor stub
		setSize(400, 300);
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height)
			frameSize.height = screenSize.height;
		if (frameSize.width > screenSize.width)
			frameSize.width = screenSize.width;
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		configGen = new ConfigGen();
		confirm = new JButton("確定");
		jp = new JPanel();
		msg = new JLabel("請選擇螢幕大小", JLabel.CENTER);
		combo = new JComboBox(str);
		combo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(combo.getSelectedItem());
				JSONObject display = new JSONObject();
				if (combo.getSelectedItem().equals("800x600")) {
					display.put("display size x", "800");
					display.put("display size y", "600");
					configGen.updateConfig("display", display.toString());
				} else if (combo.getSelectedItem().equals("1024x768")) {
					display.put("display size x", "1024");
					display.put("display size y", "768");
					configGen.updateConfig("display", display.toString());
				} else if (combo.getSelectedItem().equals("1920x1080")) {
					display.put("display size x", "1920");
					display.put("display size y", "1080");
					configGen.updateConfig("display", display.toString());
				}
			}
		});
		jp.setLayout(null);
		combo.setFont(new Font("", Font.BOLD, 35));
		combo.setBounds(getWidth() / 2 - 100, (getHeight() * 2 / 3) - 120, 200, 100);
		jp.add(combo);
		add(confirm);
		add(msg);
		add(jp);
		confirm.setBounds(getWidth() / 2 - 50, 190, 100, 50);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "您的修改將在下一次遊戲生效", "遊戲資訊", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		msg.setFont(new Font("", Font.BOLD, 18));
		msg.setBounds(0, 0, getWidth(), 90);
		jp.setBounds(0, 0, getWidth(), getHeight());
		repaint();
		revalidate();
	}

//	public static void main(String[] args) {
//		new DisplayComboBox();
//	}
}
