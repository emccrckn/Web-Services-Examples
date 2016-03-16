import org.json.JSONString;
import org.json.JSONStringer;

public class JsonPerson implements JSONString{


	public String firstName,lastName;
	public int age;
	public float height;

	@Override
	public String toJSONString() {
		String myString = new JSONStringer()
			     .object()
			         .key("name")
			         .value(firstName+" "+lastName)
			         .key("age")
			         .value(age)
			         .key("height")
			         .value(height)
			     .endObject()
			     .toString();
		return myString;
	}

	public static void main(String[] args) {
		JsonPerson person = new JsonPerson();
		person.firstName = "Jimmy";
		person.lastName = "Dean";
		person.age = 56;
		person.height = 5.6f;
		String json = person.toJSONString();
		System.out.println(json);

	}
}
