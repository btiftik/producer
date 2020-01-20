package com.example.producer;

import com.example.producer.producer.CallableSender;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableJms
public class Producer {

    public static void main(String[] args) {
        SpringApplication.run(Producer.class, args);
    }

    @Lookup
    public CallableSender getCallableSender() {
        return null;
    }

    private final List<CallableSender> callableSenderList = new ArrayList<>();

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            if (args.length != 1) {
                System.out.println("Usage: java -jar {jarFileName} {NumberOfProducers}");
                System.exit(0);
            } else {
                int producerNumber = Integer.parseInt(args[0]);

                ExecutorService executorService = Executors.newFixedThreadPool(producerNumber);
                for (int i = 0; i < producerNumber; i++) {
                    System.out.println("type: " + i);
                    callableSenderList.add(getCallableSender());
                }
                System.out.println("list size: " + callableSenderList.size());
                executorService.invokeAll(callableSenderList);
                executorService.awaitTermination(5, TimeUnit.SECONDS);
                executorService.shutdown();
            }
        };
    }
}
