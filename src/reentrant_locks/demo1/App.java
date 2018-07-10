package reentrant_locks.demo1;

public class App {

    public static void main(String[] args) throws Exception {

        final Runner runner = new Runner();

        final Thread t1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        final Thread t2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }
}
