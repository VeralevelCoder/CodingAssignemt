package main.java.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    JSONParser jsonParser;
    FileReader fileReader;

    //Read Json file
    Object obj;

    public JSONReader() throws IOException, ParseException {
        jsonParser = new JSONParser();
    }
    public JSONObject getJsonData(String fileName) throws IOException, ParseException {
        fileReader=new FileReader("src/"+fileName);
        obj=jsonParser.parse(fileReader);
        return (JSONObject)obj;
    }
}
