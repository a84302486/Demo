package jsoup._06;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

public class PrintAllDataNodes {
	static List<String> dataNodes = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		Document doc;
		doc = Jsoup.parse(new File("nodes.html"), "BIG5");
		System.out.println("處理前的內容:");
		System.out.println(doc.toString());
		findDataNodes(doc);
		System.out.println("========================");
		System.out.println("取出的DataNode:");
		for(String s: dataNodes){
			System.out.println(s);
		}
	}
	private static void findDataNodes(Node node) {
        for (int i = 0; i < node.childNodes().size();) {
            Node child = node.childNode(i);
            //if (child.nodeName().equals("#comment")) {
            if (child instanceof DataNode) {
            	DataNode dn = (DataNode)child;
            	dataNodes.add(dn.toString() + ", Node Name=" + dn.nodeName() + ", getWholeData()=" + dn.getWholeData());
            	child.remove();
            } else {
            	findDataNodes(child);
                i++;
            }
        }
    }        
}
