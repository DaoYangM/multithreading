package threadcoreknowledge.startThread;

/**
 * 对比Thread::start 和 Thread:run 方法
 */
public class StartAndRun {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

        Thread th = new Thread(runnable);

        // 普通方法调用
        th.run();

        th.start();
    }
}
