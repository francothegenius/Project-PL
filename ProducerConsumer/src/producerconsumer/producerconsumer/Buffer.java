
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private Scheme buffer;
    public int bufferSize;
    public int bufferTime;
    
    Buffer(int size, int time) {
        this.buffer = new Scheme();      
        this.bufferSize = size;
        this.bufferTime = time;
    }


    
    synchronized Scheme consume() {
        Scheme product = null;
        
        if(this.buffer == null) {
            try {
                wait(this.bufferTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer;
        this.buffer = null;
        notify();
        
        return product;
    }
    
    synchronized void produce(Scheme product) {
        if(this.buffer != null) {
            try {
                wait(this.bufferTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer = product;
        
        notify();
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        System.out.print(count++ + " ");
        System.out.println(string);
    }
    
}
