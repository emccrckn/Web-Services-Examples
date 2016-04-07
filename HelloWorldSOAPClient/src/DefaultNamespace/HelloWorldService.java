/**
 * HelloWorldService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package DefaultNamespace;

public interface HelloWorldService extends javax.xml.rpc.Service {
    public java.lang.String getHelloWorldAddress();

    public DefaultNamespace.HelloWorld getHelloWorld() throws javax.xml.rpc.ServiceException;

    public DefaultNamespace.HelloWorld getHelloWorld(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
