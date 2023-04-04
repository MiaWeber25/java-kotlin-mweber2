package commons;

import java.util.HashSet;
import java.util.Set;

class OrderCollector extends Thread {
    private String name;
    OrderCollector(String name) { this.name = name ; }
    // @Override
    public void run() {
        System.out.println("OrderCollector " + name + " running.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("OrderCollector " + name + " done.");
    }
}
public class CapFactory {
    Set<OrderCollector> orderCollectorSet = new HashSet<>();
    void makeOrderCollector(String name) {
        OrderCollector collector = new OrderCollector(name);
        collector.start();
        orderCollectorSet.add(collector);
    }
    public static void main(String[] args) {
        CapFactory factory = new CapFactory();
        factory.run();
    }

    void run() {
        makeOrderCollector("alice");
        makeOrderCollector("henry");
        makeOrderCollector("bob");
    }
}
