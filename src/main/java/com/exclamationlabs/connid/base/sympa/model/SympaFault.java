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

package com.exclamationlabs.connid.base.sympa.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fault", namespace = "https://github.com/ExclamationLabs/connector-sympa/schema.xsd", propOrder = {})
public class SympaFault
{
    @JacksonXmlProperty(localName = "faultcode")
    @XmlElement(name = "faultcode")
    private String code;

    @JacksonXmlProperty(localName = "faultstring")
    @XmlElement(name = "faultstring")
    private String name;

    @JacksonXmlProperty(localName = "detail")
    @XmlElement(name = "detail")
    private String detail;

    public String getCode()
    {
        return code;
    }

    public String getDetail()
    {
        return detail;
    }

    public String getName()
    {
        return name;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return "code: " + code + "; name: " + name + "; detail: " + detail;
    }
}
