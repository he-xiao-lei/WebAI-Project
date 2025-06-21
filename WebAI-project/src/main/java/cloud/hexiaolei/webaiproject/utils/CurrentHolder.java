package cloud.hexiaolei.webaiproject.utils;

public class CurrentHolder {

    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
