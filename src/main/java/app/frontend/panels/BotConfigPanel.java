package app.frontend.panels;

import net.miginfocom.swing.MigLayout;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;

public class BotConfigPanel extends JPanelParent {
    JLabel typeSpeed;
    JTextField typeSpeedTextField;
    JButton startButton;

    public BotConfigPanel() {
        getjPanel().setLayout(new MigLayout());
        this.typeSpeed = new JLabel("type speed (ms)");

        this.typeSpeedTextField = new JTextField();
        this.typeSpeedTextField.setText("10");
        this.typeSpeedTextField.setPreferredSize(new Dimension(30, 30));

        this.startButton = new JButton("start");

        this.getjPanel().add(this.typeSpeed);
        this.getjPanel().add(this.typeSpeedTextField, "wrap");
        this.getjPanel().add(this.startButton);

        this.getjPanel().setBorder(new LineBorder(Color.BLACK, 1));
    }

    public JTextField getTypeSpeedTextField() {
        return typeSpeedTextField;
    }

    public void setTypeSpeedTextField(JTextField typeSpeedTextField) {
        this.typeSpeedTextField = typeSpeedTextField;
    }
}
