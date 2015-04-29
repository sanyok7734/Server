import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sanyok on 29.04.15.
 */
public class Server {
    public static void main(String[] args) {
        int port = 7777;
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Waiting for a client...");

            Socket socket = serverSocket.accept();
            // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Got a client!");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            while (true) {
                line = in.readUTF();
                System.out.println("Client just sent me this line : " + line);
                System.out.println("Sending it back...");
                line = "Server: " + line;
                out.writeUTF(line);
                out.flush();
                System.out.println("Waiting for the next line...");
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
