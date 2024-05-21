package app;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

		char[] resultsArray = result.toCharArray();

		List<Character> characters = new ArrayList<Character>();

		for (Character character : resultsArray) {
			characters.add(character);
		}

		Iterator<Character> iterator = characters.iterator();

		while (iterator.hasNext()) {
			char character = iterator.next();
			if (character == '\n') {
				iterator.remove();
			}
		}

		for (int i = 0; i < 3; i++) {
			System.out.println("Program starting in " + i + " seconds.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (Character character : characters) {
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(character);

			if (character == '?') {
				robot.keyPress(KeyEvent.VK_SHIFT);
				// Press and release /
				robot.keyPress(KeyEvent.VK_SLASH);
				robot.keyRelease(KeyEvent.VK_SLASH);
				// Release Shift
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (character == '\n') {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			} else {
				robot.keyPress(charMap.get(character));
				robot.keyRelease(charMap.get(character));
			}

			System.out.println("character " + character + " is written.");

		}
	}

}
