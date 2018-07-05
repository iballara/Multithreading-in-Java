package video3.demo1;

public class App {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public static void main(String[] args) {

        final App app = new App();
        try {
            app.doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doWork() throws InterruptedException {

        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        /**
         *  We are printing before the loops have finished.
         *  This is why we used the join() method, to wait until the threads are finished.
         */
        System.out.println("Count is: " + count);
    }
}
