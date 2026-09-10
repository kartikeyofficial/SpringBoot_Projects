import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Servers {

    public Consumer<Socket> getConsumer(){
        return (clientSocket)->{
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello From The Server");
                toClient.close();
                clientSocket.close();
            }catch (IOException io){
                System.out.println(io.getMessage());
            }

        };
    }
    static void main(String[] args) {
        int port = 8018;
        Servers servers = new Servers();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server is Listening pn Port: "+port);
            while (true){
                Socket accepetedSocket = serverSocket.accept();
                Thread thread = new Thread(()->servers.getConsumer().accept(accepetedSocket));
                thread.start();

            }
        }catch (IOException io){
            System.out.println(io.getMessage());
        }
    }
}
