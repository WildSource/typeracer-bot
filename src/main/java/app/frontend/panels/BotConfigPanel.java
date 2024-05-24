package app.frontend.panels;

import app.Application;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotConfigPanel extends JPanelParent {
    private Application application;
    JLabel typeSpeed;
    JTextField typeSpeedTextField;
    JButton captureButton;
    JButton startButton;

    public BotConfigPanel(Application application) {
        this.application = application;
        getjPanel().setLayout(new MigLayout());
        this.typeSpeed = new JLabel("type speed (ms)");

        this.typeSpeedTextField = new JTextField();
        this.typeSpeedTextField.setText("10");
        this.typeSpeedTextField.setPreferredSize(new Dimension(30, 30));

        this.captureButton = new JButton("capture");
        this.captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starting mouselistener");
                SwingUtilities.invokeLater(application::startMouseListener);
            }
        });

        this.startButton = new JButton("start");
        this.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker swingWorker = new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        application.getTyper().typeKey();
                        return null;
                    }
                };

                swingWorker.run();
            }
        });

        this.getjPanel().add(this.typeSpeed);
        this.getjPanel().add(this.typeSpeedTextField, "wrap");
        this.getjPanel().add(this.startButton);
        this.getjPanel().add(this.captureButton);

        this.getjPanel().setBorder(new LineBorder(Color.BLACK, 1));
    }

    public JTextField getTypeSpeedTextField() {
        return typeSpeedTextField;
    }

    public void setTypeSpeedTextField(JTextField typeSpeedTextField) {
        this.typeSpeedTextField = typeSpeedTextField;
    }
}
