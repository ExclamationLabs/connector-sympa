/*
    Copyright 2021 Exclamation Labs
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
        http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.exclamationlabs.connid.base.sympa.driver;

import com.exclamationlabs.connid.base.sympa.driver.helper.FileHelper;
import com.exclamationlabs.connid.base.sympa.driver.helper.XMLHelper;
import com.exclamationlabs.connid.base.sympa.model.SympaCoreList;
import com.exclamationlabs.connid.base.sympa.model.SympaFault;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.AlreadyExistsException;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
import org.identityconnectors.framework.common.exceptions.ConnectorSecurityException;
import org.identityconnectors.framework.common.exceptions.UnknownUidException;

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
    private static final Log LOG = Log.getLog(SympaCore.class);
    private static final String configFile = "sympa-config.properties";
    private static final String appNameKey = "app.name";
    private static final String appPasswordKey = "app.password";
    private static final String listMaster = "list.master";
    private static final String wsdlKey = "sympa.domain.wsdl";
    private static final String domainURL = "sympa.domain.url";
    private static final String listName = "list.name";
    private static final String listSubject = "list.subject";
    private static final String listTemplate = "list.template";
    private static final String listDescription = "list.description";
    private static final String listTopics = "list.topics";
    private static final String sympaFault = "Fault";
    private static final String sympaResult = "Result";
    private static final String sympaItem = "item";
    private static final String sympaList = "list";
    private Map<String, String> config = null;
    private Map<String, String> nameSpace = null;
    private boolean canExecute = false;

    /**
     * Base Constructor using default Configuration
     */
    public SympaCore()
    {
        try
        {
            initDefaultNameSpaces();
            init();
        }
        catch(Exception exception)
        {
            LOG.warn(exception, "Failed to initialize SympaCore", new Object[0]);
        }
    }

    /**
     * Constructor with configuration parameters supplied
     * @param configParameters
     */
    public SympaCore(Map<String, String> configParameters)
    {
        initDefaultNameSpaces();
        initFromMap(configParameters);
    }

    /**
     * Constructor with configuration file name supplies.
     * Supply the fully qualified file name of this properties file
     * @param configFileName
     */
    public SympaCore(String configFileName)
    {
        try
        {
            initDefaultNameSpaces();
            initFromFile(configFileName);
        }
        catch (Exception exception)
        {
            LOG.warn(exception, "Failed to initialize SympaCore", new Object[0]);
        }
    }

    /**
     * Add some default namespace to aid with unmarshalling Sympa Responses
     */
    public void initDefaultNameSpaces()
    {
        nameSpace = new HashMap<>();
        nameSpace.put("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
        nameSpace.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
    }

    /**
     * Validates the Global Configuration required to interface with a Sympa SOAP Service. The operations will not be
     * performed unless all required values are supplied to a SympaCore instance
     * @param testConfig Map object containing configuration parameters
     * @return true when all required configuration parameters are supplied and has non blank values
     */
    public static boolean validateGlobalConfiguration( Map<String, String> testConfig )
    {
        boolean success = true;
        String value = null;
        if ( testConfig != null)
        {
            value = testConfig.get(SympaCore.appNameKey);
            if ( value == null || value.trim().length() == 0)
            {
                LOG.warn("Syma Configation does not contain value for {0}. This is the trusted application name.", SympaCore.appNameKey);
                success = false;
            }

            value = testConfig.get(SympaCore.appPasswordKey);
            if ( value == null || value.trim().length() == 0)
            {
                LOG.warn("Syma Configation does not contain value for {0}. This is the trusted application password", SympaCore.appPasswordKey);
                success = false;
            }

            value = testConfig.get(SympaCore.listMaster);
            if ( value == null || value.trim().length() == 0)
            {
                LOG.warn("Syma Configation does not contain value for {0}. This is the list master email address", SympaCore.listMaster);
                success = false;
            }

            value = testConfig.get(SympaCore.wsdlKey);
            if ( value == null || value.trim().length() == 0)
            {
                LOG.warn("Syma Configation does not contain for {0}. The URL of the Server's WSDL", SympaCore.wsdlKey);
                success = false;
            }

            value = testConfig.get(SympaCore.domainURL);
            if ( value == null || value.trim().length() == 0)
            {
                LOG.warn("Syma Configation does not contain value for {0}. The URL of the SOAP server is unknown.", SympaCore.domainURL);
                success = false;
            }
        }
        return success;
    }
    /**
     * Build a soap request message from a template file
     *
     * @param templateFile file that exists as a resource in the classpath
     * @param parameters   Map of name value pairs to be substituted into the template
     * @return The complete message that can be sent to the server
     */
    public static String buildRequestMessage(String templateFile, Map<String, String> parameters) throws IOException
    {
        String message = null;

        message = FileHelper.loadResourceToString(templateFile, StandardCharsets.UTF_8);
        StringSubstitutor substitutor = new StringSubstitutor(parameters);
        message = substitutor.replace(message);

        return message;
    }

    /**
     * Use HTTP to POST a message to the specified URL and read the response body
     * @param message The data to be posted.
     * @param url The HTTP URL of the server that will receive the POST request
     * @return The body of the response
     */
    public static String getHttpPostResponse(String message, String url) throws IOException
    {
        String responseData = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try
        {
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            StringEntity requestData = new StringEntity(message);
            post.setEntity(requestData);
            post.setHeader("Content-Type", "text/xml;charset=UTF-8");
            response = client.execute(post);
            if (response != null)
            {
                int status = response.getStatusLine().getStatusCode();
                responseData = EntityUtils.toString(response.getEntity());
            }
        }
        finally
        {
            if ( response != null )
            {
                response.close();
            }
            if ( client != null )
            {
                client.close();
            }
        }

        return responseData;
    }

    /**
     * Closes a Sympa List.
     * @param listName the listname or the listaddress
     * @return true when the operation was successful and false otherwise
     * @throws ConnectorException
     */
    public Boolean close (String listName) throws ConnectorException
    {
        Boolean success = new Boolean(false);
        Map<String, Object> response = null;

        if ( canExecute )
        {
            // Request the closeList action and unmarshal the response
            try
            {
                // Setup Parameters
                HashMap<String, String> parameters = new HashMap<String, String>();
                parameters.putAll(config);
                parameters.put(SympaCore.listName, listName);

                // Build Request Message
                String message = buildRequestMessage("message/closeList.xml", parameters);

                // Send Request and receive response
                String responseData = getHttpPostResponse(message, config.get(domainURL));
                // Check for fault code
                response = unMarshalCloseResponse(responseData);
            }
            catch (Exception exception)
            {
                LOG.warn(exception, "Failed to close Sympa list {0}", listName);
                throw new ConnectorException(exception);
            }
            // Analyse the response and log the results
            if ( response != null )
            {
                processFault("close", listName, response);
                Object obj = response.get(SympaCore.sympaResult);
                if ( obj !=  null )
                {
                    String result = obj.toString();
                    if ( result != null && result.trim().equalsIgnoreCase("true"))
                    {
                        LOG.info("Successfully closed Sympa List {0}", listName);
                        success = true;
                    }
                }
            }
        }
        else
        {
            LOG.error("Cannot Close {0}. Global Configuration is invalid or incomplete", listName);
            throw new ConnectorException("Global configuration is invalid or incomplete");
        }
        return success;
    }

    public Boolean create(String listName, String subject, String templateName, String description, String topics)
    {
        Boolean success = new Boolean(false);
        Map<String, Object> response;
        if ( canExecute )
        {
            try
            {
                // Setup Parameters
                HashMap<String, String> parameters = new HashMap<>(config);
                parameters.put(SympaCore.listName, listName);
                parameters.put(SympaCore.listSubject, subject);
                parameters.put(SympaCore.listTemplate, templateName);
                parameters.put(SympaCore.listDescription, description);
                parameters.put(SympaCore.listTopics, topics);

                // Build Request Message
                String message = buildRequestMessage("message/createList.xml", parameters);

                // Send Request and receive response
                String responseData = getHttpPostResponse(message, config.get(domainURL));
                // Check for fault code
                response = unMarshalCreateResponse(responseData);
            }
            catch (Exception exception)
            {
                LOG.warn(exception, "Failed to create Sympa list {0}", listName);
                throw new ConnectorException(exception.getMessage());
            }
            // Analyse the response and log the results
            if ( response != null )
            {
                processFault("create", listName, response);

                Object obj = response.get(SympaCore.sympaResult);
                if ( obj !=  null )
                {
                    String result = obj.toString();
                    if ( result != null && result.trim().equalsIgnoreCase("true"))
                    {
                        LOG.info("Successfully Created Sympa List {0}", listName);
                        success = true;
                    }
                }
            }
        }
        else
        {
            LOG.error("Cannot Create {0}. Global Configuration is invalid or incomplete", listName);
            throw new ConnectorException("Global configuration is invalid or incomplete");
        }
        return success;
    }

    public List<SympaCoreList> getAll() throws ConnectorException
    {
        List<SympaCoreList> lists = null;
        if ( canExecute )
        {
            try
            {
                // Construct Message
                String message = buildRequestMessage("message/getComplexLists.xml", config);

                // Send Request and receive response
                String responseData = getHttpPostResponse(message, config.get(domainURL));

                // Look for fault
                Map<String, Object> responseCheck = unMarshalInfoResponse(responseData);
                processFault("getAll", null, responseCheck);

                // Re Wrap the Response Data
                String unwrapped = XMLHelper.unWrap("item", responseData);
                String xml = XMLHelper.wrap("ListInfo", nameSpace, unwrapped);

                // Unmarshal the XML tree to list of SympaCoreList
                Map<String, Object> response = unMarshalComplexLists(xml);
                processFault("getAll", null, response);
                lists = (List) response.get(SympaCore.sympaList);
                if ( lists != null && lists.size() > 0 )
                {
                    LOG.info("Sympa getComplexLists success on server {0}", config.get(domainURL));
                }
            }
            catch (Exception exception)
            {
                LOG.warn(exception, "Failed to retrieve all Sympa lists from server {0}", config.get(domainURL));
                throw new ConnectorException(exception.getMessage());
            }
        }
        else
        {
            LOG.error("Cannot retrieve Sympa lists. Global Configuration is invalid or incomplete");
            throw new ConnectorException("Global configuration is invalid or incomplete");
        }
        return lists;
    }

    public SympaCoreList getOne(String listName) throws ConnectorException
    {
        SympaCoreList item = null;
        Map<String, Object> response;
        if ( canExecute )
        {
            try
            {
                // build parameter map for the request
                HashMap<String, String> parameters = new HashMap<>(config);
                parameters.put(SympaCore.listName, listName);

                // Construct Request Message
                String message = buildRequestMessage("message/getInfo.xml", parameters);

                // Post Request and receive response
                String responseData = getHttpPostResponse(message, config.get(domainURL));

                response = unMarshalInfoResponse(responseData);
            }
            catch (Exception exception)
            {
                LOG.error(exception, "Failed to get Sympa list {0}", listName);
                throw new ConnectorException(exception.getMessage());
            }

            if ( response != null )
            {
                if ( response.containsKey(SympaCore.sympaItem))
                {
                    Object obj = response.get(SympaCore.sympaItem);
                    if ( obj instanceof SympaCoreList )
                    {
                        item = (SympaCoreList) obj;
                        LOG.info("Retrieved Info for Sympa list {0}", listName);
                    }
                }
                else if ( response.containsKey(SympaCore.sympaFault))
                {
                    processFault("getOne", listName, response);
                }
            }
        }
        else
        {
            LOG.error("Cannot get Info for Sympa list {0}. Global Configuration is invalid or incomplete", listName );
            throw new ConnectorException("Global configuration is invalid or incomplete");
        }

        return item;
    }

    /**
     * Default initialization
     */
    public void init() throws Exception
    {
        initFromResource(configFile);
        canExecute = validateGlobalConfiguration(config);
    }

    /**
     * Initialize from a Map object
     *
     * @param aConfig run time configuration settings
     */
    public void initFromMap(Map<String, String> aConfig)
    {
        if (aConfig != null)
        {
            config = new HashMap<String, String>();
            config.putAll(aConfig);
        }
        else
        {
            LOG.warn("configuration is null");
        }
        canExecute = validateGlobalConfiguration(config);
    }

    /**
     * Initialize from properties file stored as a resource
     *
     * @param aFile resource file containing configuration parameters
     */
    public void initFromResource(String aFile) throws Exception
    {
        if (aFile != null && aFile.trim().length() > 0)
        {
            Properties properties = FileHelper.loadPropertiesFromResource(aFile);
            if (properties != null)
            {
                Map map = (Map) properties;
                config = (Map<String, String>) map;
            }
        }
        canExecute = validateGlobalConfiguration(config);
    }

    /**
     * Initialize from properties file stored on the host computer
     *
     * @param aFile file containing configuration parameters
     */
    public void initFromFile(String aFile) throws Exception
    {
        if (aFile != null && aFile.trim().length() > 0)
        {
            Properties properties = FileHelper.loadProperties(aFile);
            if (properties != null)
            {
                Map map = (Map) properties;
                config = (Map<String, String>) map;
            }
        }
        canExecute = validateGlobalConfiguration(config);
    }

    public Map<String, Object> unMarshalCloseResponse(String xml) throws XMLStreamException
    {
        SympaFault item;
        String result;
        XMLStreamReader streamReader = null;
        HashMap<String, Object> data = new HashMap<>();
        try
        {
            if (xml != null && xml.trim().length() > 0)
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
                            if (elementName != null && elementName.trim().equalsIgnoreCase(SympaCore.sympaFault))
                            {
                                item = mapper.readValue(streamReader, SympaFault.class);
                                data.put(SympaCore.sympaFault, item);
                            }
                            else if (elementName != null && elementName.trim().equalsIgnoreCase(SympaCore.sympaResult))
                            {
                                result = streamReader.getElementText();
                                data.put(SympaCore.sympaResult, result);
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
                LOG.warn("No Data to parse");
            }
        }
        catch (Exception e)
        {
            LOG.warn(e, e.getMessage());
        }
        finally
        {
            if (streamReader != null)
            {
                streamReader.close();
            }
        }

        return data;
    }

    public Map<String, Object> unMarshalCreateResponse(String xml) throws XMLStreamException
    {
        String result;
        SympaFault fault;
        XMLStreamReader streamReader = null;
        HashMap<String, Object> response = new HashMap<>();
        try
        {
            if (xml != null && xml.trim().length() > 0)
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
                            if (elementName != null && elementName.trim().equalsIgnoreCase(SympaCore.sympaFault))
                            {
                                fault = mapper.readValue(streamReader, SympaFault.class);
                                response.put(SympaCore.sympaFault, fault);
                            }
                            else if (elementName != null && elementName.trim().equalsIgnoreCase(SympaCore.sympaResult))
                            {
                                result = streamReader.getElementText();
                                response.put(SympaCore.sympaResult, result);
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
                LOG.info("No Data to parse");
            }
        }
        catch (Exception exception)
        {
            LOG.error(exception, "Unable to unmarshal response");
        }
        finally
        {
            if (streamReader != null)
            {
                streamReader.close();
            }
        }

        return response;
    }

    public HashMap<String, Object> unMarshalComplexLists(String xml) throws XMLStreamException
    {
        List<SympaCoreList> list = new ArrayList<>();
        XMLStreamReader streamReader = null;
        HashMap<String, Object> response = new HashMap<>();
        try
        {
            if (xml != null && xml.trim().length() > 0)
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
                LOG.warn("No data to parse");
            }
        }
        catch (Exception e)
        {
            LOG.warn(e, e.getMessage());
        }
        finally
        {
            if (streamReader != null)
            {
                streamReader.close();
            }
        }

        if (!list.isEmpty()) {
            response.put(SympaCore.sympaList, list);
        }
        return response;
    }

    public HashMap<String, Object> unMarshalInfoResponse(String xml) throws XMLStreamException
    {
        SympaCoreList item;
        SympaFault fault;
        XMLStreamReader streamReader = null;
        HashMap<String, Object> response = new HashMap<>();
        try
        {
            if (xml != null && xml.trim().length() > 0)
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
                            if (elementName != null && elementName.trim().equalsIgnoreCase(SympaCore.sympaItem))
                            {
                                item = mapper.readValue(streamReader, SympaCoreList.class);
                                response.put(SympaCore.sympaItem, item);
                            }
                            if (elementName != null && elementName.trim().equalsIgnoreCase(SympaCore.sympaFault))
                            {
                                fault = mapper.readValue(streamReader, SympaFault.class);
                                response.put(SympaCore.sympaFault, fault);
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
                LOG.warn("unMarshalInfo: No Data to parse");
            }
        }
        catch (Exception e)
        {
            LOG.warn(e, e.getMessage());
        }
        finally
        {
            if (streamReader != null)
            {
                streamReader.close();
            }
        }
        return response;
    }

    private void processFault(String request, String listName, Map<String, Object> responseData) throws ConnectorException {
        Object obj = responseData.get(SympaCore.sympaFault);
        if (obj instanceof SympaFault) {
            SympaFault fault = (SympaFault) obj;
            if (listName != null) {
                LOG.warn("Failed to complete Sympa request {0} for list {1}. Fault info: {2}.",
                        request, listName, fault.toString());
            } else {
                LOG.warn("Failed to complete Sympa request {0}. Fault info: {1}.",
                        request, fault.toString());
            }
            processFault(request, listName, fault);
        }
    }

    private void processFault(String request, String listName, SympaFault fault) throws ConnectorException {
        if (StringUtils.containsIgnoreCase(fault.getDetail(), "list already exists")) {
            throw new AlreadyExistsException("Sympa list " + listName + " already exists");
        } else if (StringUtils.containsIgnoreCase(fault.getName(), "Authentication failed")) {
            throw new ConnectorSecurityException("Sympa authentication failed for " +  request + ": " + fault.toString() +
                    " On Server " + config.get(domainURL));
        } else if (StringUtils.containsIgnoreCase(fault.getName(), "unknown")) {
            throw new UnknownUidException("Sympa list " + listName + " not found");
        } else {
            throw new ConnectorException("SympaFault encountered during " + request + ": " + fault.toString());
        }
    }
}
