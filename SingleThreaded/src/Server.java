import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public  void run() throws IOException {
        int port =8018;
        ServerSocket socket = new ServerSocket(port);
        socket.setSoTimeout(10000);
        while (true){
            try {
                System.out.println("Server is Listning on port: "+port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection Accepted from client: "+acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                toClient.println("Hello from the Server");
                toClient.close();
                acceptedConnection.close();
                fromClient.close();
            }
            catch (IOException io){
                System.out.println(io.getMessage());
            }

        }
    }
    static void main(String[] args) {
        Server server = new Server();
        try{
            server.run();
        }catch (IOException io){
            System.out.println(io.getMessage());
        }

    }
}
