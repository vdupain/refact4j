package org.refact4j.xml.impl;

import org.refact4j.xml.XmlElementHandler;
import org.refact4j.xml.XmlWriter;

public class DefaultXmlElementHandler implements XmlElementHandler {

    private final String tagName;
    private final XmlElementHandler[] children;
    private boolean wasProcessed = false;


    public DefaultXmlElementHandler(String tagName, XmlElementHandler xmlElementHandler) {
        this(tagName, new XmlElementHandler[]{xmlElementHandler});
    }

    public DefaultXmlElementHandler(String tagName, XmlElementHandler[] xmlElementHandlers) {
        this.tagName = tagName;
        this.children = xmlElementHandlers;
    }

    public String getTagName() {
        return this.tagName;
    }

    public boolean hasNext() {
        if (wasProcessed) {
            return false;
        }

        for (XmlElementHandler child : children) {
            if (child.hasNext()) {
                return true;
            }

        }

        return true;
    }

    public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
        wasProcessed = true;
        return children;
    }
}
