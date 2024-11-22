package org.example.ShoeWarehouseFulfillmentCenter;

public class Consumer extends Thread {
    private final Warehouse warehouse;
    private Integer numOrdersToFulfill;

    public Consumer(Warehouse warehouse, Integer numOrdersToFulfill) {
        this.warehouse = warehouse;
        this.numOrdersToFulfill = numOrdersToFulfill;
    }

    @Override
    public void run() {
        for(int i = 0; i < numOrdersToFulfill; i++) {
            warehouse.fulfillOrder();
        }
    }

}
