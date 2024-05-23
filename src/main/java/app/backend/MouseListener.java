package app.backend;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;

import java.awt.*;

public class MouseListener implements NativeMouseInputListener, Runnable {
    private KeyTyper typer;
    private boolean isDragged = false;
    private Point location1;
    private Point location2;

    public MouseListener(KeyTyper typer) {
        this.typer = typer;
        this.location1 = new Point();
        this.location2 = new Point();
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        isDragged = true;
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        this.location1.setLocation(nativeMouseEvent.getX(), nativeMouseEvent.getY());
        System.out.println("Location 1\n" + "X: " + nativeMouseEvent.getX() + "\nY: " + nativeMouseEvent.getY());
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        if (isDragged) {
            this.location2.setLocation(nativeMouseEvent.getX(), nativeMouseEvent.getY());
            System.out.println("Location 2\n" + "X: " + nativeMouseEvent.getX() + "\nY: " + nativeMouseEvent.getY());
            typer.screenshot(location1, location2);
        }
        isDragged = false;
    }

    public Point getLocation1() {
        return location1;
    }

    public Point getLocation2() {
        return location2;
    }

    @Override
    public void run() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);
    }
}
