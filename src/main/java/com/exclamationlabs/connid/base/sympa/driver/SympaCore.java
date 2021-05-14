package com.exclamationlabs.connid.base.sympa.driver;

import com.exclamationlabs.connid.base.sympa.Helper.FileHelper;
import com.exclamationlabs.connid.base.sympa.generated.ListType;
import com.exclamationlabs.connid.base.sympa.model.SympaList;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.text.StringSubstitutor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SympaCore
{
    private static String configFile = "sympa-config.properties";
    private static String appNameKey = "app.name";
    private static String appPasswordKey = "app.password";
    private static String wsdlKey = "domain.wsdl";
    private static String domainURL = "sympa.domain.url";
    private static Map<String, String> config = null;
    /**
     *  Base Constructor use default Configuration
     */
    public SympaCore()
    {
        init();
    }

    /**
     * Initialize from properties file stored as a resource
     * @param aFile
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
     * Strips an XML string of its outer layers elements
     * @param rootElement The xml element that will mark the beginning of the data we want to scan
     * @return A cropped string which begins and ends with the root element
     */
    public static String cropTo(String rootElement, String data)
    {
        String out = null;
        try
        {
            if ( rootElement != null && data != null )
            {
                int begin = data.indexOf("<"+rootElement);
                int end   = data.lastIndexOf("/"+rootElement+">");
                if ( begin >= 0 && end > begin)
                {
                    out = data.substring(begin, end+rootElement.length()+2);
                }
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return out;
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

    public List<ListType> getAll() throws IOException
    {
        CloseableHttpClient client = HttpClients.createDefault();
        try
        {
            String message = FileHelper.loadResourceToString("message/getComplexLists.xml", StandardCharsets.UTF_8);
            StringSubstitutor substitutor = new StringSubstitutor((config));
            message = substitutor.replace(message);
            HttpPost post = new HttpPost(config.get(domainURL));
            StringEntity requestData = new StringEntity(message);
            post.setEntity(requestData);
            post.setHeader("Content-Type", "text/xml;charset=UTF-8");
            CloseableHttpResponse response = client.execute(post);
            String responseData = EntityUtils.toString(response.getEntity());
            String xml = cropTo("item", responseData);
            System.out.println(xml);
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader streamReader = factory.createXMLStreamReader(new StringReader(xml));
            XmlMapper mapper = new XmlMapper();
            streamReader.next();
            SympaList item = mapper.readValue(streamReader, SympaList.class);
        }
        catch ( IOException ex )
        {
            ex.printStackTrace();
        }
        catch ( Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            client.close();
        }

        return null;
    }

    public String getOne(String listName)
    {
        return null;
    }
}
