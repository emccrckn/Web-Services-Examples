import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "randomMessageList")
public class RandomMessageList {

	@XmlElementWrapper(name="randomMessage")
	private List<RandomMessage> messages;
	private int messageCount = 0;
	
	public List<RandomMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<RandomMessage> messages) {
		this.messages = messages;
		this.setMessageCount(messages.size());
	}
	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	
	
}
