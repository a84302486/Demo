package jsoup._07;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class PrintAllTextNodes {
	static List<String> textNodes = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		Document doc;
		doc = Jsoup.parse(new File("nodes.html"), "BIG5");
//		System.out.println("處理前的內容:");
//		System.out.println(doc.toString());
		findTextNode(doc);
		System.out.println("========================");
		System.out.println("取出的TextNode:");
		for(String s: textNodes){
			System.out.println(s);
		}
	}
	private static void findTextNode(Node node) {
        for (int i = 0; i < node.childNodes().size();i++) {
            Node child = node.childNode(i);
            if (child instanceof TextNode) {
            	TextNode tn = (TextNode)child;
            	if (!tn.isBlank()) {
                   //textNodes.add(tn.toString() + ", Node Name=" + tn.nodeName() + ", text()=" + tn.text());
                   textNodes.add("text()=" + tn.text());
            	}
            } else {
            	findTextNode(child);
                
            }
        }
    }        
}
