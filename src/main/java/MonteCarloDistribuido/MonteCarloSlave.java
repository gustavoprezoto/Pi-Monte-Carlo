package MonteCarloDistribuido;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class MonteCarloSlave {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            System.out.println("Conectado ao Servidor Mestre!");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            long pointsToProcess = Long.parseLong(reader.readLine());
            System.out.println("Recebido para processar: " + pointsToProcess + " pontos");

            Random random = new Random();
            long insideCircle = 0;

            for (long i = 0; i < pointsToProcess; i++) {
                double x = random.nextDouble();
                double y = random.nextDouble();
                if (x * x + y * y <= 1) {
                    insideCircle++;
                }
            }

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(insideCircle);

            System.out.println("Enviado: " + insideCircle + " pontos dentro do círculo.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
