package com.exclamationlabs.connid.base.sympa.authenticate;

import com.exclamationlabs.connid.base.sympa.Helper.FileHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.text.StringSubstitutor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SoapTest
{
    public String myVal;

    public static void main(String[] args) throws IOException
    {
        CloseableHttpClient client = HttpClients.createDefault();
        try
        {
            Properties properties = FileHelper.loadPropertiesFromResource("sympa-config.properties");
            String message = FileHelper.loadResourceToString("message/getLists.xml", StandardCharsets.UTF_8);
            StringSubstitutor substituror = new StringSubstitutor((Map<String, String>)((Map)properties));
            message = substituror.replace(message);
            System.out.println(message);
            HttpPost post = new HttpPost(properties.getProperty("sympa.domain.url"));
            StringEntity data = new StringEntity(message);
            post.setEntity(data);
            post.setHeader("Content-Type", "text/xml;charset=UTF-8");
            CloseableHttpResponse response = client.execute(post);
            //response.getEntity();
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if ( client != null )
            {
                client.close();
            }
        }
    }
}
