package DataRace;

public class DataRace {
    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();
        Thread t1 = new Thread(() -> {
            while (true) {
                sharedClass.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                sharedClass.checkForDataRace();
            }
        });
        t1.start();
        t2.start();
    }

    public static class SharedClass {
        private volatile int x = 0;
        private volatile int y = 0;

        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if (y > x) {
                System.out.println("DataRace Occurred");
            }
        }
    }
}
