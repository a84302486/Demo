package jsoup._02;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// <p>�o�O�@�� <strong>strong</strong> �n�~�� <date>2016</date></p>
// �Q�n���X �y�o�O�@�� �n�~���z�A�Ӥ��n���X�y�o�O�@��strong�n�~��2016�z
public class Element_Text_OwnText {

	public static void main(String[] args) {
	   String html = "<p>�o�O�@�� <strong>strong</strong> �n�~�� <date>2016</date></p>" +
			         "<p>�o�O�@��  <strong>weak</strong> �n�~��  <date>2013</date></p>" ;
       Document doc = Jsoup.parse(html);
       Element e = doc.select("p").get(0);
       String ownText = e.ownText();
       String text = e.text();
       System.out.println("ownText=" + ownText);
       System.out.println("   text=" + text);
	}

}
