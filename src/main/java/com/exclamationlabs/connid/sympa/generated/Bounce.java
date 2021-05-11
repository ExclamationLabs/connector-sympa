
package com.exclamationlabs.connid.sympa.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bounce complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bounce">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="firstBounceDate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="lastBounceDate" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="bounceCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="bounceScore" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bounce", namespace = "https://lists.dev.at.internet2.edu/sympa/wsdl", propOrder = {

})
public class Bounce {

    protected java.lang.Integer firstBounceDate;
    protected java.lang.Integer lastBounceDate;
    protected int bounceCount;
    protected int bounceScore;

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
     */
    public int getBounceCount() {
        return bounceCount;
    }

    /**
     * Sets the value of the bounceCount property.
     * 
     */
    public void setBounceCount(int value) {
        this.bounceCount = value;
    }

    /**
     * Gets the value of the bounceScore property.
     * 
     */
    public int getBounceScore() {
        return bounceScore;
    }

    /**
     * Sets the value of the bounceScore property.
     * 
     */
    public void setBounceScore(int value) {
        this.bounceScore = value;
    }

}
