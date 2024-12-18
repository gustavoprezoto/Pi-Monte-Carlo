package MonteCarloDistribuido;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import Utils.ConfigValues;

public class MonteCarloMaster {
    private static final int PORT = ConfigValues.SOCKET_CONFIGS.PORT;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);

        int totalClients = 4;
        long totalPoints = ConfigValues.TOTAL_POINTS;
        long pointsPerClient = totalPoints / totalClients;

        List<Socket> clientSockets = new ArrayList<>();
        System.out.println("Aguardando conexões de " + totalClients + " clientes...");
        System.out.println(totalPoints);

        while (clientSockets.size() < totalClients) {
            Socket clientSocket = serverSocket.accept();
            clientSockets.add(clientSocket);
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress() + " (" + clientSockets.size() + "/" + totalClients + ")");
        }

        System.out.println("Todos os escravos conectados. Iniciando o cálculo...");

        System.out.println("Calculo da Aproximação de Pi por método de Monte Carlo - Distribuido");
        System.out.println("Total de pontos: " + totalPoints);
        System.out.println("Pontos por escravo: " + pointsPerClient);

        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(totalClients);

        AtomicLong totalInsideCircle = new AtomicLong();

        for (Socket clientSocket : clientSockets) {
            executor.execute(() -> {
                try {
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    writer.println(pointsPerClient);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    long insideCircle = Long.parseLong(reader.readLine());

                    synchronized (MonteCarloMaster.class) {
                        totalInsideCircle.addAndGet(insideCircle);
                    }

                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        double pi = 4.0 * totalInsideCircle.get() / totalPoints;

        long endTime = System.currentTimeMillis();

        System.out.println("Aproximação de Pi: " + pi);
        System.out.println("Tempo gasto: " + (endTime - startTime) + "ms");

        serverSocket.close();
    }
}
