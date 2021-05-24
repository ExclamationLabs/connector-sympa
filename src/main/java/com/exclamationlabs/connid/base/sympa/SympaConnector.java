package com.exclamationlabs.connid.base.sympa;

import com.exclamationlabs.connid.base.connector.BaseFullAccessConnector;
import com.exclamationlabs.connid.base.sympa.adapter.SympaListsAdapter;
import com.exclamationlabs.connid.base.sympa.configuration.SympaConfiguration;
import com.exclamationlabs.connid.base.sympa.driver.SympaDriver;
import org.identityconnectors.framework.spi.ConnectorClass;

@ConnectorClass(displayNameKey = "sympa.connector.display",
        configurationClass = SympaConfiguration.class)
public class SympaConnector extends BaseFullAccessConnector {

    public SympaConnector() {
        setDriver(new SympaDriver());
        setAdapters(new SympaListsAdapter());
    }

}
