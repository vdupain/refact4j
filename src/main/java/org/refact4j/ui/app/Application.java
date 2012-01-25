package org.refact4j.ui.app;

import java.awt.event.ActionListener;

public interface Application extends ApplicationLifecycle, ApplicationContainer, ActionListener {

    void setTitle(String title);

    void fullScreen();

    ApplicationImpl createApplicationImpl();

    ApplicationImpl getApplicationImpl();

    ApplicationContainer getApplicationContainer();

    boolean isApplet();

    boolean isStandaloneApplication();

    boolean isInternalFrameApplication();

}
