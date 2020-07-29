
package producerconsumer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Buffer {
    
    private Scheme [] buffer;
    public int bufferSize;
    public int bufferTime;
    static int counterCons;
    static int counterProd;
    static Random rand = new Random ();
    static int id = 1;

    
    Buffer(int size, int time) {
        this.buffer = new Scheme [size];      
        this.bufferSize = size;
        this.bufferTime = time;
    }

    // Singular consumption of a product
    synchronized Scheme consume() {

        Scheme product = null;
        counterCons = getRandomNumber(0, this.buffer.length);

        while (this.buffer[counterCons] == null) {
            try {
                wait(this.bufferTime);
                counterCons = getRandomNumber(0, this.buffer.length);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer[counterCons];
        this.buffer[counterCons] = null;
        count--;
        GUIFrame.removeTasks(product.operation);
        ProducerConsumer.setValue((int) Math.round((count * 100)/this.bufferSize), id);
        notifyAll();
        return product;
        }
    
    
    // Singular production of a product
    // Produces an Scheme obj in a random array position
    synchronized void produce(Scheme product) {

        counterProd = getRandomNumber(0, this.buffer.length);
        
        while(this.buffer[counterProd] != null) {
            try {
                wait(this.bufferTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.buffer[counterProd] = product;
        product.setID(id);
        id++;
        count++;
        GUIFrame.tableToDo(product.ID, product.operation);
        //ProducerConsumer.setValue(bufferProgress);
        notifyAll();
    }

    synchronized Scheme [] genBufferArray (int size){
        return this.buffer;
    }
    
    static int count = 1;
    synchronized static void print(String string) {
        //System.out.print(count++ + " ");
        System.out.println(string);
    }
    
    public static int getRandomNumber(int min, int max){
        return rand.nextInt(max - min) + min;
    }
}
