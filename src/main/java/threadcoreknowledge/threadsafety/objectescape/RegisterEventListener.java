package threadcoreknowledge.threadsafety.objectescape;

/**
 * 注册时间监听器，逃逸this
 */
public class RegisterEventListener {

    private int count;

    public RegisterEventListener(MySource mySource) throws InterruptedException {
        mySource.registerListener((event) -> System.out.println(count));
        Thread.sleep(500);
        this.count = 10;
    }

    public static void main(String[] args) throws InterruptedException {
        MySource mySource = new MySource();

        new Thread(() -> {
            try {
                new RegisterEventListener(mySource);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }).start();

        Thread.sleep(10);
        mySource.publishEven(null);
    }

    /**
     * 事件源，注册事件监听器，发布事件
     */
    private static class MySource {
        private Listener listener;

        void registerListener(Listener listener) {
            this.listener = listener;
        }

        void publishEven(Event event) {
            listener.listen(event);
        }
    }

    /**
     * 事件监听器
     */
    private interface Listener {
        void listen(Event event);
    }

    private static class Event { }
}
