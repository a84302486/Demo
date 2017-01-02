package servelet.ex01;

import java.io.Serializable;

public class FileData implements Serializable{
	String filename;
	long fileSize;
	public FileData(){
		
	}
	public FileData(String filename, long fileSize) {
		super();
		this.filename = filename;
		this.fileSize = fileSize;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	
}
