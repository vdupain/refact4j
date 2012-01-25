package org.refact4j.ui.app;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class AbstractStandaloneApplication extends JFrame implements Application, WindowListener,
        InternalFrameListener {
    private ApplicationImpl implementation;

    public AbstractStandaloneApplication() {
        super();
        setApplicationImpl(this.createApplicationImpl());
    }

    private void setApplicationImpl(ApplicationImpl implementation) {
        this.implementation = implementation;
        this.implementation.setApplication(this);
    }

    public void setMainPanel(JPanel panel) {
        this.implementation.setMainPanel(panel);
    }

    public void setDesktop(JDesktopPane desktop) {
        this.implementation.setDesktop(desktop);
    }

    public void setToolBar(JToolBar toolbar) {
        this.implementation.setToolBar(toolbar);
    }

    public void setMainMenuBar(JMenuBar menubar) {
        this.implementation.setMainMenuBar(menubar);
    }

    public ApplicationImpl getApplicationImpl() {
        return this.implementation;
    }

    public ApplicationContainer getApplicationContainer() {
        return this;
    }

    public JPanel getMainPanel() {
        return this.implementation.getMainPanel();
    }

    public JDesktopPane getDesktop() {
        return this.implementation.getDesktop();
    }

    public JToolBar getToolBar() {
        return this.implementation.getToolBar();
    }

    public JMenuBar getMainMenuBar() {
        return this.implementation.getMainMenuBar();
    }

    public Frame getFrame() {
        return this.implementation.getFrame();
    }

    public boolean isApplet() {
        return false;
    }

    public boolean isStandaloneApplication() {
        return true;
    }

    public boolean isInternalFrameApplication() {
        return false;
    }

    public void init() {
        fullScreen();
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);
        this.implementation.init();
    }

    public void start() {
        this.setVisible(true);
        getRootPane().revalidate();
        getRootPane().repaint(0);
        this.implementation.start();
    }

    public void actionPerformed(ActionEvent event) {
        this.implementation.actionPerformed(event);
    }

    public void fullScreen() {
        Dimension ecran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = new Dimension(ecran.width, ecran.height);
        setLocation((ecran.width / 2) - (size.width / 2), (ecran.height / 2) - (size.height / 2));
        setSize(size);
    }

    public void stop() {
        System.exit(0);
    }

    public void windowActivated(WindowEvent event) {
        this.implementation.windowActivated(event);
    }

    public void windowClosed(WindowEvent event) {
        this.implementation.windowClosed(event);
    }

    public void windowClosing(WindowEvent event) {
        this.implementation.windowClosing(event);
    }

    public void windowDeactivated(WindowEvent event) {
        this.implementation.windowDeactivated(event);
    }

    public void windowDeiconified(WindowEvent event) {
        this.implementation.windowDeiconified(event);
    }

    public void windowIconified(WindowEvent event) {
        this.implementation.windowIconified(event);
    }

    public void windowOpened(WindowEvent event) {
        this.implementation.windowOpened(event);
    }

    public void internalFrameActivated(InternalFrameEvent event) {
        this.implementation.internalFrameActivated(event);
    }

    public void internalFrameClosed(InternalFrameEvent event) {
        this.implementation.internalFrameClosed(event);
    }

    public void internalFrameClosing(InternalFrameEvent event) {
        this.implementation.internalFrameClosing(event);
    }

    public void internalFrameDeactivated(InternalFrameEvent event) {
        this.implementation.internalFrameDeactivated(event);
    }

    public void internalFrameDeiconified(InternalFrameEvent event) {
        this.implementation.internalFrameDeiconified(event);
    }

    public void internalFrameIconified(InternalFrameEvent event) {
        this.implementation.internalFrameIconified(event);
    }

    public void internalFrameOpened(InternalFrameEvent event) {
        this.implementation.internalFrameOpened(event);
    }
}
