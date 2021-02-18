/** Represents the parantheses that appear in an infix polynomial in Token form
 */
public class ParToken implements Token
{
    /** The symbol that represents the ParToken, either '(' or ')' */
    private char symbol;

    /** Constructor that creates a ParToken out of a char symbol, either '(' or ')'
     */
    public ParToken(char symbol){
        this.symbol = symbol;
    }

    /** Checks to see if the object is an Operator
     * @return false ParTokens are not Operators
     */
    public boolean isOperator(){
        return false;
    }

    /** Checks to see if the object is a Operand
     *  @return false ParTokens are not Operand
     */
    public boolean isOperand(){
        return false;
    }

    /** Checks to see if the object is a ParToken
     *  @return true ParTokens are ParTokens
     */
    public boolean isParToken(){
        return true;
    }

    /** returns a symbol that represents the token
     * @return symbol the symbol that represents the ParToken, '(' or ')'
     */
    public char getSymbol(){
        return symbol;
    }
}
