import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    private static final int PORT = 1989;

    public static void main(String[] args) {
        System.out.println("Sunucu " + PORT + " portunda başlatılıyor...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Sunucu dinlemede. Bağlantı bekleniyor...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Gelen her isteği ayrı bir Thread üzerinde işliyoruz
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Sunucu başlatma hatası: " + e.getMessage());
        }
    }
}