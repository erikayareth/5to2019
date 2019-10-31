
package dao;

import java.util.Scanner;

public class LaClase {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in); 
       
        double num1, num2;

        System.out.println("Dame los números");
        num1 = leer.nextDouble();
        num2 = leer.nextDouble();
        
        if (num1==0&&num2==0){
        System.out.println("Jesus va a ser feliz");
        }else{
        double r = (num1*4.27)+(num2*2.91);
        System.out.println("Jesus no va a tener novia por: " + r);
        }
    }
}
