package org.refact4j.xml;

import java.util.Iterator;

public abstract class AbstractIteratorXmlElementHandler implements XmlElementHandler, Iterator, Iterable {

    private final String tagName;
    private final Iterator iterator;

    protected AbstractIteratorXmlElementHandler(String tagName, Iterable iterable) {
        this.tagName = tagName;
        this.iterator = iterable.iterator();
    }

    public String getTagName() {
        return this.tagName;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public Object next() {
        return iterator.next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public Iterator iterator() {
        return this.iterator;
    }

}
