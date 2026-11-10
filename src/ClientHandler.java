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

            // HTTP 1.1 protokolüne uygun başlıklar
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
                "        body { font-family: 'Segoe UI', Arial, sans-serif; margin: 40px; background-color: #f8f9fa; color: #212529; }\n" +
                "        .card { max-width: 750px; background: #ffffff; padding: 35px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.08); margin: 0 auto; }\n" +
                "        h1 { color: #1e293b; border-bottom: 3px solid #3b82f6; padding-bottom: 12px; margin-top: 0; }\n" +
                "        h2 { color: #64748b; font-weight: 400; font-size: 1.25rem; margin-bottom: 24px; }\n" +
                "        .section { margin-top: 24px; }\n" +
                "        .section-title { font-weight: 600; color: #0f172a; margin-bottom: 8px; font-size: 1.1rem; }\n" +
                "        p { line-height: 1.7; color: #334155; }\n" +
                "        ul { line-height: 1.8; color: #334155; padding-left: 20px; }\n" +
                "        .badge { background-color: #e0f2fe; color: #0369a1; padding: 5px 12px; border-radius: 20px; font-size: 0.85rem; font-weight: 600; margin-right: 6px; display: inline-block; margin-bottom: 8px; }\n" +
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