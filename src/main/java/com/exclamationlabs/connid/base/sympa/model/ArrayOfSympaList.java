package com.exclamationlabs.connid.base.sympa.model;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listInfo", namespace = "https://github.com/ExclamationLabs/connector-sympa/schema.xsd", propOrder = {})
public class ArrayOfSympaList
{
    @XmlElement(name = "item", nillable = true)
    protected List<SharedSympaList> list;

    public List<SharedSympaList> getList()
    {
        return list;
    }

    public void setList(List<SharedSympaList> list)
    {
        this.list = list;
    }
}
