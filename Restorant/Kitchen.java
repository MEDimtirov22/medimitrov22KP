import java.util.concurrent.BlockingQueue;

public class Kitchen implements Runnable {
    private BlockingQueue<Order> orders;
    private BlockingQueue<Order> ready;

    public Kitchen(BlockingQueue<Order> orders, BlockingQueue<Order> ready) {
        this.orders = orders;
        this.ready = ready;
    }

    public void run() {
        try {
            while (true) {
                Order order = orders.take();
                System.out.println("Kitchen is cooking: " + order);
                Thread.sleep(2000);
                System.out.println("Kitchen finished: " + order);
                ready.put(order);
            }
        } catch (InterruptedException e) {
        }
    }
}