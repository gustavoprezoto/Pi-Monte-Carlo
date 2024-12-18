import Utils.ConfigValues;

import java.util.Random;

public class MonteCarloSequencial {

    public static void main(String[] args) {
        long totalPoints = ConfigValues.TOTAL_POINTS;
        long insideCircle = 0;

        System.out.println("Calculo da Aproximação de Pi por método de Monte Carlo - Sequencial");
        System.out.println("Total de pontos: " + totalPoints);

        Random random = new Random();

        long startTime = System.currentTimeMillis();

        for (long i = 0; i < totalPoints; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (x * x + y * y <= 1) {
                insideCircle++;
            }

        }

        double pi = 4.0 * insideCircle / totalPoints;

        long endTime = System.currentTimeMillis();

        System.out.println("Aproximação de Pi: " + pi);
        System.out.println("Tempo gasto: " + (endTime - startTime) + "ms");
    }
}
