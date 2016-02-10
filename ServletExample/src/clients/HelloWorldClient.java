package clients;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HelloWorldClient {

	public static void main(String args[]) {

		//Read from Servlet
		try {
			System.out.println("Making GET call");
			String request        = "http://localhost:8080/ServletExample/Hello";
			URL    url            = new URL( request );
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
			
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "GET" );
			conn.setUseCaches( false );
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String next_record = null;
			while ((next_record = reader.readLine()) != null) {
				System.out.println(next_record);
			}
		} catch (IOException e) {
			throw new RuntimeException("Please try again. \n" + e);
		}
		
		
		try {
			System.out.println("Making POST call");
			// Parse the URL
			String urlParameters  = "param1=a&param2=b&param3=c";
			byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
			int    postDataLength = postData.length;
			String request        = "http://localhost:8080/ServletExample/Hello";
			URL    url            = new URL( request );
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();   
			
			conn.setDoOutput( true );
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "text/html"); 
			conn.setRequestProperty( "charset", "utf-8");
			conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			conn.setUseCaches( false );
			try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
			   wr.write( postData );
			}
			

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String next_record = null;
			while ((next_record = reader.readLine()) != null) {
				System.out.println(next_record);
			}
		} catch (IOException e) {
			throw new RuntimeException("Please try again. \n" + e);
		}
	}
}
