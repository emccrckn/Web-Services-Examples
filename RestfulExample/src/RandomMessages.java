import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/json")
	public String getJson(){
		RandomMessage response = createRandomMessage();
		String json = "{'Error':'Error'}";
		try {
			json = new ObjectMapper().writeValueAsString(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	private RandomMessage createRandomMessage(){
		RandomMessage message = new RandomMessage();
		message.setWords(words[new Random().nextInt(words.length)]);
		return message;
	}
	
	private JAXBElement<RandomMessage> toXml(RandomMessage message){
		return new JAXBElement<RandomMessage>(new QName("randomMessage"), RandomMessage.class, message);
	}
}
