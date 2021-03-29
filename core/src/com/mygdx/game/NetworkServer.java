package com.mygdx.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NetworkServer implements Runnable {

        private ServerSocket serverSocket = null;
        private Socket socket = null;
        private ObjectInputStream in = null;
        private Connection conn = null;

        public static void main(String[] args) {
            NetworkServer serverApp = new NetworkServer();
            Thread serverThread = new Thread(serverApp);
            serverThread.start();   // starts the .run() method as a Thread


            while(true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Press Q for quitting...");
                String line = scanner.nextLine();
                scanner.close();
                if(line.toLowerCase().equals("q")) break;
            }

            try {
                if(serverApp.serverSocket != null)
                    serverApp.serverSocket.close();
            } catch (SocketException e) {
                System.out.println("Port closed.");
                //e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }

            System.out.println("Server terminated.");
        }

        @Override
        public void run() {
            System.out.println("Server thread is running...");
            try {
                serverSocket = new ServerSocket(59001);
                serverSocket.setSoTimeout(3000);

                while(true) {   // waiting for receiving new data
                    try {
                        Score score0 = null;
                        socket = serverSocket.accept();
                        in = new ObjectInputStream(socket.getInputStream());

                        try {
                            score0 = (Score)in.readObject();
                            System.out.println("Data received " + score0);
                            try {
                                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advanced_snake_db", "root", "");	// Parameters for Connection to Database
                                Statement st = conn.createStatement();
                                st.executeUpdate("INSERT INTO `highscores` (`id`, `highscore`)" + "VALUES (default, `"+score0.getScore_highest()+"`)");	// Value transfer from Object to DB table

                            } catch (SQLException e) {
                                System.out.println("SQL Exception:" + e.getMessage());
                            }
                        } catch (ClassNotFoundException e) {
                            System.out.println("Class not found");
                            //e.printStackTrace();
                        }
                    } catch (SocketTimeoutException e) {
                        System.out.println("Server thread is waiting for client...");
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Quitting Connection");  // e.printStackTrace();
            } finally {
                try {
                    if(in != null) in.close();
                    if(socket != null) socket.close();
                    if(serverSocket != null) serverSocket.close();
                    if(conn != null) conn.close();
                } catch (IOException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }