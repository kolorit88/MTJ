package org.example.ShoeWarehouseFulfillmentCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Warehouse2 extends Warehouse {
    private final OrderQueue queue = new OrderQueue();
    private final ExecutorService consumersEexecutorService;

    public Warehouse2(int maxConsumers){
        this.consumersEexecutorService = Executors.newFixedThreadPool(maxConsumers);
    }


    public synchronized void receiveOrder(Order order) throws InterruptedException {

        while (queue.size() >= 10){
            try {
                System.out.println("The warehouse is full. The producer is waiting for the space to be released...");
                wait();
            }
            catch (InterruptedException e){Thread.currentThread().interrupt();}
        }

        queue.put(order);
        System.out.println("The producer added an order:" + order);
        notifyAll();
    }

    public synchronized void fulfillOrder() throws InterruptedException {

        while (queue.size() == 0) {
            System.out.println("The warehouse is empty. The consumer is waiting for orders...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("The consumer has started processing the order:" + queue.take());
        try {
            Thread.sleep(random.nextInt(500) + 100);
        }
        catch (InterruptedException e){Thread.currentThread().interrupt();}

        notifyAll();
    }

    public void startConsumersPull(int quantity, int numOrdersToFulfill){
        for (int i = 0; i < quantity; i++) {
            consumersEexecutorService.submit(new Consumer(this, numOrdersToFulfill));
        }
    }
    public void stopConsumersPull(){
        consumersEexecutorService.shutdown();
    }


}
