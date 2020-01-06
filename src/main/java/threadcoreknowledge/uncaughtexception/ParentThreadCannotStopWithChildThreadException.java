package threadcoreknowledge.uncaughtexception;

/**
 * 子线程抛出的非检查异常是不会影响父线程的
 */
public class ParentThreadCannotStopWithChildThreadException {

    public static void main(String[] args) {
        runChildThread();
    }

    protected static void runChildThread() {
        Runnable exRun = () -> {
          throw new RuntimeException();
        };

        for (int i = 0; i < 10; i++) {
            new Thread(exRun).start();
        }

        System.out.println("Main thread still running");
    }
}
