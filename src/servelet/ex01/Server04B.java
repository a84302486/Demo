package servelet.ex01;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server04B {
	int portNo;
	InputStream is;
	OutputStream os;
	String[] sa = {"rt.jar", "src.zip"};
    String dir = "D:\\Java004\\SRC";
	public Server04B(int portNo) {
		this.portNo = portNo;
		listen();
	}
    public void send(){
    	DataOutputStream  dos = new DataOutputStream(os);
    	Network04BUtils utils = new Network04BUtils();
    	try {
    	  dos.writeInt(sa.length);
    	  dos.flush();
    	  for(String filename : sa){
    		  File file = new File(dir, filename);
    		  utils.sendFile(file, dos);
    	  }
    	} catch(IOException ex){
    		ex.printStackTrace();
    	}
    }
	public void listen() {
		System.out.println("S: 正在建構ServerSocket物件");
		try {
			ServerSocket ss = new ServerSocket(portNo);
			Socket sock = ss.accept();
			System.out.println("S: 有客戶端電腦提出連線");
			is = sock.getInputStream();
			os = sock.getOutputStream();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server04B ss01 = new Server04B(65432);
		//ss01.setFileName(".....");
		//...
		
		
		
		
		
        ss01.send();
	}

}
