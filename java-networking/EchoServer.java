
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {                                                                     // Validate the input args[].
            System.err.println("The args you used didn't work. Try something like this...\n");
            System.err.println("'java EchoServer <port number>'");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);                                                 // Read port-number from args[].

        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);                               // Make a ServerSocket that's listening at portNumber.
            Socket clientSocket = serverSocket.accept();                                            // When a connection attempt comes in at that socket, accept it.
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                // Create a new input/output stream with the client.
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                System.out.println("Echoing back to the client...");                                // Read user input continuously until end-of-input character is heard.
                out.println(inputLine);                                                             // Echo the input back to the client each time.
            }
        } catch (IOException e) {                                                                   // Catch IOExceptions.
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection.");
            System.out.println(e.getMessage());
        }
    }
}