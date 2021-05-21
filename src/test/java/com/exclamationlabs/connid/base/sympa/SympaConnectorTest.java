/*
    Copyright 2020 Exclamation Labs
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

package com.exclamationlabs.connid.base.sympa;

import com.exclamationlabs.connid.base.connector.test.util.ConnectorTestUtils;
import com.exclamationlabs.connid.base.sympa.attribute.SympaListAttribute;
import com.exclamationlabs.connid.base.sympa.configuration.SympaConfiguration;
import com.exclamationlabs.connid.base.sympa.driver.SympaCore;
import com.exclamationlabs.connid.base.sympa.driver.SympaDriver;
import org.apache.commons.lang3.StringUtils;
import org.identityconnectors.framework.common.objects.*;
import org.identityconnectors.framework.spi.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SympaConnectorTest {

    private SympaConnector connector;
    private SympaCore stubCore;

    @Before
    public void setup() {
        stubCore = new StubSympaCore();
        connector = new SympaConnector() {
            @Override
            public void init(Configuration configuration) {
                setAuthenticator(null);
                setDriver(new SympaDriver() {
                    @Override
                    public SympaCore getSympaCore() {
                        return stubCore;
                    }
                });
                super.init(configuration);
            }
        };
        SympaConfiguration configuration = new SympaConfiguration();
        configuration.setTestConfiguration();
        configuration.setProperty("app.name", "test");
        configuration.setProperty("app.password", "test");
        configuration.setProperty("list.master", "test");
        configuration.setProperty("sympa.domain.wsdl", "test");
        configuration.setProperty("sympa.domain.url", "test");
        connector.init(configuration);
    }

    @Test
    public void test100Test() {
        connector.test();
    }

    @Test
    public void test110ListCreate() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.SUBJECT.name()).addValue("Test Subject").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.LIST_NAME.name()).addValue("listName").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.TEMPLATE.name()).addValue("icp-public").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.DESCRIPTION.name()).addValue("Test SympaList creation").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.TOPICS.name()).addValue("computing,science").build());

        Uid newId = connector.create(new ObjectClass("List"), attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
    }

    @Test
    public void test120ListModify() {
        // NOTE: Sympa update is a no-op, but should still be allowed without failure
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.SUBJECT.name()).addValue("Modified Subject").build());

        Uid newId = connector.update(new ObjectClass("List"), new Uid("testList"), attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
    }
    @Test
    public void test130ListsGet() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);

        connector.executeQuery(new ObjectClass("List"), "", resultsHandler, new OperationOptionsBuilder().build());
        assertTrue(idValues.size() >= 1);
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
        assertTrue(StringUtils.isNotBlank(nameValues.get(0)));
    }


    @Test
    public void test140ListGet() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);

        connector.executeQuery(new ObjectClass("List"), "testList", resultsHandler, new OperationOptionsBuilder().build());

        assertEquals(1, idValues.size());
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
    }


    @Test
    public void test390ListDelete() {
        connector.delete(new ObjectClass("List"), new Uid("testAddress"), new OperationOptionsBuilder().build());
    }

 }