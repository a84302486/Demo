package jsoup._02;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Element_firstElementSibling {
	public static void main(String[] args) throws IOException {
		Document doc = null;
		File nodes = new File("nodes.html");
		try {
			doc = Jsoup.parse(nodes, "BIG5");
			Element e1 = doc.select("title").get(0);
			System.out.println("<title>.firstElementSibling()=" + e1.firstElementSibling());
			System.out.println("------------------------");
			Element e2 = doc.select("p").get(0);
			System.out.println("<p>.firstElementSibling()=" + e2.firstElementSibling());
			System.out.println("------------------------");
			Element e3 = doc.select("table").get(0);
			System.out.println("<table>.firstElementSibling()=" + e3.firstElementSibling());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
