package org.example;

class EvenThread extends Thread {
    @Override
    public void run() {
        int count = 0;
        int number = 2;
        while (count < 5) {
            System.out.println("EvenThread: " + number);
            number += 2;
            count++;
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class OddThread implements Runnable {
    @Override
    public void run() {
        int count = 0;
        int number = 1;
        while (count < 5) {
            System.out.println("OddThread: " + number);
            number += 2;
            count++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class OddEven {
    public static void main(String[] args) {
        EvenThread evenThread = new EvenThread();
        Thread oddThread = new Thread(new OddThread());

        evenThread.start();
        oddThread.start();

    }
}