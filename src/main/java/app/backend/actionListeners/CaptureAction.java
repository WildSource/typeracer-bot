package app.backend.actionListeners;

import app.Application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaptureAction implements ActionListener {
    private Application application;

    public CaptureAction(Application application) {
        this.application = application;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Starting mouselistener");
        SwingUtilities.invokeLater(application::startMouseListener);
    }
}
