package picture_upload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperate {

	File file;

	/**
	 * コンストラクタ：ファイルチェックを行う
	 */
	FileOperate(String name) {
		file = new File(name + ".js");
		if (file.exists()) {
			System.out.println("ファイル[ " + file.getAbsolutePath()
					+ " ]は既に存在しています。");
			System.exit(1);
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* ファイルへの書き込みを行う */
	public void writeFile(String str) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(str);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
