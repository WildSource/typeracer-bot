package app.frontend.panels;

import app.Application;
import app.backend.KeyTyper;
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
    }

    public void setIcon(Icon screenshot) {
        this.actualPreview.setIcon(screenshot);
        this.application.getFrame().pack();
    }
}
