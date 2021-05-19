package com.exclamationlabs.connid.base.sympa.authenticate;

import com.exclamationlabs.connid.base.sympa.driver.SympaCore;
import com.exclamationlabs.connid.base.sympa.model.SympaCoreList;

import java.io.IOException;
import java.util.List;

public class SympaTest
{
    public String myVal;



    public static void main(String[] args) throws IOException
    {
        try
        {
            SympaCore sympa = new SympaCore();
            // Get All Test
            List<SympaCoreList> lists = sympa.getAll();
            for (SympaCoreList listItem : lists)
            {
                System.out.println(listItem);
            }
            // Get Unknown List Test
            SympaCoreList aList = sympa.getOne("arbitrary");
            if ( aList != null )
            {
                System.out.println(aList);
            }
            // Close List Test
            sympa.close("stephenlist");
            // Create List Test should fail since already closed
            sympa.create("stephenlist", "About Sympa testing", "icp-public", "Lists that closes and opens", "computing");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
