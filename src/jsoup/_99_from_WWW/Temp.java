package jsoup._99_from_WWW;

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

public class Temp {

	public static void main(String[] args) {
		Document doc = null;
		File nodes = new File("nodes.html");
		try {
		   doc = Jsoup.parse(nodes, "BIG5");
		   System.out.println("----- Document物件(doc)內的組成: -----");
		   showElementsAndNodes(doc);
		   System.out.println("----- Element物件(<head>)內的組成: -----");
		   showElementsAndNodes(doc.head());
		   Element e = doc.select("p").get(0);
		   System.out.println("----- Element物件(<p>)內的組成: -----");
		   showElementsAndNodes(e);
		   Element e1 = e.child(0);
		   
		   String out = e1.outerHtml();
		   System.out.println(" e1=" + e1);
		   System.out.println("out=" + out);
		   Element n = e1.parent();
		   System.out.println("par=" + n);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
    static void showElementsAndNodes(Element el){
		   System.out.println("以下顯示Node(節點)");
		   List<Node> list = el.childNodes();
		   for(Node n: list){
			   if (n instanceof TextNode){
				   TextNode td = (TextNode)n;
				   if (td.isBlank()) {
					   continue;
				   } else {
					   System.out.println("tn=" + n.nodeName() + ", " + td.text());
				   }
			  
			   } else if (n instanceof DataNode){
				   DataNode e= (DataNode)n;
				   System.out.println("dn=" + e.nodeName()+ ", " + e.getWholeData());
			   } else if (n instanceof Element){
				   Element e= (Element)n;
				   String tagName = e.tagName();
				   String dnstr = "";
				   // 處理DataNode(<script>、<style>)
				   if (tagName.equalsIgnoreCase("script") || 
					   tagName.equalsIgnoreCase("style")
					  ){
					   List<DataNode> lt = e.dataNodes();
					   for (DataNode dn : lt){
						   dnstr += dn.getWholeData() + " ";
					   }
				   }
				   
				   
				   System.out.println("el=" + e.tagName() + (dnstr.length() > 0 ? " 內容:" + dnstr : ""));		
				   
			   } else {
				   System.out.println("ot=" + n.nodeName() + ", " + n.toString());	
			   }
			   
			   
		   }
		   System.out.println("--------------------");
		   System.out.println("以下顯示Element(HTML的標籤)");
    	   Elements els = el.children();
		   for(Element e: els){
			   System.out.println(e.nodeName());
		   }
		   System.out.println("================================");
    }
    
}
