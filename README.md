# Standalone Java HTTP Web Server

A lightweight, zero-dependency, multi-threaded custom HTTP web server built entirely using Core Java (`java.net` and `java.io`). 

This project demonstrates low-level TCP socket programming, HTTP/1.1 protocol handling, concurrent connection management, and adherence to clean architecture principles (SOLID) without relying on third-party frameworks like Spring, Express, or Tomcat.

---

## 🌟 Key Features

- **Zero External Dependencies:** Built strictly using Java Standard Library (`java.net.ServerSocket`, `java.net.Socket`).
- **Multi-Threaded Architecture:** Dispatches each incoming TCP connection to a dedicated worker thread (`ClientHandler`) to handle concurrent requests without blocking the main listener thread.
- **Custom Port Configuration:** Binds and listens on port `1989` by default.
- **HTTP/1.1 Protocol Support:** Manually constructs and parses standard HTTP response headers (`200 OK`, `Content-Type`, `Content-Length`, `Connection`).
- **UTF-8 Character Encoding:** Fully supports multi-byte dynamic HTML response rendering.

---

## 🛠 Architectural Overview & SOLID Principles

The system is designed with high modularity and separation of concerns:

- **Single Responsibility Principle (SRP):**
  - `SimpleHttpServer`: Handles socket initialization, port binding (1989), and loop listening.
  - `ClientHandler`: Manages connection life-cycle, HTTP stream parsing, HTML content generation, and payload transmission.
- **Open/GitHub repon yayınlanmış, ellerine sağlık[cite: 8]! 

Hiç ödev havası vermeyen, tamamen profesyonel bir açık kaynak projesi gibi duran ve teknik olarak ağ programlama (network programming) yeteneklerini öne çıkaran `README.md` dosyan hazır.

Bu içeriği ister doğrudan GitHub üzerindeki yeşil **Add a README** butonuna basıp yapıştırabilir, istersen IntelliJ içinde projenin ana dizinine `README.md` adında bir dosya oluşturup ekleyebilirsin[cite: 8].

---

```markdown
# Custom Lightweight Java HTTP Server

A zero-dependency, low-level HTTP Web Server implementation built completely with Standard Java (`java.net` and `java.io`) libraries. 

This project demonstrates the core fundamentals of TCP/IP socket programming, HTTP protocol handling, multi-threaded request management, and clean architecture without relying on external frameworks (e.g., Spring Boot, Apache Tomcat, Jetty).

---

## Key Features

* **Zero External Dependencies:** Implemented purely using Java Standard Edition (SE).
* **Low-Level Socket Communication:** Leverages `ServerSocket` and `Socket` classes for explicit network flow control.
* **Multi-Threaded Architecture:** Handles concurrent incoming client requests asynchronously using dedicated worker threads (`ClientHandler`).
* **HTTP/1.1 Protocol Support:** Manually constructs HTTP response headers (`200 OK`, `Content-Type`, `Content-Length`) and streams UTF-8 encoded dynamic HTML documents.
* **Object-Oriented Design:** Adheres to SOLID design principles for high maintainability and clean separation of concerns.

---

## Architectural Design & SOLID Principles

The system is structured around object-oriented principles to ensure modularity and scalability:

* **Single Responsibility Principle (SRP):** 
  * `SimpleHttpServer`: Handles socket initialization, port binding, and accepting incoming connections.
  * `ClientHandler`: Responsible for reading HTTP request packets, formulating response headers, and streaming the HTML payload.
* **Open/Closed Principle (OCP):** The request-processing logic is extensible to support additional HTTP methods (e.g., `POST`, `PUT`) or routing mechanisms without altering the core server socket loop.
* **Dependency Inversion Principle (DIP):** I/O operations rely on abstract stream abstractions (`BufferedReader`, `OutputStream`) rather than concrete implementations.

---

## Technical Specifications

| Parameter | Specification |
| :--- | :--- |
| **Language** | Java (JDK 11+) |
| **Network Protocol** | HTTP / 1.1 |
| **Transport Layer** | TCP/IP |
| **Default Port** | `1989` |
| **Concurrency Model** | Thread-per-request (`java.lang.Thread`) |
| **Dependencies** | None (Pure Standard Library) |

---

## Project Structure

```text
JavaHttpServer/
├── src/
│   ├── SimpleHttpServer.java   # Main server entry point & socket listener
│   └── ClientHandler.java      # Runnable request worker & HTML builder
├── .gitignore                  # Git tracking exclusions
└── README.md                   # Technical documentation
