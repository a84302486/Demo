package servelet.ex01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
public class Client04A {
	String ip;
    int portNo;
    public Client04A(String ip, int portNo) {
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
//			DataInputStream dis = new DataInputStream(is);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			String filename = "rt.jar";
			long fileSize = 0 ;
			String path = "D:\\";
			File file = new File(path, filename);
			if (file.exists()){
				fileSize = file.length();
			} else {
				fileSize = 0;
			}
			FileData fd = new FileData(filename, fileSize);
			oos.writeObject(fd);
			System.out.println("C: 已送出FileData物件");
			oos.flush();
			int count = 0;
			try (
			FileOutputStream fos = new FileOutputStream
                        					   (file, true);
			) {		
				byte[] b = new byte[8192];
				int len = 0;
				while ((len = is.read(b)) != -1) {
					fos.write(b, 0, len);
					count += len;
				}
			}
			System.out.println("C: 收到" + count + "位元組");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
		Client04A  ss01 = new Client04A("192.168.11.62" , 65432);
//		ss01.sendData(0);
	}
}
