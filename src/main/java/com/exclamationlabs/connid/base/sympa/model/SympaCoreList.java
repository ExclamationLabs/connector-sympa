package com.exclamationlabs.connid.base.sympa.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for listType complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", namespace = "https://github.com/ExclamationLabs/connector-sympa/schema.xsd", propOrder = {})
public class SympaCoreList
{

    @JacksonXmlProperty(localName = "homepage")
    @XmlElement(name = "homepage")
    private String homePage;

    @JacksonXmlProperty(localName = "subject")
    @XmlElement(name = "subject")
    private String subject;

    @JacksonXmlProperty(localName = "listAddress")
    @XmlElement(name = "listAddress")
    private String listAddress;

    @JacksonXmlProperty(localName = "isSubscriber")
    @XmlElement(name = "isSubscriber")
    private Boolean subscriber;

    @JacksonXmlProperty(localName = "isOwner")
    @XmlElement(name = "isOwner")
    private Boolean owner;

    @JacksonXmlProperty(localName = "isEditor")
    @XmlElement(name = "isEditor")
    private Boolean editor;

    private String listName;

    private String domain;

    /**
     * @return The domain
     */
    public String getDomain()
    {
        return domain;
    }

    /**
     * Gets the value of the homepage property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHomePage()
    {
        return homePage;
    }


    /**
     * Gets the value of the listAddress property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getListAddress()
    {
        return listAddress;
    }

    /**
     * @return The listname
     */
    public String getListName()
    {
        return listName;
    }

    /**
     * @return The list subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * Gets the value of the owner property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isOwner()
    {
        return owner;
    }

    /**
     * Gets the value of the subscriber property.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isSubscriber()
    {
        return subscriber;
    }

    /**
     * Set the domain property
     * @param domain
     */
    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    /**
     * Sets the value of the homepage property.
     *
     * @param homePage allowed object is
     *                 {@link String }
     */
    public void setHomePage(String homePage)
    {
        this.homePage = homePage;
    }

    /**
     * Sets the value of the listAddress property.
     *
     * @param listAddress allowed object is
     *                    {@link String }
     */
    public void setListAddress(String listAddress)
    {
        this.listAddress = listAddress;
    }

    /**
     * Set the listName property
     * @param listName
     */
    public void setListName(String listName)
    {
        this.listName = listName;
    }

    /**
     * Sets the value of the isOwner property.
     *
     * @param owner allowed object is
     *              {@link Boolean }
     */
    public void setOwner(Boolean owner)
    {
        this.owner = owner;
    }

    public void setEditor(Boolean editor) {
        //this.editor = editor;
    }

    /**
     * Gets the value of the subject property.
     *
     *
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    /**
     * Sets the value of the subscriber property.
     *
     * @param subscriber allowed object is
     *                   {@link Boolean }
     */
    public void setSubscriber(Boolean subscriber)
    {
        this.subscriber = subscriber;
    }

    @Override
    public String toString()
    {
        return "SympaCoreList{" +
                "listAddress='" + listAddress + '\'' +
                ", homepage='" + homePage + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
