package com.reactive.basic;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;

public class ReactiveExample1 {
	public static void main(String[] args) {

		List<String> weekDays = new ArrayList<>();

		Flux.just("Sunday", "Monday", "Tuesday", "Wednesday", "Friday", "Saturday")
			.log()
			.subscribe(weekDays::add);
		
		System.out.println(weekDays);
	}
}
