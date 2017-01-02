package jsoup._02;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// <p>這是一個 <strong>strong</strong> 好年份 <date>2016</date></p>
// 想要取出 『這是一個 好年份』，而不要取出『這是一個strong好年份2016』
public class Element_Text_OwnText {

	public static void main(String[] args) {
	   String html = "<p>這是一個 <strong>strong</strong> 好年份 <date>2016</date></p>" +
			         "<p>這是一個  <strong>weak</strong> 好年份  <date>2013</date></p>" ;
       Document doc = Jsoup.parse(html);
       Element e = doc.select("p").get(0);
       String ownText = e.ownText();
       String text = e.text();
       System.out.println("ownText=" + ownText);
       System.out.println("   text=" + text);
	}

}
