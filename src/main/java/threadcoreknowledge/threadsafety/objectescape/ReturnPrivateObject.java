package threadcoreknowledge.threadsafety.objectescape;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回private对象
 */
public class ReturnPrivateObject {

    private Map<String, String> map;

    public ReturnPrivateObject() {
        this.map = new HashMap<>();

        map.put("Monday", "Monday");
        map.put("Tuesday", "Tuesday");
        map.put("Wednesday", "Wednesday");
        map.put("Thursday", "Thursday");
        map.put("Friday", "Friday");
    }

    public Map<String, String> escape() {
        return new HashMap<>(map);
    }

    public static void main(String[] args) {
        ReturnPrivateObject returnPrivateObject = new ReturnPrivateObject();

        Map<String, String> escape = returnPrivateObject.escape();
        escape.remove("Monday");

        new Thread(() -> System.out.println(returnPrivateObject.map.get("Monday"))).start();

    }
}
