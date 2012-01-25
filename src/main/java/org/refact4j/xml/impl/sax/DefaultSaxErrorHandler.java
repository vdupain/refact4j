package org.refact4j.xml.impl.sax;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DefaultSaxErrorHandler implements ErrorHandler {

    private boolean isErrorEnabled = true;
    private boolean isFatalErrorEnabled = true;
    private boolean isWarningEnabled = false;

    public boolean isErrorEnabled() {
        return isErrorEnabled;
    }

    public void setErrorEnabled(boolean isErrorEnabled) {
        this.isErrorEnabled = isErrorEnabled;
    }

    public boolean isFatalErrorEnabled() {
        return isFatalErrorEnabled;
    }

    public void setFatalErrorEnabled(boolean isFatalErrorEnabled) {
        this.isFatalErrorEnabled = isFatalErrorEnabled;
    }

    public boolean isWarningEnabled() {
        return isWarningEnabled;
    }

    public void setWarningEnabled(boolean isWarningEnabled) {
        this.isWarningEnabled = isWarningEnabled;
    }

    public void error(SAXParseException exception) throws SAXException {
        if (isErrorEnabled) {
            throw exception;
        }
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        if (isFatalErrorEnabled) {
            throw exception;
        }
    }

    public void warning(SAXParseException exception) throws SAXException {
        if (isWarningEnabled) {
            throw exception;
        }
    }

}
