package Handler;

import com.sun.net.httpserver.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;

public class FileHandler extends HandlerParent  {

    /**
     * Handle the given request and generate an appropriate response.
     * See {@link HttpExchange} for a description of the steps
     * involved in handling an exchange.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws NullPointerException if exchange is <code>null</code>
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // HTTP GET Request/Response Steps
        // 1. Handler's handle method is called and passed an HttpExchange instance
        // 2. Process request (use HttpExchange to get request method, URI, headers, etc.
        // 3. Send response code (exchange.sendResponseHeaders(responseCode, responseLength))
        // 4. Get output stream (exchange.getResponseBody())
        // 5. Write response to stream
        // 6. Close the exchange (exchange.close())

        try {
            // Only allow GET Requests
            if (exchange.getRequestMethod().toUpperCase().equals("GET")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                String urlPath = exchange.getRequestURI().toString();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                if (urlPath == null || urlPath.equals("/")) {
                    urlPath = "/index.html";
                }
                String filePath = "web" + urlPath;
                File myFile = new File(filePath);
                if (!myFile.exists()) {
                    filePath = "web/HTML/404.html";
                    myFile = new File(filePath);
                }
                OutputStream respBody = exchange.getResponseBody();
                Files.copy(myFile.toPath(), respBody);
                respBody.flush();
                respBody.close();
            }
            // Expected a GET, but got something else
            else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException io) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            io.printStackTrace();
        }
        exchange.getResponseBody().close();
    }
}
