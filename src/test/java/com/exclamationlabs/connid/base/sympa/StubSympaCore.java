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
