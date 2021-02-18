/** Represents the operators that appear in a polynomial in Token form
 */
public class Operator implements Token
{
    /** The symbol that represents the specific operator */
    private char symbol;
    /** value that dictates the priority of the operator */
    private int priority;

    /** Empty Constructor for an Operator */
    Operator(){}

    /** Allocates a sign to its respective Operator subclass */
    public static Operator allocate(char sign){
        Operator op = new Operator();
        switch(sign){
            case '+':
            op = new AddOperator();
            break;
            case '-':
            op = new SubOperator();
            break;
            case '*':
            op = new MulOperator();
            break;
            case '/':
            op = new DivOperator();
            break;
        }
        return op;
    }

    /** Utilizes the operator's respective function. This generic function just returns null.
     * @param Poly object that is inputted
     * @param Poly object that is inputted
     * @return null
     */
    public Poly operate(Poly op1, Poly op2){
        return null;
    }

    /** Checks to see if object is an Operand
     * @return false Operator is not an Operand
     */
    public boolean isOperand(){
        return false;
    }

    /** Checks to see if object is an Operator
     * @return true Operator is an Operator
     */
    public boolean isOperator(){
        return true;
    }

    /** Checks to see if object is a ParToken
     * @return false Operator is not a ParToken
     */
    public boolean isParToken(){
        return false;
    }

    /** Returns symbol that represents the Operator
     * @return symbol the symbol that represents the Operator
     */
    public char getSymbol(){
        return symbol;
    }

    /** Returns the priority of the respective Operator
     * @return priority integer value that represents the Operator's priority.
     */
    public int getPrio(){
        return priority;
    }
}
