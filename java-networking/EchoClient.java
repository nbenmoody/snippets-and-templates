
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {                                                                     // Validate the input args[].
            System.err.println("The args you used didn't work. Try something like this...\n");
            System.err.println("'java EchoClient <host name> <port number>'");
            System.exit(1);
        }

        String hostName = args[0];                                                                  // Read the host and port from args[].
        int portNumber = Integer.parseInt(args[1]);

        try (
            Socket echoSocket = new Socket(hostName, portNumber);                                   // Make a socket at the host:port.
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);                  // Create writer/readers to perform i/o with server.
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {                                        // Write-then-read so long as no end-of-input character is detected.
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {                                                          // Catch UnknownHost and I/O exceptions.
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}