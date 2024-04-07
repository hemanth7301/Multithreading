package ThreadCreationPart1;

public class ApproachOne {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("we are now in thread " + Thread.currentThread().getName());
                System.out.println("we are now in thread " + Thread.currentThread().getPriority());
            }
        });
        thread.setName("WorkerThread");

        thread.setPriority(Thread.MAX_PRIORITY);

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting a new thread");
    }
}