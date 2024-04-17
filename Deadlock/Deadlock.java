package Deadlock;

import java.util.Random;

public class Deadlock {
    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        Thread trainAThread = new Thread(new TrainA(intersection));
        Thread trainBThread = new Thread(new TrainB(intersection));

        trainAThread.start();
        trainBThread.start();
    }

    public static class TrainB implements Runnable {
        private Intersection intersection;

        public TrainB(Intersection intersection) {
            this.intersection = intersection;
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                }

                intersection.takeRoadB();
            }
        }
    }

    public static class TrainA implements Runnable {
        private Intersection intersection;

        public TrainA(Intersection intersection) {
            this.intersection = intersection;
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                }

                intersection.takeRoadA();
            }
        }
    }

    public static class Intersection {
        Object roadA = new Object();
        Object roadB = new Object();

        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road A is occupied by " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Train is passing through road A");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }

        public void takeRoadB() {
            synchronized (roadB) {
                System.out.println("Road B is occupied by " + Thread.currentThread().getName());
                synchronized (roadA) {
                    System.out.println("Train is passing through road B");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }
    }
}
