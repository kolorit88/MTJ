package org.example.ShoeWarehouseFulfillmentCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warehouse {
    public static List<String> shoeTypeList = List.of("sneakers", "shoes", "slippers");
    private final List<Order> orderList = new ArrayList<Order>();
    private final Random random = new Random();

    public synchronized void receiveOrder(Order order) {
        while (orderList.size() >= 10){
            try {
                System.out.println("The warehouse is full. The producer is waiting for the space to be released...");
                wait();
            }
            catch (InterruptedException e){Thread.currentThread().interrupt();}
        }
        orderList.add(order);
        System.out.println("The producer added an order:" + order);
        notifyAll();

    }

    public synchronized void fulfillOrder(){
        while (orderList.isEmpty()) {
            System.out.println("The warehouse is empty. The consumer is waiting for orders...");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("The consumer has started processing the order:" + orderList.getFirst());
        try {
            Thread.sleep(random.nextInt(500) + 100);
        }
        catch (InterruptedException e){Thread.currentThread().interrupt();}
        orderList.removeFirst();
        notifyAll();
    }
}

