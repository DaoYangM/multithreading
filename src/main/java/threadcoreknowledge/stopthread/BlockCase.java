package threadcoreknowledge.stopthread;

/**
 * 线程在阻塞的情况下相应中断, 而且在相应中断后代码还是会继续运行
 */
public class BlockCase {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (!Thread.currentThread().isInterrupted() && num <= 300) {
                if (num % 100 == 0) {
                    System.out.println(num + " is time of 100");
                }
                num++;
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Interrupt");
            }
            System.out.println("After Interrupt code is still running");
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(2000);
        thread.interrupt();
    }
}
