package Synchronization;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
        Incrementingthread incrementingthread = new Incrementingthread(inventoryCounter);
        Decrementingthread decrementingthread = new Decrementingthread(inventoryCounter);
        incrementingthread.start();
        // incrementingthread.join();
        decrementingthread.start();
        // decrementingthread.join();
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
        private int item = 0;

        public void increment() {
            item++;
        }

        public void decrement() {
            item--;
        }

        public int getItems() {
            return item;
        }
    }
}
