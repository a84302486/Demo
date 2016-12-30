package jsoup._02;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Element_getElementsByAttribute {
	public static void main(String[] args) throws IOException {
		Document doc = null;
		File nodes = new File("nodes.html");
		try {
			doc = Jsoup.parse(nodes, "BIG5");
			System.out.println("body.getElementsByAttribute(): ");
			Element eb = doc.body();
			Elements elb = eb.getElementsByAttribute("color");
			for(Element e: elb){
				System.out.println(e.nodeName() + "   ©ÒÄÝ¼ÐÅÒ: " + e.outerHtml());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
