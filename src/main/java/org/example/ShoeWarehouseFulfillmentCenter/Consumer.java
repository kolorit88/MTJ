package org.example.ShoeWarehouseFulfillmentCenter;

public class Consumer extends Thread {
    private final Warehouse warehouse;
    private final Integer numOrdersToFulfill;

    public Consumer(Warehouse warehouse, Integer numOrdersToFulfill) {
        this.warehouse = warehouse;
        this.numOrdersToFulfill = numOrdersToFulfill;
    }

    @Override
    public void run() {
        for(int i = 0; i < numOrdersToFulfill; i++) {
            try {
                warehouse.fulfillOrder();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
