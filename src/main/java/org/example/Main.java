package org.example;
import org.example.ShoeWarehouseFulfillmentCenter.Producer;
import org.example.ShoeWarehouseFulfillmentCenter.Warehouse;
import org.example.ShoeWarehouseFulfillmentCenter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();
        List<Consumer> consumerList = new ArrayList<Consumer>();
        int n = 50;

        for (int i = 0; i < n / 5; i++) {
            Consumer consumer = new Consumer(warehouse, 5);
            consumerList.add(consumer);
            consumer.start();
        }

        Producer producer = new Producer(warehouse, n);
        producer.start();

        try {
            producer.join();
            for (Consumer consumer : consumerList) {
                consumer.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All orders have been processed");


    }
}
