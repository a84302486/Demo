package jsoup._99;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* **********************************************
 *  URLConnection類別的使用說明移到本程式的最後面。
 * **********************************************
 * 本程式功能說明；
 * 功能一. 讀取特定年(必須101年(含))以前，某一季度的上市公司的資產負債表。
 *       
 *       GetBalanceSheet gbs = new GetBalanceSheet(年份, 季度);
 *       gbs.getSeasonlyBalanceSheet();
 *       年份與季度都是整數，其中年份為中華民國年，季度為1,2,3,4
 *       
 * 功能二. 讀取連續年份之季度的上市公司的資產負債表。
 *       //讀取由民國92年第3季開始到101第2季的資產負債表
		 GetBalanceSheet.getSeasonlyBalanceSheetBatch(92, 3, 101, 2);  
 */

/**
 * 本程式僅適用101年(含)以前，用來取得上市、上櫃公司季報之資產負債表
 * 
 * 上市、上櫃及興櫃公司自102年開始採用IFRSs編製財務報表，請至 http://mops.twse.com.tw/mops/web/t163sb05
 * (採IFRSs)查詢報表！
 * 
 * @author user
 *
 */
public class GetBalanceSheet {
	int year; // 中華民國年
	String season; // 可能值為 "01", "02", "03", "04"
	String rawFilename;
	String dataFilename;
	String targetDir = Constant.SeasonlyBalanceSheet_DIR;
	// Server端程式的URL
	final String RESOURCE = "http://mops.twse.com.tw/mops/web/ajax_t51sb07";
	/**
	 * 參數REQUEST_PARAMETER存放HTML表單(Form)要傳送到Server的資料。
	 * 要得到最正確的表單(Form)資料格式的做法是經由Chrome瀏覽器的『開發人員工具』
	 * (按Ctrl+Shift+I)，在Network頁面中，點選Name欄位內的某一次請求，Chrome會
	 * 顯示該次『請求』與『回應』，由這些資料中的『Form Data』可以得到表單傳送到Server的資料 例: 102年第一季的Form
	 * Data應該寫成:
	 * "encodeURIComponent=1&step=1&firstin=1&off=1&TYPEK=sii&year=101&season=02"
	 *  
	 * 本程式只需設定 year=???與season=??，其餘的請求參數與本程式無關
	 */
	final String REQUEST_PARAMETER = "";

	public GetBalanceSheet(int year, int season) {
		if (year >= 102) {
			throw new RuntimeException("102年度以後請執行GetBalanceSheet102.java");
		}
		setYearSeason(year, season);
		rawFilename = "SeasonlyBalanceSheet" + year + this.season + ".txt";
		dataFilename = "SeasonlyBalanceSheet" + year + this.season + ".dat";
	}

