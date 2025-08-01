# Network Layers

The TCP/IP model consists of four main layers, each responsible for specific functions in network communication:

## Application Layer
- **Function**: Software applications perform on this layer and talk to the transport layer using special protocols like HTTP, SMTP, FTP, DNS, etc.
- **Responsibility**: Application layer adds specific headers according to the protocol being used before passing the data to the next layer
- **Examples**: Web browsers, email clients, file transfer applications

## Transport Layer
- **Function**: This layer divides the data into data packets and allocates sequence numbers along with source and destination ports
- **Protocols**: We have two main options on this layer:
  - **TCP (Transmission Control Protocol)**: This protocol makes sure that data is transferred in sequence and reliably. It requests the data again if it is missing from any part.
  - **UDP (User Datagram Protocol)**: This protocol might miss some data and order is also not guaranteed, but it's faster and more efficient for certain applications.

## Internet Layer
- **Function**: This layer prepares the data for the internet. Source and destination IP addresses are added at this stage in a header. The resultant data is called an IP packet.
- **Responsibility**: Routers use this information to drive the data in the network to the correct destination
- **Protocol**: Primarily uses IP (Internet Protocol)

## Link Layer
- **Function**: The link layer is responsible for transporting IP packets across each of the individual links that make up the path between two communicating computers.
- **Media Types**: These links could be a combination of:
  - Ethernet
  - WiFi
  - 4G/LTE
  - Satellite
  - Fiber optic cables
- **Responsibility**: Handles the physical transmission of data over the actual network medium

## Data Flow
1. **Application Layer**: Creates the initial data with application-specific headers
2. **Transport Layer**: Segments data and adds transport headers (TCP/UDP)
3. **Internet Layer**: Adds IP headers with source and destination addresses
4. **Link Layer**: Transmits the final packet over the physical network medium

This layered approach ensures that each layer has a specific responsibility and can work independently while providing a complete networking solution.
![](/diagrams/networklayers.png)

# What is a Socket?

A socket is one endpoint of a two-way communication link between two programs running on the network. A socket is bound to a port number so that the TCP layer can identify the application that data is destined to be sent to.

## Key Concepts:
- **Server is nothing but a machine which has opened a gate for someone to come**
- **That gate is generally a socket which gets opened on a server with a specific address**
- **Address = IP:Port**
- **Socket listens on an IP and port for a connection to come**

![](/diagrams/clientserver.png)


When a server accepts a connection from a client, it returns a `ClientSocket`. This `ClientSocket` is attached to the *same local port* as the listening socket, but its remote endpoint is set to the client's specific IP address and port.

## Multiple Sockets on the Same Port
### How Multiple Sockets Work on the Same Port
**Question**: How can a new socket be opened on the same port if already a listener socket is running on the same?

**Answer**: Because OS distinguishes sockets from a 4-way tuple: `(local IP, local port, remote IP, remote port)`

### Example Table

| Socket Type | Local Address | Remote Address |
|-------------|---------------|----------------|
| Listening | 0.0.0.0:8080 | * (none yet) |
| ClientSocket1 | 0.0.0.0:8080 | 192.168.1.5:3456 |
| ClientSocket2 | 0.0.0.0:8080 | 192.168.1.8:6789 |

### Socket Creation Process
- **Every time a socket is accepted**, the OS creates a new socket internally for the client connection, with its own connection state.
- **On the client side**, if the connection is accepted, a socket is successfully created and the client can use the socket to communicate with the server.
- The client and server can now communicate by writing to or reading
from their sockets.

The 4-way tuple concept ensures that while multiple sockets can share the same local address (IP:port), each connection is uniquely identified by the combination of local and remote addresses.

## Java Example: Simple Client-Server with Threads

Below is a simple Java program demonstrating how a server and a client can communicate using sockets, each running in its own thread. This example shows the basic flow of socket creation, connection, and message exchange.

```java
import java.io.*;
import java.net.*;

public class ClientServerThreads {
    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> runServer());
        Thread clientThread = new Thread(() -> runClient());

        serverThread.start();

        // Ensure server starts first
        try {
            Thread.sleep(500); // delay to allow server to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clientThread.start();
    }

    public static void runServer() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server: Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Server: Client connected!");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String messageFromClient = in.readLine();
            System.out.println("Server received: " + messageFromClient);

            out.println("Hello from Server!");

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runClient() {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Client: Connected to server.");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Hello from Client!");

            String messageFromServer = in.readLine();
            System.out.println("Client received: " + messageFromServer);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### Explanation
- **Threads**: The server and client each run in their own thread, allowing them to operate concurrently in the same program.
- **Server**: Listens on port 5000, accepts a client connection, reads a message, and sends a response.
- **Client**: Connects to the server, sends a message, and reads the server's response.
- **Synchronization**: A short delay (`Thread.sleep(500)`) ensures the server starts before the client attempts to connect.
- **Socket Communication**: Both use `BufferedReader` and `PrintWriter` for simple text-based communication over the socket.

This example demonstrates the basic socket creation process and how client-server communication works in Java using threads.