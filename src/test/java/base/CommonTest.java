package base;

public class CommonTest {

    public static void Wait(int milliseconds ) {
        try {
            Thread.sleep(milliseconds); // 1000 milliseconds = 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
