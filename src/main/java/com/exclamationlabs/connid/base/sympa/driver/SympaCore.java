package com.exclamationlabs.connid.base.sympa.driver;

import com.exclamationlabs.connid.base.sympa.Helper.FileHelper;
import com.exclamationlabs.connid.base.sympa.generated.ListType;
import org.apache.commons.text.StringSubstitutor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SympaCore
{
    private static String configFile = "sympa-config.properties";
    private static Map<String, String> config = null;
    /**
     *  Base Constructor use default Configuration
     */
    public SympaCore()
    {
        init();
    }

    /**
     * @param configFile
     */
    public void init( String configFile )
    {
        if ( config != null )
        {
            if ( configFile != null && configFile.trim().length() > 0 )
            {
                //config = FileHelper.loadPropertiesFromResource(configFile);
            }
        }
    }

    /**
     *
     * @param aConfig run time configuration settings
     */
    public void init ( Map<String, String > aConfig )
    {
        return;
    }

    /**
     * Default initialization
     */
    public void init()
    {
        init(configFile);
    }

    public List<ListType> getAll()
    {
        try
        {
            String message = FileHelper.loadResourceToString("message/getLists.xml", StandardCharsets.UTF_8);
            StringSubstitutor substituror = new StringSubstitutor((Map<String, String>)((Map)config));
            message = substituror.replace(message);
        }
        catch ( IOException ex )
        {
            ex.printStackTrace();
        }

        return null;
    }

    public String getOne(String listName)
    {
        return null;
    }
}
