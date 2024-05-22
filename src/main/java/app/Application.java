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

import app.backend.KeyTyper;
import app.backend.OpticalCharacterReader;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.swing.*;

public class Application {
    private KeyTyper typer;
    private OpticalCharacterReader ocr;

    public Application() {
        this.typer = new KeyTyper();
        this.ocr = new OpticalCharacterReader();
    }

    private void countdown() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Program starting in " + i + " seconds.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        File testImage = new File("test.png");
//        assert testImage != null : "test file not found";
    }

}
