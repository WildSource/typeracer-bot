package app;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Application {

	public static void main(String[] args) {
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();

		// Lowercase letters
		for (char ch = 'a'; ch <= 'z'; ch++) {
			charMap.put(ch, KeyEvent.getExtendedKeyCodeForChar(ch));
		}

		// Uppercase letters
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			charMap.put(ch, KeyEvent.getExtendedKeyCodeForChar(ch));
		}

		// Dot and comma
		charMap.put('.', KeyEvent.VK_PERIOD);
		charMap.put(',', KeyEvent.VK_COMMA);
		charMap.put(' ', KeyEvent.VK_SPACE);

		File testImage = new File("test.png");
		assert testImage != null : "test file not found";

		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath("tessdata");
		tesseract.setLanguage("eng");
		tesseract.setPageSegMode(1);
		tesseract.setOcrEngineMode(1);
		String result = null;
		try {
			result = tesseract.doOCR(testImage);

		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);

		Robot robot = null;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		char[] characters = result.toCharArray();

		for (int i = 0; i < 3; i++) {
			System.out.println("Program starting in " + i + " seconds.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < characters.length; i++) {
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(characters[i]);

			if (characters[i] == '?') {
				robot.keyPress(KeyEvent.VK_SHIFT);
				// Press and release /
				robot.keyPress(KeyEvent.VK_SLASH);
				robot.keyRelease(KeyEvent.VK_SLASH);
				// Release Shift
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (characters[i] == '\n') {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else {
				robot.keyPress(charMap.get(characters[i]));
				robot.keyRelease(charMap.get(characters[i]));
			}

			System.out.println("character " + characters[i] + " is written.");

		}
	}

}
