package com.demo.httpcaching.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/media")
public class HttpCachingController {

  /**
   * @formatter:off
   * This controller allows you to download a file.
   * 
   * You can download the file using http://localhost:8080/media/download/employees
   * @formatter:on
   */
  @RequestMapping(method = RequestMethod.GET, path = "/download/employees")
  public ResponseEntity<InputStreamResource> getEmployeesFile() throws IOException {
    Resource fileResource = new ClassPathResource("employees.json");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Through cache-control header, it directs browser to cache the file in local storage.
    CacheControl cacheControl = CacheControl.maxAge(60, TimeUnit.SECONDS).noTransform();
    headers.setCacheControl(cacheControl);

    InputStreamResource resource = new InputStreamResource(fileResource.getInputStream());
    return new ResponseEntity<InputStreamResource>(resource, headers, HttpStatus.OK);
  }

  /**
   * @formatter:off
   * This controller allows you to download a file.
   * The cache-control settings for this URL are set in WebConfig.
   * 
   * You can download the file using http://localhost:8080/media/files/departments
   * @formatter:on
   */
  @RequestMapping(method = RequestMethod.GET, path = "/files/departments")
  public ResponseEntity<InputStreamResource> getDepartmentsFile() throws IOException {
    Resource fileResource = new ClassPathResource("departments.json");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    InputStreamResource resource = new InputStreamResource(fileResource.getInputStream());
    return new ResponseEntity<InputStreamResource>(resource, headers, HttpStatus.OK);
  }

  /**
   * @formatter:off
   * This controller allows you to download a file.
   * The cache-control settings for this URL are set in WebConfig.
   * 
   * You can download the file using http://localhost:8080/media/files/locations
   * @formatter:on
   */
  @RequestMapping(method = RequestMethod.GET, path = "/files/locations")
  public ResponseEntity<InputStreamResource> getLocationsFile() throws IOException {
    Resource fileResource = new ClassPathResource("locations.json");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Overriding the cache-control to set no cache.
    CacheControl cacheControl = CacheControl.noCache();
    headers.setCacheControl(cacheControl);

    InputStreamResource resource = new InputStreamResource(fileResource.getInputStream());
    return new ResponseEntity<InputStreamResource>(resource, headers, HttpStatus.OK);
  }

  /**
   * @formatter:off
   * This controller allows you to download a file.
   * The cache-control settings for this URL are set in WebConfig.
   * 
   * You can download the file using http://localhost:8080/media/files/products
   * @formatter:on
   */
  @RequestMapping(method = RequestMethod.GET, path = "/files/products")
  public ResponseEntity<InputStreamResource> getProductsFile(WebRequest webRequest) throws IOException {
    File file = new File("D://products.json");
    long lastModifiedTimestamp = file.lastModified();

    if (webRequest.checkNotModified(lastModifiedTimestamp)) {
      return ResponseEntity.status(304).build();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
    return new ResponseEntity<InputStreamResource>(resource, headers, HttpStatus.OK);
  }

}
