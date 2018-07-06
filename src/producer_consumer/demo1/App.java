package producer_consumer.demo1;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        final Thread t1 = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        final Thread t2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void producer() throws InterruptedException {

        final Random random = new Random();
        while(true) {
            // If the queue is full, put() will wait until an item is removed form the queue.
            queue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {

        final Random random = new Random();
        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                // If the queue is empty, the take() method will wait until an item is added.
                final Integer value = queue.take();
                System.out.println("Taken value: " + value + ", Queue size is: " + queue.size());
            }
        }
    }
}
