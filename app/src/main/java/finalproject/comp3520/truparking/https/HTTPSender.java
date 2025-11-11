package finalproject.comp3520.truparking.https;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HTTPSender {

    private static final String WEBSERVER_URL = "https://truparking-webapp.onrender.com";

    private static String sendHTTPRequest(String extension, String method, Map<String, String> args) throws IOException {
        URL destURL = new URL(String.format("%s/%s", WEBSERVER_URL, extension).toString());
        HttpURLConnection connection = (HttpURLConnection) destURL.openConnection();
        connection.setRequestMethod(method);

        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(HTTPArgBuilder.argsToString(args));
        outputStream.flush();
        outputStream.close();

        return "";
    }
}
