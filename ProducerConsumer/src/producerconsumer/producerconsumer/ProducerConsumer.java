
package producerconsumer;

import javax.swing.JProgressBar;
import javax.swing.JSpinner;

import javax.swing.SwingUtilities;




public class ProducerConsumer {

    public Producer productores  [];
    public Consumer consumidores [];
    public Buffer buffer;
    public static javax.swing.JProgressBar JProgressBarS;
    public static javax.swing.JSpinner jSpinner;

    public ProducerConsumer(int sizeProd, int sizeCons) {

        this.productores = new Producer[sizeProd];
        this.consumidores = new Consumer[sizeCons];
    }

    /*
     * public static void main(String[] args) {
     * 
     * // @params < # productores, # consumidores, tamaño de buffer, tiempo de
     * buffer, rango de operaciones, rango min, rango max> bigStart(1, 1, 10, 1000,
     * 0, 9);
     * 
     * }
     */

    public void bigStart(int bufferSize, int bufferTime, int min, int max, javax.swing.JProgressBar JPBar, javax.swing.JSpinner jSpinner4) {

        Buffer buffer = new Buffer(bufferSize, bufferTime);
        JProgressBarS = JPBar;
        JProgressBarS.setMaximum(100);
        JProgressBarS.setMinimum(0);

        jSpinner = jSpinner4;

        for (int i = 0; i < this.productores.length; i++) {
            this.productores[i] = new Producer(buffer, min, max);
            this.productores[i].start();
        }

        for (int j = 0; j < this.consumidores.length; j++) {
            this.consumidores[j] = new Consumer(buffer);
            this.consumidores[j].start();
        }
    }

    public static void updateProgress(final int progress, final int count) {
        JProgressBarS.setValue(progress);
        jSpinner.setValue(count);
    }

    public static void setValue(final int v, final int c){
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                updateProgress(v, c);
            }
        });
    }

    public void stopAllThreads (){

        for (int i = 0; i < this.productores.length; i++) {
            this.productores [i].stopThread();
        }

        for (int j = 0; j < this.consumidores.length; j++) {
            this.consumidores [j].stopThread();
        }

    }
    
}
