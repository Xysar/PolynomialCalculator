import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Runs the calculator interface. Users can go into interactive, or file mode through this menu. They can also exit.
 * @author Cesar Camacho
 * @version CMPU 102
 */
public class Main
{
    public static void main(String args[]){
        char State = 's';
        Scanner scanner = new Scanner(System.in);
        while(State == 's' || State == 'i' || State == 'f'){
            Map<Character,PolyOperand> store = new HashMap<Character,PolyOperand>();
            while(State == 's'){
                String inputs;
                System.out.println("Enter the mode you would like to enter: 'int' for Interactive mode, 'file' for File mode, or 'exit' to exit the calculator");
                inputs = scanner.next();
                scanner.nextLine();
                if(inputs.equals("exit")){
                    State = 'e';
                    break;
                }
                if(inputs.equals("int")){
                    State = 'i'; 
                    System.out.println("Multiplication, Division, Addition, and Subtraction are supported, as are parentheses."); 
                    System.out.println("You may also save polynomials to variables, like this 'a ='.");
                    System.out.println("Enter 'exit' to return to main menu");
                    break;
                }
                if(inputs.equals("file")){
                    State = 'f';
                    break;
                }
                System.out.println("Invalid phrase: Please try again");
            }
            while(State == 'i'){
                System.out.println("Enter in polynomial expressions to calculate."); 
                char save = 'n'; //signals program whether to save or not 'n' for no, 'y' for yes.
                char key = 'a';
                String input;
                input = scanner.nextLine().toLowerCase();
                input = input.replaceAll("\\s","");
                if(input.equals("exit")){
                    State = 's';
                    break;
                }
                if(input.contains("=")){
                    String[] tos = input.split("=");
                    input = tos[1];
                    key = tos[0].charAt(0);
                    save = 'y';
                }
                List a = PolyReader.readPoly(input, store);
                if(a == null){
                    System.out.println("Invalid input: Try again");
                    continue;
                }
                List b = ConvFix.convFix(a);
                if(b == null){
                    System.out.println("Mismatched Parantheses: Try again");
                    continue;
                }
                Poly out = ExpEval.evaluate(b);
                if(out == null){
                    System.out.println("Invalid Operation: Try Again");
                    continue;
                }
                if(save == 'y'){
                    if(out.pmap.isEmpty()){
                        store.put(new Character(key), new PolyOperand(out));
                        System.out.println("" + key + "=" + 0);
                    }else {store.put(new Character(key), new PolyOperand(out));
                        System.out.println("" + key + "=" + out);
                    }
                }else if(save == 'n'){
                    if(out.pmap.isEmpty())
                        System.out.println("0");
                    else System.out.println(out);
                }
            }
            while(State == 'f'){
                System.out.println("Enter the pathname of a textfile with Polynomials you'd like to calculate");
                String fpath = scanner.next();
                if(fpath.equals("exit")){
                    State = 's';
                    break;
                }
                try{ 
                    BufferedReader r = new BufferedReader(new FileReader(fpath));
                    String input;
                    try{
                        while((input = r.readLine()) != null){
                            char save = 'n'; //signals program whether to save or not 'n' for no, 'y' for yes.
                            char key = 'z';
                            input = input.toLowerCase();
                            input = input.replaceAll("\\s","");
                            if(input.contains("=")){
                                String[] tos = input.split("=");
                                input = tos[1];
                                key = tos[0].charAt(0);
                                save = 'y';
                            }
                            List a = PolyReader.readPoly(input, store);
                            if(a == null){
                                System.out.println("Invalid input: Try again");
                                continue;
                            }
                            List b = ConvFix.convFix(a);
                            if(b == null){
                                System.out.println("Mismatched Parantheses: Try again");
                                continue;
                            }
                            Poly out = ExpEval.evaluate(b);
                            if(out == null){
                                System.out.println("Invalid Operation: Try Again");
                                continue;
                            }
                            if(save == 'y'){
                                if(out.pmap.isEmpty()){
                                    store.put(new Character(key), new PolyOperand(out));
                                    System.out.println("" + key + "=" + 0);
                                }else {store.put(new Character(key), new PolyOperand(out));
                                    System.out.println("" + key + "=" + out);
                                }
                            }else if(save == 'n'){
                                if(out.pmap.isEmpty())
                                    System.out.println("0");
                                else System.out.println(out);
                            }
                        }
                        r.close();
                    }catch(IOException e) {System.err.println(e);}
                }catch(FileNotFoundException e) {System.err.println(e);}
            }
        }
        System.out.println("Goodbye");
    }
}