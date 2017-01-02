package servelet.ex01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server02 {
    int portNo;
	public Server02(int portNo) {
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
			DataOutputStream dos = new DataOutputStream(os);
			DataInputStream dis = new DataInputStream(is);
			int n = 5123456;
			dos.writeLong(n);
			System.out.println("S: 寫出" + n);
			long l1 = dis.readLong();
			System.out.println("S: 讀回" + l1	);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }





	public static void main(String[] args) {
		Server02  ss01 = new Server02(65432);

	}

}
