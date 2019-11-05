package Handler;

import Result.AllPeopleResult;
import Result.PersonResult;
import Service.AllPeopleService;
import Service.PersonService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class PersonHandler extends HandlerParent {
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
                if (reqHeaders.containsKey("Authorization")) {
                    String authToken = reqHeaders.getFirst("Authorization");

                    if(urlPath.length() < 9){
                        AllPeopleService serviceObject = new AllPeopleService();
                        AllPeopleResult newResult = serviceObject.getAllPeople(authToken);
                        OutputStream respBody = exchange.getResponseBody();
                        generate(newResult, respBody);
                        respBody.flush();
                        respBody.close();
                    }
                    else {
                        StringBuilder person = new StringBuilder();
                        for (int i = 8; i < urlPath.length(); i++) {
                            person.append(urlPath.charAt(i));
                        }
                        String personID = person.toString();
                        PersonService serviceObject = new PersonService();
                        PersonResult newResult = serviceObject.getPerson(personID, authToken);
                        OutputStream respBody = exchange.getResponseBody();
                        generate(newResult, respBody);
                        respBody.flush();
                        respBody.close();
                    }
                }
                else {
                    // We did not get an auth token, so we return a "not authorized"
                    // status code to the client.
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_UNAUTHORIZED, 0);
                    System.out.println("Don't have an Auth Token.");
                }
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
