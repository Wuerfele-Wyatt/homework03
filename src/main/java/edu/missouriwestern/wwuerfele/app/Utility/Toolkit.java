package edu.missouriwestern.wwuerfele.app.Utility;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class is a collection of general purpose methods
 * for working w/ ArrayLists.
 *
 * @author Wyatt Wuerfele
 * @since February 2025
 */
public class Toolkit {
    /**
     * This method prints the array list passed
     * @param list:ArrayList<String>
     * @param title:String
     */
    public static <T> void printList(ArrayList<T> list, String title) {
        System.out.println(title);
        for(T line: list){
            System.out.println(line);
        }
        System.out.printf("\nThere were %d lines in the list\n", list.size());
    }//end of print list

    /**
     * This method reads lines of text from url specified in address.
     * Stored in list
     * @param address:String The URL of the text file
     * @param list:ArrayList<String> List of lines that have been read
     */
    public static void readURL(String address, ArrayList<String> list) {
        try {
            URL url = new URL(address);

            InputStreamReader inStream = new InputStreamReader(url.openStream());
            BufferedReader input = new BufferedReader(inStream);
            //read lines
            String line;
            while((line = input.readLine()) != null){
                line = line.trim();
                list.add(line);
            }
            input.close();
            inStream.close();
        } catch (IOException e) {
            System.out.printf("Error reading URL: %s\n", address);
        }

    }//end of readURL
    public static <T> void writeMarkdown(ArrayList<T> list, String pageTitle, String[] columnHeaders, String fileName) throws FileNotFoundException, InvocationTargetException, IllegalAccessException {
        Class theClass = list.get(0).getClass();
        PrintWriter writer = null;
        Method[] getters = getGetters(theClass);
        try {
            writer = new PrintWriter(new File(fileName));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        //write the title page
        writer.printf("# %s\n\n", pageTitle);

        //make the header
        writer.print("|");
        for (String header : columnHeaders){
            writer.printf(" %s |", header);
        }
        writer.println();
        writer.print("|");
        for(var header : columnHeaders){
            writer.printf(" :---: |");
        }
        writer.println();

        //write the body of the table
        for (int i = 0; i <= list.size()-1; i+=2){
            writer.print("|");
            writer.printf(" %s |", list.get(i));
            writer.printf(" %s |", list.get(i+1));
            writer.println();
        }


        writer.close();

    }//end of writeMarkdownCountries

    private static Method[] getGetters(Class theClass) {
        Method[] methods = theClass.getMethods();
        ArrayList<Method> getters = new ArrayList<>();
        for(Method method : methods){
            if(method.getName().startsWith("get")
                    && method.getParameterCount() == 0
                    && !void.class.equals(method.getReturnType())
                    && !method.getName().equals("getClass")
            ) {
                getters.add(method);
            }
        }
        //printList(getters, "Getters");
        Method[] gettersArray = new Method[getters.size()];
        getters.toArray(gettersArray);
        return gettersArray;
    }//end of getGetters

    public static<T> void writeHTML(ArrayList<T> items, String pageTitle, String[] columnHeaders, String fileName) throws InvocationTargetException, IllegalAccessException {
        Class theClass = items.get(0).getClass();
        Method[] getters = getGetters(theClass);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(fileName));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        String css = """
                <style>
                    h1 {color:blue;}
                    table, th, td {
                        border: 1px solid black;
                        border-collapse: collapse;
                    }
                    td {text-align:right;}
                </style>""";
        writer.println(css);

        //write title page
        writer.printf("<h1>%s</h1>\n", pageTitle);

        //make the header
        writer.println("<table>");
        writer.print("<tr>");
        for (String header: columnHeaders){
            writer.printf("<th>%s</ts>", header);
        }
        writer.println("</tr>");

        //write the body of the table
        for (var item : items){
            writer.print("<tr>");
            for(int i = 0; i < getters.length; i++){
                try{
                    writer.printf("<td>%s</td>", formatGetter(getters[i], item));
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
            writer.println("</tr>");
        }
        writer.println("</table>");

        writer.close();


    }//end of writeHTML

    private static <T> Object formatGetter(Method getter, T item) throws InvocationTargetException, IllegalAccessException {
        String result = "??";
        //find type of return value
        Class<?> returnType = getter.getReturnType();

        switch (returnType.getSimpleName()){
            case "String":
                result = String.format("&nbsp;%-35s&nbsp;", getter.invoke(item));
                break;
            case "int":
            case "long":
            case "Integer":
            case "Long":
                result = String.format("&nbsp;%-,16d&nbsp;", getter.invoke(item));
                break;
        }
        return result;
    }

}//end of formatGetter
