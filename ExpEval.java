
import java.util.List;
import java.util.LinkedList;
import java.util.Deque;
/**
 * Contains static method that evaluates postfix list of polynomials, and calculates the result.
 * @author Cesar Camacho
 * @version CMPU 102
 */
public class ExpEval
{
    /**
     * Contains static method that evaluates postfix list of polynomials, and calculates the result.
     * @param p The postfix Polynomial that will be calculated
     * @return output The result of calculating the postfix expression that was inputted.
     */
    public static Poly evaluate(List p)
    {
        int len = p.size();
        Deque<Token> fifo = new LinkedList<Token>(); 
        for(int i=0;i<len;i++){
            Token t = (Token) p.get(i);
            if(t.isOperand()){
                fifo.addFirst(t);
            } else if(t.isOperator()){
                Operator o = (Operator) t;
                PolyOperand op2 = (PolyOperand) fifo.removeFirst();
                PolyOperand op1 = (PolyOperand) fifo.removeFirst();
                fifo.addFirst(new PolyOperand(o.operate(op1.getPoly(), op2.getPoly())));
            }
        }
        PolyOperand output = (PolyOperand) fifo.removeFirst();
        return output.getPoly();
    }
}