	public void setYearSeason(int year, int season) {
		setYear(year);
		setSeason(season);
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setSeason(int season) {
		switch (season) {
		case 1:
		case 2:
		case 3:
		case 4:
			this.season = "0" + season;
			break;
		default:
			throw new RuntimeException("參數季節(int season)錯誤: 必須是1, 2, 3, 4, season=" + season);
		}
	}

	public void getSeasonlyBalanceSheet() {
		try {
			if (year == 0 || season == null) {
				throw new RuntimeException("參數季節(int season)錯誤: 必須是1, 2, 3, 4");
			}
			String formData = "encodeURIComponent=1&step=1&firstin=1&off=1&TYPEK=sii&year="
					+ year + "&season=" + season;
			// 建立能與Server進行網路連線的URL物件，參數RESOURCE可以是網路上任何資源的URL
			URL url = new URL(RESOURCE);
			// openConnection(): 開始進行連線，此方法的傳回值為java.net.HttpURLConnection型別的物件,
			// 此物件可對HTTP協定進行細緻的設定，例如設定：
			// (1) Http Method: GET/POST/PUT/DELETE/OPTION/TRACE/....
			// (2) Request Header: 許多Server會經由Client端送出的Request Header
			// 來判斷提出請求的客戶端究竟是真正的瀏覽器或一般的Java程式。如果Java程式
			// 能送出與瀏覽器相同的Request Header，Server將無法判斷送出請求的客戶端為何?
			// 可由Chrome瀏覽器的『開發人員工具』得知瀏覽器送出哪些Request Header。
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 說明本程式要寫出資料給遠方的資源
			connection.setDoOutput(true);
			// 說明本程式要採用POST方法寫出資料
			connection.setRequestMethod("POST");
			// 設定本程式要送出的Request Header
			connection.setRequestProperty("Accept-Charset", Constant.CHARSET);
			connection.setRequestProperty("Referer",
					"http://mops.twse.com.tw/mops/web/t51sb07");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 6.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36");
			// 設定本程式要送出的表單資料有多長，即字串formData的length()
			// 所以，下面的"72"不夠好，要改成String.valueOf(formData.length())
			// connection.setRequestProperty("Content-Length", "72");
			connection.setRequestProperty("Content-Length", String.valueOf(formData.length()));
			// 1. 由connection物件中取得java.io.OutputStream物件
			// 2. 由java.io.OutputStream物件來建構一個OutputStreamWriter物件
			// OutputStreamWriter物件會將寫出之Unicode編碼的字串，轉換為
			// Constant.CHARSET編碼的字串後寫出。
			OutputStreamWriter osw = new OutputStreamWriter(
					connection.getOutputStream(), Constant.CHARSET);
			osw.write(formData);
			osw.close();
			// 由connection物件得到java.io.InputStream物件
			InputStream is = connection.getInputStream();
			// 由java.io.InputStream物件建構BufferedReader物件
			BufferedReader in = new BufferedReader(new InputStreamReader(is,
					Constant.CHARSET));
			String decodedString;
			
			
			// 以下兩行要移到InitProgram.java
			File dir = new File(targetDir);
			if (!dir.exists())
				dir.mkdirs();
			File file = new File(dir, rawFilename);
			// 將遠方Server傳回的資料寫出到硬碟上
			PrintWriter out = new PrintWriter(new FileWriter(file));
			while ((decodedString = in.readLine()) != null) {
				out.println(decodedString);
			}
			in.close();
			out.close();
			System.out.println("資料下載完畢，" + year + "年" + season + "季" + " 文件檔位置=>" + targetDir + "\\" + rawFilename);
			extractData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    private void extractData(){
    	int count = 0 ;
    	File dir = new File(targetDir);
		if (!dir.exists())
			dir.mkdirs();
		File input = new File(dir, rawFilename);
		File output = new File(dir, dataFilename);
		
        try (PrintWriter out = new PrintWriter(output);)
        {
			Document doc = Jsoup.parse(input, Constant.CHARSET,  "http://mops.twse.com.tw/");
			// 取出HTML文件中，有關『市場別』、『年度』、『季度』的資料，作為註解
			Elements h2 = doc.select("h2");
			// 由於h2之下只有一個child, 所以使用first()方法
			Element comment = h2.first();
			// 如果h2之下有多個children, 則必須透過 for()取出小孩 
//			for(Element e : h2){
//				System.out.println("e.text=" + e.text());
//			}
			//System.out.println("*** 資產負債表，年度與季度:" + comment.text());
			if (comment != null){
				out.println("*** 資產負債表，年度與季度:" + comment.text());
			}
			//-----------------------------------------------
			// 取出HTML文件中，表單資料的<th>標籤，作為註解
			Element header = doc.select("tr:contains(公司名稱)").first();
			Elements th = header.select("th");
			StringBuffer sb = new StringBuffer();
			for(Element e : th){
				String s = e.text();
		    	sb.append(s +"|");
			}
			
			sb.deleteCharAt(sb.length()-1);  // 刪除最後一個'|'字元
			//System.out.println("*** 資料欄位名稱:" + sb.toString());
			out.println("*** 資料欄位名稱:" + sb.toString());
			// 以下取出有料的內容
			doc = Jsoup.parse(input, Constant.CHARSET,  "http://mops.twse.com.tw/");
			Elements row = doc.select("tr[class=cColor], tr[class=lColor]");
			count = 0 ; 
	        
			for(Element e : row){
//				System.out.println("e=" + e);
//				System.out.println("---------------------------------");
				StringBuffer sb1 = new StringBuffer();
				count ++ ;
				Elements entry = e.select("td");
				for(Element property : entry){
					sb1.append(property.ownText()+"|");
				}
				sb1.deleteCharAt(sb1.length()-1);
				out.println(sb1.toString());
//				System.out.println(sb1.toString());
//				System.out.println("==================================");
		    }
//			
//			
			System.out.println("資料解析完畢，" + year + "年" + season + "季" + " 資料檔位置=>" + targetDir + "\\" + dataFilename);			
			System.out.println("總筆數:" + row.size() + " / " + count);
			System.out.println();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
    }
	// 批次作業，一次讀取多個年份與季度的BalanceSheet
	public static void getSeasonlyBalanceSheetBatch
	(int fromYear, int fromSeason, int toYear, int toSeason){
		outer:for(int y=fromYear; y <= toYear ; y++){
			for(int s=1; s <= 4 ; s++){
				if (y == fromYear && s < fromSeason){
					continue;
				}
				if (y == toYear && s > toSeason){
					break outer;
				}	
				GetBalanceSheet gbs = new GetBalanceSheet(y, s); 
				gbs.getSeasonlyBalanceSheet();
				int num = (int)(Math.random() * 5000) + 3000;
				try {
					Thread.sleep(num);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}		
	}
}
/*
 * URLConnection類別: 代表Java程式與以URL表示之遠方網路資源的連結(Link), 透過此連結Java程式可以
 * 傳送資料給遠方網路的資源，以及由遠方網路資源取回程式要求的資料
 * 
 * 1. 準備相關資料 1-1 遠方網路資源 String url =
 * "http://localhost:8080/JoStock/ReflectionServlet";
 * 
 * 1-2 要傳送的資料 String charset = "UTF-8"; // Java 7 與以後可改為
 * java.nio.charset.StandardCharsets.UTF_8.name() String param1 =
 * "Hello, World 大家好"; String param2 = "時間是決定成敗的重要因素之一"; String query =
 * String.format("param1=%s&param2=%s", URLEncoder.encode(param1, charset),
 * URLEncoder.encode(param2, charset));
 * 
 * 2. 建構URL物件 URL url = new URL(url); // refer to 1-1 如果使用的是 HTTP Get方法，傳送的資料
 * (1-2的query)必須加在url的尾端， 並以 ? 隔開。 URL url = new URL(url + "?" + query); //
 * refer to 1-1
 * 
 * 3. 與遠方的Server建立連線, 以取得URLConnection物件 HttpURLConnection connection =
 * (HttpURLConnection)url.openConnection();
 * 
 * 4. 設定本程式要寫出資料(即傳送資料給Server) connection.setDoOutput(true); // 預設值為false
 * 設定此方法後，程式方可以執行connection.getOutputStream(); 來得到java.io.OutputStream物件。
 * 
 * 註；與connection.setDoOutput(true);對應的方法為 connection.setDoInput(true); //
 * 預設值為true 此方法說明本程式要由Server端讀入資料， 由於預設值為true， 因此不需要寫此敘述
 * 
 * 5. 設定請求標頭Http Request Header connection.setRequestProperty("Accept-Charset",
 * charset); connection.setRequestProperty("Content-Type",
 * "application/x-www-form-urlencoded;charset=" + charset);
 * connection.setRequestProperty("User-Agent",
 * "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.3) Gecko/20100401"
 * );
 * 
 * 
 * 6. 設定Http Method connection.setRequestMethod("POST"); //此程式將提出HTTP POST方法
 * connection.setRequestMethod("GET"); //此程式將提出HTTP GET方法, GET方法為預設值
 * 注意：當執行setRequestMethod("GET")時，程式不可以執行 connection.getOutputStream();否則HTTP
 * Method會被改為POST 2. 省略此方法或connection.setDoOutput(false)會設定HTTP Method為GET。
 * 當connection.setDoOutput(false)時，程式不可以執行connection.getOutputStream()
 * 
 * 9. 如果需要設定Proxy System.setProperty("http.proxyHost", "proxy.example.com");
 * System.setProperty("http.proxyPort", "8080");
 */