package app.frontend.panels;

import app.Application;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PreviewImagePanel extends JPanelParent {
    Application application;
    JLabel previewLabel;
    JLabel actualPreview;

    public PreviewImagePanel(Application application) {
        this.application = application;
        getjPanel().setLayout(new MigLayout());

        this.previewLabel = new JLabel("Screenshot Preview");
        this.actualPreview = new JLabel();

        getjPanel().add(this.previewLabel);
        getjPanel().add(this.actualPreview);

        this.getjPanel().setBorder(new LineBorder(Color.BLACK, 1));
    }

    public void updatePreviewUI(BufferedImage screenshot) {
        SwingUtilities.invokeLater(() -> {
            ImageIcon image = new ImageIcon(screenshot);
            setIcon(image);
        });
        this.application.getTyper().setResult(this.application.getOcr().readScreenShot(screenshot));
        SwingUtilities.invokeLater(() -> {
            this.application.getFrame().setSize(this.application.getFrame().getWidth() + screenshot.getWidth(), this.application.getFrame().getHeight() + screenshot.getHeight());
            this.application.getFrame().setLocationRelativeTo(null);
        });
    }

    public void setIcon(Icon screenshot) {
        this.actualPreview.setIcon(screenshot);
    }
}
