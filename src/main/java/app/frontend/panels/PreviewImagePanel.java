package app.frontend.panels;

import app.Application;
import app.frontend.JPanelParent;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PreviewImagePanel extends JPanelParent {
    JLabel previewLabel;
    JLabel actualPreview;

    public PreviewImagePanel(Application application) {
        super(application);
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
        getApplication().getTyper().setResult(getApplication().getOcr().readScreenShot(screenshot));
        SwingUtilities.invokeLater(() -> {
            getApplication().getFrame().setSize(getApplication().getFrame().getWidth() + screenshot.getWidth(), getApplication().getFrame().getHeight() + screenshot.getHeight());
            getApplication().getFrame().setLocationRelativeTo(null);
        });
    }

    public void setIcon(Icon screenshot) {
        this.actualPreview.setIcon(screenshot);
    }
}
