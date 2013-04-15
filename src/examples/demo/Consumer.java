package demo;


import org.codehaus.jackson.map.ObjectMapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer {

    private static final String EXCHANGE_NAME = "log4j-exchange";

    public static void main(String[] argv)
                  throws java.io.IOException,
                  java.lang.InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();

        String[] routingKeys = {""};
        /*if (argv.length < 1){
            System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
            System.exit(1);
        }*/

        for(String routingKey : routingKeys){
            channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
        }

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, consumer);

        while (true) {
            try{
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                String routingKey = delivery.getEnvelope().getRoutingKey();
                System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");
                
                ObjectMapper map = new ObjectMapper();            
                Message msg = map.readValue(message, Message.class);
                System.out.println("got one message : ") ; 
                System.out.println("name : " + msg.getName()) ;
                System.out.println("quantity : " + msg.getQuantity()) ;

            }
            catch(Exception e)
            {
                System.out.println("Received Error");
                // Client should handle this ...
            }
        }
    }

}
