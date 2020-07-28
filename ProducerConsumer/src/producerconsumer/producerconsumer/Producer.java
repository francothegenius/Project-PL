
package producerconsumer;

//import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {

    Buffer buffer;
    int auxC = 0;
    int min;
    int max;
    
    boolean activo = true;
    
    Producer(Buffer buffer, int min, int max) {
        this.buffer = buffer;
        this.min = min;
        this.max = max;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        
        while(this.activo) {

            Scheme product = new Scheme ();
            String operation = product.genExp(this.min, this.max);
            product.setOperation(operation);

            this.buffer.produce(product);
            Buffer.print("Producer produced: " + "ID -> " + product.getID() + " Expression -> " + product.getOperation());
            
            try {
                //System.out.println("Sleeping thread -> Producer.run");
                Thread.sleep(this.buffer.bufferTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    public void stopThread(){
        this.activo = false;
    }
}
