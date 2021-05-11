
package com.exclamationlabs.connid.sympa.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="gecos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSubscriber" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isOwner" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isEditor" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userType", namespace = "https://lists.dev.at.internet2.edu/sympa/wsdl", propOrder = {

})
public class UserType {

    @XmlElement(required = true)
    protected java.lang.String email;
    protected java.lang.String gecos;
    protected java.lang.Boolean isSubscriber;
    protected java.lang.Boolean isOwner;
    protected java.lang.Boolean isEditor;

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setEmail(java.lang.String value) {
        this.email = value;
    }

    /**
     * Gets the value of the gecos property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getGecos() {
        return gecos;
    }

    /**
     * Sets the value of the gecos property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setGecos(java.lang.String value) {
        this.gecos = value;
    }

    /**
     * Gets the value of the isSubscriber property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public java.lang.Boolean isIsSubscriber() {
        return isSubscriber;
    }

    /**
     * Sets the value of the isSubscriber property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setIsSubscriber(java.lang.Boolean value) {
        this.isSubscriber = value;
    }

    /**
     * Gets the value of the isOwner property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public java.lang.Boolean isIsOwner() {
        return isOwner;
    }

    /**
     * Sets the value of the isOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setIsOwner(java.lang.Boolean value) {
        this.isOwner = value;
    }

    /**
     * Gets the value of the isEditor property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public java.lang.Boolean isIsEditor() {
        return isEditor;
    }

    /**
     * Sets the value of the isEditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     */
    public void setIsEditor(java.lang.Boolean value) {
        this.isEditor = value;
    }

}
