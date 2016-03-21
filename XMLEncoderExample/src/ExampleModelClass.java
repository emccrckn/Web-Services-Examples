import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import org.json.JSONObject;
import org.json.XML;



public class ExampleModelClass implements Serializable{

	private String field = "dummy value";
	private int number = 5;
	private boolean isExact = false;
	private float preciseNumber = 6.4443f;
	
	public ExampleModelClass(){
		
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isExact() {
		return isExact;
	}
	public void setExact(boolean isExact) {
		this.isExact = isExact;
	}
	public float getPreciseNumber() {
		return preciseNumber;
	}
	public void setPreciseNumber(float preciseNumber) {
		this.preciseNumber = preciseNumber;
	}

	public int someMethod(String someParam){
		return 5;
	}
	public static void main(String[] args){
		
		ExampleModelClass myObject = new ExampleModelClass();
		myObject.setField("Test");
		myObject.setNumber(56);
		myObject.setExact(true);
		myObject.setPreciseNumber(134.234f);
		
		XMLEncoder e;
		try {
			e = new XMLEncoder(
			        new BufferedOutputStream(
			            new FileOutputStream("Test.xml")));
			e.writeObject(myObject);
			
			e.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		//Now read back the object from xml
		XMLDecoder decoder;
		try {
			decoder = new XMLDecoder(
			        new BufferedInputStream(
			            new FileInputStream("Test.xml")));
			myObject = (ExampleModelClass)decoder.readObject();
			System.out.println(myObject.getNumber());
			decoder.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		//Convert xml to json
		String xml = null;
		try{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			XMLEncoder encoder = new XMLEncoder(out);
			encoder.writeObject(myObject);
			encoder.close();
			
			xml = out.toString();
			JSONObject jobt = XML.toJSONObject(xml);
			BufferedOutputStream bout = new BufferedOutputStream(
		            new FileOutputStream("Test.json"));
			PrintWriter writer = new PrintWriter(bout);
			writer.write(jobt.toString());
			writer.close();
		}catch(Exception e2){
			
		}
	}
}
