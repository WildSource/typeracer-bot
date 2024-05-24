package app.frontend.panels;

import app.Application;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class LoggerPanel extends JPanelParent {
    private Application application;
    private JLabel title;
    private JTextArea logger;

    public LoggerPanel(Application application) {
        this.application = application;
        getjPanel().setLayout(new MigLayout());

        this.title = new JLabel("Logger");
        this.logger = new JTextArea();

        PrintStream printStream = new PrintStream(new TextAreaOutputStream(this.logger));
        System.setOut(printStream);
        System.setErr(printStream);

        getjPanel().add(this.title, "wrap");
        getjPanel().add(this.logger);

        this.getjPanel().setBorder(new LineBorder(Color.BLACK, 1));
    }

    private class TextAreaOutputStream extends OutputStream {
        private JTextArea textArea;
        private StringBuilder sb = new StringBuilder();

        public TextAreaOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            if (b == '\r') {
                return;
            }
            if (b == '\n') {
                final String text = sb.toString() + "\n";
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textArea.append(text);
                        // Scroll to the bottom
                        textArea.setCaretPosition(textArea.getDocument().getLength());
                    }
                });
                sb.setLength(0);
            } else {
                sb.append((char) b);
            }
        }
    }
}
