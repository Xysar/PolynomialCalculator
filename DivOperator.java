/** Represents the division operators that appear in a polynomial in Token form
 */
public class DivOperator extends Operator
{
    /** The '/' symbol that represents the DivOperator */
    private char symbol;
    /** value that dictates the priority of the DivOperator */
    private int priority;

      /** Constructor that creates a DivOperator object
     */
    public DivOperator()
    {
        symbol = '/';
        priority = 1;
    }

    /** Utilizes the Div function of the Poly class to divide two Poly objects.
     * @param op1 Poly object that is being divided from
     * @param op2 Poly object that is dividing the first Poly
     * @return output result of dividing second operator from first
     */
    public Poly operate(Poly op1, Poly op2)
    {
        Poly output = new Poly();
        output = op1.div(op2);
        return output;
    }

    /** Returns '/' symbol that represents the DivOperator
     * @return symbol '/' symbol that represents the DivOperator
     */
    public char getSymbol(){
        return symbol;
    }

    /** Returns priority value of DivOperator, it is equals to MulOperator, but more than AddOperator and SubOperator
     * @return priority integer value of priority
     */
    public int getPrio(){
        return priority;
    }
}
