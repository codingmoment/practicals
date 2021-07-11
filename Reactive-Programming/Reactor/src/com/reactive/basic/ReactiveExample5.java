package com.reactive.basic;

import java.time.Duration;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ReactiveExample5 {
  public static void main(String[] args) {

    ConnectableFlux<Object> connectableFlux = Flux.create(fluxSink -> {
      while (true) {
        fluxSink.next(System.currentTimeMillis());
      }
    }).sample(Duration.ofSeconds(2))
      .publish();
    
    connectableFlux.subscribe(System.out::println);
    connectableFlux.subscribe(System.out::println);
    
    connectableFlux.connect();
  }
}
