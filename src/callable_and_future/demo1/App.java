package callable_and_future.demo1;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) {

        final ExecutorService executor = Executors.newCachedThreadPool();

        final Future<Integer> future = executor.submit(() -> {
            final Random random = new Random();
            int duration = random.nextInt(4000);

            if (duration > 2000)
                throw new IOException("Sleeping for too long.");

            System.out.println("Starting...");
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished.");
            return duration;
        });

        executor.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            final IOException ioe = (IOException) e.getCause();
            System.out.println(ioe.getMessage());
        }
    }
}
