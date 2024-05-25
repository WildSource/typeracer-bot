package app.frontend;

import app.Application;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class JPanelParent {
    private Application application;
    private JPanel jPanel;

    public JPanelParent(Application application) {
        this.application = application;
        this.jPanel = new JPanel();
        this.jPanel.setLayout(new MigLayout());
        this.getjPanel().setBorder(new LineBorder(Color.BLACK, 1));
        getApplication().getFrame().pack();
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
