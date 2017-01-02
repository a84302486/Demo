package jsoup._00;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TextvsOwnText {
	public static void main(String[] args) {
	
		String html = "<html><head><title>MyTitle</title></head>"
				      + "<body><p>Hello2</p><table><tr id='tr0'>³o¬OTRªºOwnText<td>AAA</td><td>BBB</td></tr></table></body></html>";
		Document doc1  = Jsoup.parse(html) ;
		Elements es = doc1.select("td");
		for(Element e : es){
			System.out.println("<td>==>" + e + "  ownText:" + e.ownText());	
		}
	}
}
