package com.exclamationlabs.connid.base.sympa.authenticate;

import com.exclamationlabs.connid.base.sympa.Helper.FileHelper;
import com.exclamationlabs.connid.base.sympa.driver.SympaCore;
import org.apache.commons.text.StringSubstitutor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class SympaTest
{
    public String myVal;

    public static void main(String[] args) throws IOException
    {
        try
        {
            SympaCore sympa = new SympaCore();
            sympa.getAll();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
     }
}
