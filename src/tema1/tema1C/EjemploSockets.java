package tema1.tema1C;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.util.Scanner;


// Este ejemplo muestra cómo crear un socket servidor y un socket de cliente
// El programa además hace uso de los argumentos del programa para poder pasar
// un parámetro por la línea de comandos para lanzarlo de una forma o de otra.

// ¡Atención! Este ejemplo no usa threads y el servidor se queda bloqueado hasta
// recibir un mensaje del cliente.

// Para lanzar el server: java es.deusto.prog3.cap03.api.EjemploSockets --server port
// Para lanzar el cliente: java es.deusto.prog3.cap03.api.EjemploSockets --client ip port


public class EjemploSockets {

    private static void openServerSocket(int port) {
        // Se crea el socket del servidor escuchando en el puerto indicado
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor aceptando conexiones en el puerto: " + port);

            // El server se queda bloqueado esperando a que un cliente se conecte.
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado");

            //Obtenemos los streams de entrada y salida de datos
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            boolean exit = false;
            while(!exit) {
                // Leemos una linea del stream de entrada desde el cliente.
                String line = reader.readLine();
                if (line.equals("exit")) {
                    exit = true;
                    System.out.println("Recibido el comando 'exit'");
                } else {
                    System.out.println("El cliente ha dicho: " + line);
                    // le contestamos al cliente duplicando su mensaje
                    writer.println(line + line);
                }
            }

            socket.close();
            System.out.println("Socket del servidor cerrado");
                    
        } catch (IOException e) {
            System.out.println("No se pudo crear el servidor. " + e);
        }
    }

    private static void openClientSocket(String host, int port) {
        System.out.println("Conectado al servidor en " + host + " puerto " + port);
        try (Socket socket = new Socket(host, port)) {
            //Obtenemos los streams de entrada y salida de datos
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Leemos de la entrada en el cliente y mandamos la linea al servidor
            try (Scanner sc = new Scanner(System.in)) {
                while(true) {
                    System.out.print("Mensaje para el servidor: ");
                    String line = sc.nextLine();
                    // Escrimos el mensaje al servidor
                    writer.println(line);

                    if (line.equals("exit")) {
                        break;
                    }

                    // Leemos la respuesta desde el servidor
                    String response = reader.readLine();
                    System.out.println("El servidor dice: " + response);
                }
            }

            System.out.println("Cliente finalizado");

        } catch (IOException e) {
            System.out.println("No se pudo crear la conexión." + e);
        }
    }

    public static void main(String[] args) {
        // Comprobamos si se han recibido suficientes argumentos
        // en la línea de comandos
        if (args.length >= 1) {
            // Si se quiere lanzar el server
            if (args[0].equals("--server")) {
                if (args.length == 2) {
                    //  Se comprueba el resto de argumentos
                    int port = Integer.parseInt(args[1]);
                    openServerSocket(port);
                } else {
                    System.out.println("Error. Se esperaba --server puerto");
                }
            } else if (args[0].equals("--client")) {
                // Si se quiere lanzar el cliente
                if (args.length == 3) {
                    // Se comprueba el resto de argumentos
                    String host = args[1];
                    int port = Integer.parseInt(args[2]);
                    openClientSocket(host, port);
                }
            } else {
                System.out.println("Error. Se esperaba --client o --server");
            }
        } else {
            System.out.println("Error. Se esperaba --client o --server");
        }
    }
}