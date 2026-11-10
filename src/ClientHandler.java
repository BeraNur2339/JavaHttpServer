import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                OutputStream out = socket.getOutputStream()
        ) {
            String requestLine = in.readLine();
            if (requestLine == null) return;

            String htmlContent = generateHtmlResponse();
            byte[] bodyBytes = htmlContent.getBytes(StandardCharsets.UTF_8);

            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html; charset=UTF-8\r\n" +
                    "Content-Length: " + bodyBytes.length + "\r\n" +
                    "Connection: close\r\n" +
                    "\r\n";

            out.write(httpResponse.getBytes(StandardCharsets.UTF_8));
            out.write(bodyBytes);
            out.flush();

        } catch (IOException e) {
            System.err.println("İstek işleme hatası: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Soket kapatılamadı: " + e.getMessage());
            }
        }
    }

    private String generateHtmlResponse() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"tr\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Kişisel Sunucu</title>\n" +
                "    <style>\n" +
                "        body { font-family: 'Segoe UI', Arial, sans-serif; margin: 40px; background-color: #fff0f5; color: #4a4a4a; }\n" +
                "        .card { max-width: 750px; background: #ffffff; padding: 35px; border-radius: 16px; box-shadow: 0 4px 15px rgba(236, 72, 153, 0.15); margin: 0 auto; border: 1px solid #fbcfe8; }\n" +
                "        h1 { color: #db2777; border-bottom: 3px solid #f472b6; padding-bottom: 12px; margin-top: 0; }\n" +
                "        h2 { color: #9d174d; font-weight: 400; font-size: 1.25rem; margin-bottom: 24px; }\n" +
                "        .section { margin-top: 24px; }\n" +
                "        .section-title { font-weight: 600; color: #831843; margin-bottom: 8px; font-size: 1.1rem; }\n" +
                "        p { line-height: 1.7; color: #475569; }\n" +
                "        ul { line-height: 1.8; color: #475569; padding-left: 20px; }\n" +
                "        .badge { background-color: #fce7f3; color: #be185d; padding: 6px 14px; border-radius: 20px; font-size: 0.85rem; font-weight: 600; margin-right: 6px; display: inline-block; margin-bottom: 8px; border: 1px solid #fbcfe8; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"card\">\n" +
                "        <h1>Bera Nur Tursun</h1>\n" +
                "        <h2>Öğrenci No: 1250505006</h2>\n" +
                "        <div class=\"section\">\n" +
                "            <div class=\"section-title\">Hakkımda / Biyografi</div>\n" +
                "            <p>Kırklareli Üniversitesi Yazılım Mühendisliği 2. sınıf öğrencisiyim. Nesne yönelimli programlama, yazılım mimarileri, sistem seviyesi programlama ve veri tabanı yönetim sistemleri üzerine çalışmalar yürütmekteyim.</p>\n" +
                "        </div>\n" +
                "        <div class=\"section\">\n" +
                "            <div class=\"section-title\">Yetkinlikler & Teknolojiler</div>\n" +
                "            <div>\n" +
                "                <span class=\"badge\">Java</span>\n" +
                "                <span class=\"badge\">C / C++</span>\n" +
                "                <span class=\"badge\">HTML5 / CSS3</span>\n" +
                "                <span class=\"badge\">SQL / VTYS</span>\n" +
                "                <span class=\"badge\">Git & GitHub</span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"section\">\n" +
                "            <div class=\"section-title\">İlgi Alanları & Hobiler</div>\n" +
                "            <ul>\n" +
                "                <li>Klasik gitar pratikleri ve müzik teorisi</li>\n" +
                "                <li>Satranç analizleri ve stratejik oyunlar</li>\n" +
                "                <li>İkinci Yeni şiir akımı ve edebi metin incelemeleri</li>\n" +
                "                <li>Yeni şehirler ve kültürler keşfetmek</li>\n" +
                "            </ul>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}