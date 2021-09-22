package ru.job4j.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Простой сокет сервер ,который принимает запросы от программы curl и в зависимости от сообщения
 * отвечает из зараннее заготовленных ответов
 * @author Shegai Evgenii
 * @since 22.09.2021
 * @version 1.0
 */

public class EchoServer {

// Хранилише заданных ответов содержит по ключу объекты outputstream , которые отправляют
// ответ от сервера пользователю

    private static final Map<String, Consumer<OutputStream>> MAPA = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            writeDataInMap(server);
            while (!server.isClosed()) {
                int count = 0;
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    while (in.ready()) {
                        String temp = in.readLine();
                        if (count == 0) {
                            String value = splitArgument(temp);
                            writeInOut(value, out);
                            count = 1;
                        }
                        System.out.println(temp);
                    }
                }
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    //записываем по ключу опред данные в out

    private static void writeDataInMap(ServerSocket server) {
        MAPA.put("Hello", out -> {
            try {
                out.write("Hello my dear friend\r\n\r\n".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        MAPA.put("Any", x -> {
            try {
                x.write("What\r\n\r\n".getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        MAPA.put("Exit", x -> {
            try {
                x.write("GoodBye My Dear friend\r\n\r\n".getBytes());
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    //Ищем ключ для поиска в мапе

    private static String splitArgument(String value) {
        String[] temp = value.split("=");
        String[] tmp = temp[1].split(" ");
        return tmp[0];
    }

    private static void writeInOut(String value, OutputStream out) throws IOException {
        out.write("HTTP/1.1 200 OK\\r\\n\\r\\n".getBytes());
        out.write(System.lineSeparator().getBytes());

        // метод возвращает значение по ключу или если нет значения тогда возвращает значение по умолчанию
        MAPA.getOrDefault(value, outputStream -> {
            try {
                // значение по умолчанию
                outputStream.write("I Don't understand\r\n\r\n".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).accept(out);
    }
}
