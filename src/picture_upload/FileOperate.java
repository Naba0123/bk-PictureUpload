package picture_upload;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

public class FileOperate {

	File file;
	String filename;
	BufferedImage image;
	CreateThumnail cts;

	/**
	 * コンストラクタ：ファイルチェックを行う
	 */
	FileOperate(String name) {
		file = new File(name);
		if (!file.exists()) {
			System.out.println("写真[ " + file.getAbsolutePath() + " ]は存在していません。");
			System.exit(1);
		}
	    makeDir("2014_img/");
	    makeDir("2014_js/");
	}
	
	private void makeDir(String str) {
		File dirs = new File(str);
		if (!dirs.exists()) {  
	        dirs.mkdirs();    //make folders  
	    }  
	}

	/**
	 * 文字入力を行う
	 * 
	 * @param str
	 *            : 説明文
	 * @param i
	 *            : 重複チェックをするか否か
	 * @return String : 入力された文字列
	 */
	public static String inputStr(String str, boolean i) {
		String input = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(str + " : ");
		try {
			while (true) {
				input = br.readLine();
				File check_file = new File(input + ".png");
				if (i == true && check_file.exists()) {
					System.out.print("そのファイル名は既に存在しています。\n" + str);
					continue;
				} else {
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("入力エラー : " + e.getMessage());
		}
		return input;
	}

	/**
	 * ファイルをコピーする
	 * @param name : コピー先のパス
	 * @return true : リネーム成功, false : リネーム失敗
	 */
	@SuppressWarnings("resource")
    public void copyFile() throws IOException {

		FileChannel srcChannel = new FileInputStream(file.getPath()).getChannel();
		FileChannel destChannel = new FileOutputStream("2014_img/" + filename + ".png").getChannel();
		try {
			srcChannel.transferTo(0, srcChannel.size(), destChannel);
		} finally {
			srcChannel.close();
			destChannel.close();
		}
	}

	public void createThum() {
		try {
			CreateThumnail cts = new CreateThumnail();
			BufferedImage image = ImageIO.read(file);
			image = cts.resizeImage(image);
			ImageIO.write(image, "PNG", new File("2014_img/" + filename + "_thum.png"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/* ファイルへの書き込みを行う */
	public void writeFile(String str, String type) {
		try {
			File writeFile = new File("2014_js/" + filename + type);
			writeFile.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(writeFile, true));
			bw.write(str);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void newFile(String name) {
		filename = name;
	}

}
