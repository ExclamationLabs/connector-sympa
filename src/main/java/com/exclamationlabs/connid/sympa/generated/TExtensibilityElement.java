
package com.exclamationlabs.connid.sympa.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tExtensibilityElement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tExtensibilityElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute ref="{http://schemas.xmlsoap.org/wsdl/}required"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tExtensibilityElement", namespace = "http://schemas.xmlsoap.org/wsdl/")
public abstract class TExtensibilityElement {

    @XmlAttribute(name = "required", namespace = "http://schemas.xmlsoap.org/wsdl/")
    protected java.lang.Boolean required;

    /**
     * Gets the value of the required property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public java.lang.Boolean isRequired() {
        return required;
    }

    /**
     * Sets the value of the required property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setRequired(java.lang.Boolean value) {
        this.required = value;
    }

}
