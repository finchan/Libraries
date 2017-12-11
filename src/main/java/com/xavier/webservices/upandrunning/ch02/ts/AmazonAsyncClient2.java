package com.xavier.webservices.upandrunning.ch02.ts;

import com.xavier.webservices.upandrunning.ch02.ts.unwrappedasynchronousawsclient.*;
import javax.xml.ws.Response;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Xavier on 2017/12/11.
 */
public class AmazonAsyncClient2 {
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

        ItemSearch item_search = new ItemSearch();
        item_search.getRequest().add(request);
        item_search.setAWSAccessKeyId(access_key);

        Response<ItemSearchResponse> res = port.itemSearchAsync(item_search);
        try {
            ItemSearchResponse response = res.get();
            List<Items> item_list = response.getItems();
            for (Items next : item_list)
                for (Item item : next.getItem())
                    System.out.println(item.getItemAttributes().getTitle());
        }
        catch(InterruptedException e) { System.err.println(e); }
        catch(ExecutionException e) { System.err.println(e); }
    }
}
