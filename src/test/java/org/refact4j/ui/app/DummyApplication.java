package org.refact4j.ui.app;

public class DummyApplication extends AbstractStandaloneApplication {

    public ApplicationImpl createApplicationImpl() {
        return new DummyApplicationImpl();
    }

    public static void main(String[] args) {
        DummyApplication appli = new DummyApplication();
        appli.init();
        appli.start();
    }

}
