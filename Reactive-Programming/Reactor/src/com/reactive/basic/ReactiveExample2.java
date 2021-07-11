package com.reactive.basic;

import java.util.ArrayList;
import java.util.List;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;

public class ReactiveExample2 {
  public static void main(String[] args) {
    List<String> weekDays = new ArrayList<>();

    Flux.just("Sunday", "Monday", "Tuesday", "Wednesday", "Friday", "Saturday")
      .log()
      .subscribe(new Subscriber<String>() {
        @Override
        public void onSubscribe(Subscription s) {
          s.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(String i) {
          weekDays.add(i);
        }

        @Override
        public void onComplete() {
        }

        @Override
        public void onError(Throwable t) {
        }
      });
  }
}
