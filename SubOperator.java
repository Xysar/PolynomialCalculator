/** Represents the subtraction operators that appear in a polynomial in Token form
 */
public class SubOperator extends Operator
{
    /** The '-' symbol that represents the SubOperator */
    private char symbol;
    /** value that dictates the priority of the AddOperator */
    private int priority;

    /** Constructor that creates a SubOperator object
     */
    public SubOperator()
    {
        symbol = '-';
        priority = -1;
    }

    /** Utilizes the Sub function of the Poly class to subtract two Poly objects.
     * @param op1 Poly object that is being subtracted from
     * @param op2 Poly object that is being subtracted from first Poly
     * @return output result of subtracting second operator from first
     */
    public Poly operate(Poly op1, Poly op2)
    {
        Poly output = new Poly();
        output = op1.sub(op2);
        return output;
    }

    /** Returns '-' symbol that represents the SubOperator
     * @return symbol '-' symbol that represents the SubOperator
     */
    public char getSymbol(){
        return symbol;
    }

    /** Returns priority value of SubOperator, it is equals to AddOperator, but less than DivOperator and MulOperator
     * @return priority integer value of priority
     */
    public int getPrio(){
        return priority;
    }
}
