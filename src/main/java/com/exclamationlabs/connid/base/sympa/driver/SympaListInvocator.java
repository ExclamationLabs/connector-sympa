package com.exclamationlabs.connid.base.sympa.driver;

import com.exclamationlabs.connid.base.connector.driver.DriverInvocator;
import com.exclamationlabs.connid.base.sympa.model.SharedSympaList;
import com.exclamationlabs.connid.base.sympa.model.SympaCoreList;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.ConnectorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SympaListInvocator implements DriverInvocator<SympaDriver, SharedSympaList> 
{
    private static final Log LOG = Log.getLog(SympaListInvocator.class);

    /**
     * Creates a Sympa List and populates the SharedSympaList with results
     * @param sympaDriver The driver contains the SympaCore object and its configuration
     * @param sharedSympaList The requested attributes
     * @return if successful, The email address of the newly created list. Otherwise null
     * @throws ConnectorException when something is wrong
     */
    @Override
    public String create(SympaDriver sympaDriver, SharedSympaList sharedSympaList) throws ConnectorException
    {
        String listAddress = null;
        SympaCoreList list = null;
        SympaCore sympaCore = sympaDriver.getSympaCore();
        Boolean result = sympaCore.create(
                sharedSympaList.getListName(),
                sharedSympaList.getSubject(),
                sharedSympaList.getTemplate(),
                sharedSympaList.getDescription(),
                sharedSympaList.getTopics()
                );
        if ( result )
        {
            list = sympaCore.getOne(sharedSympaList.getListName());
            if ( list != null )
            {
                sharedSympaList.setListAddress(list.getListAddress());
                sharedSympaList.setHomePage(list.getHomePage());
                sharedSympaList.setSubject(list.getSubject());
                listAddress = list.getListAddress();
            }
        }
        return listAddress;
    }

    /**
     * Requests an update of the specified Sympa list.
     * The connector does not change the configuration of the Sympa list.
     * @param sympaDriver The driver contains the SympaCore object and its configuration
     * @param s
     * @param sharedSympaList contains the listname or the list address.
     * @throws ConnectorException
     */
    @Override
    public void update(SympaDriver sympaDriver, String s, SharedSympaList sharedSympaList) throws ConnectorException
    {
        SympaCoreList list = null;
        SympaCore sympaCore = sympaDriver.getSympaCore();
        if ( sharedSympaList != null
                && sharedSympaList.getListName() != null
                && sharedSympaList.getListName().trim().length() > 0
                && sympaCore != null )
        {
            list = sympaCore.getOne(sharedSympaList.getListName());
            if ( list != null )
            {
                sharedSympaList.setListAddress(list.getListAddress());
                sharedSympaList.setHomePage(list.getHomePage());
                sharedSympaList.setSubject(list.getSubject());
            }
        }
    }

    /**
     * Closes a sympa list. Once the list is closed it cannot be reopened or created by this connector
     * @param sympaDriver The driver contains the SympaCore object and its configuration
     * @param listAddress The listName or List address
     * @throws ConnectorException
     */
    @Override
    public void delete(SympaDriver sympaDriver, String listAddress) throws ConnectorException
    {
        SympaCore sympaCore = sympaDriver.getSympaCore();
        if ( sympaCore != null && listAddress != null && listAddress.trim().length() > 0 )
        {
            sympaCore.close(listAddress);
        }
    }

    /**
     * Retrieves all the SympaLists from the server
     * @param sympaDriver The driver contains the SympaCore object and its configuration
     * @param map
     * @return
     * @throws ConnectorException
     */
    @Override
    public List<SharedSympaList> getAll(SympaDriver sympaDriver, Map<String, Object> map) throws ConnectorException 
    {
        List<SharedSympaList> sharedLists = null;
        SympaCore sympaCore = sympaDriver.getSympaCore();
        if ( sympaCore != null )
        {
            List<SympaCoreList> lists = sympaCore.getAll();
            if ( lists != null && lists.size() > 0 )
            {
                SharedSympaList sharedList = null;
                sharedLists = new ArrayList<SharedSympaList>();
                for (SympaCoreList coreList: lists )
                {
                    sharedList = new SharedSympaList();
                    sharedList.setSubject(coreList.getSubject());
                    sharedList.setHomePage(coreList.getHomePage());
                    sharedList.setListAddress(coreList.getListAddress());
                    sharedLists.add(sharedList);
                }
            }
        }
        else
        {
            LOG.warn("Invalid Request. SympaCore unavailable", new Object());
        }
        return null;
    }

    /**
     * Retrieves a Sympa List from the Sympa Server
     * @param sympaDriver The driver contains the SympaCore object and its configuration
     * @param listAddress The list address or list name
     * @param map
     * @return
     * @throws ConnectorException
     */
    @Override
    public SharedSympaList getOne(SympaDriver sympaDriver, String listAddress, Map<String, Object> map) throws ConnectorException
    {
        SharedSympaList sharedList = null;
        SympaCore sympaCore = sympaDriver.getSympaCore();
        if ( sympaCore != null && listAddress != null && listAddress.trim().length() > 0 )
        {
            SympaCoreList coreList = sympaCore.getOne(listAddress);
            if ( coreList != null )
            {
                sharedList = new SharedSympaList();
                sharedList.setSubject(coreList.getSubject());
                sharedList.setHomePage(coreList.getHomePage());
                sharedList.setListAddress(coreList.getListAddress());
            }
        }
        else
        {
            LOG.warn("Invalid Request. SympaCore not available or listAddress is blank", new Object());
        }
        return sharedList;
    }
}
