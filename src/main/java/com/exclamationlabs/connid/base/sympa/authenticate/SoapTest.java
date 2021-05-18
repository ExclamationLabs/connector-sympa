package com.exclamationlabs.connid.base.sympa.authenticate;

import com.exclamationlabs.connid.base.sympa.Helper.FileHelper;
import com.exclamationlabs.connid.base.sympa.Helper.XMLHelper;
import com.exclamationlabs.connid.base.sympa.driver.SympaCore;

import java.io.IOException;
import java.util.Map;

public class SoapTest
{
    public static void main(String[] args) throws IOException
    {
        try
        {
            Map<String, String> properties = FileHelper.loadPropertiesFromResourceAsMap("sympa-config.properties");
            properties.put("list.name", "arbitrary");
            String message = SympaCore.buildRequestMessage("message/getInfo.xml", properties);
            System.out.println(message);
            String responseData = SympaCore.getHttpPostResponse(message, properties.get("sympa.domain.url"));
            System.out.println(responseData);
            XMLHelper.scanXML(responseData);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
