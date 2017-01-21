/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downtoearth.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rickclephas
 */
public class DatabaseAPI {
    
    private static DatabaseAPI instance;
    
    public static DatabaseAPI getInstance() {
        if(instance == null) {
            instance = new DatabaseAPI();
        }
        return instance;
    }
    
    private final String databaseName = "settings";
    private final String filePath;
    
    public DatabaseAPI(){
        filePath = System.getProperty("user.dir") + File.separator + databaseName + ".db";
        if(!Files.exists(Paths.get(filePath))) {
            try {
                Files.createFile(Paths.get(filePath));
                try(Connection connection = getConnection()){
                    Statement statement = connection.createStatement();
                    statement.execute("CREATE TABLE User(id INT NOT NULL UNIQUE DEFAULT 0 CHECK(id = 0), tokenId INT NOT NULL, token TEXT NOT NULL)");
                }
            } catch (IOException | SQLException ex) {
                Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setUser(int tokenId, String token) {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO User (tokenId, token) VALUES (?,?)");
            statement.setInt(1, tokenId);
            statement.setString(2, token);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public boolean isUserAvailable(){
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(id) FROM User");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public JSONObject getUser(){
        JSONObject user = null;
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT tokenId, token FROM User");
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new JSONObject();
                user.put("tokenId", resultSet.getInt(1));
                user.put("token", resultSet.getString(2));
            }
        } catch (SQLException | JSONException ex) {
            Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public void removeUser() {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM User");
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + filePath);
    }
    
}
