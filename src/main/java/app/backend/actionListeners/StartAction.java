package app.backend.actionListeners;

import app.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartAction implements ActionListener {
    private Application application;

    public StartAction(Application application) {
        this.application = application;
    }

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
}
