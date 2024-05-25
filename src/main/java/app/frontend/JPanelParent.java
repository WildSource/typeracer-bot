package app.frontend;

import app.Application;

import javax.swing.*;
import java.awt.*;

public class JPanelParent {
    private Application application;
    private JPanel jPanel;

    public JPanelParent(Application application) {
        this.application = application;
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

    public Application getApplication() {
        return application;
    }
}
