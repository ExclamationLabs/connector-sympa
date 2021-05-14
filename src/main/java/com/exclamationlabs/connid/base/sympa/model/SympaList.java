package com.exclamationlabs.connid.base.sympa.model;

import com.exclamationlabs.connid.base.connector.model.IdentityModel;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SympaList implements IdentityModel {

    @JacksonXmlProperty(localName = "homepage")
    private String homePage;

    @JacksonXmlProperty(localName = "subject")
    private String subject;

    @JacksonXmlProperty(localName = "listAddress")
    private String listAddress;

    @JacksonXmlProperty(localName = "isSubscriber")
    private Boolean subscriber;

    @JacksonXmlProperty(localName = "isOwner")
    private Boolean owner;

    private String listName;

    private String domain;

    @Override
    public String getIdentityIdValue() {
        return getListAddress();
    }

    @Override
    public String getIdentityNameValue() {
        return getSubject();
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getListAddress() {
        return listAddress;
    }

    public void setListAddress(String listAddress) {
        this.listAddress = listAddress;
    }

    public Boolean getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Boolean subscriber) {
        this.subscriber = subscriber;
    }

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
