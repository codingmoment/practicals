package com.devmango.booking.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmango.booking.domain.Booking;
import com.devmango.booking.service.BookingService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

	private BookingService bookingService;

	public BookingController(BookingService employeeService) {
		this.bookingService = employeeService;
	}

	@PostMapping
	public Booking bookRoom(@RequestBody Booking booking) {
		return bookingService.bookRoom(booking);
	}

	@GetMapping(value = "/notifications", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Booking> getBookingNotifications() {
		return bookingService.getBookingNotifications();
	}
}
