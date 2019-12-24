package threadcoreknowledge.stopthread;

/**
 * 正常没有阻塞的情况下停止线程
 */
public class NormalCase {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            // 线程启动时轮询线程状态
            while (!Thread.currentThread().isInterrupted() && num < Integer.MAX_VALUE / 2) {
                if (num % 10000 == 0) {
                    System.out.println(num);
                }
                num++;
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(1000);
        // 另一个线程通知，停止任务线程
        thread.interrupt();
    }
}
