
public class Aleatorio{


    public static void main(String[] args) { 
        int valX0, valA, valB, valN;
        //~ valX0 = 111; valA = 78; 
        //~ valB = 45; valN = 77;
        
        valX0 = 110;  valA = 77; 
        valB = 44; valN = 76;
        
        
        
        for (int genera=1; genera<=100; genera++) { 
            valX0 = (valX0 * valA + valB) % valN; double valR = (double) valX0 / valN; 
            System.out.println("r = " + valR); 
        } 
    
    }  
}
