package com.plant42.log4j.layouts;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

public class RawJSONLayout extends Layout {

    @Override
    public void activateOptions() {
        // TODO Auto-generated method stub

    }

    @Override
    public String format(LoggingEvent event) {
        StringWriter stringWriter = new StringWriter();
        
        
        try {
            //== write basic fields
            writeBasic(stringWriter, event);

            //== write throwable fields
            //writeThrowable(stringWriter, event);

            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringWriter.toString();
    }

    private void writeBasic(StringWriter stringWriter, LoggingEvent event) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper map = new ObjectMapper();
        map.writeValue(stringWriter, event.getMessage());
        
    }

    @Override
    public boolean ignoresThrowable() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
