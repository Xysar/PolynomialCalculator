
/** An interface that standardizes the methods of a token, which can be a ParToken, Operator or Operand.
 */
public interface Token
{
    boolean isOperand();
    
    boolean isOperator();
    
    boolean isParToken();
    
    char getSymbol();
}
