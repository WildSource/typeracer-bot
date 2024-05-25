package app.backend.actionListeners;

import app.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmTypeSpeedAction implements ActionListener {
    private Application application;

    public ConfirmTypeSpeedAction(Application application) {
        this.application = application;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingWorker swingWorker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                int typespeed = Integer.parseInt(application.getBotConfigPanel().getTypeSpeedTextField().getText());
                application.getTyper().setTypeSpeed(typespeed);
                System.out.println("TypeSpeed: " + typespeed);
                return null;
            }
        };
        swingWorker.run();
    }
}
