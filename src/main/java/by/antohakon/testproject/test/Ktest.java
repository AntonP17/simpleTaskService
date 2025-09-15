package by.antohakon.testproject.test;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
public class Ktest {

    private static Object object = new Object();

    public static void main(String[] args) throws ExecutionException, InterruptedException {




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
                    return "completableFuture, thread = " + Thread.currentThread().getName();
                }, executorService);

        System.out.println(completableFuture.join());

        //executorService.shutdown();

        Future<String> future = executorService.submit(new CallableThread());
        System.out.println(future.get()); // блокирует основной поток так то
        executorService.shutdown();


        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenAccept(result -> System.out.println(result));

        Test2 test2 = new Test2();
        test2.run();



    }

    public static synchronized void test() throws InterruptedException {

        synchronized (object) {
            int intik = 1;
            System.out.println("synschronized method test, thread = " + Thread.currentThread().getName());
            for (int i = 0; i < 10000; i++) {
                intik++;
            }
            System.out.println(intik);
        }
    }


public static class CallableThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Callable thread class, thread = " + Thread.currentThread().getName();
    }
}


}

