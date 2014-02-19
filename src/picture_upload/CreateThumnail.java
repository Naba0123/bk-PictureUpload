package picture_upload;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class CreateThumnail {
	private int standardSize; // 画像の長辺がこのピクセルになるように縮小する
	private int width;
	private int height;

	public CreateThumnail() {
		standardSize = 160;
	}

	// 画像を縮小
	public BufferedImage resizeImage(BufferedImage image) {
		width = image.getWidth();
		height = image.getHeight();
		BufferedImage resizedImage = null;
		// 縮小する必要があるかを判定
		if (standardSize < width || standardSize < height) {
			calcSize(); // 長辺を基準にして縮小後のサイズを求める
			resizedImage = new BufferedImage(width, height, image.getType());
			// シャギー軽減のためのヒントあり 綺麗だが処理は遅い
			resizedImage.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING), 0, 0, width, height, null);
		} else {
			resizedImage = image;
		}
		return resizedImage;
	}

	// 縮小後のサイズを計算
	private void calcSize() {
		double magnification = 0;
		// 長辺を基準として縦横の縮小倍率を求める
		if (width <= height) {
			magnification = ((double) standardSize / height);
		} else {
			magnification = ((double) standardSize / width);
		}
		height = (int) (height * magnification);
		width = (int) (width * magnification);
	}

}