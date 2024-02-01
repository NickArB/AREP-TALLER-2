package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
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
     * @return The HTML content for the index page.
     * @throws IOException 
     */
    public String getFileData(URI requestedfile) throws IOException{
        String outPut = null;
        Path file = Paths.get("web-files" + requestedfile.getPath());
        Charset charset = Charset.forName("UTF-8");
        BufferedReader reader = Files.newBufferedReader(file, charset);
        String line = null;
        while((line = reader.readLine()) != null){
            System.out.println(line);
            outPut += line;
        }
        reader.close();
        return outPut;
    }

    /**
     * Generates the HTML content for the "not found" page, indicating that the requested resource does not exist.
     * @return The HTML content for the "not found" page.
     */
    public String getNotFoundPage(){
        return "<!DOCTYPE html>\r\n" +
                    "<html>\r\n" +
                        "<h1>Error, the resource does not exist</h1>\r\n" +
                    "</html>";
    }

    /**
     * Returns a JSON error message indicating that the requested resource was not found.
     * @return The JSON error message.
     */
    public String getJSONErrorMessage(){
        return "{\"Not found\":\"The resource that you were looking for does not exist\"}";
    }
}