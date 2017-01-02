package jsoup._00;

import java.io.*;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CreateDocument {
	public static void main(String[] args) {
		// 方法一:
		// 由一份HTML文件所組成的字串來產生org.jsoup.nodes.Document
//		String html = "<!DOCTYPE html><html><head><meta charset='utf-8'>"  
//				      + "<title>練習ex02_02</title></head>"  
//				      + "<body><center><table border='1'>"
//				      + "<tr><td width='40' align='center'>虎</td><td width='40' align='CENTER'>豹</td>"
//				      + "<td width='40' align='CENTER'>獅</td><td width='40' align='CENTER'>象</td></tr>"
//				      + "<tr><td width='40' align='center'>梅</td><td width='40' align='CENTER'>蘭</td>"
//				      + "<td width='40' align='CENTER'>竹</td><td width='40' align='CENTER'>菊</td></tr>"
//				      + "</table>"
//				      + "</body></html>";
		String html = "<table border='1'>"
				      + "<tr><td width='40' align='center'>虎</td><td width='40' align='CENTER'>豹</td>"
				      + "<td width='40' align='CENTER'>獅</td><td width='40' align='CENTER'>象</td></tr>"
				      + "<tr><td width='40' align='center'>梅</td><td width='40' align='CENTER'>蘭</td>"
				      + "<td width='40' align='CENTER'>竹</td><td width='40' align='CENTER'>菊</td></tr>"
				      + "</table>";
		Document doc1  = Jsoup.parse(html) ;
		System.out.println(doc1.toString());
		System.out.println("------------------------------------------------");
		// 方法二:
		// 由HTML文件檔來產生org.jsoup.nodes.Document
		// 
		File htmlFile = new File("Season.html");
		Document doc2 = null; 
		try {
			doc2 = Jsoup.parse(htmlFile, "UTF-8");
		} catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(doc2.toString());
		System.out.println("------------------------------------------------");
		// 方法三:
		// 由URL來產生org.jsoup.nodes.Document
		//
		Document doc3  = null;
		String url = "http://www.solvusoft.com/zh-tw/file-extensions/file-extension-html/";
		String baseurl = "http://www.solvusoft.com/"; 
		try {
		    doc3 = Jsoup.parse(new URL(url).openStream(), "UTF-8", baseurl);
		} catch(IOException e){
			e.printStackTrace();
		}
		System.out.println(doc3.toString());
		System.out.println("------------------------------------------------");
	}
}
