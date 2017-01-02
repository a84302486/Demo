package jsoup._08;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class Node_vs_Element {

	public static void main(String[] args) {
		Document doc = null;
		File nodes = new File("nodes.html");
		try {
		   doc = Jsoup.parse(nodes, "BIG5");
//		   System.out.println("----- Document����(doc)�����զ�: -----");
//		   showElementsAndNodes(doc);
		   System.out.println("----- Element����(<head>)�����զ�: -----");
		   showElementsAndNodes(doc.head());
//		   Element e = doc.select("p").get(0);
//		   System.out.println("----- Element����(<p>)�����զ�: -----");
//		   showElementsAndNodes(e);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
    static void showElementsAndNodes(Element el){
		   System.out.println("�H�U���Node(�`�I)");
		   List<Node> list = el.childNodes();
		   for(Node n: list){
			   if (n instanceof TextNode){
				   TextNode td = (TextNode)n;
				   if (td.isBlank()) {
					   continue;
				   } else {
					   System.out.println("TextNode=" + n.nodeName() + ", " + td.text());
				   }
			  
			   } else if (n instanceof DataNode){
				   DataNode e= (DataNode)n;
				   System.out.println("DataNode=" + e.nodeName()+ ", " + e.getWholeData());
			   } else if (n instanceof Element){
				   Element e= (Element)n;
				   String tagName = e.tagName();
				   String dnstr = "";
				   // �B�zDataNode(<script>�B<style>)
				   if (tagName.equalsIgnoreCase("script") || 
					   tagName.equalsIgnoreCase("style")
					  ){
					   List<DataNode> lt = e.dataNodes();
					   for (DataNode dn : lt){
						   dnstr += dn.getWholeData() + " ";
					   }
				   }
				   System.out.println("Element=" + e.tagName() + (dnstr.length() > 0 ? " ���e:" + dnstr : ""));		
			   } else {
				   System.out.println("Other=" + n.nodeName() + ", " + n.toString());	
			   }
		   }
		   System.out.println("--------------------");
		   System.out.println("�H�U���Element(HTML������)");
    	   Elements els = el.children();
		   for(Element e: els){
			   System.out.println(e.nodeName());
		   }
		   System.out.println("================================");
    }
    
}
