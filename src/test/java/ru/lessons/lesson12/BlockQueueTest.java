package ru.lessons.lesson12;

import org.junit.Test;
import ru.lessons.lesson10.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

/**
 * Created by User on 09.06.2017.
 */
public class BlockQueueTest {

    @Test
    public void queue() throws InterruptedException {
        final BlockQueue<User> queue = new BlockQueue<>();
        final List<Customer> customers = Arrays.asList(
                new Customer(queue),
                new Customer(queue),
                new Customer(queue),
                new Customer(queue)
        );
        for (Customer customer : customers){
            customer.start();
        }
        Producer producer = new Producer(queue, Arrays.asList(
                new User("1", "1"), new User("2", "2"),
                new User("3", "3"), new User("4", "4"),
                new User("5", "5"), new User("6", "6"),
                new User("7", "7"), new User("8", "8"),
                new User("9", "9"), new User("10", "10")
        ));
        producer.start();
        producer.join();
        Thread.sleep(101);
        int count = 0;
        for (Customer c: customers){
            count += c.size();
        }
        assertEquals(count,producer.store.size());

    }

    private static final class Customer extends Thread{
        private final BlockQueue<User> queue;
        private final AtomicInteger counter = new AtomicInteger(0);

        public Customer(BlockQueue<User> queue) {
            super();
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                System.err.println(String.format("%s : %s", Thread.currentThread().getId(), this.queue.poll()));
                counter.incrementAndGet();
            }
        }

        public int size() {return this.counter.get();}
    }

    private class Producer extends Thread {

        private final BlockQueue<User> queue;
        private final List<User> store;

        public Producer(final BlockQueue<User> queue, final List<User> store) {
            super();
            this.queue = queue;
            this.store = store;
        }

        @Override
        public void run() {
            for (User u: this.store) {
                this.queue.push(u);
            }
        }
    }
}