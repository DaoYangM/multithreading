package threadcoreknowledge.objectandthread;

public class EvenAndOdd {

    private static Integer count = 1;

    public static void main(String[] args) {
        Runnable runnable = () -> {
            while (count < 100) {
                synchronized (EvenAndOdd.class) {
                    EvenAndOdd.class.notify();
                    System.out.println(Thread.currentThread().getName() + ": " + count++);
                    if (count < 100) {
                        try {
                            EvenAndOdd.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
