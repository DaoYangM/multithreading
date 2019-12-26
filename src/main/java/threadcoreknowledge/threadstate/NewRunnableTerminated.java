package threadcoreknowledge.threadstate;

/**
 * 线程的状态 NEW -> RUNNABLE -> TERMINATED
 */
public class NewRunnableTerminated {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                if (i == 1000) {
                    System.out.println(Thread.currentThread().getState());
                }
                System.out.println(i);
            }
        };

        Thread thread = new Thread(runnable);
        // NEW
        System.out.println(thread.getState());

        thread.start();
        // 只要线程调用start()方法那么 状态就是RUNNABLE
        System.out.println(thread.getState());

        // 调用join方法的线程等待thread对象执行完毕
        thread.join();
        // TERMINATED
        System.out.println(thread.getState());
    }
}
