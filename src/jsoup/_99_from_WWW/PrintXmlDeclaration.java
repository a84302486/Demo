package jsoup._99_from_WWW;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class PrintXmlDeclaration {
	static List<String> dataNodes = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		Document doc;
		InputStream is = new FileInputStream(new File("web.xml"));
		doc = Jsoup.parse(is, "UTF-8","",Parser.xmlParser()); 
		findXmlDeclaration(doc);
		System.out.println("------------------------");
		findTags(doc);
	}
	
	private static void findXmlDeclaration(Node node) {
        for (int i = 0; i < node.childNodes().size();i++) {
            Node child = node.childNode(i);
            if (child instanceof TextNode ){
            	TextNode td = (TextNode)child;
            	if (td.isBlank())  continue;
            }
            if (child instanceof XmlDeclaration) {
            	XmlDeclaration xml = (XmlDeclaration)child;
            	System.out.println(xml.toString() + ", Node Name=" + xml.nodeName());
            } 
        }
    }
	private static void findTags(Element node) {
        Elements els = node.select("servlet");
        for(int n=0; n < els.size(); n++){
        	Element e = els.get(n);
        	List<Node> list = e.childNodes();
        	for(Node nd : list){
        		if (nd instanceof TextNode){
        			TextNode tn = (TextNode)nd;
        			if (tn.isBlank()) {
        				continue;
        			} else {
        				System.out.println(tn.text());
        			}
        		} else {
        			System.out.println("¸`ÂI¦WºÙ:" + nd.nodeName());
        		}
        		
        	}
        	System.out.println("-----------------------");
        }
//        for(Element e: els){
//        	System.out.println(e.ownText());
//        }
    }     
	        
}
