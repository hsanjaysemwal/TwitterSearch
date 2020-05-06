package com.java.rest.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        // As the tls/ssl communication was not happening correctly via java code, hence added cde to trust all certificates to bypass tls
        SSLUtilities.trustAllHttpsCertificates();
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
