package threadcoreknowledge.createthread;

/**
 * 实现 {@link Runnable} 接口创建线程
 */
public class RunnableImpl implements Runnable {

    public void run() {
        System.out.println("Runnable implement Multithreading");
    }

    public static void main(String[] args) {
        new Thread(new RunnableImpl()).start();
    }
}
