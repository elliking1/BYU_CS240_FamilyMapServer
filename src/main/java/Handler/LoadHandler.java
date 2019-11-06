package Handler;

import Request.LoadRequest;
import Result.StandardResult;
import Service.LoadService;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoadHandler extends HandlerParent {
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
        // HTTP Post Request/Response Steps
        // 1. Handler's handle method is called and passed an HttpExchange instance
        // 2. Process request (use HttpExchange to get request method, URI, headers (e.g. authorization), etc.
        // 3. Get input stream (exchange.getRequestBody())
        // 4. Process request (convert json to object, do business logic)
        // 5. Send response code (exchange.sendResponseHeaders(responseCode, responseLength))
        // 6. Get output stream (exchange.getResponseBody())
        // 7. Write response to stream
        // 8. Close the exchange (exchange.close())
        try {
            // Only allow POST Requests
            if (exchange.getRequestMethod().toUpperCase().equals("POST")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);

                // Display/log the request JSON data
                System.out.println("Load Data");
                System.out.println(reqData);
                Gson g = new Gson();
                LoadRequest reqObject = g.fromJson(reqData, LoadRequest.class);
                LoadService serviceObject = new LoadService();
                StandardResult newResult = serviceObject.load(reqObject);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                OutputStream respBody = exchange.getResponseBody();
                generate(newResult, respBody);
                respBody.flush();
                respBody.close();

            }
            // Expected a POST, but got something else
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
