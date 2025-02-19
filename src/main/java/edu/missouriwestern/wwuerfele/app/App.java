package edu.missouriwestern.wwuerfele.app;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class App {
    public static void main(String[] args) {
        


        System.out.println("Done");

    }//end of main

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
            if(parts.length != 2){
                credentials.setUsername(parts[0]);
                credentials.setPassword(parts[1]);
            }
        }

        return credentials;
    }//end of Credentials
}//end of App

