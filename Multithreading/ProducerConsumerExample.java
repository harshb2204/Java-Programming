

import java.util.LinkedList;
import java.util.Queue;

/**
 * Producer-Consumer Problem Implementation
 * 
 * Problem: Two threads (producer and consumer) share a fixed-size buffer (queue).
 * The producer generates data and puts it into the buffer.
 * The consumer consumes data from the buffer.
 * 
 * Challenge: Ensure the producer doesn't add data if the buffer is full,
 * and the consumer doesn't consume data if the buffer is empty.
 */
public class ProducerConsumerExample {
    
    public static void main(String args[]) {
        // Initialize shared buffer with size 3
        SharedResource sharedBuffer = new SharedResource(3);
        
        // Producer Thread - generates 6 items
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    sharedBuffer.produce(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        // Consumer Thread - consumes 6 items
        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    sharedBuffer.consume();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        // Start both threads
        producerThread.start();
        consumerThread.start();
    }
}

/**
 * SharedResource class manages the buffer and provides thread-safe
 * produce and consume operations using wait() and notify().
 */
class SharedResource {
    private Queue<Integer> sharedBuffer;
    private int bufferSize;
    
    public SharedResource(int bufferSize) {
        this.sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }
    
    /**
     * Produces an item and adds it to the buffer.
     * If buffer is full, the producer waits until space is available.
     */
    public synchronized void produce(int item) throws Exception {
        // Wait if buffer is full
        while (sharedBuffer.size() == bufferSize) {
            System.out.println("Buffer is full, Producer is waiting for consumer");
            wait(); // Release lock and wait for notification
        }
        
        // Add item to buffer
        sharedBuffer.add(item);
        System.out.println("Produced: " + item);
        
        // Notify consumer that item is available
        notify();
    }
    
    /**
     * Consumes an item from the buffer.
     * If buffer is empty, the consumer waits until an item is available.
     */
    public synchronized int consume() throws Exception {
        // Wait if buffer is empty
        while (sharedBuffer.isEmpty()) {
            System.out.println("Buffer is empty, Consumer is waiting for producer");
            wait(); // Release lock and wait for notification
        }
        
        // Remove and return item from buffer
        int item = sharedBuffer.poll();
        System.out.println("Consumed: " + item);
        
        // Notify producer that space is available
        notify();
        
        return item;
    }
} 