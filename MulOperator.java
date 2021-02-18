/** Represents the multiplication operators that appear in a polynomial in Token form
 */
public class MulOperator extends Operator
{
    /** The '*' symbol that represents the MulOperator */
    private char symbol;
    /** value that dictates the priority of the MulOperator */
    private int priority;

    /** Constructor that creates a MulOperator object
     */
    public MulOperator()
    {
        symbol = '*';
        priority = 1;
    }

    /** Utilizes the Mul function of the Poly class to multiply two Poly objects.
     * @param Poly object that is inputted
     * @param Poly object that is inputted
     * @return output result of the two Poly's being multiplied together
     */
    public Poly operate(Poly op1, Poly op2)
    {
        Poly output = new Poly();
        output = op1.mul(op2);
        return output;
    }

    /** Returns '*' symbol that represents the MulOperator
     * @return symbol '*' symbol that represents the MulOperator
     */
    public char getSymbol(){
        return symbol;
    }

    /** Returns priority value of MulOperator, it is equals to DivOperator, but more than AddOperator and SubOperator
     * @return priority integer value of priority
     */
    public int getPrio(){
        return priority;
    }
}
