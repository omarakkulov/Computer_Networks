package ru.akkulov.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServerRunner {
    public static void main(String[] args) throws IOException {
        try (var serverSocket = new ServerSocket(8081);
             var socket = serverSocket.accept();
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)
        ) {
            var request = inputStream.readUTF();
            while (!request.equals("stop")) {
                System.out.println("Client: " + request);

                var response = scanner.nextLine();
                outputStream.writeUTF(response);

                request = inputStream.readUTF();
            }
        } catch (EOFException exception) {
            System.out.println("Server is stop!");
        }
    }
}
