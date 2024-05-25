package app.frontend.panels;

import app.Application;
import app.frontend.JPanelParent;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PreviewImagePanel extends JPanelParent {
    private JLabel previewLabel;
    private JLabel actualPreview;

    public PreviewImagePanel(Application application) {
        super(application);

        this.previewLabel = new JLabel("Screenshot Preview");
        this.actualPreview = new JLabel();

        getjPanel().add(this.previewLabel);
        getjPanel().add(this.actualPreview);
    }

    public void updatePreviewUI(BufferedImage screenshot) {
        SwingUtilities.invokeLater(() -> {
            this.actualPreview.setIcon(new ImageIcon(screenshot));
        });
        notifyTyper(screenshot);
        updateFrameSize(screenshot.getWidth(), screenshot.getHeight());
    }

    private void notifyTyper(BufferedImage screenshot) {
        getApplication().getTyper().setResult(getApplication().getOcr().readScreenShot(screenshot));
    }

    private void updateFrameSize(int width, int height) {
        int frameWidth = getApplication()
                .getFrame()
                .getWidth() + width;

        int frameHeight = getApplication()
                .getFrame()
                .getHeight() + height;

        SwingUtilities.invokeLater(() -> {
            getApplication().getFrame().setSize(frameWidth, frameHeight);
            getApplication().getFrame().setLocationRelativeTo(null);
        });
    }
}
