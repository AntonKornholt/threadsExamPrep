/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadprogrammingexam1;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kornh
 */
public class Consumer implements Runnable {

    BlockingQueue s2 = null;

    public Consumer(BlockingQueue s2) {
        this.s2 = s2;
    }

    @Override
    public void run() {

        boolean keepGoing = true;
        long fibTotal = 0;

        while (keepGoing) {

            Object fibNumber = null;
            try {
                fibNumber = s2.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (fibNumber == null) {
                keepGoing = false;
                

            } else {
                Long longFib = (long) fibNumber;

                System.out.println("Consumer is consuming fib number: " + longFib);
                fibTotal += longFib;
            }
        }
        System.out.println("Sum of fib numbers: " + fibTotal);
    }

}
