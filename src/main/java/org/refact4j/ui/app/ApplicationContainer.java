package org.refact4j.ui.app;

import javax.swing.*;
import java.awt.*;

public interface ApplicationContainer {

    void setContentPane(Container contentPane);

    void setJMenuBar(JMenuBar menuBar);

    JRootPane getRootPane();

    JPanel getMainPanel();

    void setMainPanel(JPanel panel);

    JDesktopPane getDesktop();

    void setDesktop(JDesktopPane desktopPane);

    JToolBar getToolBar();

    void setToolBar(JToolBar toolBar);

    JMenuBar getMainMenuBar();

    void setMainMenuBar(JMenuBar menuBar);

    Frame getFrame();

}