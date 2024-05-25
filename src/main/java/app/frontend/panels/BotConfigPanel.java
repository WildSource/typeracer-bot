package app.frontend.panels;

import app.Application;
import app.frontend.JPanelParent;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotConfigPanel extends JPanelParent {
    JLabel typeSpeed;
    JTextField typeSpeedTextField;
    JButton confirmTypeSpeedButton;
    JButton captureButton;
    JButton startButton;

    public BotConfigPanel(Application application) {
        super(application);
        getjPanel().setLayout(new MigLayout());
        this.typeSpeed = new JLabel("type speed (ms)");

        this.typeSpeedTextField = new JTextField();
        this.typeSpeedTextField.setText("10");
        this.typeSpeedTextField.setPreferredSize(new Dimension(30, 30));

        this.confirmTypeSpeedButton = new JButton("confirm");
        this.confirmTypeSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingWorker swingWorker = new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        int typespeed = Integer.parseInt(typeSpeedTextField.getText());
                        getApplication().getTyper().setTypeSpeed(typespeed);
                        System.out.println("TypeSpeed: " + typespeed);
                        return null;
                    }
                };
                swingWorker.run();
            }
        });

        this.captureButton = new JButton("capture");
        this.captureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Starting mouselistener");
                SwingUtilities.invokeLater(getApplication()::startMouseListener);
            }
        });

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

        this.getjPanel().setBorder(new LineBorder(Color.BLACK, 1));
    }

    public JTextField getTypeSpeedTextField() {
        return typeSpeedTextField;
    }

    public void setTypeSpeedTextField(JTextField typeSpeedTextField) {
        this.typeSpeedTextField = typeSpeedTextField;
    }
}
