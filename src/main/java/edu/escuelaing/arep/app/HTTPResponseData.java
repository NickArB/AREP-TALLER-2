package edu.escuelaing.arep.app;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The `HTTPResponseData` class provides methods for generating HTML pages and JSON error messages
 * to be sent as responses in an HTTP server. It includes a method to create the index page, a method
 * for the "not found" page, and a method to return a JSON error message.
 * @author Nicolas Ariza Barbosa
 */
public class HTTPResponseData {

    /**
     * Generates the HTML content for the index page, which includes a form to query movies and a
     * div to display the API response as an HTML table.
     * @param requestedfile The URI of the requested file
     * @param client The socket used to communicate with the client
     * @throws IOException 
     */
    public void sendFileData(URI requestedfile, Socket client) throws IOException{
        OutputStream out = client.getOutputStream();
        Path imagePath = Paths.get("web-files", requestedfile.getPath());
        byte[] imageData = Files.readAllBytes(imagePath);
        out.write(imageData);
        out.flush();
        out.close();
    }

    /**
     * Calls the HTML content for the "not found" page, indicating that the requested resource does not exist.
     * @throws URISyntaxException 
     * @throws IOException 
     */
    public void sendNotFoundPage(Socket client) throws IOException, URISyntaxException{
        sendFileData(new URI("/not-found.html"), client);
    }
}