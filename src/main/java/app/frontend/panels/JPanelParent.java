package app.frontend.panels;

import javax.swing.*;

public class JPanelParent {
    private JPanel jPanel;

    public JPanelParent() {
        this.jPanel = new JPanel();
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setjPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }
}
