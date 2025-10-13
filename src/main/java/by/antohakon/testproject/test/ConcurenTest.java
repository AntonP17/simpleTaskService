package by.antohakon.testproject.test;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class ConcurenTest {

   private final ExecutorService executorService = Executors.newFixedThreadPool(5);

   @Scheduled(fixedRate = 5000)
    private  void completableFutureTasks() {

        List<Callable<String>> tasksCallable = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tasksCallable.add(new Task1());
        }

        List<Runnable> tasksRunnable = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tasksRunnable.add(new Task2());
        }

        List<CompletableFuture<String>> futureCallable = new ArrayList<>();
        for (int i = 0; i < tasksCallable.size(); i++) {
            int finalI = i;
            futureCallable.add(
                    CompletableFuture.supplyAsync(() -> {
                        try {
                            return tasksCallable.get(finalI).call();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }, executorService)
            );
        }

        tasksRunnable.forEach(executorService::execute);

        futureCallable.stream()
                .map(CompletableFuture::join)
                .forEach(result -> System.out.println("method completableFutureTasks result: " + result));

    }

    @Scheduled(fixedRate = 5000)
    private  void futureTasks() {

        List<Callable> tasksCallable = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            tasksCallable.add(new Task1());
        }

        List<Runnable> tasksRunnable = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            tasksRunnable.add(new Task2());
        }

        List<Future<String>> futuresCallable = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            futuresCallable.add(executorService.submit(tasksCallable.get(i)));
        }

        List<Runnable> futuresRunnable = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            futuresRunnable.add(tasksRunnable.get(i));
        }

        futuresRunnable.forEach(executorService::execute);

        futuresCallable.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(result -> System.out.println("method futureTasks result: " + result));
    }

    static class Task1 implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "имитация работы типа callable";
        }
    }

    static class Task2 implements Runnable {

        @Override
        public void run() {
            System.out.println(" имитация работы runnable");
        }
    }
}
