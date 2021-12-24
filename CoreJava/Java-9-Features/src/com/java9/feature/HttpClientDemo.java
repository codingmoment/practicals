package com.java9.feature;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientDemo {

  public static void main(String[] args) throws Exception {
    HttpRequest httpRequest = HttpRequest.newBuilder()
                                .uri(new URI("https://postman-echo.com/get"))
                                .GET()
                                .build();
    HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                                          .send(httpRequest, HttpResponse.BodyHandlers.ofString());
    
    System.out.println("Body => " + httpResponse.body());
    System.out.println("Response Code => " + httpResponse.statusCode());
    System.out.println("Response Headers => " + httpResponse.headers());
  }
}
