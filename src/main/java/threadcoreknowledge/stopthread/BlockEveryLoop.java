package threadcoreknowledge.stopthread;

/**
 * 在每次循环中，阻塞线程
 */
public class BlockEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;


            while (num <= Integer.MAX_VALUE / 2 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + " is time of 100");
                }
                num++;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("Thread state clear");
                    System.out.println("Interrupt");
                    System.out.println("Clean context and return");
//                    return;
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread.sleep(20);

        thread.interrupt();
    }
}
