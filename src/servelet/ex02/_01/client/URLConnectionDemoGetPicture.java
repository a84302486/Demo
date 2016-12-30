package servelet.ex02._01.client;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class URLConnectionDemoGetPicture {
	String param1 = "Hello, World, 大家�?";
	String param2 = "value2";
	String param3 = "age";
	String charset = java.nio.charset.StandardCharsets.UTF_8.name();

	public void setGetMethod() {
		try {
			String query = String.format("param1=%s&param2=%s&age=%d", URLEncoder.encode(param1, charset),
					URLEncoder.encode(param2, charset), 18);

			URL url = new URL("http://192.168.11.62:8080/JoStock/SendPictureServlet" + "?" + query);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			try (InputStream is = connection.getInputStream();
					OutputStream os = new FileOutputStream("D:\\picture0630.jpg");) {
				int len = 0;
				byte[] b = new byte[8192];
				while ((len = is.read(b)) != -1) {
					os.write(b, 0, len);
				}
			}
			System.out.println("Happy Ending");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		URLConnectionDemoGetPicture url = new URLConnectionDemoGetPicture();
		url.setGetMethod();
	}
}