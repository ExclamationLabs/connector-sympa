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

package com.exclamationlabs.connid.base.sympa.configuration;

import com.exclamationlabs.connid.base.connector.configuration.BaseConnectorConfiguration;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.identityconnectors.framework.spi.ConfigurationClass;
import org.identityconnectors.framework.spi.ConfigurationProperty;

import java.util.*;

@ConfigurationClass(skipUnsupported = true)
public class SympaConfiguration extends BaseConnectorConfiguration
{

    public static final String APP_NAME = "app.name";
    public static final String APP_PASSWORD = "app.password";
    public static final String LIST_MASTER = "list.master";
    public static final String DOMAIN_WSDL = "sympa.domain.wsdl";
    public static final String SYMPA_DOMAIN_URL = "sympa.domain.url";

    private String appName;
    private String appPassword;
    private String listMaster;
    private String sympaDomainWSDL;
    private String sympaDomainURL;

    public SympaConfiguration()
    {
        super();
        setConnectorProperties(new Properties());
    }

    public SympaConfiguration(String configurationName)
    {
        super(configurationName);
        setConnectorProperties(new Properties());
    }

    @Override
    public List<String> getAdditionalPropertyNames()
    {
        return Arrays.asList(APP_NAME, APP_PASSWORD, LIST_MASTER, DOMAIN_WSDL, SYMPA_DOMAIN_URL);
    }

    @ConfigurationProperty(order = 20, displayMessageKey = "appName.display", helpMessageKey = "appName.help")
    public String getAppName()
    {
        return appName;
    }

    @ConfigurationProperty(order = 30, displayMessageKey = "appPassword.display", helpMessageKey = "appPassword.help", confidential = true)
    public String getAppPassword()
    {
        return appPassword;
    }

    @Override
    @ConfigurationProperty(
            order = 10,
            displayMessageKey = "configurationFilePath.display",
            helpMessageKey = "configurationFilePath.help" )
    public String getConfigurationFilePath()
    {
        return innerGetMidPointConfigurationFilePath();
    }

    @ConfigurationProperty(order = 40, displayMessageKey = "listMaster.display", helpMessageKey = "listMaster.help")
    public String getListMaster()
    {
        return listMaster;
    }

    @ConfigurationProperty(order = 50, displayMessageKey = "sympaURL.display", helpMessageKey = "sympaURL.help")
    public String getSympaDomainURL()
    {
        return sympaDomainURL;
    }

    @ConfigurationProperty(order = 60, displayMessageKey = "sympaWSDL.display", helpMessageKey = "sympaWSDL.help")
    public String getSympaDomainWSDL()
    {
        return sympaDomainWSDL;
    }

    /**
     * Collect all the Configuration properties
     * @return Map containing all the configuration properties
     */
    public Map<String, String> getSympaPropertyMap()
    {
        Map<String, String> propertyMap = new HashMap<>();
        getAdditionalPropertyNames().forEach(item ->
                propertyMap.put(item, getProperty(item)));
        return propertyMap;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
        forcePropertyCreation();
        if ( appName != null && appName.trim().length()> 0)
        {
            setProperty(APP_NAME, appName);
        }
    }

    public void setAppPassword(String appPassword)
    {
        this.appPassword = appPassword;
        forcePropertyCreation();
        if ( appPassword != null && appPassword.trim().length()> 0)
        {
            setProperty(APP_PASSWORD, appPassword);
        }
    }

    public void setListMaster(String listMaster)
    {
        this.listMaster = listMaster;
        forcePropertyCreation();
        if ( listMaster != null && listMaster.trim().length()> 0)
        {
            setProperty(LIST_MASTER, listMaster);
        }
    }

    public void setSympaDomainURL(String sympaDomainURL)
    {
        this.sympaDomainURL = sympaDomainURL;
        forcePropertyCreation();
        if ( sympaDomainURL != null && sympaDomainURL.trim().length()> 0)
        {
            setProperty(SYMPA_DOMAIN_URL, sympaDomainURL);
        }
    }

    public void setSympaDomainWSDL(String sympaDomainWSDL)
    {
        this.sympaDomainWSDL = sympaDomainWSDL;
        forcePropertyCreation();
        if ( sympaDomainWSDL != null && sympaDomainWSDL.trim().length()> 0)
        {
            setProperty(DOMAIN_WSDL, sympaDomainWSDL);
        }
    }

    /**
     * Force connectorProperties creation to ensure setProperty method works
     */
    public void forcePropertyCreation()
    {
        String propertyFilePath = getConfigurationFilePath();

        if ( propertyFilePath == null || propertyFilePath.trim().length() == 0)
        {
            setTestConfiguration();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
