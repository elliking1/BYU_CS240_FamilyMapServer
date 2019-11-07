package Handler;

import Result.StandardResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class HandlerParent implements HttpHandler {

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

    }

    String readString(InputStream reqBody) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(reqBody);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

     protected void generate(StandardResult result, OutputStream output) throws IOException {
        Gson gson;
        if(result.getMessage() != null) {
            gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        }
        else {
           gson = new GsonBuilder().setPrettyPrinting().create();
        }
         String jsonString = gson.toJson(result);
         OutputStreamWriter outputWriter = new OutputStreamWriter(output);
         BufferedWriter bufferedWriter = new BufferedWriter(outputWriter);
         bufferedWriter.write(jsonString);
         bufferedWriter.flush();

         System.out.println(jsonString);
    }

}
