
package com.exclamationlabs.connid.base.sympa.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="listAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="homepage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isSubscriber" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isOwner" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isEditor" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstBounceDate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="lastBounceDate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="bounceCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="bounceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bounceScore" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listType", namespace = "https://lists.dev.at.internet2.edu/sympa/wsdl", propOrder = {

})
public class ListType {

    @XmlElement(required = true)
    protected java.lang.String listAddress;
    protected java.lang.String homepage;
    protected java.lang.Boolean isSubscriber;
    protected java.lang.Boolean isOwner;
    protected java.lang.Boolean isEditor;
    protected java.lang.String subject;
    protected java.lang.Integer firstBounceDate;
    protected java.lang.Integer lastBounceDate;
    protected java.lang.Integer bounceCount;
    protected java.lang.String bounceCode;
    protected java.lang.Integer bounceScore;

    /**
     * Gets the value of the listAddress property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getListAddress() {
        return listAddress;
    }

    /**
     * Sets the value of the listAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setListAddress(java.lang.String value) {
        this.listAddress = value;
    }

    /**
     * Gets the value of the homepage property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getHomepage() {
        return homepage;
    }

    /**
     * Sets the value of the homepage property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setHomepage(java.lang.String value) {
        this.homepage = value;
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

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setSubject(java.lang.String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the firstBounceDate property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Integer }
     *     
     */
    public java.lang.Integer getFirstBounceDate() {
        return firstBounceDate;
    }

    /**
     * Sets the value of the firstBounceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Integer }
     *     
     */
    public void setFirstBounceDate(java.lang.Integer value) {
        this.firstBounceDate = value;
    }

    /**
     * Gets the value of the lastBounceDate property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Integer }
     *     
     */
    public java.lang.Integer getLastBounceDate() {
        return lastBounceDate;
    }

    /**
     * Sets the value of the lastBounceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Integer }
     *     
     */
    public void setLastBounceDate(java.lang.Integer value) {
        this.lastBounceDate = value;
    }

    /**
     * Gets the value of the bounceCount property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Integer }
     *     
     */
    public java.lang.Integer getBounceCount() {
        return bounceCount;
    }

    /**
     * Sets the value of the bounceCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Integer }
     *     
     */
    public void setBounceCount(java.lang.Integer value) {
        this.bounceCount = value;
    }

    /**
     * Gets the value of the bounceCode property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getBounceCode() {
        return bounceCode;
    }

    /**
     * Sets the value of the bounceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setBounceCode(java.lang.String value) {
        this.bounceCode = value;
    }

    /**
     * Gets the value of the bounceScore property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Integer }
     *     
     */
    public java.lang.Integer getBounceScore() {
        return bounceScore;
    }

    /**
     * Sets the value of the bounceScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Integer }
     *     
     */
    public void setBounceScore(java.lang.Integer value) {
        this.bounceScore = value;
    }

}
