/** Represents the addition operators that appear in a polynomial in Token form
 */
public class AddOperator extends Operator
{
    /** The '+' symbol that represents the AddOperator */
    private char symbol;
    /** value that dictates the priority of the AddOperator */
    private int priority;

    /** Constructor that creates an AddOperator object
     */
    public AddOperator()
    {
        symbol = '+';
        priority = -1;
    }

    /** Utilizes the add function of the Poly class to add two Poly objects.
     * @param Poly object that is inputted
     * @param Poly object that is inputted
     * @return output result of the two Poly's being added together
     */
    public Poly operate(Poly op1, Poly op2)
    {
        Poly output = new Poly();
        output = op1.add(op2);
        return output;
    }

    /** Returns '+' symbol that represents the AddOperator
     * @return symbol '+' symbol that represents the AddOperator
     */
    public char getSymbol(){
        return symbol;
    }
    
    /** Returns priority value of AddOperator, it is equals to SubOperator, but less than DivOperator and MulOperator
     * @return priority integer value of priority
     */
    public int getPrio(){
        return priority;
    }
}
