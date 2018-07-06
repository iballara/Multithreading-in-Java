package wait_and_notify.demo1;

import java.util.Scanner;

public class Processor {

    public void produce() throws InterruptedException {

        synchronized (this) {
            System.out.println("Producer thread running");
            wait(); // Method of the object class.
            System.out.println("Resumed");
        }
    }

    public void consume() throws InterruptedException {

        final Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Waiting for return key");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify();
            Thread.sleep(5000);
        }
    }
}
