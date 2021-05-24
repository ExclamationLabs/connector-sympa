package com.exclamationlabs.connid.base.sympa.model;

import com.exclamationlabs.connid.base.connector.model.IdentityModel;


public class SharedSympaList implements IdentityModel
{

    private String homePage;


    private String subject;

    private String listAddress;

    private String listName;
    private String domain;
    private String description;
    private String topics;
    private String template;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTopics()
    {
        return topics;
    }

    public void setTopics(String topics)
    {
        this.topics = topics;
    }

    public String getTemplate()
    {
        return template;
    }

    public void setTemplate(String template)
    {
        this.template = template;
    }

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

    @Override
    public String getIdentityIdValue()
    {
        return getListAddress();
    }

    @Override
    public String getIdentityNameValue()
    {
        return getSubject();
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
     * Gets the value of the subject property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }


    @Override
    public String toString()
    {
        return "SharedSympaList{" +
                "listAddress='" + listAddress + '\'' +
                ", homepage='" + homePage + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
