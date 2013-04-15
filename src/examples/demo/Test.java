package demo;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {

        DOMConfigurator.configure("src/examples/configs/log4j.xml");
        Logger internalLog = Logger.getLogger("internal");
        Logger externalLog = Logger.getLogger("external");
        for (int i=0 ; i<100 ; i++){
            try {
                internalLog.info(String.format("message %d sent", i));
                externalLog.info(new Message("foo", i)); 
                System.out.println("\n");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                System.out.println("Transmission error"); 
            }
        }
    }

    

}
