package jsoup._02;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Element_getAllElements {
	public static void main(String[] args) throws IOException {
		Document doc = null;
		File nodes = new File("nodes.html");
		try {
			doc = Jsoup.parse(nodes, "BIG5");
			Element e1 = doc.select("p").get(0);
			System.out.println("<p>.getAllElements(): ");
			Elements els = e1.getAllElements();
			for(Element e: els){
				System.out.println(e.nodeName());
			}
			System.out.println("------------------------");
			System.out.println("body.getAllElements(): ");
			Element eb = doc.body();
			Elements elb = eb.getAllElements();
			for(Element e: elb){
				System.out.println(e.nodeName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
