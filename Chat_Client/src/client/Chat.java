package client;
import java.io.*;

import javax.jms.*;
import javax.naming.InitialContext;

public class Chat implements javax.jms.MessageListener{

	private TopicSession pubSession;
	private TopicPublisher publisher;
	private TopicConnection connection;
	private String username;
	
	public Chat(String topicFactory, String topicName, String username) throws Exception{
		
		//Obtain a JNDI connection using the jndi.properties file
		InitialContext ctx = new InitialContext();
		
		//Look up a JMS connection factory and create the connection
		TopicConnectionFactory conFactory = (TopicConnectionFactory)ctx.lookup(topicFactory);
		TopicConnection connection = conFactory.createTopicConnection();
		
		//Create two JMS session objects
		TopicSession pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//Look up the JMS topic
		Topic chatTopic = (Topic)ctx.lookup(topicName);
		
		//Create the subscriber/publisher
		TopicPublisher publisher = pubSession.createPublisher(chatTopic);
		TopicSubscriber subscriber = subSession.createSubscriber(chatTopic, null, true);
		
		//Set a JMS listener
		subscriber.setMessageListener(this);
		
		//Initialize chat app
		this.connection = connection;
		this.publisher = publisher;
		this.pubSession = pubSession;
		this.username = username;
		
		connection.start();
	}
	
	/* Receive a message from Topic Subscriber */
	public void onMessage(Message message){
		try{
			TextMessage textMessage = (TextMessage) message;
			System.out.println(textMessage.getText());
		}catch(JMSException jmse) {jmse.printStackTrace();}
		
	}
	
	//Create and send message using publisher
	protected void writeMessage(String text) throws JMSException {
		TextMessage message = pubSession.createTextMessage();
		message.setText(text);
		publisher.publish(message);
	}
	
	//Close the connection
	public void close() throws JMSException{
		connection.close();
	}
	
	public static void main(String [] args){
		try{
			if(args.length != 3){
				System.out.println("Factory, Topic, or username missing");
			}
			
			Chat chat = new Chat(args[0],args[1],args[2]);
			BufferedReader commandLine = new java.io.BufferedReader(new InputStreamReader(System.in));
			
			while(true){
				String s = commandLine.readLine();
				if(s.equals("exit")){
					chat.close();
					System.exit(0);
				}else{
					chat.writeMessage(s);
				}
			}
		}catch(Exception e){e.printStackTrace();}
	}
}
