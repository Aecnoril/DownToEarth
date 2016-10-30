package downtoearth.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rickclephas
 */
public class ServerAPI {
    
    private static final String SERVER_URL = "https://downtoearth.clephas.synology.me";
    
    private static final X509TrustManager trustManagers = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };

    public enum Error{
        /**
         * Something went horrible wrong
         */
        FATAL
    }
    
    public static class Response{
        
        private boolean success;
        private int statusCode;
        private String response;
        private Error error;
        
        /**
         * Create error response
         * @param error the error
         */
        public Response(Error error){
            this.statusCode = statusCode;
            this.error = error;
            this.success = false;
        }
        
        /**
         * Create success response
         * @param statusCode the HTTP status code
         * @param response the response body
         */
        public Response(int statusCode, String response){
            this.statusCode = statusCode;
            this.response = response;
            this.success = true;
        }
        
        /**
         * Check if the request was successful
         * @return true if the request was successful, false otherwise
         */
        public boolean isSuccess(){
            return success;
        }
        
        /**
         * Get the HTTP status code returned by the server
         * @return HTTP status code
         */
        public int getStatusCode(){
            return statusCode;
        }
        
        /**
         * Get the response send by the server
         * @return 
         */
        public String getResponse(){
            return response;
        }
        
        /**
         * Get the JSONObject response send by the server
         * @return the JSONObject or null if there isn't one
         */
        public JSONObject getJSONObjectResponse(){
            try {
                return new JSONObject(response);
            } catch (JSONException ex) {}
            return null;
        }
        
        /**
         * Get the JSONArray response send by the server
         * @return the JSONArray or null if there isn't one
         */
        public JSONArray getJSONArrayResponse(){
            try {
                return new JSONArray(response);
            } catch (JSONException ex) {}
            return null;
        }
        
    }
    
    public static interface ResponseListener{
        /**
         * Callback called when the request has a response
         * @param response the response containgin the status and response data of the request
         */
        void onResponse(Response response);
    }
    
    /**
     * Register the user 
     * @param email the email of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param responseListener callback with response. 400 = email or username already used (see response for which one), 200 = account created (JSONObject with token and tokenId)
     */
    public static void register(String email, String username, String password, ResponseListener responseListener){
        try {
            JSONObject body = new JSONObject();
            body.put("email", email);
            body.put("username", username);
            body.put("password", password);
            sendRequest("PUT", "user", null, null, body.toString(), null, responseListener);
        } catch (JSONException ex) {}
    }
        
    /**
     * Login the user (create a session)
     * @param username the username of the user
     * @param password the password of the user
     * @param responseListener callback with response. 400 = username and/or password is incorrect, 200 = login success (JSONObject with token and tokenId)
     */
    public static void login(String username, String password, ResponseListener responseListener){
        try {
            JSONObject body = new JSONObject();
            body.put("username", username);
            body.put("password", password);
            sendRequest("PUT", "session", null, null, body.toString(), null, responseListener);
        } catch (JSONException ex) {}
    }
    
    /**
     * Logout the user (end the session)
     * @param token token of the session
     * @param tokenId tokenId of the session
     * @param responseListener callback without response. 200 = session ended
     */
    public static void logout(String token, String tokenId, ResponseListener responseListener){
        sendRequest("DELETE", "session", token, tokenId, null, null, responseListener);
    }
    
    /**
     * Update user account
     * @param token token of the session
     * @param tokenId tokenId of the session
     * @param email the new email of the user (or null to keep the current one)
     * @param username the new username of the user (or null to keep the current one)
     * @param password the new password of the user (or null to keep the current one)
     * @param responseListener callback without response. 200 = data updated
     */
    public static void updateUserAccount(String token, String tokenId, String email, String username, String password, ResponseListener responseListener){
        try {
            JSONObject body = new JSONObject();
            if(email != null){
                body.put("email", email);
            }
            if(username != null){
                body.put("username", username);
            }
            if(password != null){
                body.put("password", password);
            }
            sendRequest("POST", "user", token, tokenId, body.toString(), null, responseListener);
        } catch (JSONException ex) {}
    }
    
    /**
     * Delete the user his account
     * @param token token of the session
     * @param tokenId tokenId of the session
     * @param responseListener callback without response. 200 = account deleted
     */
    public static void deleteUserAcccount(String token, String tokenId, ResponseListener responseListener){
        sendRequest("DELETE", "user", token, tokenId, null, null, responseListener);
    }
    
    /**
     * Find a user with username
     * @param token token of the session
     * @param tokenId tokenId of the session
     * @param username the username to search for
     * @param responseListener callback with response. 404 = no user found, 200 = found 1 user (JSONObject with username and email), 600 = list of matching users (JSONArray users their username and email)
     */
    public static void findUser(String token, String tokenId, String username, ResponseListener responseListener){
        HashMap<String, String> args = new HashMap<>();
        args.put("username", username);
        sendRequest("GET", "user", token, tokenId, null, args, responseListener);
    }
    
    private static void sendRequest(final String method, final String endpoint, final String token, final String tokenId, final String body, final HashMap<String, String> args, final ResponseListener responseListener){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = new Response(Error.FATAL);
                String urlArgs = "";
                if(args != null){
                    for(Map.Entry<String, String> arg : args.entrySet()){
                        urlArgs += "&" + arg.getKey() + "=" + arg.getValue();
                    }
                    urlArgs = urlArgs.replaceFirst("&", "?");
                }
                try {
                    URL url = new URL(SERVER_URL + "/" + endpoint + urlArgs);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod(method);
                    httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                    String authorization = "";
                    if(token != null){
                        authorization += "token=" + token + ";";
                    }
                    if(tokenId != null){
                        authorization += "tokenId=" + tokenId + ";";
                    }
                    if(!authorization.equals("")) {
                        httpsURLConnection.setRequestProperty("Authorization", authorization);
                    }
                    httpsURLConnection.setDoInput(true);
                    SSLContext sslContext = SSLContext.getInstance("SSL");
                    sslContext.init(null, new TrustManager[]{trustManagers}, new SecureRandom());
                    httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
                    if(body != null){
                        httpsURLConnection.setDoOutput(true);
                        OutputStream outputStream = httpsURLConnection.getOutputStream();
                        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"))) {
                            bufferedWriter.write(body);
                        }
                    }
                    httpsURLConnection.connect();

                    StringBuilder stringBuilder = new StringBuilder();
                    InputStream inputStream;
                    try{
                        inputStream = httpsURLConnection.getInputStream();
                    } catch (Exception e){
                        inputStream = httpsURLConnection.getErrorStream();
                    }
                    try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))){
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                    }

                    httpsURLConnection.disconnect();

                    response = new Response(httpsURLConnection.getResponseCode(), stringBuilder.toString());
                } catch (IOException | KeyManagementException | NoSuchAlgorithmException ex) {
                    System.out.println(ex.toString());
                }
                if(responseListener != null){
                    final Response finalResponse = response;
                    new JFXPanel();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            responseListener.onResponse(finalResponse);
                        }
                    });
                }
            }
        });
        thread.start();
    }
    
}
