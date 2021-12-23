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

import com.exclamationlabs.connid.base.connector.authenticator.Authenticator;
import com.exclamationlabs.connid.base.connector.configuration.TrustStoreConfiguration;
import com.exclamationlabs.connid.base.connector.driver.BaseDriver;
import com.exclamationlabs.connid.base.sympa.configuration.SympaConfiguration;
import com.exclamationlabs.connid.base.sympa.model.SharedSympaList;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
import org.identityconnectors.framework.common.exceptions.UnknownUidException;

import java.util.HashMap;
import java.util.Map;

public class SympaDriver extends BaseDriver<SympaConfiguration>
{
    private SympaCore sympaCore;

    public SympaDriver()
    {
        super();
        addInvocator(SharedSympaList.class, new SympaListInvocator());
    }

    @Override
    public void close()
    {

    }

    public SympaCore getSympaCore()
    {
        return sympaCore;
    }

    @Override
    public void initialize(SympaConfiguration configuration, Authenticator authenticator) throws ConnectorException
    {
        TrustStoreConfiguration.clearJdkProperties();
        sympaCore = new SympaCore(getSympaPropertyMap(configuration));
    }

    public void setSympaCore(SympaCore sympaCore)
    {
        this.sympaCore = sympaCore;
    }

    @Override
    public void test() throws ConnectorException
    {
        // Test basic connectivity to Sympa by trying to get a dummy list that doesn't exist
        try
        {
            getSympaCore().getOne("dummyList");
        }
        catch (UnknownUidException unk)
        {
            // expected exception - there should be no dummyList
        }
    }

    /**
     * Collect all the Configuration properties
     * @return Map containing all the configuration properties
     */
    public Map<String, String> getSympaPropertyMap(SympaConfiguration configuration)
    {
        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("app.name", configuration.getAppName());
        propertyMap.put("app.password", configuration.getAppPassword());
        propertyMap.put("list.master", configuration.getListMaster());
        propertyMap.put("sympa.domain.wsdl", configuration.getSympaDomainWSDL());
        propertyMap.put("sympa.domain.url", configuration.getSympaDomainURL());
        return propertyMap;
    }
}
