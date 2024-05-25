package app.frontend.panels;

import app.Application;
import app.backend.actionListeners.CaptureAction;
import app.backend.actionListeners.ConfirmTypeSpeedAction;
import app.frontend.JPanelParent;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotConfigPanel extends JPanelParent {
    private JLabel typeSpeed;
    private JTextField typeSpeedTextField;
    private JButton confirmTypeSpeedButton;
    private JButton captureButton;
    private JButton startButton;

    public BotConfigPanel(Application application) {
        super(application);

        this.typeSpeed = new JLabel("type speed (ms)");

        this.typeSpeedTextField = new JTextField();
        this.typeSpeedTextField.setText("10");
        this.typeSpeedTextField.setPreferredSize(new Dimension(30, 30));

        this.confirmTypeSpeedButton = new JButton("confirm");
        this.confirmTypeSpeedButton.addActionListener(new ConfirmTypeSpeedAction(getApplication()));

        this.captureButton = new JButton("capture");
        this.captureButton.addActionListener(new CaptureAction(getApplication()));

        this.startButton = new JButton("start");
        this.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker swingWorker = new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        getApplication().getTyper().typeKey();
                        return null;
                    }
                };

                swingWorker.run();
            }
        });

        this.getjPanel().add(this.typeSpeed);
        this.getjPanel().add(this.typeSpeedTextField);
        this.getjPanel().add(this.confirmTypeSpeedButton, "wrap");
        this.getjPanel().add(this.startButton);
        this.getjPanel().add(this.captureButton);
    }

    public JTextField getTypeSpeedTextField() {
        return typeSpeedTextField;
    }

    public void setTypeSpeedTextField(JTextField typeSpeedTextField) {
        this.typeSpeedTextField = typeSpeedTextField;
    }
}
