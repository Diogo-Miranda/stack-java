package javacore.ZZMcompletablefuture;

import java.util.concurrent.*;

public class FutureTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    // Make with 1 core at full time
    public static void main(String[] args) {
        // Run async (other thread)
        Future<Double> future = executorService.submit( () -> {
            TimeUnit.SECONDS.sleep(2);
            return 2000D;
        });
        enrolando();
        // Override method, with timestamp (timeout exception) and other simple
        try {
            while(!future.isDone()) {
                Double resultado = future.get();
                System.out.println(resultado);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void enrolando() {
        long soma = 0;
        for (int i = 0; i < 1_000_000; i++) {
            soma += i;
        }
        System.out.println(soma);
    }
}
