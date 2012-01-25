package org.refact4j.ui.app;

import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;

public class ApplicationImpl implements Application, WindowListener, InternalFrameListener {
    private Application appli;
    private Container conentPane;
    private JPanel mainPanel;
    private JDesktopPane desktop;
    private JMenuBar mainMenuBar;
    private JToolBar mainToolBar;
    private JPanel leftPanel;
    private JPanel rightPanel;

    public ApplicationImpl() {
        super();
    }

    public void setContentPane(Container contentPane) {
        this.appli.setContentPane(contentPane);
    }

    public void setJMenuBar(JMenuBar menuBar) {
        this.appli.setJMenuBar(menuBar);
    }

    public void setApplication(Application appli) {
        this.appli = appli;
    }

    public void setMainMenuBar(JMenuBar menuBar) {
        this.mainMenuBar = menuBar;
        this.appli.setJMenuBar(this.mainMenuBar);
    }

    public void setDesktop(JDesktopPane desktopPane) {
        this.desktop = desktopPane;
    }

    public void setToolBar(JToolBar toolBar) {
        this.mainToolBar = toolBar;
        this.conentPane.add(this.mainToolBar, BorderLayout.NORTH);
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.conentPane.add(this.mainPanel, BorderLayout.CENTER);
    }

    void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
        this.conentPane.add(this.leftPanel, BorderLayout.WEST);
    }

    void setRightPanel(JPanel rigthPanel) {
        this.rightPanel = rigthPanel;
        this.conentPane.add(this.rightPanel, BorderLayout.EAST);
    }

    public void setTitle(String title) {
        this.appli.setTitle(title);
    }

    public ApplicationImpl getApplicationImpl() {
        return this;
    }

    public Application getApplicationContainer() {
        return this.appli;
    }

    public JMenuBar getMainMenuBar() {
        return this.mainMenuBar;
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public JToolBar getToolBar() {
        return this.mainToolBar;
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    JPanel getLeftPanel() {
        return this.leftPanel;
    }

    JPanel getRightPanel() {
        return this.rightPanel;
    }

    public JRootPane getRootPane() {
        return this.appli.getRootPane();
    }

    public ApplicationImpl createApplicationImpl() {
        return this.appli.createApplicationImpl();
    }

    public Frame getFrame() {
        if (isStandaloneApplication()) {
            return (Frame) getApplicationContainer();
        } else if (isApplet() || isInternalFrameApplication()) {
            Frame f = null;
            Component c = (Component) getApplicationContainer();

            do {
                c = c.getParent();

                if (c instanceof Frame) {
                    f = (Frame) c;

                    break;
                }
            } while (c != null);

            return f;
        }

        return null;
    }

    public boolean isApplet() {
        return (getApplicationContainer() instanceof JApplet);
    }

    public boolean isStandaloneApplication() {
        return (getApplicationContainer() instanceof JFrame);
    }

    public boolean isInternalFrameApplication() {
        return (getApplicationContainer() instanceof JInternalFrame);
    }

    public void init() {
        initLayout();
    }

    private void initLayout() {
        this.conentPane = new JPanel();
        this.conentPane.setLayout(new BorderLayout());
        setContentPane(this.conentPane);
        setMainPanel(new JPanel());
        getMainPanel().setLayout(new BorderLayout());

        setDesktop(new JDesktopPane());
        getMainPanel().add(getDesktop(), BorderLayout.CENTER);

        setLeftPanel(new JPanel());
        getLeftPanel().setLayout(new BorderLayout());

        setRightPanel(new JPanel());
        getRightPanel().setLayout(new BorderLayout());
    }

    public void start() {
    }

    public void windowActivated(WindowEvent evt) {
    }

    public void windowClosed(WindowEvent evt) {
    }

    public void windowClosing(WindowEvent evt) {
        this.stop();
    }

    public void windowDeactivated(WindowEvent evt) {
    }

    public void windowDeiconified(WindowEvent evt) {
    }

    public void windowIconified(WindowEvent evt) {
    }

    public void windowOpened(WindowEvent evt) {
    }

    public void internalFrameActivated(InternalFrameEvent evt) {
    }

    public void internalFrameClosed(InternalFrameEvent evt) {
    }

    public void internalFrameClosing(InternalFrameEvent evt) {
    }

    public void internalFrameDeactivated(InternalFrameEvent evt) {
    }

    public void internalFrameDeiconified(InternalFrameEvent evt) {
        try {
            ((JInternalFrame) evt.getSource()).setSelected(true);
        } catch (PropertyVetoException ex) {
        }
    }

    public void internalFrameIconified(InternalFrameEvent evt) {
    }

    public void internalFrameOpened(InternalFrameEvent evt) {
    }

    public void fullScreen() {
        getApplicationContainer().fullScreen();
    }

    public void stop() {
        getApplicationContainer().stop();
    }

    public void addInternalFrame(final JInternalFrame iframe) {
        final InternalFrameListener l = this;

        Runnable runnable = new Runnable() {
            public void run() {
                iframe.removeInternalFrameListener(l);
                iframe.addInternalFrameListener(l);
                getDesktop().add(iframe);
            }
        };

        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(runnable);
        } else {
            runnable.run();
        }
    }

    public void removeInternalFrame(final JInternalFrame iframe) {
        final InternalFrameListener l = this;

        Runnable runnable = new Runnable() {
            public void run() {
                getDesktop().remove(iframe);
                iframe.removeInternalFrameListener(l);
            }
        };

        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(runnable);
        } else {
            runnable.run();
        }
    }

    public void actionPerformed(ActionEvent evt) {
        String action = evt.getActionCommand();
        if (action == null) {
            action = "";
        }
    }

}
