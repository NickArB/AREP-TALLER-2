package edu.escuelaing.arep.app;

import java.net.*;
import java.io.*;

/**
 * The `HTTPServer` class represents a simple HTTP server that handles client connections.
 * It uses multiple threads to handle each client independently. The server responds to different
 * queries, including returning the index page, querying an API based on a movie name, and handling errors.
 * @author Nicolas Ariza Barbosa
 */
public class HTTPServer {

    private static HTTPResponseHeaders serverResponseHeaders = new HTTPResponseHeaders("html");
    private static HTTPResponseData serverResponseData = new HTTPResponseData();

    /**
     * The main method initializes the server socket and continuously accepts client connections.
     * For each client, a new thread is created to handle the connection independently.
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35500);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;

        while(running){
            try {
                Socket clientSocket = serverSocket.accept();
                handleClientConnection(clientSocket);
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        }
        serverSocket.close();
    }

    /**
     * Handles the client connection, including reading the query, processing it, and sending the response.
     * @param client The client socket for the connection.
     * @throws IOException If an I/O error occurs.
     */
    private static void handleClientConnection(Socket client) throws IOException {
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String inputLine;
        boolean firstLine = true;
        String query = "";

        while ((inputLine = in.readLine()) != null) {
            if(firstLine){
                query = inputLine.split(" ")[1].toLowerCase();
                firstLine = false;
            }
            if (!in.ready()) {
                break;
            }
        }

        if(query.equals("/")){ // Return page index
            HTTPResponse(out);
        // } else if ((query.startsWith("/?name=")) && (query.length() > 7)){ // Validates the message and queries in the API
            
        } else { // Error if everything else fails
            HTTPError(out);
        }

        out.close();
        in.close();
        client.close();
    }

    /**
     * Sends the HTTP response for the index page to the client.
     * @param outPut The PrintWriter for sending the response.
     * @throws FileNotFoundException If the index page file is not found.
     * @throws IOException If an I/O error occurs.
     */
    public static void HTTPResponse (PrintWriter outPut) throws FileNotFoundException, IOException {
        // Send headers to the client
        serverResponseHeaders.setContentType("html");
        outPut.println(serverResponseHeaders.OKResponse());
        // Send index.html to the client
        outPut.println(serverResponseData.getIndexPage());
    }

    /**
     * Sends the HTTP error response to the client.
     * @param outPut The PrintWriter for sending the response.
     */
    private static void HTTPError(PrintWriter outPut) {
        // Send headers to the client
        serverResponseHeaders.setContentType("html");
        outPut.println(serverResponseHeaders.NotFoundResponse());
        // Send HTML structure to the client
        outPut.println(serverResponseData.getNotFoundPage());
    }
}



