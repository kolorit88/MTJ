package org.example;
import org.example.ShoeWarehouseFulfillmentCenter.Producer;
import org.example.ShoeWarehouseFulfillmentCenter.Warehouse;
import org.example.ShoeWarehouseFulfillmentCenter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class Main {

    public static void task1(int n) {
        Warehouse warehouse = new Warehouse();
        List<Consumer> consumerList = new ArrayList<Consumer>();

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


    public static void task2(int n){
        Warehouse2 warehouse = new Warehouse2(n / 5);
        warehouse.startConsumersPull(n / 5, 5);

        Producer producer = new Producer(warehouse, n);
        try (ExecutorService producerService = Executors.newSingleThreadExecutor()) {
            producerService.submit(producer);
            producerService.shutdown();
        }

        warehouse.stopConsumersPull();

        System.out.println("All orders have been processed");


    }



    public static void main(String[] args){
        int n = 20;
        task1(n);
//        task2(n);
    }
}
