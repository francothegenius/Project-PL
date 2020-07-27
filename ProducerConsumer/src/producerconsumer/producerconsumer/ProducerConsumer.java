
package producerconsumer;

public class ProducerConsumer {

    public static void main(String[] args) {
        
        bigStart(2, 4, 20, 1000);

    }

    public static void bigStart(int productores, int consumidores, int bufferSize, int bufferTime){

        Producer [] producers = new Producer [productores];
        Consumer [] consumers = new Consumer [consumidores];
        Buffer buffer = new Buffer(bufferSize, bufferTime);

        for (int i = 0; i < producers.length; i++) {
            producers [i] = new Producer(buffer);
            producers [i].start();
        }

        for (int j = 0; j < consumers.length; j++) {
            consumers [j] = new Consumer(buffer);
            consumers [j].start();
        }
    }
    
}
