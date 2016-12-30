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
				//���F�קK�{���L�X�����n������r���A�[�W�U�C��if�ԭz
				if (node instanceof TextNode) {
					TextNode tn = (TextNode) node;
					if (tn.isBlank())
						return;
				}
				// ---------------------------------------------
				String msg = "�J��s�`�I: NodeName=" + node.nodeName();
				if (node instanceof Comment) {
					Comment comment = (Comment) node;
					msg += ", �`�I���A=Comment, ���e:" + comment.getData();
				} else if (node instanceof DataNode) {
					DataNode dn = (DataNode) node;
					msg += ", �`�I���A=DataNode, ���e:" + dn.getWholeData();
				} else if (node instanceof DocumentType) {
					msg += ", �`�I���A=DocumentType, ���e:" + node.toString();
				} else if (node instanceof Document) {
					msg += ", �`�I���A=Document";
				} else if (node instanceof Element) {
					Element el = (Element) node;
					msg += ", �`�I���A=Element, ���ҦW��=" + el.tagName()
							+ ", ownText:" + (el.ownText().trim().length() == 0 ? "(�L)" : el.ownText()) + "\n" ;
					Attributes attrs = el.attributes();
					List<Attribute>  list1 = attrs.asList();
					for(Attribute attr : list1){
						msg += attr.html() + ", [(�ݩ�)" + attr.getKey() + "," + attr.getValue() + "]  ";  
					}
				} else if (node instanceof TextNode) {
					TextNode tn = (TextNode) node;
                    msg += ", �`�I���A=TextNode, ���e:" + (tn.isBlank() ? "(��)" : tn.text());
				} else {
					msg += ", �`�I���A=��L";
				}
				System.out.println(msg);
			}

			public void tail(Node node, int depth) {
				if (node instanceof TextNode) {
					TextNode tn = (TextNode) node;
					if (tn.isBlank())
						return;
				}
				System.out.println("���}���`�I: NodeName=" + node.nodeName());
			}
		});
	}
}
