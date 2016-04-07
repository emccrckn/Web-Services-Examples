import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import asyncClient.Next1Response;
import asyncClient.RandServiceInterface;
import asyncClient.RandServiceService;

public class SoapClient {

	public static void main(String[] args) {
		RandServiceService randomService = new RandServiceService();
		RandServiceInterface randInterface = randomService.getRandServicePort();
		
		System.out.println(randInterface.next1());
		System.out.println(randInterface.next1());
		System.out.println(randInterface.next1());
		
		randInterface.next1Async(new MyHandler());
		try{
			Thread.sleep(5000);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Exiting main method");
	}

	static class MyHandler implements AsyncHandler<Next1Response>{
		public void handleResponse(Response<Next1Response> future){
			try{
				Next1Response response = future.get();
				System.out.println("Async:" + response.getReturn());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
