package service;

import java.util.List;

import javax.xml.ws.Holder;

import amazon.AWSECommerceService;
import amazon.AWSECommerceServicePortType;
import amazon.Item;
import amazon.ItemSearch;
import amazon.ItemSearchRequest;
import amazon.Items;
import amazon.OperationRequest;
import resolvers.AwsHandlerResolver;

public class AmazonClient {

	public static void main(String[] args) {
		if(args.length < 2){
			System.err.println("java AmazonClient <accessId> <secretKey>");
			return;
		}
		final String accessId = args[0];
		final String secretKey = args[1];
		
		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver(secretKey));
		AWSECommerceServicePortType port = service.getAWSECommerceServicePort();
		ItemSearchRequest request = new ItemSearchRequest();
		request.setSearchIndex("Books");
		request.setKeywords("Austen");
		ItemSearch search = new ItemSearch();
		search.getRequest().add(request);
		search.setAWSAccessKeyId(accessId);
		search.setAssociateTag("kalin");
		Holder<OperationRequest> operationRequest = null;
		Holder<List<Items>> items = new Holder<List<Items>>();
		port.itemSearch(search.getMarketplaceDomain(),
				search.getAWSAccessKeyId(), 
				search.getAssociateTag(), 
				search.getXMLEscaping(), 
				search.getValidate(), 
				search.getShared(), 
				search.getRequest(), 
				operationRequest, 
				items);
		
		Items retval = items.value.get(0);
		int i = 1;
		List<Item> itemList = retval.getItem();
		for(Item item : itemList){
			System.out.println(String.format("%2d: ", i++) + item.getItemAttributes().getTitle());
		}
		
	}

}
