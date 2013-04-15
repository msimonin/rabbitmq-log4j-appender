package demo;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String name = "test";
    double quantity = 12;
    
    ArrayList<String> array;
    
    public Message(String name, double quantity) {        
        this.name = name;
        this.quantity = quantity;
        array = new ArrayList<String>();
        array.add("salut");
        array.add("toto");
    }
    
    
    
    public Message() {
       
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public ArrayList<String> getArray() {
        return array;
    }
    public void setArray(ArrayList<String> array) {
        this.array = array;
    }
    
    
    
    
    
}

