package jmm.reorder;

import java.util.concurrent.CountDownLatch;

/**
 * 可能发生的重排序
 */
public class PossibleReordering {

    static int x = 0, y = 0;
    static int a = 0, b = 0;
    static int count;

    public static void main(String[] args) throws InterruptedException {
        do {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch countDownLatch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            other.start();
            one.start();
            countDownLatch.countDown();
            one.join();
            other.join();

            System.out.println("count = " + count++ + ", x = " + x + ", y = " + y);
        } while (x != 0 || y != 0);
    }
}
