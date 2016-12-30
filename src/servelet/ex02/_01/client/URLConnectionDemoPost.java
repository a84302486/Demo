package servelet.ex02._01.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class URLConnectionDemoPost {
	String param1 = "Hello, World, 大家好�?�中??��??";
	String param2 = "value2";
	String param3 = "age";
	String charset = java.nio.charset.StandardCharsets.UTF_8.name();
	public void setPostMethod()  {
		try {
		   String query = String.format("param1=%s&param2=%s&age=%d",
				URLEncoder.encode(param1, charset),
				URLEncoder.encode(param2, charset), 38);
		   URL url = new URL("http://localhost:8080/javaNetwork/Kitty");
		   InputStream is = url.openStream();
		   HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
		    connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			OutputStreamWriter osw = new OutputStreamWriter(
					connection.getOutputStream());
			osw.write(query);
			osw.close();
			
			InputStream is1 = connection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is1, "UTF-8"));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				System.out.println("Server端�?��?��?��?��?��?��??==>" + decodedString);
			}
			in.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void setPostMethodA() {
	  String resource = "http://mops.twse.com.tw/mops/web/ajax_t51sb07";

      try {
		String query1 = "encodeURIComponent=1&step=1&firstin=1&off=1&TYPEK=sii&year=101&season=02";
		URL url = new URL(resource);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Referer", "http://mops.twse.com.tw/mops/web/t51sb07");
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");
		connection.setRequestProperty("Content-Length", "72");
		OutputStreamWriter osw = new OutputStreamWriter(
				connection.getOutputStream());
		osw.write(query1);
		osw.close();
		InputStream is = connection.getInputStream();

		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		String decodedString;
		String targetDir = "d:\\_Stock\\2010Q1";
		String filename  = "SeasonlyReport.txt";
		File dir = new File(targetDir);
		if (!dir.exists())  dir.mkdirs();
		File file = new File(dir, filename);
		PrintWriter out = new PrintWriter(new FileWriter(file));
		while ((decodedString = in.readLine()) != null) {
		   out.println(decodedString);
		}
		in.close();
		System.out.println("程�?��?��?��?��?��?��?��?�置�?" + targetDir + "\\" + filename );
      } catch(Exception e){
    	  e.printStackTrace();
      }
	}

	public static void main(String[] args) throws Exception {
		URLConnectionDemoPost url = new URLConnectionDemoPost();
		url.setPostMethodA();
	}
}