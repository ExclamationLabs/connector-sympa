package com.exclamationlabs.connid.base.sympa.configuration;

import com.exclamationlabs.connid.base.connector.configuration.BaseConnectorConfiguration;
import org.identityconnectors.framework.spi.ConfigurationClass;
import org.identityconnectors.framework.spi.ConfigurationProperty;

import java.util.*;

@ConfigurationClass(skipUnsupported = true)
public class SympaConfiguration extends BaseConnectorConfiguration {

    public static final String APP_NAME = "app.name";
    public static final String APP_PASSWORD = "app.password";
    public static final String LIST_MASTER = "list.master";
    public static final String DOMAIN_WSDL = "sympa.domain.wsdl";
    public static final String SYMPA_DOMAIN_URL = "sympa.domain.url";

    public SympaConfiguration() {
        super();
    }

    public SympaConfiguration(String configurationName) {
        super(configurationName);
    }

    @Override
    public List<String> getAdditionalPropertyNames() {
        return Arrays.asList(APP_NAME, APP_PASSWORD, LIST_MASTER,
                DOMAIN_WSDL, SYMPA_DOMAIN_URL);
    }

    @Override
    @ConfigurationProperty(
            displayMessageKey = "Sympa Configuration File Path",
            helpMessageKey = "File path for the Sympa Configuration File",
            required = true)
    public String getConfigurationFilePath() {
        return innerGetMidPointConfigurationFilePath();
    }

    public Map<String, String> getSympaPropertyMap() {
        Map<String, String> propertyMap = new HashMap<>();
        getAdditionalPropertyNames().forEach(item ->
                propertyMap.put(item, getProperty(item)));
        return propertyMap;
    }

}
