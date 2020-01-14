package threadcoreknowledge.threadsafety.objectescape;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 构造函数中新建线程设置属性
 */
public class ConstructorStartThread {

    Map<String, String> map = new HashMap<>();

    public ConstructorStartThread() {
        new Thread(() -> {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                return;
            }
            map.put("Monday", "Monday");
            map.put("Tuesday", "Tuesday");
            map.put("Wednesday", "Wednesday");
            map.put("Thursday", "Thursday");
            map.put("Friday", "Friday");
        });
    }

    public static void main(String[] args) {
        ConstructorStartThread constructorStartThread = new ConstructorStartThread();

        System.out.println(constructorStartThread.map.get("Thursday"));

        Map<Integer, Integer> map = new HashMap<>();
    }
}
