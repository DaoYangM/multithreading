package threadcoreknowledge.threadproperties;

/**
 * Thread的id属性，Main线程从1开始，但是我们创建的线程的ID早已超过1。其原因是会有其他的JVM线程在虚拟机启动之后就被创建了
 */
public class ID {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " ID: " + Thread.currentThread().getId());

        Thread thread = new Thread();

        System.out.println(thread.getId());
    }
}
