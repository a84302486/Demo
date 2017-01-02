package jsoup._01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeVisitor;

public class DumpHtmlDocument {
	public static void main(String[] args) throws IOException {
		Document doc;
		doc = Jsoup.parse(new File("nodes.html"), "BIG5");
		doc.traverse(new NodeVisitor() {
			public void head(Node node, int depth) {
				//為了避免程式印出不必要的換行字元，加上下列的if敘述
				if (node instanceof TextNode) {
					TextNode tn = (TextNode) node;
					if (tn.isBlank())
						return;
				}
				// ---------------------------------------------
				String msg = "遇到新節點: NodeName=" + node.nodeName();
				if (node instanceof Comment) {
					Comment comment = (Comment) node;
					msg += ", 節點型態=Comment, 內容:" + comment.getData();
				} else if (node instanceof DataNode) {
					DataNode dn = (DataNode) node;
					msg += ", 節點型態=DataNode, 內容:" + dn.getWholeData();
				} else if (node instanceof DocumentType) {
					msg += ", 節點型態=DocumentType, 內容:" + node.toString();
				} else if (node instanceof Document) {
					msg += ", 節點型態=Document";
				} else if (node instanceof Element) {
					Element el = (Element) node;
					msg += ", 節點型態=Element, 標籤名稱=" + el.tagName()
							+ ", ownText:" + (el.ownText().trim().length() == 0 ? "(無)" : el.ownText()) + "\n" ;
					Attributes attrs = el.attributes();
					List<Attribute>  list1 = attrs.asList();
					for(Attribute attr : list1){
						msg += attr.html() + ", [(屬性)" + attr.getKey() + "," + attr.getValue() + "]  ";  
					}
				} else if (node instanceof TextNode) {
					TextNode tn = (TextNode) node;
                    msg += ", 節點型態=TextNode, 內容:" + (tn.isBlank() ? "(空)" : tn.text());
				} else {
					msg += ", 節點型態=其他";
				}
				System.out.println(msg);
			}

			public void tail(Node node, int depth) {
				if (node instanceof TextNode) {
					TextNode tn = (TextNode) node;
					if (tn.isBlank())
						return;
				}
				System.out.println("離開此節點: NodeName=" + node.nodeName());
			}
		});
	}
}
