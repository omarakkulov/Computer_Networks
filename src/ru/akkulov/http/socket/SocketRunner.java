package ru.akkulov.http.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketRunner {
    public static void main(String[] args) throws IOException {
        final InetAddress inetAddress = Inet4Address.getByName("localhost");

        try (var socket = new Socket(inetAddress, 8081);
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)
        ) {
            var request = scanner.nextLine();
            while (!request.equals("stop")) {
                outputStream.writeUTF(request);
                System.out.println("Server: " + inputStream.readUTF());
                request = scanner.nextLine();
            }
        }
    }
}
