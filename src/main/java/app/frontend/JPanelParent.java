package app.frontend;

import javax.swing.*;
import java.awt.*;

public class JPanelParent {
    private JPanel jPanel;

    public JPanelParent() {
        this.jPanel = new JPanel();
    }

    public void addComponents(Component... components) {
        for (Component component : components) {
            this.jPanel.add(component);
        }
    }

    public JPanel getjPanel() {
        return jPanel;
    }
}
