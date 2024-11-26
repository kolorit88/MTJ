package org.example.ShoeWarehouseFulfillmentCenter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderQueue {
    private final BlockingQueue<Order> queue;

    public OrderQueue() {
        queue = new LinkedBlockingQueue<>(10);
    }

    public void put(Order order) throws InterruptedException {
        queue.put(order);
    }

    public Order take() throws InterruptedException {
        return queue.take();
    }

    public Integer size() {
        return queue.size();
    }
}

