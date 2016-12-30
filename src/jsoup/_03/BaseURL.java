package jsoup._03;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BaseURL {

	public static void main(String[] args) {
		File htmlFile = new File("Season.html");
		Document doc2 = null; 
		try {
			//doc2 = Jsoup.parse(htmlFile, "UTF-8", "http://www.google.com/image/abc/");
			doc2 = Jsoup.parse(htmlFile, "UTF-8", "http://tw.yahoo.com/");
			Elements els = doc2.select("img");
			for(Element e : els){
				System.out.println(e.absUrl("src"));
			}
			
		} catch(IOException e){
			e.printStackTrace();
		}		
	}
}