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
import app.backend.MouseListener;
import app.backend.OpticalCharacterReader;
import app.frontend.panels.BotConfigPanel;
import app.frontend.panels.LoggerPanel;
import app.frontend.panels.PreviewImagePanel;
import app.frontend.panels.PreviewTextPanel;
import net.miginfocom.swing.MigLayout;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.swing.*;

public class Application {
    private JFrame frame;
    private KeyTyper typer;
    private OpticalCharacterReader ocr;

    public Application() {
        this.typer = new KeyTyper();
        this.ocr = new OpticalCharacterReader();
        initFrame();
        addComponentsToFrame();
    }

    private void initFrame() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("TypeRacer Bot");
        this.frame.setLayout(new MigLayout());
        this.frame.setLocationRelativeTo(null);
    }

    public void addComponentsToFrame() {
        this.frame.add(new BotConfigPanel().getjPanel());
        this.frame.add(new PreviewImagePanel().getjPanel());
        this.frame.add(new LoggerPanel().getjPanel());
        this.frame.add(new PreviewTextPanel().getjPanel());
        this.frame.setVisible(true);
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

    public static void main(String[] args) {
//        File testImage = new File("test.png");
//        assert testImage != null : "test file not found";
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    Application application = new Application();
                });

                SwingUtilities.invokeLater(() -> {
                    Thread mouselistenerThread = new Thread(new MouseListener());
                    mouselistenerThread.start();
                });
            }
        });


    }

}
