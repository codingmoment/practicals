package com.devmango.booking.service;

import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.devmango.booking.domain.Booking;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@Service
public class BookingService {

	private final static Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

	private FluxSink<Booking> bookingEmitter;

	private Flux<Booking> bookingFlux;

	private AtomicLong atomicLong = new AtomicLong();

	@PostConstruct
	public void initializeFlux() {
		System.out.println("initializeFlux");
		bookingFlux = Flux.<Booking>create(emitter -> {
			System.out.println("Emitter = " + emitter);
			this.registerEmitter(emitter);
		}).share();
	}

	public Booking bookRoom(Booking booking) {
		LOGGER.info("Booking request received..." + booking.getCustomerName());
		booking.setBookingId(atomicLong.getAndIncrement());
		if (bookingEmitter != null) {
			bookingEmitter.next(booking);
		}
		return booking;
	}

	private void registerEmitter(FluxSink<Booking> bookingEmitter) {
		this.bookingEmitter = bookingEmitter;
	}

	public Flux<Booking> getBookingNotifications() {
		return bookingFlux;
	}
}
