package producerconsumer;

public class Scheme {
    
    public int ID;
    public String operation;
    public int result;
    public boolean status;
    public int counter = 0;      // Helps as an aux to assign a consecutive number to ID


    public Scheme () {

        this.counter = counter + 1;
        this.ID = counter;
        this.operation = null;
        this.result = -1;
    }


    // This function will receive an Scheme Obj, evaluate it and return it. 
    public Scheme evaluateOP(Scheme obj){

        int result;

        System.out.println("ID ->   " + obj.getID());
        System.out.println("Operation ->    " + obj.getOperation());
        
        if(syntaxOP(obj.getOperation())){

            char[] ch;
            ch = stringToChar(obj.getOperation());

            int a = Character.getNumericValue(ch[2]);
            int b = Character.getNumericValue(ch[3]);

            switch(ch[1]){

                case 42 :
                    result = a * b;
                    obj.setResult(result);
                    break;
                
                case 43 :
                    result = a + b;
                    obj.setResult(result);
                    break;

                case 45 :
                    result = a - b;
                    obj.setResult(result);
                    break;

                case 47 :
                    result = a / b;
                    obj.setResult(result);
                    break;

                default:
                    break;
            }
        }
        else{
            System.out.println("Invalid Operation: ID " + this.ID);
            return null;
        }

        return obj;
    }

    /* AUXILIAR METHODS */

    public boolean syntaxOP(String str){

        // state 1
        if(str.charAt(0) == 40){
            // state 2
            if(str.charAt(1) >= 42 && str.charAt(1) <= 47){
                // state 3
                if(str.charAt(2) >= 48 && str.charAt(2) <= 57){
                    // state 4
                    if(str.charAt(3) >= 48 && str.charAt(3) <= 57){
                        if(str.charAt(4) == 41){
                            return true;
                        }
                        return false;
                    }
                    return false; 
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public char[] stringToChar(String str){

        char [] ch = new char[str.length()];

        for ( int i = 0; i < str.length(); i++ ){
            ch[i] = str.charAt(i);
        }

        return ch;
    } 

    public void print(Scheme obj){


    }



    /* GETTERS & SETTERS */

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}