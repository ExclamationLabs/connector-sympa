package com.exclamationlabs.connid.base.sympa;

import com.exclamationlabs.connid.base.connector.configuration.ConfigurationNameBuilder;
import com.exclamationlabs.connid.base.connector.test.IntegrationTest;
import com.exclamationlabs.connid.base.connector.test.util.ConnectorTestUtils;
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

    private static String generatedUserId;
    private static String generatedGroupId;

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

    /*
    @Test
    public void test110UserCreate() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.USER_NAME.name()).addValue("Gonzo Test").build());
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.EMAIL.name()).addValue("gonzo@test.com").build());
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.FIRST_NAME.name()).addValue("Gonzo").build());
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.LAST_NAME.name()).addValue("Test").build());

        Uid newId = connector.create(ObjectClass.ACCOUNT, attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
        generatedUserId = newId.getUidValue();
    }

    @Test(expected=AlreadyExistsException.class)
    public void test110UserCreateAlreadyExists() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.USER_NAME.name()).addValue("Gonzo Test").build());
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.EMAIL.name()).addValue("gonzo@test.com").build());
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.FIRST_NAME.name()).addValue("Gonzo").build());
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.LAST_NAME.name()).addValue("Test").build());

        Uid newId = connector.create(ObjectClass.ACCOUNT, attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
        generatedUserId = newId.getUidValue();
    }

    @Test
    public void test120UserModify() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.LAST_NAME.name()).addValue("Bonzzo").build());

        Uid newId = connector.update(ObjectClass.ACCOUNT, new Uid(generatedUserId), attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
    }
*/

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

        connector.executeQuery(new ObjectClass("List"), "ethantest", resultsHandler, new OperationOptionsBuilder().build());
        assertEquals(1, idValues.size());
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
    }

  /*
    @Test
    public void test210GroupCreate() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(DocuSignGroupAttribute.GROUP_NAME.name()).addValue("Cool Muppets").build());
        Uid newId = connector.create(ObjectClass.GROUP, attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
        generatedGroupId = newId.getUidValue();
    }

    @Test(expected=AlreadyExistsException.class)
    public void test215GroupCreateAlreadyExists() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(DocuSignGroupAttribute.GROUP_NAME.name()).addValue("Everyone").build());

        Uid newId = connector.create(ObjectClass.GROUP, attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
        generatedGroupId = newId.getUidValue();
    }

    @Test
    public void test220GroupModify() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(DocuSignGroupAttribute.GROUP_NAME.name()).addValue("Cool Muppets2").build());
        Uid newId = connector.update(ObjectClass.GROUP, new Uid(generatedGroupId), attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
    }

    @Test
    public void test230GroupsGet() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);

        connector.executeQuery(ObjectClass.GROUP, "", resultsHandler, new OperationOptionsBuilder().build());
        assertTrue(idValues.size() >= 1);
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
        assertTrue(StringUtils.isNotBlank(nameValues.get(0)));
    }


    @Test
    public void test240GroupGet() {
        List<String> idValues = new ArrayList<>();
        List<String> nameValues = new ArrayList<>();
        ResultsHandler resultsHandler = ConnectorTestUtils.buildResultsHandler(idValues, nameValues);

        connector.executeQuery(ObjectClass.GROUP, generatedGroupId, resultsHandler, new OperationOptionsBuilder().build());
        assertEquals(1, idValues.size());
        assertTrue(StringUtils.isNotBlank(idValues.get(0)));
        assertTrue(StringUtils.isNotBlank(nameValues.get(0)));
    }

    @Test
    public void test260AddGroupToUser() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.GROUP_IDS.name()).addValue(
                Collections.singletonList(generatedGroupId)).build());
        Uid newId = connector.update(ObjectClass.ACCOUNT, new Uid(generatedUserId), attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
    }

    @Test
    public void test270RemoveGroupFromUser() {
        Set<Attribute> attributes = new HashSet<>();
        attributes.add(new AttributeBuilder().setName(DocuSignUserAttribute.GROUP_IDS.name()).addValue(
                Collections.EMPTY_LIST).build());
        Uid newId = connector.update(ObjectClass.ACCOUNT, new Uid(generatedUserId), attributes, new OperationOptionsBuilder().build());
        assertNotNull(newId);
        assertNotNull(newId.getUidValue());
    }

    @Test
    public void test290GroupDelete() {
        connector.delete(ObjectClass.GROUP, new Uid(generatedGroupId), new OperationOptionsBuilder().build());
    }

    @Test
    public void test390UserDelete() {
        connector.delete(ObjectClass.ACCOUNT, new Uid(generatedUserId), new OperationOptionsBuilder().build());
    }
     */

}