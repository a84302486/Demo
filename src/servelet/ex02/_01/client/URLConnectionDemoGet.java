package servelet.ex02._01.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class URLConnectionDemoGet {
	String param1 = "Hello, World,大家好";
	String param2 = "value2";
	String param3 = "age";
	String charset = java.nio.charset.StandardCharsets.UTF_8.name();

	public void setGetMethod() {
		try {
			String query = String.format("param1=%s&param2=%s&age=%d", URLEncoder.encode(param1, charset),
					URLEncoder.encode(param2, charset), 18);

			URL url = new URL("http://192.168.11.62:8080/JoStock/ReflectionServlet" + "?" + query);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream is = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				System.out.println("Server:回傳資料==>  " + decodedString);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		URLConnectionDemoGet url = new URLConnectionDemoGet();
		url.setGetMethod();
	}
}