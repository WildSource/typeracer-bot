package app;

import app.backend.KeyTyper;
import app.backend.MouseListener;
import app.backend.OpticalCharacterReader;
import app.frontend.panels.BotConfigPanel;
import app.frontend.panels.PreviewImagePanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Application {
    private JFrame frame;
    private BotConfigPanel botConfigPanel;
    private PreviewImagePanel previewImagePanel;
    private KeyTyper typer;
    private OpticalCharacterReader ocr;

    public Application() {
        initFrame();
        addComponentsToFrame();
        this.typer = new KeyTyper(this);
        this.ocr = new OpticalCharacterReader(this);
    }

    private void initFrame() {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setTitle("TypeRacer Bot");
        this.frame.setPreferredSize(new Dimension(720, 480));
        this.frame.setLayout(new MigLayout());
        this.frame.setLocationRelativeTo(null);
        this.frame.setAlwaysOnTop(true);
        this.frame.pack();
    }

    public void addComponentsToFrame() {
        this.botConfigPanel = new BotConfigPanel(this);
        this.frame.add(this.botConfigPanel.getjPanel(), "wrap");

        this.previewImagePanel = new PreviewImagePanel(this);
        this.frame.add(this.previewImagePanel.getjPanel(), "wrap");

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

    public void startMouseListener() {
        Application app = this;
        SwingWorker mouseListener = new SwingWorker() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread mouselistenerThread = new Thread(new MouseListener(app));
                mouselistenerThread.start();
                return null;
            }
        };

        mouseListener.run();
    }

    public KeyTyper getTyper() {
        return typer;
    }

    public JFrame getFrame() {
        return frame;
    }

    public BotConfigPanel getBotConfigPanel() {
        return botConfigPanel;
    }

    public PreviewImagePanel getPreviewImagePanel() {
        return previewImagePanel;
    }

    public OpticalCharacterReader getOcr() {
        return ocr;
    }

    public static void main(String[] args) {
//        File testImage = new File("test.png");
//        assert testImage != null : "test file not found";
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application application = new Application();
            }
        });
    }
}