package downtoearth.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    /**
     * Used to determine which error occurred while making a server request 
     */
    public enum Error{
        /**
         * Something went horrible wrong
         */
        FATAL
    }
    
    /**
     * Used to provide the responses from the server requests
     */
    public static class Response{
        
        private final boolean success;
        private final int statusCode;
        private final String responseBody;
        private final Error error;
        
        /**
         * Create error response
         * @param error the error
         */
        public Response(Error error){
            this.error = error;
            this.success = false;
            this.statusCode = 0;
            this.responseBody = null;
        }
        
        /**
         * Create success response
         * @param statusCode the HTTP status code
         * @param responseBody the response body
         */
        public Response(int statusCode, String responseBody){
            this.statusCode = statusCode;
            this.responseBody = responseBody;
            this.success = true;
            this.error = null;
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
            return responseBody;
        }
        
        /**
         * Get the JSONObject response send by the server
         * @return the JSONObject or null if there isn't one
         */
        public JSONObject getJSONObjectResponse(){
            try {
                return new JSONObject(responseBody);
            } catch (JSONException ex) {
                Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        
        /**
         * Get the JSONArray response send by the server
         * @return the JSONArray or null if there isn't one
         */
        public JSONArray getJSONArrayResponse(){
            try {
                return new JSONArray(responseBody);
            } catch (JSONException ex) {
                Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        
        /**
         * Get the error that occurred during the request
         * @return the error
         */
        public Error getError(){
            return error;
        }
        
    }
    
    /**
     * Listener used to provide the response from the server
     */
    @FunctionalInterface
    public static interface ResponseListener{
        /**
         * Callback called when the request has a response
         * @param response the response containing the status and response data of the request
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
        } catch (JSONException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
            responseListener.onResponse(new Response(Error.FATAL));
        }
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
        } catch (JSONException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
            responseListener.onResponse(new Response(Error.FATAL));
        }
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
        } catch (JSONException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
            responseListener.onResponse(new Response(Error.FATAL));
        }
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
    
    /**
     * Create a new game
     * @param token token of the session
     * @param tokenId tokenId of the session
     * @param gameName a name for the new game
     * @param hostIP the IP-Address of the host
     * @param isPrivate true if this game is a private game, false otherwise
     * @param responseListener callback with response. 200 = game created (JSONObject with the gameID)
     */
    public static void createGame(String token, String tokenId, String gameName, String hostIP, boolean isPrivate, ResponseListener responseListener){
        try {
            JSONObject body = new JSONObject();
            body.put("name", gameName);
            body.put("hostIP", hostIP);
            body.put("private", isPrivate ? 1 : 0);
            sendRequest("PUT", "game", token, tokenId, body.toString(), null, responseListener);
        } catch (JSONException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
            responseListener.onResponse(new Response(Error.FATAL));
        }
    }
    
    /**
     * Join a game
     * @param token token of the session
     * @param tokenId tokenId of the session
     * @param gameId the gameId
     * @param responseListener callback with response. 400 = Invalid gameId | Already joined, 201 = Joined, 202 = Awaiting acceptation
     */
    public static void joinGame(String token, String tokenId, int gameId, ResponseListener responseListener){
        HashMap<String, String> args = new HashMap<>();
        args.put("action", "join");
        try {
            JSONObject body = new JSONObject();
            body.put("gameId", gameId);
            sendRequest("POST", "game", token, tokenId, body.toString(), args, responseListener);
        } catch (JSONException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
            responseListener.onResponse(new Response(Error.FATAL));
        }
    }
    
    /**
     * Leave a game
     * @param token token of the session
     * @param tokenId tokenId of the session
     * @param gameId the gameId
     * @param responseListener callback with response. 400 = Invalid gameId | Already joined | You are the host, 200 = left game
     */
    public static void leaveGame(String token, String tokenId, int gameId, ResponseListener responseListener){
        HashMap<String, String> args = new HashMap<>();
        args.put("action", "leave");
        try {
            JSONObject body = new JSONObject();
            body.put("gameId", gameId);
            sendRequest("DELETE", "game", token, tokenId, body.toString(), args, responseListener);
        } catch (JSONException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
            responseListener.onResponse(new Response(Error.FATAL));
        }
    }
    
    /**
     * Delete a game
     * @param token token of the session
     * @param tokenId tokenId of the session
     * @param gameId the gameId
     * @param responseListener callback with response. 400 = Invalid gameId | You are not the host, 200 = game deleted
     */
    public static void deleteGame(String token, String tokenId, int gameId, ResponseListener responseListener){
        try {
            JSONObject body = new JSONObject();
            body.put("gameId", gameId);
            sendRequest("DELETE", "game", token, tokenId, body.toString(), null, responseListener);
        } catch (JSONException ex) {
            Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
            responseListener.onResponse(new Response(Error.FATAL));
        }
    }
    
    /**
     * Get info about a game
     * @param token token of the session
     * @param tokenId tokenId of the session
     * @param gameId the gameId
     * @param responseListener callback with response. 400 = Invalid gameId, 200 = success (JSONObject with info about the game)
     */
    public static void getGameInfo(String token, String tokenId, int gameId, ResponseListener responseListener){
        HashMap<String, String> args = new HashMap<>();
        args.put("gameId", Integer.toString(gameId));
        sendRequest("GET", "game", token, tokenId, null, args, responseListener);
    }
    
    private static void sendRequest(final String method, final String endpoint, final String token, final String tokenId, final String body, final HashMap<String, String> args, final ResponseListener responseListener){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = new Response(Error.FATAL);
                String urlArgs = "";
                StringBuilder urlArgsBuilder = new StringBuilder();
                if(args != null){
                    for(Map.Entry<String, String> arg : args.entrySet()){
                        urlArgsBuilder.append("&").append(arg.getKey()).append("=").append(arg.getValue());
                    }
                    urlArgs = urlArgsBuilder.toString().replaceFirst("&", "?");
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
                    if(!"".equals(authorization)) {
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
                        Logger.getLogger(ServerAPI.class.getName()).log(Level.INFO, null, e);
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
                    Logger.getLogger(ServerAPI.class.getName()).log(Level.SEVERE, null, ex);
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
