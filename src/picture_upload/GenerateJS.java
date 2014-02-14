package picture_upload;

import java.util.Calendar;

public class GenerateJS {

	String str;
	String usage;
	int year;
	int month;
	int day;

	GenerateJS() {
	}

	/* 書き込み用の情報を用意する */
	public void readyCal() {
		Calendar cal1 = Calendar.getInstance(); // (1)オブジェクトの生成
		year = cal1.get(Calendar.YEAR); // (2)現在の年を取得
		month = cal1.get(Calendar.MONTH) + 1; // (3)現在の月を取得
		day = cal1.get(Calendar.DATE); // (4)現在の日を取得
	}

	void GenerateStr(String path, String title, String explain) {
		// strの生成
		str = "document.write('<th id=\"picture_text\"><a href=\"2014_img/"
				+ path + ".png\" data-lightbox=\"2014\" title=\"" + explain
				+ "\" class=\"pso2\">";
		str += "<img src=\"2014_img/" + path + "(1).png\" alt=\"" + title
				+ "\" class=\"thum\">";
		str += "</a>" + year + " / " + String.format("%1$2d", month) + " / "
				+ String.format("%1$2d", day) + "<br>" + title + "</th>')";
		// usageの生成
		usage = "<script src=\"2014_js/" + path + ".js\"></script>";
	}

	String GetStr() {
		return str;
	}

	String GetUsage() {
		return usage;
	}

}
