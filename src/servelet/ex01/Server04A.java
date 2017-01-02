package servelet.ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server04A {
    int portNo;
	public Server04A(int portNo) {
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
			ObjectInputStream ois = new ObjectInputStream(is);
			FileData fd = (FileData) ois.readObject();
			String filename = fd.getFilename();
			long   filesize = fd.getFileSize();
			String path = "D:\\Java004";
            File file = new File(path, filename);
			FileInputStream fis = new FileInputStream(file);
			fis.skip(filesize);   // <=========
			System.out.println("S: 已跳過(skip)" + filesize + "個位元組");
			int count = 0;
			byte[] b = new byte[8192];
			int len = 0 ;
			while ((len=fis.read(b))!=-1){
				os.write(b, 0, len);
				count += len;
				if (count > 63294833 / 2) {
					break;
				}
			}
			System.out.println("S: 送出" + count + "位元組");
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }





	public static void main(String[] args) {
		Server04A  ss01 = new Server04A(65432);

	}

}
