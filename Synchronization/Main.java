package Synchronization;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        Incrementingthread incrementingthread = new Incrementingthread(inventoryCounter);
        Decrementingthread decrementingthread = new Decrementingthread(inventoryCounter);
        incrementingthread.start();
        decrementingthread.start();
        incrementingthread.join();
        decrementingthread.join();
        System.out.println("We currently have " + inventoryCounter.getItems() + " items");
    }

    public static class Incrementingthread extends Thread {
        InventoryCounter inventoryCounter;

        Incrementingthread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.increment();
            }
        }
    }

    public static class Decrementingthread extends Thread {
        InventoryCounter inventoryCounter;

        Decrementingthread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        public void run() {
            for (int i = 0; i < 10000; i++) {
                inventoryCounter.decrement();
            }
        }
    }

    private static class InventoryCounter {
        private int items = 0;

        Object lock = new Object();

        public void increment() {
            synchronized (this.lock) {
                items++;
            }
        }

        public void decrement() {
            synchronized (this.lock) {
                items--;
            }
        }

        public int getItems() {
            synchronized (this.lock) {
                return items;
            }
        }
    }
}
