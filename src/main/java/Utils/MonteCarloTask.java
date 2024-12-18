package Utils;

import java.util.Random;
import java.util.concurrent.Callable;

public class MonteCarloTask implements Callable<Long> {
    private final long points;

    public MonteCarloTask(long points) {
        this.points = points;
    }

    @Override
    public Long call() {
        Random random = new Random();
        long insideCircle = 0;

        for (long i = 0; i < points; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (x * x + y * y <= 1) {
                insideCircle++;
            }
        }

        return insideCircle;
    }
}