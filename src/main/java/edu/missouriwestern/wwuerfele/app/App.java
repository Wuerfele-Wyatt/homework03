package edu.missouriwestern.wwuerfele.app;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<String> states = new ArrayList<>();
        try{
            Credentials credentials = getCredentials(args);
            String query = "SELECT state, slug FROM states";

            retrieveStates(states, credentials, query);
            print(states, "States");

        }catch(Exception e){
            System.err.println(e.getMessage());
            System.err.println("Aborting");
            System.exit(1);
        }


        System.out.println("Done");

    }//end of main

    private static void retrieveStates(ArrayList<String> states, Credentials credentials, String query) throws SQLException {
        String connectionURL = "jdbc:mariadb://woz.csmp.missouriwestern.edu:3306/misc";
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        //connect to the database
        Connection connection = DriverManager.getConnection(connectionURL, username, password);

        //do the query
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        //iterate through the result set
        while (resultSet.next()) {
            String state = resultSet.getString("state");
            String slug = resultSet.getString("slug");
            String s = String.format("%s, %s", state, slug);
            states.add(s);
        }
    }//end of retrieveStates
    private static <T> void print(ArrayList<T> list, String title){
        System.out.println("******" + title + "*****");
        for(T item : list){
            System.out.println(item);
        }
        System.out.printf("\n%d items \n", list.size());
    }

    /**
     * The credentials file must be a single line with the username
     * and password separated by ':'
     *
     * This takes the arg list and reads the file. It then attempts
     * to read the file
     * @param args -- This should be the args array from main()
     * @return -- Returns a credential. If credential file was flawed then Username: ??? Password: !!!
     * @throws EmptyCommandLineException
     * @throws IOException
     */

    private static Credentials getCredentials(String[] args) throws EmptyCommandLineException, IOException {
        Credentials credentials = new Credentials("???", "!!!");
        if(args.length < 1){
            throw new EmptyCommandLineException("No file name was entered in command line");
        }else {
            String fileName = args[0].trim();
            System.out.println("The file name is " + fileName);
            String line = Files.readString(new File(fileName).toPath(), StandardCharsets.UTF_8);
            String[] parts = line.split(":");
            if(parts.length == 2){
                credentials.setUsername(parts[0].trim());
                credentials.setPassword(parts[1].trim());
            }
        }

        return credentials;
    }//end of Credentials
}//end of App

