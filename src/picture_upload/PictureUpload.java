package picture_upload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PictureUpload {

	static String title;
	static String explain;

	public static void main(String[] args) {

		// 当分はコマンドライン上からの実行となる

		// 引数チェック
		if (args.length < 1) {
			System.out.println("引数が足りないんですねぇ…（困惑）");
			System.out.println("$ java -jar ./PictureUpload.jar 写真パス");
			System.exit(1);
		}

		FileOperate file = new FileOperate(args[0]);
		GenerateJS js = new GenerateJS();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("写真のタイトル : ");
		try {
			title = br.readLine();
		} catch (IOException e) {
			System.out.println("入力エラー : " + e.getMessage());
		}
		System.out.print("写真の説明 : ");
		try {
			explain = br.readLine();
		} catch (IOException e) {
			System.out.println("入力エラー : " + e.getMessage());
		}

		js.readyCal();
		js.GenerateStr(args[0], title, explain);

		System.out.println(js.GetStr());

		file.writeFile(js.GetStr(), js.GetUsage());

	}

}
