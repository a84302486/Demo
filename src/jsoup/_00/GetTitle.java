package jsoup._00;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetTitle {
	public static void main(String[] args) {
	
		String html = "<html><head><title>MyTitle</title></head>"
				      + "<body><p>Hello2</p></body></html>";
		Document doc1  = Jsoup.parse(html) ;
		System.out.println(doc1.title());
	}
}
