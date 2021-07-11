package com.demo.bank.config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import feign.Response;

public class FeignDecoder extends SpringDecoder {
  private final ObjectMapper mapper;

  public FeignDecoder(ObjectMapper mapper, ObjectFactory<HttpMessageConverters> messageConverters) {
    super(messageConverters);
    this.mapper = mapper;
  }

  /**
   * Decodes a response to an instance of an object
   *
   * @param response the received response
   * @param type the type of the response
   * @return A decoded response object
   * @throws IOException reason of failure due to IO exception
   * @throws FeignException reason of failure due to Feign implementation
   */
  @Override
  public Object decode(final Response response, Type type) throws IOException, FeignException {
    System.out.println("FeignDecoder.decode() called");
    Response convertResponseBody = convertResponseBody(response, type);
    return super.decode(convertResponseBody, type);
  }

  private Response convertResponseBody(Response response, Type type) throws IOException {
    if (response.status() == 200) {

      return Response.builder().body(generateJsonBody(response, type), Charset.forName("UTF-8")).headers(response.headers()).reason(response.reason()).request(response.request())
        .status(response.status()).build();
    }
    return response;
  }

  private String generateJsonBody(Response response, Type type) throws IOException {
    // Check if the response has a generic type argument
    if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
      Type generic = ((ParameterizedType) type).getActualTypeArguments()[0];

      // Check if the response is a Collection and convert it
      if (Collection.class.isAssignableFrom((Class) ((ParameterizedType) type).getRawType())) {
        Collection<Map<String, Object>> bodyList = mapper.readValue(response.body().asInputStream(), Collection.class);
        for (Map<String, Object> bodyMap : bodyList) {
          handlePropertyAnnotation((Class) generic, bodyMap);
        }
        return mapper.writeValueAsString(bodyList);
      }
    }
    // If nothing matches, just convert it like a normal object
    Map<String, Object> bodyMap = mapper.readValue(response.body().asInputStream(), Map.class);
    handlePropertyAnnotation((Class) type, bodyMap);
    return mapper.writeValueAsString(bodyMap);
  }

  private void handlePropertyAnnotation(Class type, Map<String, Object> body) {
    Class<?> current = type;
    while (current != null) {
      List<Field> fields = Arrays.stream(current.getDeclaredFields()).filter(field -> field.isAnnotationPresent(FeignProperty.class)).collect(Collectors.toList());
      for (Field field : fields) {
        String fieldName = field.getName();
        if (field.isAnnotationPresent(JsonProperty.class)) {
          JsonProperty propertyAnnotation = field.getDeclaredAnnotation(JsonProperty.class);
          propertyAnnotation.value();
          fieldName = propertyAnnotation.value();
        }
        FeignProperty feignProperty = field.getDeclaredAnnotation(FeignProperty.class);
        feignProperty.value();
        body.put(fieldName, body.get(feignProperty.value()));
        body.remove(feignProperty.value());
      }
      current = current.getSuperclass();
    }
  }

}
