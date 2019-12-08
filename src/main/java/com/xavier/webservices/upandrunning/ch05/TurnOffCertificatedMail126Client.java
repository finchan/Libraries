package com.xavier.webservices.upandrunning.ch05;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * Created by Xavier on 2018/3/8.
 */
public class TurnOffCertificatedMail126Client {
    private static final String url_s = "https://126.com";
    public static void main(String[] args) {
        new TurnOffCertificatedMail126Client().do_it();
    }

    private void do_it() {
        try {
            SSLContext ssl_ctx = SSLContext.getInstance("SSL");
            TrustManager[] trust_mgr = get_trust_mgr();
            ssl_ctx.init(null, trust_mgr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private TrustManager[] get_trust_mgr() {
        TrustManager[] certs = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] c, String t) {};
                    public void checkServerTrusted(X509Certificate[] c, String t) {};
                }
        };
        return certs;
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
