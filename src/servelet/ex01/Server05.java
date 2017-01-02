package servelet.ex01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server05 {
	int portNo;
	public Server05(int portNo) {
		this.portNo = portNo;
		listen();
	}
    public void listen(){
    	System.out.println("S: 正在建構ServerSocket物件");
    	try {
			ServerSocket ss = new ServerSocket(portNo);
			Socket sock = ss.accept();
			System.out.println("S: 有客戶端電腦提出連線");
			InputStream is = sock.getInputStream();
			OutputStream os = sock.getOutputStream();
			BufferedReader br = new  BufferedReader(
					new InputStreamReader(is, "UTF-8"));
			String line  = null;
			while ((line = br.readLine())!= null){
				if((line.trim().length())==0)
					break;
				System.out.println("瀏覽器送來的資料:" + line);
				}
			System.out.println("讀取完畢....");
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
			pw.println("HTTP/1.1 200 OK"); //狀態列
			pw.println("Content-Type: text/html;charset=UTF-8");//回應標頭
			pw.println("Connection: Close"); //回應標頭
			pw.println("");  //空一列空白行
			//回應的本體
			pw.println("<html><head><title>由小小伺服器送出的回應");
			pw.println("</title></head><body>");
			pw.println("<h1>Hello ,world,大家好</h1>");
			pw.println("<hr>");
			pw.println("<h2>Hello ,world,大家好</h2>");
			pw.println("</body></html>");
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		Server05  ss01 = new Server05(65432);
	}

}
