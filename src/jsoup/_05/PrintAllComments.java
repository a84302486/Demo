package jsoup._05;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

public class PrintAllComments {
	static List<String> comments = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		Document doc;
		doc = Jsoup.parse(new File("nodes.html"), "BIG5");
		System.out.println("處理前的內容:");
		System.out.println(doc.toString());
		findAndRemoveComments(doc);
		System.out.println("----------------------");
		System.out.println("處理後的內容:");
		System.out.println(doc.toString());
		System.out.println("========================");
		System.out.println("取出的註解:");
		for(String s: comments){
			System.out.println(s);
		}
	}
	private static void findAndRemoveComments(Node node) {
        for (int i = 0; i < node.childNodes().size();) {
            Node child = node.childNode(i);
            //if (child.nodeName().equals("#comment")) {
            if (child instanceof Comment) {
            	Comment com = (Comment)child;
            	comments.add(com.toString() + ", Node Name=" + com.nodeName() + ", getData()=" + com.getData());
            	child.remove();
            } else {
            	findAndRemoveComments(child);
                i++;
            }
        }
    }        
}
