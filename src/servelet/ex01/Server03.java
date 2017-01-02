package servelet.ex01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server03 {
    int portNo;
	public Server03(int portNo) {
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
//			DataOutputStream dos = new DataOutputStream(os);
//			DataInputStream dis = new DataInputStream(is);
			FileInputStream fis = new FileInputStream("D:\\rt.jar");
			int count = 0;
			byte[] b = new byte[8192];
			int len = 0 ;
			while ((len=fis.read(b))!=-1){
				os.write(b, 0, len);
				count += len;
			}
			System.out.println("S: 送出" + count + "位元組");
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public static void main(String[] args) {
		Server03  ss01 = new Server03(65432);
	}
}
