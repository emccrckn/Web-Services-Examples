import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
public class ServerResponse {
		public String code;
		public String message;
}
