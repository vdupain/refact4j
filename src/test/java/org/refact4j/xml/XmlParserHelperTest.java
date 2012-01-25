package org.refact4j.xml;

import java.io.StringReader;

public class XmlParserHelperTest extends AbstractXmlReaderTest {


    @Override
    protected void readXml() throws Exception {
        XmlParserHelper.parse(new StringReader(xmlWithNameSpace), xmlRootElement);
    }

}
