package app.backend.components;

import app.Application;
import app.backend.ApplicationParent;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class KeyTyper extends ApplicationParent {
    private Map<Character, Integer> charMap;
    private Robot robot;
    private List<Character> result;
    private int typeSpeed;

    public KeyTyper(Application application) {
        super(application);
        keyMapSetup();
        robotSetup();
        this.result = null;
        this.typeSpeed = 10;
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
        charMap.put('\'', KeyEvent.VK_QUOTE);
        charMap.put('"', KeyEvent.VK_QUOTEDBL);
        charMap.put(';', KeyEvent.VK_SEMICOLON);
        charMap.put('!', KeyEvent.VK_EXCLAMATION_MARK);
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

    public BufferedImage screenshot(Point point1, Point point2) {
        System.out.println("Screenshoted");
        return robot.createScreenCapture(createRectangle(point1, point2));
    }

    private void countdown() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Program starting in " + i + " seconds.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void typeKey() {
        countdown();
        for (Character character : result) {
            sleep();

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
                if (Character.isUpperCase(character)) {
                    robot.keyPress(KeyEvent.VK_SHIFT);

                    robot.keyPress(charMap.get(character));
                    robot.keyRelease(charMap.get(character));

                    // Release the shift key
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                } else {
                    robot.keyPress(charMap.get(character));
                    robot.keyRelease(charMap.get(character));
                }
            }
            System.out.println("character " + character + " is written.");
        }
    }

    private void sleep() {
        try {
            Thread.sleep(this.typeSpeed);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private List<Character> convertStringtoCharArrayFormat(String result) {
        char[] resultsArray = result.toCharArray();

        List<Character> characters = new ArrayList<Character>();

        for (Character character : resultsArray) {
            characters.add(character);
        }

        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i) == '\n') {
                characters.set(i, ' ');
            }
        }
        return characters;
    }

    public void setResult(String result) {
        this.result = convertStringtoCharArrayFormat(result);
    }

    public int getTypeSpeed() {
        return typeSpeed;
    }

    public void setTypeSpeed(int typeSpeed) {
        this.typeSpeed = typeSpeed;
    }
}

