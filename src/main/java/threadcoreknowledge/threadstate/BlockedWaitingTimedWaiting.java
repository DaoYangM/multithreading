package threadcoreknowledge.threadstate;

/**
 * 线程状态 Blocked -> Waiting -> TimedWaiting
 */
public class BlockedWaitingTimedWaiting {

    private static Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            synchronized (LOCK) {
                try {
                    Thread.sleep(3000);
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        t1.start();

        Thread.sleep(100);

        Thread t2 = new Thread(runnable);
        t2.start();

        Thread.sleep(100);

        // Blocked.
        System.out.println(t2.getState());

        // Timed Waiting
        System.out.println(t1.getState());

        Thread.sleep(5000);

        // Waiting
        System.out.println(t1.getState());

    }
}
