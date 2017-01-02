package servelet.ex01;

import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client04B {
	String ip;
	int portNo;
	String dir = "D:\\Java004\\Data";
	InputStream is;
	OutputStream os;

	public Client04B(String ip, int portNo) {
		super();
		this.ip = ip;
		this.portNo = portNo;
		connect();
		
	}

	private void receive() {
		DataInputStream dis = new DataInputStream(is);
		Network04BUtils  utils = new Network04BUtils();
		try {
		   File folder = new File(dir);
		   int no = dis.readInt();
		   for(int n=0 ; n < no; n++){
			  utils.receiveFile(folder, dis);
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void connect() {
		try {
			Socket sock = new Socket(ip, portNo);
			System.out.println("C: 已經與伺服器連線");
			is = sock.getInputStream();
			os = sock.getOutputStream();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client04B cl04 = new Client04B("127.0.0.1", 65432);
		cl04.receive();
	}
}
