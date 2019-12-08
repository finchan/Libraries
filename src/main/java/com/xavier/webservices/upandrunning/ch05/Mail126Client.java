package com.xavier.webservices.upandrunning.ch05;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

/**
 * Created by Xavier on 2018/2/27.
 */
public class Mail126Client {
    private static final String url_s = "https://126.com";
    public static void main(String[] args) {
        new Mail126Client().do_it();
    }

    private void do_it() {
        try {
            URL url = new URL(url_s);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            dump_features(conn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dump_features(HttpsURLConnection conn) {
        try {
            p("Status code: " + conn.getResponseCode());
            p("Cipher suite: " + conn.getCipherSuite());
            Certificate[] certs = conn.getServerCertificates();
            for(Certificate cert: certs) {
                p("\t Cert. type: " + cert.getType());
                p("\t Hash code: " + cert.hashCode());
                p("\t Algorithm: " + cert.getPublicKey().getAlgorithm());
                p("\t Format: " + cert.getPublicKey().getFormat());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void p(String s) {
        System.out.println(s);
    }
}
