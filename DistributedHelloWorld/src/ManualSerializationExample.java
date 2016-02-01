import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ManualSerializationExample {

	public int a;
	public int b;
	
	public static void main(String[] args) {
		ManualSerializationExample example = new ManualSerializationExample();
		example.a = 6;
		example.b = 9;
		byte[] bytes = ManualSerializationExample.toBinary(example);
		try {
			FileOutputStream fOut = new FileOutputStream("test.dat");
			fOut.write(bytes);
			fOut.close();
			System.out.println("Wrote object to file");
			
			
			//Now read the file
			FileInputStream fIn = new FileInputStream("test.dat");
			byte[] inBytes = new byte[bytes.length];
			fIn.read(inBytes);
			fIn.close();
			System.out.println("Done reading file");
			example = ManualSerializationExample.fromBinary(inBytes);
			System.out.println("a:"+example.a+" b:"+example.b);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static byte[] toBinary(ManualSerializationExample example){
		ByteBuffer dbuf = ByteBuffer.allocate(8); //two ints at 32bits each
		dbuf.putInt(example.a);
		dbuf.putInt(example.b);
		return dbuf.array();
		
	}
	
	public static ManualSerializationExample fromBinary(byte[] bytes){
		ByteBuffer wrapped = ByteBuffer.wrap(bytes); // big-endian by default
		ManualSerializationExample example = new ManualSerializationExample();
		example.a = wrapped.getInt();
		example.b = wrapped.getInt();
		return example;
	}
}
