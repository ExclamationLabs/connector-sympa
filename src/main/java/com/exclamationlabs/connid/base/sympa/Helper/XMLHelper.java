package com.exclamationlabs.connid.base.sympa.Helper;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.util.Iterator;
import java.util.Map;

public class XMLHelper
{
    /**
     * Strips an XML string of its outer layers elements
     * @param elementName The xml element that will mark the beginning of the data we want to keep
     * @return A cropped string which begins and ends with the element name specified
     */
    public static String unWrap(String elementName, String xmlData)
    {
        String out = null;
        try
        {
            if ( elementName != null && xmlData != null )
            {
                int begin = xmlData.indexOf("<"+elementName);
                int end   = xmlData.lastIndexOf("/"+elementName+">");
                if ( begin >= 0 && end > begin)
                {
                    out = xmlData.substring(begin, end+elementName.length()+2);
                }
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return out;
    }

    public static String wrap(String elementName, Map<String, String> nameSpace, String xmlData)
    {
        StringBuilder out = new StringBuilder();
        try
        {
            if ( elementName != null && elementName.trim().length() > 0 )
            {
                out.append("<");
                out.append(elementName);
                if ( nameSpace != null && nameSpace.size() > 0 )
                {
                    Iterator<Map.Entry<String, String>> iterator = nameSpace.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> entry = iterator.next();
                        out.append(" ");
                        out.append(entry.getKey());
                        out.append("=\"");
                        out.append(entry.getValue());
                        out.append("\"");
                    }
                }
                out.append(">");
                out.append(xmlData);
                out.append("</");
                out.append(elementName);
                out.append(">");
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return out.toString();

    }

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
}
