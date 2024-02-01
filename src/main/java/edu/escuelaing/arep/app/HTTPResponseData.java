package edu.escuelaing.arep.app;

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
     */
    public String getIndexPage(){
        return "<!DOCTYPE html>\r\n" + //
        "<html>\r\n" + //
        "    <head>\r\n" + //
        "        <title>Movie DB</title>\r\n" + //
        "        <meta charset=\"UTF-8\">\r\n" + //
        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
        "        <style>\r\n" + //
        "            body {\r\n" + //
        "                font-family: 'Arial', sans-serif;\r\n" + //
        "                margin: 0; /* Elimina el margen predeterminado del cuerpo */\r\n" + //
        "                display: flex;\r\n" + //
        "                flex-direction: column;\r\n" + //
        "                align-items: center; /* Centra los elementos horizontalmente */\r\n" + //
        "                justify-content: center; /* Centra los elementos verticalmente */\r\n" + //
        "                min-height: 100vh; /* Establece la altura mínima al 100% de la ventana */\r\n" + //
        "            }\r\n" + //
        "            h1 {\r\n" + //
        "                color: #333;\r\n" + //
        "            }\r\n" + //
        "            form {\r\n" + //
        "                margin-bottom: 20px;\r\n" + //
        "            }\r\n" + //
        "            label {\r\n" + //
        "                font-weight: bold;\r\n" + //
        "                margin-right: 10px;\r\n" + //
        "            }\r\n" + //
        "            input[type=\"text\"] {\r\n" + //
        "                padding: 5px;\r\n" + //
        "                width: 200px;\r\n" + //
        "            }\r\n" + //
        "            input[type=\"button\"] {\r\n" + //
        "                padding: 5px 10px;\r\n" + //
        "                background-color: #4CAF50;\r\n" + //
        "                color: white;\r\n" + //
        "                border: none;\r\n" + //
        "                cursor: pointer;\r\n" + //
        "            }\r\n" + //
        "            table {\r\n" + //
        "                width: 100%;\r\n" + //
        "                border-collapse: collapse;\r\n" + //
        "                margin-top: 20px;\r\n" + //
        "            }\r\n" + //
        "            table, th, td {\r\n" + //
        "                border: 1px solid #ddd;\r\n" + //
        "            }\r\n" + //
        "            th, td {\r\n" + //
        "                padding: 10px;\r\n" + //
        "                text-align: left;\r\n" + //
        "            }\r\n" + //
        "            th {\r\n" + //
        "                background-color: #4CAF50;\r\n" + //
        "                color: white;\r\n" + //
        "            }\r\n" + //
        "        </style>\r\n" + //
        "    </head>\r\n" + //
        "    <body>\r\n" + //
        "        <h1>Query Your Favorite Movies</h1>\r\n" + //
        "        <form>\r\n" + //
        "            <label for=\"movieName\">Name:</label><br>\r\n" + //
        "            <input type=\"text\" id=\"name\" name=\"name\"><br><br>\r\n" + //
        "            <input type=\"button\" value=\"Query\" onclick=\"loadGetMovie()\">\r\n" + //
        "        </form> \r\n" + //
        "        <div id=\"getresp\"></div>\r\n" + //
        "\r\n" + //
        "        <script>\r\n" + //
        "            function loadGetMovie(){\r\n" + //
        "                let name = document.getElementById(\"name\").value;\r\n" + //
        "                let url = \"/?name=\" + name;\r\n" + //
        "\r\n"

 + //
        "                fetch(url, { method: 'GET' })\r\n" + //
        "                    .then(x => x.json())\r\n" + //
        "                    .then(y => {\r\n" + //
        "                        \r\n" + //
        "                        // Map the JSON data to an HTML table\r\n" + //
        "                        let tableHTML = '<table border=\"1\">';\r\n" + //
        "                        for (const key in y) {\r\n" + //
        "                            if (y.hasOwnProperty(key)) {\r\n" + //
        "                                tableHTML += `<tr><td>${key}</td><td>${formatValue(y[key])}</td></tr>`;\r\n" + //
        "                            }\r\n" + //
        "                        }\r\n" + //
        "                        tableHTML += '</table>';\r\n" + //
        "\r\n" + //
        "                        // Display the table in the \"getresp\" div\r\n" + //
        "                        document.getElementById(\"getresp\").innerHTML = tableHTML;\r\n" + //
        "                    })\r\n" + //
        "                    .catch(error => console.error('Error:', error));\r\n" + //
        "            }\r\n" + //
        "\r\n" + //
        "            // Función para formatear los valores, maneja las estructuras anidadas\r\n" + //
        "            function formatValue(value) {\r\n" + //
        "                if (typeof value === 'object') {\r\n" + //
        "                    // Si el valor es un objeto, formatea recursivamente\r\n" + //
        "                    let subTable = '<table border=\"1\">';\r\n" + //
        "                    for (const subKey in value) {\r\n" + //
        "                        if (value.hasOwnProperty(subKey)) {\r\n" + //
        "                            subTable += `<tr><td>${subKey}</td><td>${formatValue(value[subKey])}</td></tr>`;\r\n" + //
        "                        }\r\n" + //
        "                    }\r\n" + //
        "                    subTable += '</table>';\r\n" + //
        "                    return subTable;\r\n" + //
        "                } else {\r\n" + //
        "                    // Si no es un objeto, simplemente devuelve el valor\r\n" + //
        "                    return value;\r\n" + //
        "                }\r\n" + //
        "            }\r\n" + //
        "        </script>\r\n" + //
        "    </body>\r\n" + //
        "</html>\r\n" + //
        "";
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