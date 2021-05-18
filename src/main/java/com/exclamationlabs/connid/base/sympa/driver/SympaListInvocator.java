package com.exclamationlabs.connid.base.sympa.driver;

import com.exclamationlabs.connid.base.connector.driver.DriverInvocator;
import com.exclamationlabs.connid.base.sympa.model.SharedSympaList;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

import java.util.List;
import java.util.Map;

public class SympaListInvocator implements DriverInvocator<SympaDriver, SharedSympaList> {

    @Override
    public String create(SympaDriver sympaDriver, SharedSympaList sharedSympaList) throws ConnectorException {
        return null;
    }

    @Override
    public void update(SympaDriver sympaDriver, String s, SharedSympaList sharedSympaList) throws ConnectorException {

    }

    @Override
    public void delete(SympaDriver sympaDriver, String s) throws ConnectorException {

    }

    @Override
    public List<SharedSympaList> getAll(SympaDriver sympaDriver, Map<String, Object> map) throws ConnectorException {
        return null;
    }

    @Override
    public SharedSympaList getOne(SympaDriver sympaDriver, String s, Map<String, Object> map) throws ConnectorException {
        return null;
    }
}
