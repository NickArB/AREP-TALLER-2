package edu.escuelaing.arep.app;

/**
 * The `HTTPResponseHeaders` class provides methods for generating HTTP response headers based on the
 * content type. It includes methods for creating a successful (OK) response and a "not found" response.
 * @author Nicolas Ariza Barbosa
 */
public class HTTPResponseHeaders {

    private String CONTENT_TYPE;
    private String[] mediaFilesLst = {"jpeg", "png", "ico"};

    /**
     * Constructs an `HTTPResponseHeaders` object with the specified content type.
     * @param contentType The content type for the HTTP response.
     */
    public HTTPResponseHeaders(String contentType){
        this.CONTENT_TYPE = contentType;
    }

    /**
     * Generates the HTTP response headers for a successful (OK) response with the specified content type.
     * @return The HTTP response headers for a successful response.
     */
    public String OKResponse(){
        String reponse = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/" + this.CONTENT_TYPE + "\r\n" +
                        "";

        for(String contentType: this.mediaFilesLst){
            if(CONTENT_TYPE.equals(contentType)){
                return "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: image/" + this.CONTENT_TYPE + "\r\n" +
                "";
            }
        }
        return reponse;
    }

    /**
     * Generates the HTTP response headers for a "not found" response with the specified content type.
     * @return The HTTP response headers for a "not found" response.
     */
    public String NotFoundResponse(){
        return "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/" + this.CONTENT_TYPE + "\r\n" +
                "";
    }

    /**
     * Sets the content type for the HTTP response headers.
     * @param newContentType The new content type to be set.
     */
    public void setContentType(String newContentType) {
        this.CONTENT_TYPE = newContentType;
    }
}
