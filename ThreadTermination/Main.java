package ThreadTermination;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTask());
        thread.setDaemon(true);
        thread.start();
        // thread.interrupt();
    }

    public static class BlockingTask implements Runnable {
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println("Here");
            } catch (Exception e) {
                System.out.println("Existing blocking thread");
            }
        }
    }
}
