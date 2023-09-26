// Only import necessary packages, don't over do it.
import javax.xml.parsers.*;
import org.xml.sax.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

/**
 * A simple class that parses and processes a specific kind of XML into a JSON
 * object and then writes on to a file.
 *
 * @author Roberto
 */
public class SimpleParser {
    // Add whatever attributes you think you'll need


    /**
     * Constructor for a SimpleParser
     */
    public SimpleParser() {
        // If appropriate/needed, set up your SimpleParser object here
    }

    /**
     *
     * @param args The program (command line) argument list
     *             args[0] = the XML file (path to it)
     *             args[1] = the JSON file (path to it) that will be created
     *             Describe any other arguments you have implemented for.
     */
    public static void main(String[] args) {
        // Write your code here
        SimpleParser parser = new SimpleParser();   //creates new parser
        String xmlPath = args[0];
        String jsonPath = args[1];
        String jsonOutput = parser.parseXMLtoJSON(xmlPath);

        parser.saveJSON(jsonOutput, jsonPath);  //saves JSON file to specified path
    }

    /**
     * This method parses and processes information from an XML file
     * into a JSON object as per the description in the assignment document.
     *
     * @param filePath The absolute or relative path to the XML file to be parsed
     * @return Returns String that contains a JSON object that represents
     *         the relevant data from the XML file
     */
    public String parseXMLtoJSON(String filePath){
        String jsonOut = "";
        // write your code here
        try {
            System.out.println("----------------");
            System.out.println("Parsing " + filePath);      //Not necessary but helpful to see what is being parsed
            System.out.println();
            //Gets instance of SAXParserFactory and gets XMLReader from it
            SAXParserFactory factory = SAXParserFactory.newInstance();  //creates new instance called factory
            XMLReader reader = factory.newSAXParser().getXMLReader();   //creates the reader that will process the XML
            xmlHandler handler = new xmlHandler();  //Registers handler for parser

            reader.setContentHandler(handler);  //sets handler for the reader
            reader.setErrorHandler(handler);

            //The data is given to the parser
            InputSource inputSource = new InputSource(filePath);
            reader.parse(inputSource);
            System.out.println("----------------");
            System.out.println();



            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            jsonOut = gson.toJson(handler.getResult()); //Saves the output in a variable called jsonOut (string)

        } catch (Exception exception) {
            System.err.println("Could not parse file - " + exception);  //Throws an exception if file was not parsed
        }
        return jsonOut;
    }

    /**
     * Writes a string containing a JSON object into a file
     * @param jsonObj The string containing the JSON object
     * @param filePath The absolute or relative path to the JSON file to be written
     */
    public void saveJSON(String jsonObj, String filePath){
        // write your code here
        try {
            FileWriter writer = new FileWriter (filePath);
            writer.write(jsonObj);  //Saves file using filewriter
            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}


// Modified 25/01/2023