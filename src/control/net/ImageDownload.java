package control.net;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class ImageDownload {
	public byte[] download(String source) throws IOException, ConnectException {
		TrustManager[] trustMyCerts = new TrustManager[] { new MyX509TrustManager() };
		URL url = new URL(source);
		SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
			sc.init(null, trustMyCerts, new java.security.SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		HttpsURLConnection URLConn = (HttpsURLConnection) url.openConnection();
		InputStream is = URLConn.getInputStream();
		byte[] buffer = new byte[1024];

		// 暫存BYTE陣列
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 緩衝
		// 紀錄讀進來長度
		int length = 0;
		// 假如等於-1代表沒有資料了
		while ((length = is.read(buffer)) != -1) {
			// 從緩衝區讀取buffer裡面0~length-1的位置
			baos.write(buffer, 0, length);
		}
		// ByteArrayOutputStream轉成位元陣列
		byte data[] = baos.toByteArray();
		is.close();
		baos.close();
		return data;
	}

}
