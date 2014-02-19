package picture_upload;

import java.io.IOException;

public class PictureUpload {

	static String filename;
	static String title;
	static String explain;

	public static void main(String[] args) {

		/* 当分はコマンドライン上からの実行となる */

		// 引数チェック
		if (args.length < 1) {
			System.out.println("引数が足りないんですねぇ…（困惑）");
			System.out.println("$ java -jar ./PictureUpload.jar 写真パス");
			System.exit(1);
		}

		// ファイルの読み込み
		FileOperate file = new FileOperate(args[0]);

		GenerateJS js = new GenerateJS();

		// ファイル情報
		filename = FileOperate.inputStr("写真ファイル名(拡張子除く)", true);
		title = FileOperate.inputStr("写真のタイトル", false);
		explain = FileOperate.inputStr("写真の説明", false);

		// 新しいファイル名を伝える
		file.newFile(filename);

		// ファイルのコピー
		try {
			file.copyFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// サムネイルの作成
		file.createThum();

		js.readyCal();
		js.GenerateStr(filename, title, explain);

		System.out.println(js.GetUsage());

		file.writeFile(js.GetStr(), ".js");
		file.writeFile(js.GetUsage(), ".txt");

	}

}
