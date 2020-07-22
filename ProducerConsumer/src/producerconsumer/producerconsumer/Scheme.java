package producerconsumer;

public class Scheme {
    
    public int ID;
    public String operation;
    public int result;
    public boolean status;
    public int counter = 0;      // Helps as an aux to assign a consecutive number to ID


    // Default constructor
    public Scheme () {

        this.counter = counter + 1;
        this.ID = counter;
        this.operation = null;
        this.result = -1;
        this.status = false;
    }

    // This function will receive an Scheme Obj, evaluate it and return it. 
    public int evaluate(Scheme obj){

        System.out.println("Hello Scheme");
        return 0;
    }

}