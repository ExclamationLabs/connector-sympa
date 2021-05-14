package com.exclamationlabs.connid.base.sympa.driver;

import com.exclamationlabs.connid.base.connector.driver.DriverInvocator;
import com.exclamationlabs.connid.base.sympa.model.SympaList;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

import java.util.List;
import java.util.Map;

public class SympaListInvocator implements DriverInvocator<SympaDriver, SympaList> {

    @Override
    public String create(SympaDriver sympaDriver, SympaList sympaList) throws ConnectorException {
        return null;
    }

    @Override
    public void update(SympaDriver sympaDriver, String s, SympaList sympaList) throws ConnectorException {

    }

    @Override
    public void delete(SympaDriver sympaDriver, String s) throws ConnectorException {

    }

    @Override
    public List<SympaList> getAll(SympaDriver sympaDriver, Map<String, Object> map) throws ConnectorException {
        return null;
    }

    @Override
    public SympaList getOne(SympaDriver sympaDriver, String s, Map<String, Object> map) throws ConnectorException {
        return null;
    }
}
