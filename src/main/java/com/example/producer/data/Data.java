package com.example.producer.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    @XmlElement
    private double amount;
    @XmlElement
    private long timeStamp;


    public Data() {

    }

    public Data(double amount, long timeStamp) {
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


    @Override
    public String toString() {
        return "Data{" +
                "amount=" + amount +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
