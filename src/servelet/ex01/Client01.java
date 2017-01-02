package servelet.ex01;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Client01 {
	String ip;
    int portNo;
    public Client01(String ip, int portNo) {
		super();
		this.ip = ip;
		this.portNo = portNo;
		connect();
	}

	public void connect(){
    	try {
			Socket sock = new Socket(ip , portNo);
			System.out.println("C: 已經與伺服器連線");
			InputStream is = sock.getInputStream();
			OutputStream os = sock.getOutputStream();
			int n = is.read();
			System.out.println("C: 讀到" + n);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		Client01  ss01 = new Client01("127.0.0.1" , 65432);
//		ss01.sendData(0);
	}
}
