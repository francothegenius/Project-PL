
package producerconsumer;

public class ProducerConsumer {

    public Producer productores  [];
    public Consumer consumidores [];
    public Buffer buffer;


    public ProducerConsumer (int sizeProd, int sizeCons) {
        
        this.productores = new Producer [sizeProd];
        this.consumidores = new Consumer [sizeCons];
    }

    /*public static void main(String[] args) {
        
        //  @params < # productores, # consumidores, tamaño de buffer, tiempo de buffer, rango de operaciones, rango min, rango max>
            bigStart(1, 1, 10, 1000, 0, 9);
        
    }*/

    public void bigStart( int bufferSize, int bufferTime, int min, int max){

        Buffer buffer = new Buffer(bufferSize, bufferTime);

        for (int i = 0; i < this.productores.length; i++) {
            this.productores [i] = new Producer(buffer, min, max);
            this.productores [i].start();
        }

        for (int j = 0; j < this.consumidores.length; j++) {
            this.consumidores [j] = new Consumer(buffer);
            this.consumidores [j].start();
        }
    }

    public void stopAllThreads (){

        for (int i = 0; i < this.productores.length; i++) {
            this.productores [i].stopThread();;
        }

        for (int j = 0; j < this.consumidores.length; j++) {
            this.consumidores [j].stopThread();
        }

    }
    
}
