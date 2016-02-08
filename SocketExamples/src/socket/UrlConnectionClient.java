package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionClient {

	public static void main(String args[]) {
		if (args.length < 1) {
			System.err.println("Usage: SimpleHttpClient <url>");
			return;
		}

		try {
			// Parse the URL
			URL url = new URL(args[0]);
			URLConnection sock = url.openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String next_record = null;
			while ((next_record = reader.readLine()) != null) {
				System.out.println(next_record);
			}
			reader.close();
		} catch (IOException e) {
			throw new RuntimeException("Please try again. \n" + e);
		}
	}
}
