package app.backend;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

public class KeyTyper {
    private Map<Character, Integer> charMap;
    private Robot robot;

    public KeyTyper() {
        keyMapSetup();
        robotSetup();
    }

    private void keyMapSetup() {
        this.charMap = new HashMap<Character, Integer>();
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
    }

    private void robotSetup() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void typeKey(List<Character> characters) {
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

    public List<Character> convertStringtoCharArrayFormat(String result) {
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

        return characters;
    }
}

