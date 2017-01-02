package jsoup.ex01;

import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Jsoup01 {

	public static void main(String[] args) throws Exception {
//		String html = "<html><head><title>Jsoup Demo</title>"
//			+ "</head>" 
//			+ "<body><h1>Hello</h1><h1>Kitty</h1></body></html>";
//		Document doc = Jsoup.parse(html);
//		//System.out.println(doc.toString());
//		Element e1 = doc.body();
//		Elements  es1 = e1.select("h1");
//		for(Element e:es1){
//			System.out.println(e.toString());
//			System.out.println("---------------");
//			
//		}
		//System.out.println(e1.toString());
		
		File file = new File("D:\\_Stock\\2010Q1\\SeasonlyReport.txt");
		Document doc2 = Jsoup.parse(file, "UTF8");
		Element e2 = doc2.body();
		Elements  es2 = e2.select("td");
		int count = 0 ;
		int newStock = 0;
		for(Element e:es2){
			count++;
			if (count >= 7) {
			   System.out.println(e.ownText());
			   System.out.println("---------------");
			   newStock++;
				if (newStock % 24 == 0) {
					System.out.println("===================");
				}
			}
		}
	}
}
