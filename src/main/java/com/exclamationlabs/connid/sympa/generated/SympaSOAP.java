
package com.exclamationlabs.connid.sympa.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SympaSOAP", targetNamespace = "https://lists.dev.at.internet2.edu/sympa/wsdl", wsdlLocation = "file:/Users/sfox/projects/connector-sympa/src/main/resources/sympa-wsdl.xml")
public class SympaSOAP
    extends Service
{

    private final static URL SYMPASOAP_WSDL_LOCATION;
    private final static WebServiceException SYMPASOAP_EXCEPTION;
    private final static QName SYMPASOAP_QNAME = new QName("https://lists.dev.at.internet2.edu/sympa/wsdl", "SympaSOAP");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/Users/sfox/projects/connector-sympa/src/main/resources/sympa-wsdl.xml");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SYMPASOAP_WSDL_LOCATION = url;
        SYMPASOAP_EXCEPTION = e;
    }

    public SympaSOAP() {
        super(__getWsdlLocation(), SYMPASOAP_QNAME);
    }

    public SympaSOAP(WebServiceFeature... features) {
        super(__getWsdlLocation(), SYMPASOAP_QNAME, features);
    }

    public SympaSOAP(URL wsdlLocation) {
        super(wsdlLocation, SYMPASOAP_QNAME);
    }

    public SympaSOAP(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SYMPASOAP_QNAME, features);
    }

    public SympaSOAP(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SympaSOAP(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SympaPort
     */
    @WebEndpoint(name = "SympaPort")
    public SympaPort getSympaPort() {
        return super.getPort(new QName("https://lists.dev.at.internet2.edu/sympa/wsdl", "SympaPort"), SympaPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SympaPort
     */
    @WebEndpoint(name = "SympaPort")
    public SympaPort getSympaPort(WebServiceFeature... features) {
        return super.getPort(new QName("https://lists.dev.at.internet2.edu/sympa/wsdl", "SympaPort"), SympaPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SYMPASOAP_EXCEPTION!= null) {
            throw SYMPASOAP_EXCEPTION;
        }
        return SYMPASOAP_WSDL_LOCATION;
    }

}
