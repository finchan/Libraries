package com.xavier.webservices.upandrunning.ch03.fib;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xavier on 2017/12/17.
 */

@WebService (targetNamespace = "http://ch03.fib")
@SOAPBinding (style = SOAPBinding.Style.DOCUMENT,
                            use = SOAPBinding.Use.LITERAL,
                            parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class RabbitCounter {
    private Map<Integer, Integer> cache = Collections.synchronizedMap(new HashMap<Integer, Integer>());
    @WebMethod
    public int countRabbits(int n) throws FibException {
        if (n < 0) throw new FibException("Neg. arg. not allowed.", n + "<0");
        if (n < 2) return n;

        if(cache.containsKey(n)) return cache.get(n);
        if(cache.containsKey(n-1) &&
                cache.containsKey(n-2)) {
            cache.put(n, cache.get(n-1) + cache.get(n-2));
            return cache.get(n);
        }
        int fib=1, prev=0;
        for(int i=2;i<=n;i++) {
            int temp = fib;
            fib += prev;
            prev = temp;
        }
        cache.put(n, fib);
        return fib;
    }
}
