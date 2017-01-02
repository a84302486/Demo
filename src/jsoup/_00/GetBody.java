package jsoup._00;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class GetBody {
	public static void main(String[] args) {
	
		String html = "<p>Hello1</p>"
				      +  "<!-- Comment 1 -->"
				      +  "<table border='1'>"
				      + "<tr><td width='40' align='center'>ªê</td><td width='40' align='CENTER'>°\</td>"
				      + "<td width='40' align='CENTER'>·à</td><td width='40' align='CENTER'>¶H</td></tr>"
				      + "<tr><td width='40' align='center'>±ö</td><td width='40' align='CENTER'>Äõ</td>"
				      + "<td width='40' align='CENTER'>¦Ë</td><td width='40' align='CENTER'>µâ</td></tr>"
				      + "</table>"
				      +  "<!-- Comment 2 -->"
				      + "<p>Hello2</p>";
		Document doc1  = Jsoup.parse(html) ;
		Element  body = doc1.body();
		
		//Elements allElements = body.getAllElements();
		List<Node> list = body.childNodes();
		
		for(Node n : list){
			System.out.println(n);
			System.out.println("-----------------------------------");
		}
		System.out.println("======================================");
		System.out.println(body.toString());
	}
}
