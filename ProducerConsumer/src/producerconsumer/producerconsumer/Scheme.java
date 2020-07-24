package producerconsumer;

import java.util.Random;

public class Scheme {
    
    public int ID;
    public String operation;
    public int result;
    public int counter = 0;                 
    static Random rand = new Random();



    public Scheme () {

        this.ID = genID();
        this.operation = genExp();
        this.result = -1;
    }

    // This function will receive an Scheme Obj, evaluate it and return it. 
    public static Scheme evaluateOP(Scheme obj){

    int result;
        
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
            System.out.println("Invalid Operation: ID " + obj.getID());
            return null;
        }
        return obj;
    }

    /* AUXILIAR METHODS */

    public static boolean syntaxOP(String str){

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

    public static char[] stringToChar(String str){

        char [] ch = new char[str.length()];

        for ( int i = 0; i < str.length(); i++ ){
            ch[i] = str.charAt(i);
        }

        return ch;
    } 

    public static String genExp(){

        String a, b, sym;

        a = Integer.toString(getRandomNumber(0, 10));
        b = Integer.toString(getRandomNumber(0, 10));
        sym = Integer.toString(getRandomNumber(0, 4));

        switch (Integer.parseInt(sym)){

            case 0:
                sym = "+";
                break;
            
            case 1:
                sym = "-";
                break;
            
            case 2:
                sym = "*";
                break;
            
            case 3:
                sym = "/";
                break;
            
            default: 
                break;
        }


        String str = "(" + sym + a + b + ")";
        return str;

    }

    public static int getRandomNumber(int min, int max){
        return rand.nextInt(max - min) + min;
    }

    public int genID (){

        counter = getRandomNumber(0, 1000);
        return counter;

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