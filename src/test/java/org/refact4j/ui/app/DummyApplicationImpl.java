package org.refact4j.ui.app;

import javax.swing.*;

public class DummyApplicationImpl extends ApplicationImpl {

    public DummyApplicationImpl() {
        super();
    }

    public void init() {
        super.init();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("menu1");
        menu.add(new JMenuItem("item1"));
        menuBar.add(menu);
        setMainMenuBar(menuBar);

        JToolBar toolBar = new JToolBar();
        toolBar.add(new JButton("button1"));
        setToolBar(toolBar);

    }

}
