package edu.escuelaing.arep.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HTTPResponseHeadersTest {
    
    @Test
    public void shouldgetOKResponse(){
        HTTPResponseHeaders server = new HTTPResponseHeaders("html");
        String correctString = "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/html\r\n" +
                                "";
        assertEquals(correctString, server.OKResponse());
    }

    @Test
    public void shouldgetNotFoundResponse(){
        HTTPResponseHeaders server = new HTTPResponseHeaders("html");
        String correctString = "HTTP/1.1 404 Not Found\r\n" +
                                "Content-Type: text/html\r\n" +
                                "";
        assertEquals(correctString, server.NotFoundResponse());
    }

    @Test
    public void shouldModifyContentType(){
        HTTPResponseHeaders server = new HTTPResponseHeaders("html");
        // Get request
        server.setContentType("json");
        String correctString = "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/json\r\n" +
                                "";
        assertEquals(correctString, server.OKResponse());
        // Not found request
        server.setContentType("html");
        correctString = "HTTP/1.1 404 Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "";
        assertEquals(correctString, server.NotFoundResponse());
    }

    @Test
    public void shouldChangeContentTypeToImg(){
        HTTPResponseHeaders server = new HTTPResponseHeaders("html");
        // Get request
        server.setContentType("json");
        String correctString = "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/json\r\n" +
                                "";
        assertEquals(correctString, server.OKResponse());
        // Get image
        server.setContentType("jpeg");
        correctString = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: image/jpeg\r\n" +
                        "";
        assertEquals(correctString, server.OKResponse());
    }
}
