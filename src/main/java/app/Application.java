package app;

import app.backend.KeyTyper;
import app.backend.MouseListener;
import app.backend.OpticalCharacterReader;
import app.frontend.panels.BotConfigPanel;
import app.frontend.panels.LoggerPanel;
import app.frontend.panels.PreviewImagePanel;
import app.frontend.panels.PreviewTextPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Application {
    private JFrame frame;
    private BotConfigPanel botConfigPanel;
    private PreviewImagePanel previewImagePanel;
    private LoggerPanel loggerPanel;
    private PreviewTextPanel previewTextPanel;
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
        this.frame.pack();
    }

    public void addComponentsToFrame() {
        this.botConfigPanel = new BotConfigPanel();
        this.frame.add(this.botConfigPanel.getjPanel());

        this.previewImagePanel = new PreviewImagePanel(this);
        this.frame.add(this.previewImagePanel.getjPanel());

        this.loggerPanel = new LoggerPanel();
        this.frame.add(this.loggerPanel.getjPanel());

        this.previewTextPanel = new PreviewTextPanel();
        this.frame.add(this.previewTextPanel.getjPanel());
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

    public LoggerPanel getLoggerPanel() {
        return loggerPanel;
    }

    public PreviewTextPanel getPreviewTextPanel() {
        return previewTextPanel;
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

                SwingWorker mouseListener = new SwingWorker() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        Thread mouselistenerThread = new Thread(new MouseListener(application));
                        mouselistenerThread.start();
                        return null;
                    }
                };

                mouseListener.run();
            }
        });
    }
}