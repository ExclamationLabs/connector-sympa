package com.exclamationlabs.connid.base.sympa.adapter;
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
import com.exclamationlabs.connid.base.connector.adapter.AdapterValueTypeConverter;
import com.exclamationlabs.connid.base.connector.adapter.BaseAdapter;
import com.exclamationlabs.connid.base.connector.attribute.ConnectorAttribute;
import com.exclamationlabs.connid.base.sympa.configuration.SympaConfiguration;
import com.exclamationlabs.connid.base.sympa.model.SharedSympaList;
import org.identityconnectors.framework.common.objects.Attribute;
import org.identityconnectors.framework.common.objects.AttributeBuilder;
import org.identityconnectors.framework.common.objects.ObjectClass;

import java.util.HashSet;
import java.util.Set;

import static com.exclamationlabs.connid.base.connector.attribute.ConnectorAttributeDataType.*;
import static org.identityconnectors.framework.common.objects.AttributeInfo.Flags.NOT_UPDATEABLE;

import static com.exclamationlabs.connid.base.sympa.attribute.SympaListAttribute.*;

public class SympaListsAdapter extends BaseAdapter<SharedSympaList, SympaConfiguration> {
    @Override
    public ObjectClass getType() {
        return new ObjectClass("List");
    }

    @Override
    public Class<SharedSympaList> getIdentityModelClass() {
        return SharedSympaList.class;
    }

    @Override
    public Set<ConnectorAttribute> getConnectorAttributes() {
        Set<ConnectorAttribute> result = new HashSet<>();
        result.add(new ConnectorAttribute(HOMEPAGE.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(SUBJECT.name(), STRING));
        result.add(new ConnectorAttribute(LIST_ADDRESS.name(), STRING, NOT_UPDATEABLE));
        result.add(new ConnectorAttribute(LIST_NAME.name(), STRING));
        result.add(new ConnectorAttribute(DESCRIPTION.name(), STRING));
        result.add(new ConnectorAttribute(TEMPLATE.name(), STRING));
        result.add(new ConnectorAttribute(TOPICS.name(), STRING));
        result.add(new ConnectorAttribute(DOMAIN.name(), STRING, NOT_UPDATEABLE));
        return result;
    }

    @Override
    protected Set<Attribute> constructAttributes(SharedSympaList sympaList) {
        Set<Attribute> attributes = new HashSet<>();
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
    protected SharedSympaList constructModel(Set<Attribute> attributes, Set<Attribute> multiValueAdd,
                                             Set<Attribute> multiValueRemove, boolean isCreation) {
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
