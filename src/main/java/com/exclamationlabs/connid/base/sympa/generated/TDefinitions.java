
package com.exclamationlabs.connid.base.sympa.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for tDefinitions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tDefinitions">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/wsdl/}tExtensibleDocumented">
 *       &lt;sequence>
 *         &lt;group ref="{http://schemas.xmlsoap.org/wsdl/}anyTopLevelOptionalElement" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="targetNamespace" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tDefinitions", namespace = "http://schemas.xmlsoap.org/wsdl/", propOrder = {
    "anyTopLevelOptionalElement"
})
public class TDefinitions
    extends TExtensibleDocumented
{

    @XmlElements({
        @XmlElement(name = "import", namespace = "http://schemas.xmlsoap.org/wsdl/", type = TImport.class),
        @XmlElement(name = "types", namespace = "http://schemas.xmlsoap.org/wsdl/", type = TTypes.class),
        @XmlElement(name = "message", namespace = "http://schemas.xmlsoap.org/wsdl/", type = TMessage.class),
        @XmlElement(name = "portType", namespace = "http://schemas.xmlsoap.org/wsdl/", type = TPortType.class),
        @XmlElement(name = "binding", namespace = "http://schemas.xmlsoap.org/wsdl/", type = TBinding.class),
        @XmlElement(name = "service", namespace = "http://schemas.xmlsoap.org/wsdl/", type = TService.class)
    })
    protected List<TDocumented> anyTopLevelOptionalElement;
    @XmlAttribute(name = "targetNamespace")
    @XmlSchemaType(name = "anyURI")
    protected java.lang.String targetNamespace;
    @XmlAttribute(name = "name")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected java.lang.String name;

    /**
     * Gets the value of the anyTopLevelOptionalElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anyTopLevelOptionalElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnyTopLevelOptionalElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TImport }
     * {@link TTypes }
     * {@link TMessage }
     * {@link TPortType }
     * {@link TBinding }
     * {@link TService }
     * 
     * 
     */
    public List<TDocumented> getAnyTopLevelOptionalElement() {
        if (anyTopLevelOptionalElement == null) {
            anyTopLevelOptionalElement = new ArrayList<TDocumented>();
        }
        return this.anyTopLevelOptionalElement;
    }

    /**
     * Gets the value of the targetNamespace property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getTargetNamespace() {
        return targetNamespace;
    }

    /**
     * Sets the value of the targetNamespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setTargetNamespace(java.lang.String value) {
        this.targetNamespace = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setName(java.lang.String value) {
        this.name = value;
    }

}
