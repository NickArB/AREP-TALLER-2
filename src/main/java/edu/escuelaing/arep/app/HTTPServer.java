package edu.escuelaing.arep.app;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;

        while(running){
            try {
                Socket clientSocket = serverSocket.accept();
                handleClientConnection(clientSocket);
            } catch (IOException | URISyntaxException e) {
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
     * @throws URISyntaxException 
     */
    private static void handleClientConnection(Socket client) throws IOException, URISyntaxException {
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

        // Prepare to read the URI
        URI request = new URI(query);
        String apiRequest = null;

        try {
            // Check for API services
            if(request.getPath().startsWith("/change-img")){
                apiRequest = "/k" + request.getPath().split("/")[2] + ".jpeg";
                request = new URI(apiRequest);
            }
            // Validates if the file exists
            if(!Files.exists(Paths.get("web-files", request.getPath()))){
                throw new NoSuchFileException("File: " + request.getPath() + " does not exists!");
            }
            //Return HTTP response
            HTTPResponse(out, client, request);
        } catch (Exception e) {
            System.err.println(e);
            HTTPError(out, client);
        }

        out.close();
        in.close();
        client.close();
    }

    /**
     * Sends the HTTP response for the index page to the client.
     * @param outPut The PrintWriter for sending the response.
     * @param client The socket used to communicate with the client
     * @param request The request from the client
     * @throws FileNotFoundException If the index page file is not found.
     * @throws IOException If an I/O error occurs.
     * @throws URISyntaxException 
     */
    public static void HTTPResponse (PrintWriter outPut, Socket client, URI request) throws FileNotFoundException, IOException, URISyntaxException {
        // Send headers to the client
        String extension = request.toString();
        extension = extension.substring(extension.lastIndexOf(".") + 1);
        // Set content type
        serverResponseHeaders.setContentType(extension);
        outPut.println(serverResponseHeaders.OKResponse());
        // Prevents the connection to be closed by exchanging output stream
        outPut.flush();
        // Send index.html to the client
        serverResponseData.sendFileData(request, client);
    }

    /**
     * Sends the HTTP error response to the client.
     * @param outPut The PrintWriter for sending the response.
     * @throws URISyntaxException
     * @throws IOException
     */
    private static void HTTPError(PrintWriter outPut, Socket client) {
        // Send headers to the client
        serverResponseHeaders.setContentType("html");
        outPut.println(serverResponseHeaders.NotFoundResponse());
        // Prevents the connection to be closed by exchanging output stream
        outPut.flush();
        // Send HTML structure to the client
        try {
            serverResponseData.sendNotFoundPage(client);
        } catch (IOException | URISyntaxException e) {
            System.err.println(e);
        }
    }
}



