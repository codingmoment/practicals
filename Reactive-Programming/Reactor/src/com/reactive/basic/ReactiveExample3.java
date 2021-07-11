package com.reactive.basic;

import java.util.ArrayList;
import java.util.List;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;

public class ReactiveExample3 {
  public static void main(String[] args) {
    List<String> weekDays = new ArrayList<>();

    Flux.just("Sunday", "Monday", "Tuesday", "Wednesday", "Friday", "Saturday")
      .log()
      .subscribe(new Subscriber<String>() {
        private Subscription subscription;
        private int counter;

        @Override
        public void onSubscribe(Subscription s) {
          this.subscription = s;
          s.request(2);
        }

        @Override
        public void onNext(String i) {
          weekDays.add(i);
          counter++;
          if (counter % 2 == 0) {
            subscription.request(2);
          }
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
