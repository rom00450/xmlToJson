import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;


public class xmlHandler extends DefaultHandler{

    //Several print lines to keep track of how the program is processing the XML file

    //Instantiate attributes used
    private List<Map<String, String>> list = new ArrayList<>();
    private Map<String, String> map = new LinkedHashMap<>();
    private String elementName;

    //Method called when characters are present
    public void characters (char ch[], int start, int length){
        String characters = new String(ch,start,length).trim(); //.trim gets rid of empty space
        System.out.println(length);
        if(!characters.isEmpty()) {
            System.out.println("Characters '" + characters + "'");
            map.put(elementName, characters);   //Adds the data into LinkedHashMap
        }
    }

    public void endDocument() throws SAXException {
        System.out.println("endDocument callback");
    }

    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {
        System.out.println("endElement callback for '" + qName + "' '" + localName + "'");
        if (qName.equals(elementName)) {    //Adds to list which then creates a new linkedHashMap for new values
            list.add(map);
            map = new LinkedHashMap<>();
        }
    }

    public void startDocument() throws SAXException{
        System.out.println("startDocument callback");
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException{
        elementName = qName;
        for(int i = 0; i < attributes.getLength(); i++){
            System.out.println("attribute '" + attributes.getQName(i) + "' is '" + attributes.getValue(i) + "'");
            map.put(attributes.getQName(i), attributes.getValue(i));    //For loop to add key and value to map
        }
    }

    public List<Map<String, String>> getResult() {
        return list;    //returns the resultant list
    }

}