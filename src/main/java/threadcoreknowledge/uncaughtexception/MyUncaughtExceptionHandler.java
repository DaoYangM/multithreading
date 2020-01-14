package threadcoreknowledge.uncaughtexception;

/**
 * 自定义的全局未捕获异常
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.getName() + " with error " + e);
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());

        ParentThreadCannotStopWithChildThreadException.runChildThread();
        System.out.println("MERGE MERGE MASTER BRANCH");
    }
}
