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

package com.exclamationlabs.connid.base.sympa;

import com.exclamationlabs.connid.base.connector.configuration.ConfigurationNameBuilder;
import com.exclamationlabs.connid.base.connector.test.IntegrationTest;
import com.exclamationlabs.connid.base.connector.test.util.ConnectorTestUtils;
import com.exclamationlabs.connid.base.sympa.attribute.SympaListAttribute;
import com.exclamationlabs.connid.base.sympa.configuration.SympaConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.identityconnectors.framework.common.exceptions.AlreadyExistsException;
import org.identityconnectors.framework.common.objects.*;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.*;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SympaConnectorIntegrationTest extends IntegrationTest {

    private SympaConnector connector;

    private static final String TEST_LIST_NAME = "elabstest_" +
            UUID.randomUUID().toString();

    private static String generatedListAddress;

    @Override
    public String getConfigurationName() {
        return new ConfigurationNameBuilder().withConnector(() -> "SYMPA")
                .withOwner(() -> "INTERNET2").build();
    }

    @Before
    public void setup() {
        connector = new SympaConnector();
        setup(connector, new SympaConfiguration(getConfigurationName()));
    }

    @Test
    public void test050TestMethod() {
        connector.test();
    }


    @Test
    public void test110ListCreate() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.SUBJECT.name()).addValue("Test Subject").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.LIST_NAME.name()).addValue(TEST_LIST_NAME).build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.TEMPLATE.name()).addValue("icp-public").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.DESCRIPTION.name()).addValue("Test SympaList creation").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.TOPICS.name()).addValue("computing,science").build());

        Uid newId = connector.create(new ObjectClass("List"), attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
        generatedListAddress = newId.getUidValue();
    }

    @Test(expected=AlreadyExistsException.class)
    public void test110UserCreateAlreadyExists() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.SUBJECT.name()).addValue("Test Subject").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.LIST_NAME.name()).addValue(TEST_LIST_NAME).build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.TEMPLATE.name()).addValue("icp-public").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.DESCRIPTION.name()).addValue("Test SympaList creation").build());
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.TOPICS.name()).addValue("computing,science").build());

        connector.create(new ObjectClass("List"), attributes, new OperationOptionsBuilder().build());
    }

    @Test
    public void test120UserModify() {
        // NOTE: Sympa update is a no-op, but should still be allowed without failure
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(SympaListAttribute.SUBJECT.name()).addValue("Modified Subject").build());

        Uid newId = connector.update(new ObjectClass("List"), new Uid(TEST_LIST_NAME), attributes, new OperationOptionsBuilder().build());
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

        connector.executeQuery(new ObjectClass("List"), TEST_LIST_NAME, resultsHandler, new OperationOptionsBuilder().build());
        //connector.executeQuery(new ObjectClass("List"), "elabstest_241b5b65-7060-49cf-8ad6-aef649a425f8", resultsHandler, new OperationOptionsBuilder().build());

        assertEquals(1, idValues.size());
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
    }


    @Test
    public void test390ListDelete() {
        connector.delete(new ObjectClass("List"), new Uid(generatedListAddress), new OperationOptionsBuilder().build());
        //connector.delete(new ObjectClass("List"), new Uid("elabstest_241b5b65-7060-49cf-8ad6-aef649a425f8@lists.dev.at.internet2.edu"), new OperationOptionsBuilder().build());

    }
}