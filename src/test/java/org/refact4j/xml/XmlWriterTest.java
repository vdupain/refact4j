package org.refact4j.xml;

import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;


public class XmlWriterTest {


    @Test
    public void testWriteXml() throws Exception {
        StringWriter output = new StringWriter();
        XmlWriterHelper.build(output, new XmlElementHandler() {

            public String getTagName() {
                return "root";
            }

            public boolean hasNext() {
                return true;
            }

            public XmlElementHandler[] handleNext(XmlWriter xmlWriter) {
                return new XmlElementHandler[]{new DummyElementHandler("foo", xmlWriter1 -> {
                    xmlWriter1.writeAttribute("p1", "v1");
                    xmlWriter1.writeCharacters("abc123");
                }), new DummyElementHandler("bar", xmlWriter1 -> {
                    xmlWriter1.writeAttribute("param1", "xxx");
                    xmlWriter1.writeAttribute("param2", "zzz");
                    xmlWriter1.writeCharacters("azerty");
                })};
            }

        });
        Assert.assertEquals("<root><foo p1=\"v1\">abc123</foo><bar param1=\"xxx\" param2=\"zzz\">azerty</bar></root>",
                output.toString());
    }

    class DummyElementHandler implements XmlElementHandler {
        private final String tagName;
        private final XmlWriterCallback xmlWriterCallback;
        private boolean wasProcessed = false;


        public DummyElementHandler(String tagName, XmlWriterCallback xmlWriterCallback) {
            this.tagName = tagName;
            this.xmlWriterCallback = xmlWriterCallback;
        }

        public String getTagName() {
            return this.tagName;
        }

        public boolean hasNext() {
            return !wasProcessed;

        }

        public XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception {
            xmlWriterCallback.doWithXmlWriter(xmlWriter);
            wasProcessed = true;
            return new XmlElementHandler[0];
        }
    }
}
