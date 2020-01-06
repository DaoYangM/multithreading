package threadcoreknowledge.objectandthread;

import java.util.ArrayList;
import java.util.List;

public class CustBlockingQueue {

    private static class Storage {
        private final int size;

        private List<Integer> storage;

        public Storage(int size) {
            this.size = size;
            storage = new ArrayList<>();
        }

        public synchronized void put(int e) throws InterruptedException {
            while (storage.size() >= size) {
                wait();
            }
            storage.add(e);
            notifyAll();
        }

        public synchronized int take() throws InterruptedException {
            while (storage.size() == 0) {
                wait();
            }

            int x = storage.remove(0);
            notifyAll();
            return x;
        }
    }

    public static void main(String[] args) {
        Storage storage = new Storage(10);
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                    storage.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Producer").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ": " + storage.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Consumer").start();
    }
}
