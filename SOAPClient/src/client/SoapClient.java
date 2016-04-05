package client;


public class SoapClient {

	public static void main(String[] args) {
		RandServiceService randomService = new RandServiceService();
		RandServiceInterface randInterface = randomService.getRandServicePort();
		System.out.println(randInterface.next1());
		System.out.println(randInterface.next1());
		System.out.println(randInterface.next1());

	}

}
