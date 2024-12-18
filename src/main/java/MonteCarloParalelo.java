import Utils.ConfigValues;
import Utils.MonteCarloTask;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MonteCarloParalelo {
    public static void main(String[] args) throws Exception {
        int totalThreads = Runtime.getRuntime().availableProcessors();
        long totalPoints = 5_000_000_000L;
        long pointsPerThread = totalPoints / totalThreads;

        System.out.println("Calculo da Aproximação de Pi por método de Monte Carlo - Paralelo");
        System.out.println("Total de pontos: " + totalPoints);
        System.out.println("Pontos por thread: " + pointsPerThread);

        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);

        Future<Long>[] results = new Future[totalThreads];

        for (int i = 0; i < totalThreads; i++) {
            results[i] = executor.submit(new MonteCarloTask(pointsPerThread));
        }

        System.out.println();
        long insideCircle = 0;
        for (Future<Long> result : results) {
            insideCircle += result.get();
        }

        executor.shutdown();

        double pi = 4.0 * insideCircle / totalPoints;

        long endTime = System.currentTimeMillis();

        System.out.println("Aproximação de Pi: " + pi);
        System.out.println("Tempo gasto: " + (endTime - startTime) + "ms");
    }
}

