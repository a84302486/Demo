package servelet.ex01;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class Client04BUtils {
	public void sendFile(File file, OutputStream os){
		//���ǰe�ɮת��줸�խӼ�
		//�ǰe�ɮת����e
		long fileSize = file.length();
		String filename = file.getName();
		DataOutputStream dos = new DataOutputStream(os);
		try {
		  dos.writeUTF(filename);
		  dos.writeLong(fileSize);
		  dos.flush();
		  try (
		    FileInputStream fis = new FileInputStream(file);
		  ){
			 int len = 0;
			 byte[] b = new byte[81920];
			 while ((len=fis.read(b))!=-1){
				 dos.write(b, 0, len);
			 }
		  }
		} catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
    public void receiveFile(File file, InputStream is, OutputStream os){
		
	}
}
