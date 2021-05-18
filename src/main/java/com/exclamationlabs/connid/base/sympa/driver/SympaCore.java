package com.exclamationlabs.connid.base.sympa.driver;

import com.exclamationlabs.connid.base.sympa.Helper.FileHelper;
import com.exclamationlabs.connid.base.sympa.Helper.XMLHelper;
import com.exclamationlabs.connid.base.sympa.model.SharedSympaList;
import com.exclamationlabs.connid.base.sympa.model.SympaCoreList;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.text.StringSubstitutor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SympaCore
{
    private static String configFile     = "sympa-config.properties";
    private static String appNameKey     = "app.name";
    private static String appPasswordKey = "app.password";
    private static String wsdlKey        = "domain.wsdl";
    private static String domainURL      = "sympa.domain.url";
    private static String listName       = "list.name";
    private static String listSubject    = "list.subject";
    private static String listTemplate   = "list.template";
    private static String listDescription= "list.description";
    private static String listTopics     = "list.topics";
    private static Map<String, String> config = null;
    private static Map<String, String> nameSpace = null;

    public static void initDefaultNameSpaces()
    {
        nameSpace = new HashMap<>();
        nameSpace.put("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        nameSpace.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
    }

    /**
     *  Base Constructor use default Configuration
     */
    public SympaCore()
    {
        init();
        initDefaultNameSpaces();
    }


    /**
     * Initialize from properties file stored as a resource
     * @param aFile resource file containing configuration parameters
     */
    public void initFromResource( String aFile )
    {
        if ( config == null )
        {
            if ( configFile != null && configFile.trim().length() > 0 )
            {
                Properties properties = FileHelper.loadPropertiesFromResource(aFile);
                if ( properties != null)
                {
                    Map map = (Map) properties;
                    config = (Map<String, String>) map;
                }
            }
        }
    }

    /**
     * Initialize from a Map object
     * @param aConfig run time configuration settings
     */
    public void initFromMap ( Map<String, String > aConfig )
    {
        if ( aConfig != null )
        {
            config = new HashMap<String, String>();
            config.putAll(aConfig);
        }
    }

    /**
     * Default initialization
     */
    public void init()
    {
        initFromResource(configFile);
    }

    /**
     * Build a soap request message from a template file
     * @param templateFile file that exists as a resource in the classpath
     * @param parameters Map of name value pairs to be substituted into the template
     * @return The complete message that can be sent to the server
     */
    public static String buildRequestMessage(String templateFile, Map<String, String> parameters)
    {
        String message = null;
        try
        {
            message = FileHelper.loadResourceToString(templateFile, StandardCharsets.UTF_8);
            StringSubstitutor substitutor = new StringSubstitutor(parameters);
            message = substitutor.replace(message);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return message;
    }

    public static String getHttpPostResponse(String message, String url)
    {
        String responseData = null;
        CloseableHttpClient client = HttpClients.createDefault();
        try
        {
            HttpPost post = new HttpPost(url);
            StringEntity requestData = new StringEntity(message);
            post.setEntity(requestData);
            post.setHeader("Content-Type", "text/xml;charset=UTF-8");
            CloseableHttpResponse response = client.execute(post);
            if ( response != null )
            {
                int status = response.getStatusLine().getStatusCode();
                responseData = EntityUtils.toString(response.getEntity());
                response.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            client.close();
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }

        return responseData;
    }

    public SympaCoreList unMarshalInfo(String xml) throws XMLStreamException
    {
        SympaCoreList item = null;
        XMLStreamReader streamReader = null;
        try
        {
            if ( xml != null && xml.trim().length() > 0 )
            {
                XMLInputFactory factory = XMLInputFactory.newFactory();
                streamReader = factory.createXMLStreamReader(new StringReader(xml));
                XmlMapper mapper = new XmlMapper();
                int eventType;
                do
                {
                    eventType = streamReader.getEventType();
                    switch (eventType)
                    {
                        case XMLStreamConstants.END_DOCUMENT:
                            break;
                        case XMLStreamConstants.START_ELEMENT:
                            String elementName = streamReader.getLocalName();
                            if (elementName != null && elementName.trim().equalsIgnoreCase("item"))
                            {
                                item = mapper.readValue(streamReader, SympaCoreList.class);
                            }
                            streamReader.next();
                            break;
                        default:
                            streamReader.next();
                            break;
                    }
                }
                while (eventType != XMLStreamConstants.END_DOCUMENT || streamReader.hasNext());
            }
            else
            {
                System.out.println("WARNING: No Data");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( streamReader != null)
            {
                streamReader.close();
            }
        }

        return item;
    }

    public List<SympaCoreList> unMarshalComplexLists(String xml) throws XMLStreamException
    {
        List<SympaCoreList> list = new ArrayList<SympaCoreList>();
        XMLStreamReader streamReader = null;
        try
        {
            if ( xml != null && xml.trim().length() > 0 )
            {
                XMLInputFactory factory = XMLInputFactory.newFactory();
                streamReader = factory.createXMLStreamReader(new StringReader(xml));
                XmlMapper mapper = new XmlMapper();
                int eventType;
                do
                {
                    eventType = streamReader.getEventType();
                    switch (eventType)
                    {
                        case XMLStreamConstants.END_DOCUMENT:
                            break;
                        case XMLStreamConstants.START_ELEMENT:
                            String elementName = streamReader.getLocalName();
                            if (elementName != null && elementName.trim().equalsIgnoreCase("item"))
                            {
                                SympaCoreList item = mapper.readValue(streamReader, SympaCoreList.class);
                                list.add(item);
                            }
                            streamReader.next();
                            break;
                        default:
                            streamReader.next();
                            break;
                    }
                }
                while (eventType != XMLStreamConstants.END_DOCUMENT || streamReader.hasNext());
            }
            else
            {
                System.out.println("WARNING: No Data");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if ( streamReader != null)
            {
                streamReader.close();
            }
        }

        return list;
    }

    public Boolean create(String listName, String subject, String templateName, String description, String topics)
    {
        Boolean success = new Boolean(false);
        try
        {
            // Setup Parameters
            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.putAll(SympaCore.config);
            parameters.put(SympaCore.listName, listName);
            parameters.put(SympaCore.listSubject, subject);
            parameters.put(SympaCore.listTemplate, templateName);
            parameters.put(SympaCore.listDescription, description);
            parameters.put(SympaCore.listTopics, topics);

            // Build Request Message
            String message = buildRequestMessage("message/createList.xml", parameters);

            // Send Request and receive response
            String responseData = getHttpPostResponse(message, SympaCore.config.get(domainURL));
            // Check for fault code
            if ( responseData != null )
            {
                if ( responseData.contains("faultcode") )
                {
                    System.out.println("Unknown List" + listName);
                }
                else if ( responseData.contains("result") )
                {
                    // Re Wrap the Response Data
                    String result= XMLHelper.unWrap("result", responseData);
                    if ( result.contains("true"))
                    {
                        success = true;
                    }
                    else
                    {
                        success = false;
                    }
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return success;
    }

    public List<SympaCoreList> getAll() throws IOException
    {
        List<SympaCoreList> lists = null;
        try
        {
            // Construct Message
            String message = buildRequestMessage("message/getComplexLists.xml", config);

            // Send Request and receive response
            String responseData = getHttpPostResponse(message, config.get(domainURL));

            // Re Wrap the Response Data
            String unwrapped = XMLHelper.unWrap("item", responseData);
            String xml = XMLHelper.wrap("ListInfo", nameSpace, unwrapped);

            // Unmarshal the XML tree to list of SympaList
            lists = unMarshalComplexLists(xml);
        }
        catch ( Exception exception)
        {
            exception.printStackTrace();
        }
        return lists;
    }

    public SympaCoreList getOne(String listName)
    {
        SympaCoreList item = null;
        try
        {
            // build parameter map for the request
            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.putAll(SympaCore.config);
            parameters.put(SympaCore.listName, listName);

            // Construct Request Message
            String message = buildRequestMessage("message/getInfo.xml", parameters);

            // Post Request and receive response
            String responseData = getHttpPostResponse(message, SympaCore.config.get(domainURL));

            // Check for fault code
            if ( responseData != null && responseData.contains("faultcode") )
            {
                System.out.println("Unknown List" + listName);
            }
            else
            {
                // Re Wrap the Response Data
                String unwrapped = XMLHelper.unWrap("item", responseData);
                String xml = XMLHelper.wrap("ListInfo", nameSpace, unwrapped);
                // Unmarshal the XML to SympaList
                item = unMarshalInfo(xml);
            }
        }
        catch ( Exception exception)
        {
            exception.printStackTrace();
        }
        return item;
    }
}
