package com.exclamationlabs.connid.base.sympa.driver;

import com.exclamationlabs.connid.base.connector.authenticator.Authenticator;
import com.exclamationlabs.connid.base.connector.configuration.BaseConnectorConfiguration;
import com.exclamationlabs.connid.base.connector.configuration.ConnectorProperty;
import com.exclamationlabs.connid.base.connector.driver.BaseDriver;
import com.exclamationlabs.connid.base.sympa.configuration.SympaConfiguration;
import com.exclamationlabs.connid.base.sympa.model.SharedSympaList;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

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

    }
}