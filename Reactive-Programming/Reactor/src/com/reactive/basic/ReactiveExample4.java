package com.reactive.basic;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ReactiveExample4 {
  public static void main(String[] args) {

    ConnectableFlux<Object> connectableFlux = Flux.create(fluxSink -> {
      while (true) {
        fluxSink.next(System.currentTimeMillis());
      }
    }).publish();
    
    connectableFlux.subscribe(System.out::println);
    connectableFlux.subscribe(System.out::println);
    
    connectableFlux.connect();
  }
}
