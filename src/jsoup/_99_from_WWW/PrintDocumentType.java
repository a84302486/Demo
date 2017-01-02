package jsoup._99_from_WWW;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class PrintDocumentType {
	static List<String> dataNodes = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		Document doc;
		doc = Jsoup.parse(new File("nodes.html"), "BIG5");
//		System.out.println("處理前的內容:");
//		System.out.println(doc.toString());
		findDocumentType1(doc);
		
	}
	private static void findDocumentType1(Document doc) {
		Element html = doc.getElementsByTag("html").get(0);
		Node node = html.previousSibling();
		
		while ( node != null ){
		   if ( node instanceof DocumentType){
			   DocumentType dt = (DocumentType)node;
			   System.out.println(dt.toString() + ", Node Name=" + dt.nodeName());
		   } else {
			   System.out.println("不是DocumentType, " + node.getClass().getName());
		   }
		   node =   node.previousSibling();
		}
    }
	private static void findDocumentType2(Node node) {
        for (int i = 0; i < node.childNodes().size();i++) {
            Node child = node.childNode(i);
            if (child instanceof DocumentType) {
            	DocumentType dt = (DocumentType)child;
            	System.out.println(dt.toString() + ", Node Name=" + dt.nodeName());
            	return ;   // 由於每份HTML文件只有一個doctype，因此找到後就沒必要繼續執行
            } 
        }
    }
	private static void findDocumentType3(Node node) {
        for (int i = 0; i < node.childNodes().size();i++) {
            Node child = node.childNode(i);
            System.out.println(child.toString());
            System.out.println("-------------------");
        }
    }     
	        
}
