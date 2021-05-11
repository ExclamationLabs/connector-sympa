package com.exclamationlabs.connid.sympa.authenticate;

import com.exclamationlabs.connid.sympa.generated.ArrayOfString;
import com.exclamationlabs.connid.sympa.generated.SympaPort;
import com.exclamationlabs.connid.sympa.generated.SympaSOAP;

public class SoapTest
{
    public String myVal;

    public static void main(String[] args)
    {
        try
        {
            SympaSOAP client = new SympaSOAP();
            SympaPort port = client.getSympaPort();
            String cookie = port.login("sfox@exclamationlabs.com", "Secret1234!");
            ArrayOfString strings = port.authenticateRemoteAppAndRun("soapTest", "cQe{WYL_Q4T7[;_$", "USER_EMAIL=sfox@exclamationlabs.com", "lists", new ArrayOfString());
            System.out.println(cookie);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public String getMyVal()
    {
        return myVal;
    }

    public void setMyVal(String myVal)
    {
        this.myVal = myVal;
    }
}
