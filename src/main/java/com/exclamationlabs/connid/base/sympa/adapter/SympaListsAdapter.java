package com.exclamationlabs.connid.base.sympa.adapter;

import com.exclamationlabs.connid.base.connector.adapter.BaseAdapter;
import com.exclamationlabs.connid.base.connector.attribute.ConnectorAttribute;
import com.exclamationlabs.connid.base.sympa.model.SharedSympaList;
import org.identityconnectors.framework.common.objects.Attribute;
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
        result.add(new ConnectorAttribute(DOMAIN.name(), STRING));
        result.add(new ConnectorAttribute(IS_SUBSCRIBER.name(), BOOLEAN));
        result.add(new ConnectorAttribute(IS_OWNER.name(), BOOLEAN));
        return result;
    }

    @Override
    protected List<Attribute> constructAttributes(SharedSympaList sharedSympaList) {
        return null;
    }

    @Override
    protected SharedSympaList constructModel(Set<Attribute> set, boolean b) {
        return null;
    }
}
