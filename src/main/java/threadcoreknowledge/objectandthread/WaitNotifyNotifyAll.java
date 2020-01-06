package threadcoreknowledge.objectandthread;

public class WaitNotifyNotifyAll {
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                    // return
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(100);

        new Thread(() -> {
            synchronized (LOCK) {
                // 并不会立马释放monitor锁而是等到synchronized代码块执行完毕后才释放monitor锁
                LOCK.notify();
                System.out.println(thread.getState());
            }
        }).start();
    }
}
