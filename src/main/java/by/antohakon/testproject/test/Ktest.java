package by.antohakon.testproject.test;

import by.antohakon.testproject.entity.Task;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
public class Ktest {

    public static void main(String[] args) {


        Runnable task1 = new Runnable() {
            @SneakyThrows
            public void run() {
                test();
            }
        };

        Runnable task2 = new Runnable() {
            @SneakyThrows
            public void run() {
                test();
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();


        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(() -> {
                    return "hello from " + Thread.currentThread().getName();
                }, executorService);
        System.out.println(completableFuture.join());
        executorService.shutdown();

    }

    public static synchronized void test() throws InterruptedException {
        int intik = 1;
        System.out.println("Hello from " + Thread.currentThread().getName());
        for (int i = 0; i < 10000; i++) {
            intik++;

        }
        System.out.println(intik);
    }





}

