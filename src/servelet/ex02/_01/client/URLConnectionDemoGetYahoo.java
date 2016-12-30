package servelet.ex02._01.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class URLConnectionDemoGetYahoo {
	String param1 = "Hello, World,大家好";
	String param2 = "value2";
	String param3 = "age";
	String charset = java.nio.charset.StandardCharsets.UTF_8.name();

	public void setGetMethod() {
		try {
//			String query = String.format("param1=%s&param2=%s&age=%d", URLEncoder.encode(param1, charset),
//					URLEncoder.encode(param2, charset), 18);

			URL url = new URL("https://tw.yahoo.com/");

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			InputStream is = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			String filename = "Yahoo.html";
			File folder = new File("D:\\html\\");
			if(!folder.exists()) folder.mkdirs();
			File file = new File (folder,filename);
			try(
				FileOutputStream fos = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fos , "UTF8");
				PrintWriter pw = new PrintWriter(osw);
				){
				String decodedString;
				while ((decodedString = in.readLine()) != null) {
					System.out.println("Server:回傳資料==>  " + decodedString);
					pw.println(decodedString);
				}
				in.close();
			}catch(IOException e ){
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		URLConnectionDemoGetYahoo url = new URLConnectionDemoGetYahoo();
		url.setGetMethod();
	}
}