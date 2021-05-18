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
            List<SympaCoreList> lists = sympa.getAll();
            for (SympaCoreList listItem : lists)
            {
                System.out.println(listItem);
            }
            SympaCoreList aList = sympa.getOne("ethantest");
            System.out.println(aList);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
