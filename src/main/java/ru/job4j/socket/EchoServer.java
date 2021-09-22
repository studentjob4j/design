package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Простой сокет сервер
 * @author Shegai Evgenii
 * @since 22.09.2021
 * @version 1.0
 */

public class EchoServer {

    public static void main(String[] args) {
        String str = null;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    while (in.ready()) {
                        str = in.readLine();
                        if (str.contains("Bye")) {
                            out.write("HTTP/1.1 200 Bye\r\n\r\n".getBytes());
                            out.flush();
                            server.close();
                            break;
                        }
                        System.out.println(str);
                        out.write("HTTP/1.1 200 All ok\r\n\r\n".getBytes());
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                    out.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
