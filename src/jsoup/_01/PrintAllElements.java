package jsoup._01;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PrintAllElements {
    //
	public static void main(String[] args) {
		Document doc = null;
		File nodes = new File("nodes.html");
		try {
		   doc = Jsoup.parse(nodes, "BIG5");
		   Elements els = doc.getAllElements();
		   for(Element e : els ){
			   System.out.println(" e=" + e);
			   System.out.println("-------------------------");
		   }
		} catch(IOException e){
			e.printStackTrace();
		}

	}

}
