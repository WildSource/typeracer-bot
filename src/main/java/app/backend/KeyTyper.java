package app.backend;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    private Rectangle createRectangle(Point point1, Point point2) {
        // Calculate the x, y, width, and height of the rectangle
        int x = Math.min(point1.x, point2.x);
        int y = Math.min(point1.y, point2.y);
        int width = Math.abs(point1.x - point2.x);
        int height = Math.abs(point1.y - point2.y);

        // Create and return the rectangle
        return new Rectangle(x, y, width, height);
    }

    public void screenshot(Point point1, Point point2) {
        BufferedImage image = robot.createScreenCapture(createRectangle(point1, point2));
        try {
            ImageIO.write(image, "png", new File("screenshot.png"));
            System.out.println("Screenshoted");
        } catch (IOException e) {
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

