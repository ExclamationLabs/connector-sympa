package com.exclamationlabs.connid.base.sympa.adapter;

import com.exclamationlabs.connid.base.connector.adapter.AdapterValueTypeConverter;
import com.exclamationlabs.connid.base.connector.adapter.BaseAdapter;
import com.exclamationlabs.connid.base.connector.attribute.ConnectorAttribute;
import com.exclamationlabs.connid.base.sympa.model.SharedSympaList;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.ObjectClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.exclamationlabs.connid.base.connector.attribute.ConnectorAttributeDataType.*;
import static org.identityconnectors.framework.common.objects.AttributeInfo.Flags.NOT_UPDATEABLE;

import static com.exclamationlabs.connid.base.sympa.attribute.SympaListAttribute.*;

public class SympaListsAdapter extends BaseAdapter<SharedSympaList> {
    @Override
    public ObjectClass getType() {
        return new ObjectClass("List");
    }

    @Override
    public Class<SharedSympaList> getIdentityModelClass() {
        return SharedSympaList.class;
    }

    @Override
    public List<ConnectorAttribute> getConnectorAttributes() {
        List<ConnectorAttribute> result = new ArrayList<>();
        result.add(new ConnectorAttribute(HOMEPAGE.name(), STRING));
        result.add(new ConnectorAttribute(SUBJECT.name(), STRING));
        result.add(new ConnectorAttribute(LIST_ADDRESS.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(LIST_NAME.name(), STRING));
        result.add(new ConnectorAttribute(DESCRIPTION.name(), STRING));
        result.add(new ConnectorAttribute(TEMPLATE.name(), STRING));
        result.add(new ConnectorAttribute(TOPICS.name(), STRING));
        result.add(new ConnectorAttribute(DOMAIN.name(), STRING));
        result.add(new ConnectorAttribute(IS_SUBSCRIBER.name(), BOOLEAN));
        result.add(new ConnectorAttribute(IS_OWNER.name(), BOOLEAN));
        return result;
    }

    @Override
    protected List<Attribute> constructAttributes(SharedSympaList sympaList) {
        List<Attribute> attributes = new ArrayList<>();
        attributes.add(AttributeBuilder.build(HOMEPAGE.name(), sympaList.getHomePage()));
        attributes.add(AttributeBuilder.build(SUBJECT.name(), sympaList.getSubject()));
        attributes.add(AttributeBuilder.build(LIST_ADDRESS.name(), sympaList.getListAddress()));
        attributes.add(AttributeBuilder.build(LIST_NAME.name(), sympaList.getListName()));
        attributes.add(AttributeBuilder.build(DOMAIN.name(), sympaList.getDomain()));
        attributes.add(AttributeBuilder.build(DESCRIPTION.name(), sympaList.getDescription()));
        attributes.add(AttributeBuilder.build(TEMPLATE.name(), sympaList.getTemplate()));
        attributes.add(AttributeBuilder.build(TOPICS.name(), sympaList.getTopics()));
        return attributes;
    }

    @Override
    protected SharedSympaList constructModel(Set<Attribute> attributes, boolean isCreation) {
        SharedSympaList list = new SharedSympaList();
        list.setTemplate(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, TEMPLATE));
        list.setTopics(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, TOPICS));
        list.setDescription(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, DESCRIPTION));
        list.setListAddress(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, LIST_ADDRESS));
        list.setHomePage(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, HOMEPAGE));
        list.setSubject(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, SUBJECT));
        list.setListName(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, LIST_NAME));
        list.setDomain(AdapterValueTypeConverter.getSingleAttributeValue(String.class, attributes, DOMAIN));
        return list;
    }
}
