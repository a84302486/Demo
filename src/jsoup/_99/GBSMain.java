package jsoup._99;

public class GBSMain {

	public static void main(String[] args) {
		//讀取民國100年第一季的BalanceSheet
//		GetBalanceSheet gbs = new GetBalanceSheet(100, 1); 
//		gbs.getSeasonlyBalanceSheet();

		//讀取由民國79年第3季開始到101第2季的資產負債表
		GetBalanceSheet.getSeasonlyBalanceSheetBatch(79, 1, 101, 4);  
	}

}
