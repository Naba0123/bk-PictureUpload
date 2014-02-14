package picture_upload;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperate {

	File file;
	File file2;

	/**
	 * コンストラクタ：ファイルチェックを行う
	 */
	FileOperate(String name) {
		file = new File(name + ".js");
		file2 = new File(name + ".txt");
		if (file.exists()) {
			System.out.println("ファイル[ " + file.getAbsolutePath()
					+ " ]は既に存在しています。");
			System.exit(1);
		} else if (file2.exists()) {
			System.out.println("ファイル[ " + file2.getAbsolutePath()
					+ " ]は既に存在しています。");
			System.exit(1);
		}
		try {
			file.createNewFile();
			file2.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* ファイルへの書き込みを行う */
	public void writeFile(String str, String usage) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(str);
			bw.newLine();
			bw.close();
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(file2, true));
			bw2.write(usage);
			bw2.newLine();
			bw2.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
