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
                return new XmlElementHandler[]{new DummyElementHandler("foo", new XmlWriterCallback() {
                    public void doWithXmlWriter(XmlWriter xmlWriter) {
                        xmlWriter.writeAttribute("p1", "v1");
                        xmlWriter.writeCharacters("abc123");
                    }
                }), new DummyElementHandler("bar", new XmlWriterCallback() {

                    public void doWithXmlWriter(XmlWriter xmlWriter) {
                        xmlWriter.writeAttribute("param1", "xxx");
                        xmlWriter.writeAttribute("param2", "zzz");
                        xmlWriter.writeCharacters("azerty");
                    }
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
