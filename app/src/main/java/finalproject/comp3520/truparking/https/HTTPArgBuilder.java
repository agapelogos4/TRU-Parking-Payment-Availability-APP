package finalproject.comp3520.truparking.https;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class HTTPArgBuilder {
    public static String argsToString(Map<String, String> args) throws UnsupportedEncodingException {
        if(args.isEmpty()) return "";
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, String> entry : args.entrySet()) {
            String key = URLEncoder.encode(entry.getKey(), "UTF-8");
            String value = URLEncoder.encode(entry.getValue(), "UTF-8");
            builder.append(key).append("=").append(value).append("&");

        }
        String argStrings = builder.toString();
        return argStrings.substring(0, argStrings.length() - 1);
    }
}
