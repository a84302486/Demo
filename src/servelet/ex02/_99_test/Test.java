package servelet.ex02._99_test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = URLEncoder.encode("Mary", "UTF8");
		System.out.println(s);
	}
}
