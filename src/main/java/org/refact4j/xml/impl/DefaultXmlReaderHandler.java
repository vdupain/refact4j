package org.refact4j.xml.impl;

import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.XmlReaderHandler;

import java.util.Stack;

public class DefaultXmlReaderHandler implements XmlReaderHandler {
    // A stack for the xml elements
    private final Stack<XmlElement> stack = new Stack<>();
    // Buffer for collecting data from the "characters" SAX event.
    private final StringBuilder stringBuilder = new StringBuilder();

    public void startElement(String namespaceURI, String localName,
                             String qName, XmlAttributes attributes) {
        // Resetting contents buffer.
        // Assuming that tags either tag content or children, not both.
        // This is usually the case with XML that is representing
        // data structures in a programming language independent way.
        // This assumption is not typically valid where XML is being
        // used in the classical text mark up style where tagging
        // is used to style content and several styles may overlap
        // at once.
        stringBuilder.setLength(0);
        XmlElement currentXmlElement = stack.peek();
        // delegate the event handling to the xml element
        XmlElement nextXmlElement = currentXmlElement.createChildXmlElement(localName, qName, attributes);
        stack.push(nextXmlElement);
        nextXmlElement.onStartElement();
    }

    public void endElement(String namespaceURI, String localName, String qName) {
        // delegate the event handling to the xml element
        XmlElement xmlElement = stack.pop();
        xmlElement.setStringValue(stringBuilder.toString());
        xmlElement.onEndElement();
        stringBuilder.setLength(0);
    }

    public void characters(char[] ch, int start, int length) {
        // accumulate the contents into a buffer.
        stringBuilder.append(ch, start, length);
    }


    public void setXmlRootElement(XmlElement xmlRootElement) {
        // This xml root element anchors the xml
        // element to the beginning of the XML document.
        // (before the first tag name is located).
        // By placing it first on the stack
        // all future xml element will follow
        // the element anchored by this
        // xml root element.
        stack.clear();
        stack.push(xmlRootElement);
    }

}
