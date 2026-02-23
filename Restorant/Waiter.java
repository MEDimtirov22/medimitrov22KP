import java.util.concurrent.BlockingQueue;

public class Waiter implements Runnable {
    private String name;
    private BlockingQueue<Order> ready;

    public Waiter(String name, BlockingQueue<Order> ready) {
        this.name = name;
        this.ready = ready;
    }

    public void run() {
        try {
            while (true) {
                Order order = ready.take();
                System.out.println(name + " is taking order: " + order);
                Thread.sleep(2000);
                System.out.println(name + " delivered: " + order);
            }
        } catch (InterruptedException e) {
        }
    }
}