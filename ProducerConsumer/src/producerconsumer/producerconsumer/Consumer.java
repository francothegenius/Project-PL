
package producerconsumer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {

    Buffer buffer;
    
    //booleano para correr el thread
    boolean activo = true;
    
    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        Scheme product;
        
        while(this.activo) {

            product = this.buffer.consume();

            if(product != null){
                Scheme.evaluateOP(product);
                Buffer.print("Consumer consumed -> " + product.getID() + " Result: -> " + product.getResult());
                GUIFrame.tableDone(product.getID(), product.getOperation(), product.getResult());
            }
            else{

                try {
                    Thread.sleep(this.buffer.bufferTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
                stopThread();
            }
        }
    }
    
    public void stopThread(){
        this.activo = false;
    }
}
