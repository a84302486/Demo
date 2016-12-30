package jsoup._04;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class FindSpecifiedTag {

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.connect("http://www.javatpoint.com").get();
		String title = doc.title();
		System.out.println("title is: " + title);
		System.out.println("------------------------");
		String html = "<!DOCTYPE html><html><head><meta charset='utf-8'>"  
			      + "<title>�m��ex02_02</title></head>"  
			      + "<body><center><table border='1'>"
			      + "<tr><td width='40' align='center'>��</td><td width='40' align='CENTER'>�\</td><td width='40' align='CENTER'>��</td><td width='40' align='CENTER'>�H</td></tr>"
			      + "<tr><td width='40' align='center'>��</td><td width='40' align='CENTER'>��</td><td width='40' align='CENTER'>��</td><td width='40' align='CENTER'>��</td></tr>"
			      + "</table>"
			      + "</body></html>";
			      
	    Document doc1  = Jsoup.parse(html) ;
	    Element body = doc1.body();
		System.out.println("body is: ");
		System.out.println(body.toString());
		System.out.println("============================");
	    Element head = doc1.head();
		System.out.println("head is: ");
		System.out.println(head.toString());
		System.out.println("============================");
		String fullhtml = doc1.html();
		System.out.println("fullhtml is: ");
		System.out.println(fullhtml.toString());
		System.out.println("============================");
	}

}
