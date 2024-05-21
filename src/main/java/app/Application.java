package app;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Application {

	public static void main(String[] args) {
		File testImage = new File("test.png");
		assert testImage != null : "test file not found";

		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("tessdata");
		tesseract.setLanguage("eng");
		tesseract.setPageSegMode(1);
		tesseract.setOcrEngineMode(1);
		try {
			String result = tesseract.doOCR(testImage);
			System.out.println(result);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
