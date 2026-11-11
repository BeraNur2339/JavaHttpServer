# Standalone Java HTTP Web Server

A lightweight, zero-dependency, multi-threaded custom HTTP web server built entirely using Core Java (`java.net` and `java.io`).

This project demonstrates low-level TCP socket programming, HTTP/1.1 protocol handling, concurrent connection management, and adherence to clean architecture principles (SOLID) without relying on third-party frameworks like Spring, Express, or Tomcat.

---

## 🌟 Key Features

* **Zero External Dependencies:** Built strictly using Java Standard Library (`java.net.ServerSocket`, `java.net.Socket`).
* **Multi-Threaded Architecture:** Utilizes a Thread-per-Request model to handle concurrent client connections non-blockingly.
* **HTTP/1.1 Protocol Support:** Manages low-level HTTP headers (`Content-Type`, `Content-Length`, `Connection: close`) and status codes manually.
* **Responsive HTML/CSS Engine:** Renders dynamic, modern, and fully-styled responsive HTML pages via custom stream writers.
* **Object-Oriented Design:** Adheres to SOLID design principles for high maintainability and clean structure.

---

## 🏗️ Architectural Design & SOLID Principles

The system is structured around object-oriented principles to ensure modularity and scalability:

* **Single Responsibility Principle (SRP):** 
  * `SimpleHttpServer`: Handles socket initialization, port binding, and accepting incoming connections.
  * `ClientHandler`: Responsible for reading HTTP request packets, formulating response headers, and streaming HTML payloads.
* **Open/Closed Principle (OCP):** The request-processing logic is extensible to support additional HTTP methods (e.g., POST, PUT) without altering the main server loop.
* **Dependency Inversion Principle (DIP):** I/O operations rely on abstract stream abstractions (`InputStream`, `OutputStream`, `BufferedReader`).

---

## 🛠️ Technical Specifications

| Parameter | Specification |
| :--- | :--- |
| **Language** | Java (JDK 11+) |
| **Network Protocol** | HTTP / 1.1 |
| **Transport Layer** | TCP/IP |
| **Default Port** | `1989` |
| **Concurrency Model** | Thread-per-request (`java.lang.Thread`) |
| **Dependencies** | None (Pure Standard Library) |

---

## 📁 Project Structure

```text
JavaHttpServer/
├── src/
│   ├── SimpleHttpServer.java    # Main server entry point & socket listener
│   └── ClientHandler.java       # Runnable request worker & HTML builder
├── .gitignore                   # Git tracking exclusions
└── README.md                    # Technical documentation
