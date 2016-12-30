package jsoup._04;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SelectTags02 {

	public static void main(String[] args) throws IOException {
		String html = "<!DOCTYPE html><html><head><meta charset='utf-8'>"  
			      + "<title>½m²ßex02_02</title></head>"  
			      + "<body><center><table border='1'>"
			      + "<tr><td width='40' align='center'>ªê</td><td width='40' align='CENTER'>°\</td>"
			      + "<td width='40' align='CENTER'>·à</td><td width='40' align='CENTER'>¶H</td></tr>"
			      + "<tr><td width='40' align='center'>±ö</td><td width='40' align='CENTER'>Äõ</td>"
			      + "<td width='40' align='CENTER'>¦Ë</td><td width='40' align='CENTER'>µâ</td></tr>"
			      + "</table>"
			      + "</body></html>";
	    Document doc1  = Jsoup.parse(html) ;
	    Elements tr = doc1.select("tr");
		for(Element e: tr){
			System.out.println("text: ");
			System.out.println(e.text());
			System.out.println("----------------------------------------");
			System.out.println("html: ");
			System.out.println(e.html());
			System.out.println("========================================");
		}	
	}
}
