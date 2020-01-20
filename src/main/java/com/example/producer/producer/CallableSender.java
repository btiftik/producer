package com.example.producer.producer;

import com.example.producer.data.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Random;
import java.util.concurrent.Callable;

public class CallableSender implements Callable<String> {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Marshaller marshaller;

    @Override
    public String call() throws JAXBException, InterruptedException {
        while (true) {
            Data data = new Data(getRandomDouble(), System.currentTimeMillis());
            StringWriter sw = new StringWriter();
            marshaller.marshal(data, sw);
            String xmlString = sw.toString();
            jmsTemplate.convertAndSend("mailbox", xmlString);

            Thread.sleep(1);
        }
    }

    private final Random random = new Random();

    private double getRandomDouble() {
        return ((random.nextDouble() - 0.4) * 10000);
    }
}
