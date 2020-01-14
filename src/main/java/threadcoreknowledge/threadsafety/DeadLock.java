package threadcoreknowledge.threadsafety;

/**
 * 死锁
 */
public class DeadLock {

    private static final Object LOCK1 = new Object();

    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        new Thread(new Dead(true)).start();
        new Thread(new Dead(false)).start();
    }

    private static class Dead implements Runnable {

        private boolean sign;

        public Dead(boolean sign) {
            this.sign = sign;
        }

        @Override
        public void run() {
            if (sign) {
                synchronized (LOCK1) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                        return;
                    }

                    synchronized (LOCK2) {

                    }
                }
            } else {
                synchronized (LOCK2) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                        return;
                    }
                    synchronized (LOCK1) {

                    }
                }
            }
        }
    }

}
