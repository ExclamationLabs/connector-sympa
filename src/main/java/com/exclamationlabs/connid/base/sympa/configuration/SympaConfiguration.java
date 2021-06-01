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
