package commons;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class Ticket {
    private int ticketPrice;

    public Ticket(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        //if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticket.equals(ticket.customer) && item.equals(ticket.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticket);
    }
}

class TicketBank {
    private List<Ticket> ticketList = new LinkedList<Ticket>();
    // ADD PENDING HERE
    private Object lock = new Object(); // lock = baton
    void addTicket(Ticket ticket) {
        synchronized (lock) { // before the methods are called, they first have to get the baton
            ticketList.add(ticket);
            lock.notifyAll(); // wake up the threads... (ring the bell)
        }
    }
    boolean isEmpty() {
        synchronized (lock) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return ticketList.isEmpty();
        }
    }

    public Ticket getNextOrder() {
        synchronized (lock) {
            if (ticketList.isEmpty()) return null; // this might be null. THIS IS THE SOlUTION TO PROBLEM BELOW.
            Ticket ticket = ticketList.remove(0);
            lock.notifyAll();
            return ticket;
        }
    }
    void waitForChanges() {
        try {
            lock.wait();
        } catch (InterruptedException e) {
        }
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
            Ticket ticket = new Ticket(customer, item);
            factory.orders.addOrder(ticket); // because you know which factory you are in, you can add the order to that factory
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
            if (! factory.orders.isEmpty()) { // this is the problem. here it can be identified that there's something to get
                // another maker may sneak in here.
                Ticket ticket = factory.orders.getNextOrder(); // but by now it could be gone and now there's nothing there. can't remove job from empty list (null pointer)
                if (ticket == null) continue;
                try {
                    System.out.println("making order " + ticket);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(name + " made " + ticket.getItem() + " for " + ticket.getCustomer());
                // process order
            }
        }
        ///System.out.println("OrderMaker " + name + " done.");
    }
}
public class InformationDesk {
    TicketBank ticketBank = new TicketBank(); // where all the ticket info is stored
    Set<OrderCollector> orderCollectorSet = new HashSet<>();
    Set<OrderMaker> orderMakerSet = new HashSet<>();
    void makeOrderCollector(String name) {
        OrderCollector collector = new OrderCollector(this, name);
        collector.start();
        orderCollectorSet.add(collector);
    }
    void makeOrderMaker(String name) {
        OrderMaker maker = new OrderMaker(this, name);
        maker.start();
        orderMakerSet.add(maker);
    }
    void openBooth(int boothNum) {
        Booth booth = new Booth(this, boothNum);
        booth.start();
        BoothSet.add(booth);
    }
    // this is the main entry point for a standard java desktop application.
    // to use this in android studio, you have to modify the gradle.xml file in the .idea directory
    // then create a new run configuration: application, JDK 11, commons.CapFactory main, class
    // path of Threads.main this should be enough to run this application as a desktop command line application.
    public static void main(String[] args) {
        CapFactory factory = new CapFactory();
        factory.run();
    }
    // class example: multiple cap makers dealing with orders
    // my implementation: multiple booths dealing with tickets

    void run() {
       openBooth(1);
       openBooth(2);
       openBooth(3);

    }
}
