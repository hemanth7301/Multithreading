public class HandlingException {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                throw new RuntimeException("Critical Exception Thrown");
            }
        });
        thread.setName("CriticalThread");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName()
                        + " the error is " + e.getMessage());
            }
        });
        thread.start();
    }
}