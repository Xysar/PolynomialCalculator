import java.util.List;
import java.util.LinkedList;
import java.util.Deque;
/** Rearranges a infix list of tokens into a postfix list of tokens
 */
public class ConvFix
{
    /** Rearranges a list of tokens representing a infix polynomial into a list of tokens representing a postfix polynomial
     * @param infx an infix list of tokens
     * @return output a postfix list of tokens
     */
    public static List convFix(List infx)
    {
        Deque<Token> fifo = new LinkedList<Token>(); 
        List<Token> output = new LinkedList<Token>();
        int len = infx.size();
        for(int i=0;i<len;i++){
            Token t = (Token) infx.get(i); 
            if(t.isOperand()){
                output.add(t);
            } else if(t.getSymbol() == '('){
                fifo.addFirst(t);
            } else if(t.getSymbol() == ')'){
                while(fifo.peekFirst() != null && fifo.peekFirst().getSymbol() != '(')
                    output.add(fifo.removeFirst());
                if(fifo.peekFirst() == null)
                    return null; //error "Mismatched right parenthesis"
                fifo.removeFirst();
            } else if(t.isOperator()){
                Operator op = (Operator) t;
                while(fifo.peekFirst() != null && fifo.peekFirst().getSymbol() != '('){
                    Operator o = (Operator) fifo.getFirst(); 
                    if(o.getPrio() >= op.getPrio())
                        output.add(fifo.removeFirst());
                    else break;
                }
                fifo.addFirst(t);
            }
        }
        while(fifo.peekFirst() != null){
            Token t = fifo.removeFirst();
            if(t.getSymbol() == '(')
                return null; // error "Mismatched left parenthesis"
            output.add(t);
        }
        return output;
    }
}