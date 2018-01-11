package threadprogrammingexam1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*General part
    
    -    When and why will we use Threads in our programs?
            To solve tasks that require us two do multiple tasks at the same time. When we have to work with two different tasks in parralel.
            If we don't code with threads one task might unnecessesarily wait for another task even though it could easily run at the same time.
            This makes the code potentially run much faster.
    -    What is the Race Condition Problem and how can you solve it?
            Race condition problems occur when two threads try to access the same data and change it at the same time. Due to the thread
            swapping algorithm you don't know what thread performed its action first. They are both racing there to do their task.
            The most common way to solve Race condition problems are by using a lock to make sure that only one thread access and change data at a time.
    -    Explain the Producer/Consumer-problem and how to solve it in modern Java Programs
            The producer/consumer problem occur either when a producer tries to add data to a full buffer
            or if a consumer tries to consume data from an empy buffer.
            Can be fixed by making the producer either go to sleep or dicard its data if the buffer is full. And then when the consumer consumes data it 
            can notify the producer to wake it up and let it add more data to the buffer. And if the consumer tries to get data that isn't there it can go to 
            sleep and then the producer can notify it and wake it when there is more data.
            Important to make sure that both threads can and will make the other wake up to prevent a deadlock.
    -    Explain what Busy Waiting is and why it's a bad thing in a modern software system.
            It is when you are constantly checking for a specific condition without anything happening. 
            This wastes CPU resources and can be a big problems when
            you need to make the most of your CPU power.
    -    Describe Java's BlockingQueue interface, relevant implementations and methods relevant for the producer consumer problem. 
            The java BlockingQueue interface allows us to create a threadsafe queue we can add data to and take data from. It has a number of 
            usable methods for inserting, removing and examining the data in the queue. block methods for inserting and removing
            include put(object) and take(object). 
 */
/**
 *
 * @author Kornh
 */
public class ThreadProgrammingExam1{

    public static void main(String[] args) {
        ThreadProgrammingExam1 TPE1 = new ThreadProgrammingExam1(); 
        BlockingQueue s1 = new ArrayBlockingQueue(12);
        s1.add(4L);
        s1.add(5L);
        s1.add(8L);
        s1.add(12L);
        s1.add(21L);
        s1.add(22L);
        s1.add(34L);
        s1.add(35L);
        s1.add(36L);
        s1.add(37L);
        s1.add(42L);

        BlockingQueue s2 = new ArrayBlockingQueue(12);
        System.out.println(TPE1.runThreads(4, s1, s2));
        
        
        
    }

    public String runThreads(int ConsumerCount, BlockingQueue s1, BlockingQueue s2) {
        String done = "Closing the Fib - Producer/Consumer program";
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < ConsumerCount; i++) {
           
            es.execute(new Producer(s1, s2));
            
        }
        es.execute(new Consumer(s2));
        es.shutdown();
        try {
            es.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadProgrammingExam1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return done;
               
    }

}
