package commons;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Order {
    private String customer;
    private String item;

    public Order(String customer, String item) {
        this.customer = customer;
        this.item = item;
    }

    public String getCustomer() {
        return customer;
    }

    public String getItem() {
        return item;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        //if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return customer.equals(order.customer) && item.equals(order.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, item);
    }
}

class Orders {
    private Set<Order> orderSet = new HashSet<Order> ();
    void addOrder(Order order) {
        orderSet.add(order);
    }
}
class OrderCollector extends Thread { // every OrderCollector that I make creates an associated Thread
    private String name;
    private CapFactory factory;
    OrderCollector(CapFactory factory, String name) { this.factory = factory; this.name = name ; }
    // @Override
    public void run() {
        System.out.println("OrderCollector " + name + " running.");
        for (int i=0; i<4; ++i) { // create a few orders (like they are answering the phone)
            String customer = "customer #" + i + " from collecter " + name;
            String item = "fozzle # " + i;
            Order order = new Order(customer, item);
            factory.orders.addOrder(order); // because you know which factory you are in, you can add the order to that factory
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("OrderCollector " + name + " done.");
    }
}
class OrderMaker extends Thread { // has to know what factory they are in
    private String name;
    private CapFactory factory;
    OrderMaker(CapFactory factory, String name) { this.factory = factory; this.name = name ; }
    // @Override
    public void run() {
        System.out.println("OrderMaker " + name + " running.");
        while (true) {
            if (! factory.orders.isEmpty()) {
                Order order = factory.orders.getNextOrder();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(name + " made " + order.getItem() + " for " + order.getCustomer());
                // process order
            }
        }
        System.out.println("OrderMaker " + name + " done.");
    }
}
public class CapFactory {
    Orders orders = new Orders();
    Set<OrderCollector> orderCollectorSet = new HashSet<>();
    void makeOrderCollector(String name) {
        OrderCollector collector = new OrderCollector(this, name);
        collector.start();
        orderCollectorSet.add(collector);
    }
    // this is the main entry point for a standard java desktop application.
    // to use this in android studio, you have to modify the gradle.xml file in the .idea directory
    // then create a new run configuration: application, JDK 11, commons.CapFactory main, class
    // path of Threads.main this should be enough to run this application as a desktop command line application.
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
