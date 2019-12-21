package threadcoreknowledge.createthread;

/**
 * 两种方法同时使用
 */
public class ExtendsThreadImplRunnable {

    public static void main(String[] args) {

        // run方法被匿名内部类重写了
        new Thread(() -> System.out.println("From Runnable")) {
            @Override
            public void run() {
                System.out.println("From Thread");
            }
        }.start();
    }
}
