package jsoup._20;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParsingBodyFragment {
	public static void main(String[] args) throws IOException {
        String htmlFragment = "<h1>Hi you!</h1><p>將網頁片段恢復成一份完整的HTML文件</p>";
		Document doc = Jsoup.parseBodyFragment(htmlFragment);
		Element head = doc.head();
		System.out.println(head.toString() + " NodeName=" + head.nodeName());
		System.out.println("-------------------");
		Element body = doc.body();
		System.out.println(body.toString() + " NodeName=" + body.nodeName());
		System.out.println("-------------------");		
		String fullHtml = doc.html();
		System.out.println(fullHtml);
		
	}
}
