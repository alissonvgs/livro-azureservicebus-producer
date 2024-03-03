package com.estudo.azureservicebus.producer.azure.servicebus;


import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusProcessorClient;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBusProcessorClientConfiguration {

    @Value("${azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${spring.cloud.azure.servicebus.entity-name}")
    private String queueName;

    @Bean
    public ServiceBusProcessorClient serviceBusProcessorClient() {
        return new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .processor()
                .queueName(queueName)
                .processMessage(this::processMessage)
                .processError(context -> processError(context.getException()))
                .buildProcessorClient();
    }

    @Bean
    public ServiceBusSenderClient serviceBusSenderClient() {
        return new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient();
    }


    private void processMessage(ServiceBusReceivedMessageContext context) {
        System.out.println("Received message: " + context.getMessage().getBody().toString());
    }


    private void processError(Throwable throwable) {
        System.err.println("Error occurred: " + throwable.getMessage());
    }
}
