package edu.missouriwestern.wwuerfele.app;

public class App {
    public static void main(String[] args) {
        System.out.println("Done");

    }//end of main

    private static Credentials getCredentials(String[] args) throws EmptyCommandLineException {
        Credentials credentials = new Credentials("???", "!!!");
        if(args.length < 1){
            throw new EmptyCommandLineException("No file name was entered in command line");
        }else {
            String fileName = args[0].trim();
            System.out.println("The file name is " + fileName);
        }

        return credentials;
    }//end of Credentials
}//end of App

