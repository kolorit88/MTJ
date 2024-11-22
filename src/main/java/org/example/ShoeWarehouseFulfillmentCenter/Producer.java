package org.example.ShoeWarehouseFulfillmentCenter;

import java.util.Random;

public class Producer extends Thread{
    private final Warehouse warehouse;
    private Random rand = new Random();
    private final Integer n;

    public Producer(Warehouse warehouse, Integer n) {
        this.warehouse = warehouse;
        this.n = n;
    }

    @Override
    public void run(){
        for (int i = 0; i < n; i++){
            Order anotherOrder = new Order(i,  warehouse.shoeTypeList.get(rand.nextInt(warehouse.shoeTypeList.size())),
                    rand.nextInt(1000));
            warehouse.receiveOrder(anotherOrder);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
