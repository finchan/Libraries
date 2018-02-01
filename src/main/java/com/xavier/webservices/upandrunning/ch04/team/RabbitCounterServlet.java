package com.xavier.webservices.upandrunning.ch04.team;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by Xavier on 2018/2/1.
 */
public class RabbitCounterServlet extends HttpServlet {
    private Map<Integer, Integer> cache;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String num = req.getParameter("num");
        if(num == null) {
            Collection<Integer> fibs = cache.values();
            send_typed_response(req, resp, fibs);
        }else {
            Integer key = Integer.parseInt(num.trim());
            Integer fib = cache.get(key);
            if (fib == null) fib = -1;
            send_typed_response(req, resp, fib);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String nums = req.getParameter("nums");
        if (nums == null)
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        // Extract the integers from a string such as: "[1, 2, 3]"
        nums = nums.replace('[', '\0');
        nums = nums.replace(']', '\0');
        String[ ] parts = nums.split(", ");
        List<Integer> list = new ArrayList<Integer>();
        for (String next : parts) {
            int n = Integer.parseInt(next.trim());
            cache.put(n, countRabbits(n));
            list.add(cache.get(n));
        }
        send_typed_response(req, resp, list + " added.");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String key = req.getParameter("num");
        // Only one Fibonacci number may be deleted at a time.
        if (key == null)
            throw new HTTPException(HttpServletResponse.SC_BAD_REQUEST);
        int n = Integer.parseInt(key.trim());
        cache.remove(n);
        send_typed_response(req, resp, n + " deleted.");
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    public void init() throws ServletException {
        cache = Collections.synchronizedMap(new HashMap<Integer, Integer>());
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) {
        throw new HTTPException(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    private void send_typed_response(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Object data) {
        String desired_type = request.getHeader("accept");
        // If client requests plain text or HTML, send it; else XML.
        if (desired_type.contains("text/plain"))
            send_plain(response, data);
        else if (desired_type.contains("text/html"))
            send_html(response, data);
        else
            send_xml(response, data);
    }

    // For simplicity, the data are stringified and then XML encoded.
    private void send_xml(HttpServletResponse response, Object data) {
        try {
            XMLEncoder enc = new XMLEncoder(response.getOutputStream());
            enc.writeObject(data.toString());
            enc.close();
        }
        catch(IOException e) {
            throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void send_html(HttpServletResponse response, Object data) {
        String html_start =
                "<html><head><title>send_html response</title></head><body><div>";
        String html_end = "</div></body></html>";
        String html_doc = html_start + data.toString() + html_end;
        send_plain(response, html_doc);
    }

    private void send_plain(HttpServletResponse response, Object data) {
        try {
            OutputStream out = response.getOutputStream();
            out.write(data.toString().getBytes());
            out.flush();
        }
        catch(IOException e) {
            throw new HTTPException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private int countRabbits(int n) {
        if (n < 0) {
            throw new HTTPException(403);
        }
        // Easy cases.
        if (n < 2) {
            return n;
        }
        // Return cached value if present.
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        if (cache.containsKey(n - 1) && cache.containsKey(n - 2)) {
            cache.put(n, cache.get(n - 1) + cache.get(n - 2));
            return cache.get(n);
        }
        // Otherwise, compute from scratch, cache, and return.
        int fib = 1, prev = 0;
        for (int i = 2; i <= n; i++) {
            int temp = fib;
            fib += prev;
            prev = temp;
        }
        cache.put(n, fib);
        return fib;
    }
}
