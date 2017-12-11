package com.xavier.webservices.upandrunning.ch02.ts;

import com.xavier.webservices.upandrunning.ch02.ts.unwrappedawsclient.*;

import java.util.List;

/**
 * Created by Xavier on 2017/12/11.
 */
public class UnwrappedAmazonClientW {
    public static void main(String[] args) {
        final String access_key = args[0];

        AWSECommerceService service = new AWSECommerceService();
        AWSECommerceServicePortType port = service.getAWSECommerceServicePort();

        ItemSearchRequest request = new ItemSearchRequest();

        request.setSearchIndex("Books");
        request.setKeywords("quantum gravity");
        ItemSearch item_search = new ItemSearch();
        item_search.setAWSAccessKeyId(access_key);
        item_search.getRequest().add(request);

        ItemSearchResponse response = port.itemSearch(item_search);

        List<Items> item_list = response.getItems();
        for(Items next: item_list) {
            for (Item item: next.getItem())
                System.out.println(item.getItemAttributes().getTitle());
        }
    }
}
