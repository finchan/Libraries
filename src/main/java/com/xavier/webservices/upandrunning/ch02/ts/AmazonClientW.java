package com.xavier.webservices.upandrunning.ch02.ts;

import com.xavier.webservices.upandrunning.ch02.ts.awsclient.*;

import javax.xml.ws.Holder;
import java.util.List;

/**
 * Created by Xavier on 2017/12/5.
 */
public class AmazonClientW {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java AmazonClientW <acess key> & <secret key>");
            return;
        }
        final String access_key = args[0];

        AWSECommerceService service = new AWSECommerceService();
        AWSECommerceServicePortType port = service.getAWSECommerceServicePort();

        ItemSearchRequest request = new ItemSearchRequest();
        request.setSearchIndex("Books");
        request.setKeywords("quantum 'gravity");

        ItemSearch search = new ItemSearch();
        search.getRequest().add(request);
        search.setAWSAccessKeyId(access_key);

        Holder<OperationRequest> operation_request = null;
        Holder<List<Items>> items = new Holder<List<Items>>();

        port.itemSearch(search.getMarketplaceDomain(),
                search.getAWSAccessKeyId(),
                search.getAssociateTag(),
                search.getXMLEscaping(),
                search.getValidate(),
                search.getShared(),
                search.getRequest(),
                operation_request,
                items);

        Items retval = items.value.get(0);
        List<Item> item_list = retval.getItem();
        for (Item item : item_list) {
            System.out.println(item.getItemAttributes().getTitle());
        }
    }
}
