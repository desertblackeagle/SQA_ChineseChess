package control.net;

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

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

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
		ByteOutputStream bos = new ByteOutputStream();
		for (int length; (length = is.read(buffer)) > 0; bos.write(buffer, 0, length))
			;
		is.close();
		bos.close();
		return bos.getBytes();
	}

//	public static void main(String[] args) {
//		ImageDownload h = new ImageDownload();
//
//		try {
//			FileOutputStream fos = new FileOutputStream("c:\\1.png");
////			System.out.println(h.download("https://sqa.swim-fish.info/media/uploaded_files/user/18fdd1ad6380f180dd42f87085ead66a48183f927e1ea0b1c22efa845015adb5/2014_05_21_4bfa431ae739ca227f4a10605fcaec6d.jpg"));
//			byte[] buffer = h.download("https://sqa.swim-fish.info/media/uploaded_files/user/18fdd1ad6380f180dd42f87085ead66a48183f927e1ea0b1c22efa845015adb5/2014_05_21_4bfa431ae739ca227f4a10605fcaec6d.jpg");
//			fos.write(buffer);
//			System.out.println(buffer.length);
//			fos.close();
//		} catch (ConnectException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
