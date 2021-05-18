package com.exclamationlabs.connid.base.sympa.authenticate;

import com.exclamationlabs.connid.base.sympa.Helper.FileHelper;
import com.exclamationlabs.connid.base.sympa.Helper.XMLHelper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class Deserialize
{
    public static void scanXML( XMLStreamReader streamReader) throws Exception
    {
        int eventType;
        do
        {
            eventType = streamReader.getEventType();
            switch (eventType)
            {
                case XMLStreamConstants.START_DOCUMENT:
                    System.out.println("Start DOCUMENT");
                    streamReader.next();
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    System.out.println("END DOCUMENT");
                    break;
                case XMLStreamConstants.ATTRIBUTE:
                    for (int i =0; i<streamReader.getAttributeCount(); i++)
                    {
                        System.out.println("Attribute Local Name="+ streamReader.getAttributeLocalName(i));
                        System.out.println("Attribute Value ="+streamReader.getAttributeValue(i));
                    }
                    streamReader.next();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    System.out.println("Start Element Name="+ streamReader.getName());
                    if(streamReader.hasText())
                    {
                        System.out.println("Text=" + streamReader.getText());
                        // System.out.println("ElementText=" + streamReader.getElementText());
                    }
                    streamReader.next();
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    System.out.println("End Element Name="+ streamReader.getName());
                    streamReader.next();
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String data = streamReader.getText();
                    if ( data != null && data.trim().length() > 0 )
                    {
                        System.out.println("Character Data=" + streamReader.getText());
                    }
                    streamReader.next();
                    break;
                default:
                    System.out.println("EventType="+eventType);
                    if (streamReader.hasName())
                    {
                        System.out.println("Element Name="+ streamReader.getName());
                    }
                    if (streamReader.hasText())
                    {
                        System.out.println("Text=" + streamReader.getText());
                    }
                    streamReader.next();
                    break;
            }
        }
        while( eventType != XMLStreamConstants.END_DOCUMENT || streamReader.hasNext());
    }
    public static void main(String[] args) throws Exception
    {
        try
        {
            String message = FileHelper.loadResourceToString("getComplexListsResponse.xml", StandardCharsets.UTF_8);
            System.out.print(message);
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader streamReader = factory.createXMLStreamReader(new StringReader(message));
            XMLHelper.scanXML(streamReader);
            streamReader.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
