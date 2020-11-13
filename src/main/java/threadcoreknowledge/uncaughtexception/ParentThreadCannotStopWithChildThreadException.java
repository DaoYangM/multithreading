package threadcoreknowledge.uncaughtexception;

import java.util.concurrent.locks.Lock;

/**
 * 子线程抛出的非检查异常是不会影响父线程的
 */
public class ParentThreadCannotStopWithChildThreadException {

    public static void main(String[] args) {
//        runChildThread();

        threadThrowExWillReleaseLock();

    }

    /**
     * 线程在抛出异常后会释放获得的监视器锁
     */
    private static void threadThrowExWillReleaseLock() {
        final Object LOCK = new Object();

        new Thread(() -> {
            synchronized (LOCK) {
                try {
                    System.out.println(Thread.currentThread().getName() + " get Lock");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " throw");
                throw new RuntimeException();
            }
        }, "throw").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName() + " get Lock");
            }
        }, "need lock").start();
    }

    protected static void runChildThread() {
        Runnable exRun = () -> {
          throw new RuntimeException();
        };

        for (int i = 0; i < 10; i++) {
            new Thread(exRun).start();
        }

        System.out.println("Main thread still running");
    }
}
