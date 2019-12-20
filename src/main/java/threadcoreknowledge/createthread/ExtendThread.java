package threadcoreknowledge.createthread;

/**
 * 继承 {@link Thread} 类实现多线程
 */
public class ExtendThread extends Thread {

    // 重写run方法
    @Override
    public void run() {
        System.out.println("Extends thread implement Multithreading");
    }

    public static void main(String[] args) {
        ExtendThread extendThread = new ExtendThread();
        extendThread.start();
    }
}
