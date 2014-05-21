package log;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private FileWriter fw;

	public Logger(String path) {
		// TODO Auto-generated constructor stub
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		System.out.println(strDate);
		try {
			fw = new FileWriter(path + strDate + ".txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void log(String msg) {
		try {
			fw.write(msg + "\r\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger l = new Logger("c:/");
		l.log("123456789");

	}

}
