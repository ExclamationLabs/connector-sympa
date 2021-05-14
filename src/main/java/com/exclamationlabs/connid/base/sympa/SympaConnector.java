package com.exclamationlabs.connid.base.sympa;

import com.exclamationlabs.connid.base.connector.BaseFullAccessConnector;
import com.exclamationlabs.connid.base.connector.authenticator.Authenticator;
import com.exclamationlabs.connid.base.connector.authenticator.JWTRS256Authenticator;
import com.exclamationlabs.connid.base.connector.authenticator.OAuth2TokenJWTAuthenticator;
import com.exclamationlabs.connid.base.connector.authenticator.keys.PEMRSAPrivateKeyLoader;
import com.exclamationlabs.connid.base.connector.configuration.ConnectorProperty;
import com.exclamationlabs.connid.base.sympa.adapter.SympaListsAdapter;
import com.exclamationlabs.connid.base.sympa.configuration.SympaConfiguration;
import com.exclamationlabs.connid.base.sympa.driver.SympaDriver;
import org.identityconnectors.framework.spi.ConnectorClass;

import java.security.interfaces.RSAPrivateKey;
import java.util.Set;

@ConnectorClass(displayNameKey = "sympa.connector.display",
        configurationClass = SympaConfiguration.class)
public class SympaConnector extends BaseFullAccessConnector {

    public SympaConnector() {

        setAuthenticator(setupAuthenticator());
        setDriver(new SympaDriver());
        setAdapters(new SympaListsAdapter());
    }

    private Authenticator setupAuthenticator() {
        Authenticator jwtAuthenticator = new JWTRS256Authenticator() {
            @Override
            protected RSAPrivateKey getPrivateKey() {

                return new PEMRSAPrivateKeyLoader().load(configuration);
            }

            @Override
            protected Set<ConnectorProperty> getPrivateKeyLoaderPropertyNames() {
                return new PEMRSAPrivateKeyLoader().getRequiredPropertyNames();
            }
        };
        return new OAuth2TokenJWTAuthenticator(jwtAuthenticator);
    }


}
