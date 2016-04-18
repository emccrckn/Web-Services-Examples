package resolvers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.PortInfo;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class AwsHandlerResolver implements HandlerResolver {
	private String awsSecretKey;
	
	public AwsHandlerResolver(String secretKey){
		this.awsSecretKey = secretKey;
	}
	
	@Override
	public List<Handler> getHandlerChain(PortInfo portInfo) {
		List<Handler> handlerChain = new ArrayList<Handler>();
		QName serviceQName = portInfo.getServiceName();
		if(serviceQName.getLocalPart().equals("AWSECommerceService")) {
			handlerChain.add(new AwsSoapHandler(awsSecretKey));
		}
		return handlerChain;
	}

}

class AwsSoapHandler implements SOAPHandler<SOAPMessageContext> {

	private byte[] secretBytes;
	
	public AwsSoapHandler(String awsSecretKey){
		secretBytes = getBytes(awsSecretKey);
	}
	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext mCtx) {
		Boolean outbound = (Boolean) mCtx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(outbound){
			try{
				SOAPMessage soapMessage = mCtx.getMessage();
				SOAPBody soapBody = soapMessage.getSOAPBody();
				Node firstChild = soapBody.getFirstChild();
				String timestamp = getTimestamp();
				String signature = getSignature(firstChild.getLocalName(), timestamp,secretBytes);
				append(firstChild, "Signature", signature);
				append(firstChild,"Timestamp",timestamp);
				
			}catch(Exception e){
				throw new RuntimeException("SOAPException thrown",e);
			}
		}
		return true;  //keep going down the handler chain.
		
	}

	private String getSignature(String operation, String timestamp, byte[] secretBytes){
		try{
			String toSign = operation + timestamp;
			byte[] toSignBytes = getBytes(toSign);
			Mac signer = Mac.getInstance("HmacSHA256");
			SecretKeySpec keySpec = new SecretKeySpec(secretBytes, "HmacSHA256");
			signer.init(keySpec);
			signer.update(toSignBytes);
			byte[] signBytes = signer.doFinal();
			String signature = new String(Base64.encode(signBytes));
			return signature;
		}catch(Exception e){ throw new RuntimeException(e);}
	}
	
	private String getTimestamp(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		return dateFormat.format(calendar.getTime());
	}

	private void append(Node node, String elementName, String elementText){
		Element element = node.getOwnerDocument().createElement(elementName);
		element.setTextContent(elementText);
		node.appendChild(element);
	}
	
	private byte[] getBytes(String str) {
		try{
			return str.getBytes("UTF-8");
		}catch(Exception e) {throw new RuntimeException(e);}
	}
}
