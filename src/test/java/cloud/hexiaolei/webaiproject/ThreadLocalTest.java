package cloud.hexiaolei.webaiproject;

public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("Main message");

        System.out.println(Thread.currentThread() + "threadLocal.get() = " + threadLocal.get());


        new Thread(() -> {
            threadLocal.set("Thread message");
            System.out.println(Thread.currentThread() + ":" + threadLocal.get());
        }).start();


        threadLocal.remove();
        System.out.println(Thread.currentThread() + ":" + threadLocal.get());
    }

}

