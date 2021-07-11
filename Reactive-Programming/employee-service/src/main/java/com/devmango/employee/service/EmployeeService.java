package com.devmango.employee.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.devmango.employee.domain.Leave;
import com.devmango.employee.domain.Task;

import reactor.core.publisher.Flux;

@Service
public class EmployeeService {

	private ExecutorService executorService = Executors.newFixedThreadPool(3);

	public List<Task> getAllEmployeesTasks() {
		List<Task> tasks = new ArrayList<>();

		AtomicLong atomicLong = new AtomicLong();
		for (long employeeId = 0; employeeId <= 1000; employeeId++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}

			for (long taskCount = 0; taskCount <= 10; taskCount++) {
				tasks.add(new Task(employeeId, atomicLong.get(), "NWS-" + atomicLong.getAndIncrement()));
			}
		}

		return tasks;
	}

	public List<Leave> getAllEmployeesLeaves() {
		List<Leave> leaves = new ArrayList<>();

		for (long employeeId = 0; employeeId <= 1000; employeeId++) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}

			for (long leaveCount = 0; leaveCount <= 2; leaveCount++) {
				leaves.add(new Leave(employeeId, LocalDate.now(), LocalDate.now().plusDays(3), "PLANNED"));
			}

		}

		return leaves;
	}

	public Flux<Task> getAllEmployeesTasksReactively() {
		return Flux.create(emitter -> {
			AtomicLong atomicLong = new AtomicLong();
			for (long employeeId = 0; employeeId <= 1000; employeeId++) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}

				for (long taskCount = 0; taskCount <= 10; taskCount++) {
					emitter.next(new Task(employeeId, atomicLong.get(), "NWS-" + atomicLong.getAndIncrement()));
				}
			}
			emitter.complete();
		});
	}

	public Flux<Task> getAllEmployeesTasksReactivelyMultiThreaded() {
		return Flux.create(emitter -> {
			AtomicLong atomicLong = new AtomicLong();
			List<CompletableFuture<Void>> completableFutures = new ArrayList<>();

			for (long employeeId = 0; employeeId <= 1000; employeeId++) {

				final long internalEmployeeId = employeeId;

				completableFutures.add(CompletableFuture.runAsync(() -> {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}

					for (long taskCount = 0; taskCount <= 10; taskCount++) {
						emitter.next(
								new Task(internalEmployeeId, atomicLong.get(), "NWS-" + atomicLong.getAndIncrement()));
					}

				}, executorService));
			}

			CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0])).thenRun(() -> {
				emitter.complete();
			});
		});
	}
}
