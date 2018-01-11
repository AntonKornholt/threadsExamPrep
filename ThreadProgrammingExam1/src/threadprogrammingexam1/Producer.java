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
public class Producer implements Runnable {

    private BlockingQueue s1 = null;
    private BlockingQueue s2 = null;

    public Producer(BlockingQueue s1, BlockingQueue s2) {

        this.s1 = s1;
        this.s2 = s2;
    }

    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;

        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    @Override
    public void run() {
        boolean keepGoing = true;
        while (keepGoing) {
            Object number = null;
            try {
                number = s1.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (number == null) {
                keepGoing = false;

            } else {
                System.out.println("Calculating fibonacci number from: " + number);
                Long fibNumber = fib((long) number);
                try {
                    s2.put(fibNumber);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
