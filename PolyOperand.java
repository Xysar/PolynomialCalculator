/** Represents a Poly object in Token form
 * 
 */
public class PolyOperand implements Token
{
    /** The polynomial that the PolyOperand is representing */ 
    Poly p;

    /** Constructor that takes in a string of a monomial and creates a Poly object. */
    public PolyOperand(String t)
    {
        String[] s = t.split("x(\\^?)");
        if(t.contains("x")){
            if(t.contains("x^")){
                if(s[0].equals("-")){
                    p = new Poly(-1,Integer.parseInt(s[1]));
                }else if(s[0].isEmpty()){
                    p = new Poly(1, Integer.parseInt(s[1]));   
                }else p = new Poly(Double.parseDouble(s[0]),Integer.parseInt(s[1]));
            } else if(s.length == 1){
                if(s[0].equals("-")){
                    p = new Poly(-1,1);
                }else p = new Poly(Double.parseDouble(s[0]), 1);
            }else p = new Poly(1,1);
        }else p = new Poly(Double.parseDouble(s[0]),0);
    }

    /** Constructor that takes in a Poly object */
    public PolyOperand(Poly p){
        this.p = p;
    }

    /** Checks to see if the object is an Operand 
       * @return true PolyOperands are Operands 
       */
    public boolean isOperand(){
        return true;
    }

    /** Checks to see if the object is an Operator
     * @return false PolyOperands are not Operators
     */
    public boolean isOperator()
    {
        return false;
    }

    /** Checks to see if the object is a ParToken
     *  @return false PolyOperands are not ParTokens
     */
    public boolean isParToken(){
        return false;
    }

    /** returns a symbol that represents the token
     * @return x the symbol that represents the Operand
     */
    public char getSymbol(){
        return 'x';
    }

    /** returns the Poly that the PolyOperand represents
     * @return p the Poly that the PolyOperand represents
     */
    public Poly getPoly(){
        return p;
    }
}
