package threadcoreknowledge.threadsafety.objectescape;

/**
 * 在构造函数中，把this赋值给外部
 */
public class ConstructorEscapeThis {

    private static Point escape;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                new Point(1, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }).start();

        Thread.sleep(10);
        if (escape != null) {
            // y = 0 对象未初始化完毕
            System.out.println(escape);
        }
    }

    private static class Point {
        private int x;

        private int y;

        public Point(int x, int y) throws InterruptedException {
            this.x = x;

            // 构造函数未执行完毕，就把this应用赋值给外部escape引用
            escape = this;
            Thread.sleep(500);
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
