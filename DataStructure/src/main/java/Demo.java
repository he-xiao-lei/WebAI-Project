public class Demo {
    public static void main(String[] args) {
        new Thread(()-> {
            System.out.println("hello world");
            for (int i = 0; i < 100; i++) {
                System.out.println("Demo.main"+ i);
            }
        }).start();
    }
}
