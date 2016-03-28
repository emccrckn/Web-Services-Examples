import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/")
public class RandomMessages {

	private String[] words = {
			"What can be shown cannot be said.",
			"If a lion could talk, we could not understand him.",
			"REST is a stateless client-server ",
			"Servlets are the HTTP workhorse of Java EE."
	};
	
	public RandomMessages(){}
	
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/xml")
	public JAXBElement<RandomMessage> getXml() {
		return toXml(createRandomMessage());
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/xmlResponse")
	public Response getXmlResponse() {
		return Response.ok(createRandomMessage(),"application/xml").build();
		//return toXml(createRandomMessage());
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/xml/{id: [0-9]+}")
	public Response getSpecificXml(@PathParam("id") int id) {
		try{
			RandomMessage r = createMessage(id);
			return Response.ok(r,"application/xml").build();
		}catch(Exception e){
			return Response.status(500).build();
		}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/xmllist")
	public JAXBElement<RandomMessageList> getXmlList() {
		RandomMessageList messages = new RandomMessageList();
		RandomMessage m1 = createRandomMessage();
		RandomMessage m2 = createRandomMessage();
		List<RandomMessage> messageList = new ArrayList<RandomMessage>();
		messageList.add(m1);
		messageList.add(m2);
		messages.setMessages(messageList);
		return new JAXBElement<RandomMessageList>(new QName("randomMessageList"), RandomMessageList.class, messages);

	}
	
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/xmlResponse")
	public Response addRandomMessage(RandomMessage message) {
		
		return Response.ok().build();
		//return toXml(createRandomMessage());
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/json")
	public Response getJson(){
		RandomMessage response = createRandomMessage();
		String json = "{'Error':'Error'}";
		try {
			json = new ObjectMapper().writeValueAsString(response);
		} catch (Exception e) {
			return Response.status(500).build();
		}
		return Response.ok(json,"application/json").build();
	}
	
	private RandomMessage createRandomMessage(){
		RandomMessage message = new RandomMessage();
		message.setWords(words[new Random().nextInt(words.length)]);
		return message;
	}
	
	private RandomMessage createMessage(int index){
		RandomMessage message = new RandomMessage();
		message.setWords(words[index]);
		return message;
	}
	
	private JAXBElement<RandomMessage> toXml(RandomMessage message){
		return new JAXBElement<RandomMessage>(new QName("randomMessage"), RandomMessage.class, message);
	}
}
