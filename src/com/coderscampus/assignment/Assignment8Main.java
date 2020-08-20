package com.coderscampus.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

public class Assignment8Main {

	@Test
	public void multiThreading() {
		ExecutorService pool = Executors.newCachedThreadPool();
		List<CompletableFuture<ConcurrentMap<Integer, Integer>>> tasks = new ArrayList<>();

		// instantiate the assignment 8 constructor which returns the numbers from the
		// output file
		Assignment8 assignment8 = new Assignment8();
		for (int i = 0; i < 1000; i++) {
			// returns 1000 numbers, we also need to apply the function to sort dups
			CompletableFuture<ConcurrentMap<Integer, Integer>> task = CompletableFuture.supplyAsync(() -> assignment8.countDuplicates(), pool);
			// tasks = 1000 lists of 1000 numbers == 1 000 000 records
			tasks.add(task);

		}
		while (tasks.stream().filter(CompletableFuture::isDone).count() < 1000) {}
		
		// Testing of all threads completed
		System.out.println(
				"Number of completed threads: " + tasks.stream().filter(CompletableFuture::isDone).count());

		assignment8.outputMap();
	}

//	@Test
//	public void countDuplicatesTest() {
//		Assignment8 assignment8 = new Assignment8();
//		for (int i=0; i<1; i++) {
//            ConcurrentMap<Integer, Integer> map = assignment8.countDuplicates();
//            System.out.println(map);
//        }
//	}

}
