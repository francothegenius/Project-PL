
package producerconsumer;

//import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {

    Buffer buffer;
    int auxC = 0;
    
    //booleano para correr el run
    boolean activo = true;
    
    Producer(Buffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");

        //  Function to create an array filled with Scheme objs
        //  IMPORTANT: Pass buffer size input into genProducts(<bufferSize>)

        Scheme [] products = genProducts(this.buffer.bufferSize); 
       // Random r = new Random(System.currentTimeMillis()); // random will be used to get array index in the selection of product
        Scheme product;
        
        while(this.activo) {
            product = products[auxC]; // Choose a random index of products array
            this.buffer.produce(product);
            Buffer.print("Producer produced: " + "ID -> " + product.getID() + " Expression -> " + product.getOperation());
            auxC++;

            if( auxC >= products.length){
                stopThread();
            }
            
            try {
                Thread.sleep(this.buffer.bufferTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Scheme [] genProducts(int size){

        Scheme [] products = new Scheme [size];

        for(int i = 0; i < products.length; i++){
            products[i] = new Scheme();
        }
        return products;
    }
    
    
    //detiene el thread
    public void stopThread(){
        this.activo = false;
    }
    
}
