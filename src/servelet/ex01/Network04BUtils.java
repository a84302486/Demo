package servelet.ex01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Network04BUtils {
	/**
	 * 
	 * @param file: 要傳送的檔案
	 * @param os: 能夠寫出檔案的輸出串流
	 */
	public void sendFile(File file, OutputStream os) {
		// 先傳送檔案的位元組個數
		// 傳送檔案的內容
		long fileSize = file.length();
		String filename = file.getName();
		DataOutputStream dos = new DataOutputStream(os);
		try {
			dos.writeUTF(filename);
			dos.writeLong(fileSize);
			dos.flush();
			try (FileInputStream fis = new FileInputStream(file);) {
				int len = 0;
				byte[] b = new byte[81920];
				while ((len = fis.read(b)) != -1) {
					dos.write(b, 0, len);
				}
				dos.flush();
				System.out.println("發送方: " + filename + "傳送完畢");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 
	 * @param folder: 儲存接收到之檔案的資料夾
	 * @param is: 能夠讀入檔案的輸入串流
	 */
	public void receiveFile(File folder, InputStream is) {
		long fileSize;   // 會收到的位元組個數
		String filename;
		DataInputStream dis = new DataInputStream(is);
		try {
			filename = dis.readUTF();
			fileSize = dis.readLong();
			File file = new File(folder, filename);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] b = new byte[8192];
			while (true) {
				int len = dis.read(b);
				fos.write(b, 0, len);
				fileSize -= len;
				if (fileSize <= 0) {
					break;
				}
			}
			System.out.println("接收方: " + filename + "讀取完畢");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
