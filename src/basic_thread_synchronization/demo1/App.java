package basic_thread_synchronization.demo1;

import java.util.Scanner;

class Processor extends Thread {

    /**
     * This thread might decide to cache this value
     * The volatile keyword avoid that java caches this variable
     */
    private volatile boolean running = true;

    @Override
    public void run() {

        while(running) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        this.running = false; // Warning: Might be cached
    }
}

public class App {

    public static void main(String[] args) {

        final Processor proc1 = new Processor();

        proc1.start();

        System.out.println("Press return to stop...");
        final Scanner in = new Scanner(System.in);
        in.nextLine();

        proc1.shutdown();
    }
}
