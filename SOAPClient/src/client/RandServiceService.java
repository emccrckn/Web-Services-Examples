
package client;

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
@WebServiceClient(name = "RandServiceService", targetNamespace = "http://endpoints/", wsdlLocation = "http://localhost:8889/rs?wsdl")
public class RandServiceService
    extends Service
{

    private final static URL RANDSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException RANDSERVICESERVICE_EXCEPTION;
    private final static QName RANDSERVICESERVICE_QNAME = new QName("http://endpoints/", "RandServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8889/rs?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RANDSERVICESERVICE_WSDL_LOCATION = url;
        RANDSERVICESERVICE_EXCEPTION = e;
    }

    public RandServiceService() {
        super(__getWsdlLocation(), RANDSERVICESERVICE_QNAME);
    }

    public RandServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), RANDSERVICESERVICE_QNAME, features);
    }

    public RandServiceService(URL wsdlLocation) {
        super(wsdlLocation, RANDSERVICESERVICE_QNAME);
    }

    public RandServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RANDSERVICESERVICE_QNAME, features);
    }

    public RandServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RandServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns RandServiceInterface
     */
    @WebEndpoint(name = "RandServicePort")
    public RandServiceInterface getRandServicePort() {
        return super.getPort(new QName("http://endpoints/", "RandServicePort"), RandServiceInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RandServiceInterface
     */
    @WebEndpoint(name = "RandServicePort")
    public RandServiceInterface getRandServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://endpoints/", "RandServicePort"), RandServiceInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RANDSERVICESERVICE_EXCEPTION!= null) {
            throw RANDSERVICESERVICE_EXCEPTION;
        }
        return RANDSERVICESERVICE_WSDL_LOCATION;
    }

}
