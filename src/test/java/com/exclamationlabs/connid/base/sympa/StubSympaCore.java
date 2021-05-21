package com.exclamationlabs.connid.base.sympa;

import com.exclamationlabs.connid.base.sympa.driver.SympaCore;
import com.exclamationlabs.connid.base.sympa.model.SympaCoreList;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

import java.util.ArrayList;
import java.util.List;

public class StubSympaCore extends SympaCore {

    @Override
    public Boolean create(String listName, String subject, String templateName,
                          String description, String topics)
    {
        return Boolean.TRUE;
    }

    @Override
    public SympaCoreList getOne(String listName) throws ConnectorException
    {
        SympaCoreList item = new SympaCoreList();
        item.setDomain("testDomain");
        item.setEditor(false);
        item.setHomePage("testHomePage");
        item.setListAddress("testing@unittest.org");
        item.setListName("testing");
        item.setOwner(false);
        item.setSubject("testing subject");
        item.setSubscriber(false);

        return item;
    }

    @Override
    public List<SympaCoreList> getAll() throws ConnectorException
    {
        List<SympaCoreList> lists = new ArrayList<>();
        SympaCoreList item = new SympaCoreList();
        item.setDomain("testDomain");
        item.setEditor(false);
        item.setHomePage("testHomePage");
        item.setListAddress("testing@unittest.org");
        item.setListName("testing");
        item.setOwner(false);
        item.setSubject("testing subject");
        item.setSubscriber(false);

        SympaCoreList item2 = new SympaCoreList();
        item2.setDomain("testDomain2");
        item2.setEditor(false);
        item2.setHomePage("testHomePage 2");
        item2.setListAddress("testing2@unittest.org");
        item2.setListName("testing2");
        item2.setOwner(false);
        item2.setSubject("testing subject2");
        item2.setSubscriber(false);

        lists.add(item);
        lists.add(item2);
        return lists;
    }

    @Override
    public Boolean close (String listName) throws ConnectorException {
        return Boolean.TRUE;
    }

}
