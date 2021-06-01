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
import com.exclamationlabs.connid.base.connector.configuration.BaseConnectorConfiguration;
import com.exclamationlabs.connid.base.connector.configuration.ConnectorProperty;
import com.exclamationlabs.connid.base.connector.configuration.TrustStoreConfiguration;
import com.exclamationlabs.connid.base.connector.driver.BaseDriver;
import com.exclamationlabs.connid.base.sympa.configuration.SympaConfiguration;
import com.exclamationlabs.connid.base.sympa.model.SharedSympaList;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
import org.identityconnectors.framework.common.exceptions.UnknownUidException;

import java.util.Set;

public class SympaDriver extends BaseDriver
{
    private SympaCore sympaCore;
    private SympaConfiguration sympaConfiguration;

    public SympaDriver()
    {
        super();
        addInvocator(SharedSympaList.class, new SympaListInvocator());
    }

    @Override
    public void close()
    {

    }

    @Override
    public Set<ConnectorProperty> getRequiredPropertyNames()
    {
        return null;
    }

    SympaConfiguration getSympaConfiguration()
    {
        return sympaConfiguration;
    }

    public SympaCore getSympaCore()
    {
        return sympaCore;
    }

    @Override
    public void initialize(BaseConnectorConfiguration baseConnectorConfiguration, Authenticator authenticator) throws ConnectorException
    {
        TrustStoreConfiguration.clearJdkProperties();
        sympaConfiguration = (SympaConfiguration) baseConnectorConfiguration;
        sympaCore = new SympaCore(sympaConfiguration.getSympaPropertyMap());
    }

    public void setSympaCore(SympaCore sympaCore)
    {
        this.sympaCore = sympaCore;
    }

    @Override
    public void test() throws ConnectorException
    {
        // Test basic connectivity to Sympa by trying to get a dummy list that doesn't exist
        try {
            getSympaCore().getOne("dummyList");
        } catch (UnknownUidException unk) {
            // expected exception - there should be no dummyList
        }
    }
}
