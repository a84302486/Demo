package servelet.ex01;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server01 {
	 int portNo;
		public Server01(int portNo) {
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
				int n = 511;
				os.write(n);
				System.out.println("S: 寫出" + n);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	public static void main(String[] args) {
		Server01  ss01 = new Server01(65432);
		
	}
}
