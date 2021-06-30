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
        if ( domain == null || domain.length() == 0 )
        {
            if ( listAddress != null && listAddress.length() > 0 )
            {
                int at = listAddress.indexOf("@");
                if ( at > 0 )
                {
                    listName = listAddress.substring(at+1);
                }
            }
        }
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
        return getListName();
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
        if ( listName == null || listName.length() == 0 )
        {
            if ( listAddress != null && listAddress.length() > 0 )
            {
                int at = listAddress.indexOf("@");
                if ( at > 0 )
                {
                    listName = listAddress.substring(0, at);
                }
            }
        }
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
     * @param domain the mail domain
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
     * @param name The name of the list
     */
    public void setListName(String name)
    {
        this.listName = name;
    }

    /**
     * Gets the value of the subject property.
     *
     * @param subject
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
                "listAddress='" + getListAddress() + '\'' +
                ", homepage='" + homePage + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
