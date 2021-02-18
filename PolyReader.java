 import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
/**
 * Reads a string of a polynomial and creates a list of tokens from it.
 * It separates monomials, operators, and parantheses
 * @author Cesar Camacho
 * @version CMPU 102
 */
public class PolyReader
{ /** Enum data structure that is used to check at which point in a string of a polynomial it's appropiate to end at */ 
    enum PrState{
        WSIG(false),

        WDB(false),

        DB(true),

        WDEC(false),

        DEC(true),

        X(true),

        WINT(false),

        INT(true),

        OP(true),

        ERR(false);
        /** Each state is allocated a boolean that can't be changed
         * This dictates whether the string can end on these states.
         */
        private final boolean isAccept;
        /** Constructor that allows you to allocate boolean to certain state */
        PrState(boolean isAccept){
            this.isAccept = isAccept;
        }

        /** Returns the boolean allocated to each state */
        public boolean isAccept(){
            return isAccept;
        }
    }

    /** Reads a string that represents a polynomial, and divides it into tokens.
     * @param input represents the polynomial that is being divided
     * @param store the storage of previous polynomials that have initialized so that they can be reused.
     * @return pl The list of Tokens that were constructed from the string.
       */
    public static List readPoly(String input, Map store){
        PrState curState = PrState.WSIG;
        input = input.replaceAll("\\s","");
        int i = 0, len = input.length();
        List<Token> pl = new LinkedList<Token>();
        String polyStr = "";
        while(curState != PrState.ERR && i < len){
            char c = input.charAt(i++);
            switch(curState){
                case WSIG:
                if(c == '-'){
                    curState = PrState.WDB; 
                    polyStr += c;
                } else if(c == '.'){
                    curState = PrState.WDEC;
                    polyStr += c;
                } else if(c == 'x'){
                    curState = PrState.X;
                    polyStr += c;
                } else if(Character.isDigit(c)){
                    polyStr += c;
                    curState = PrState.DB;
                } else if(Character.isLetter(c) && c != 'x' && c != '('){
                    PolyOperand var = (PolyOperand) store.get(c); //Calls back to previously initiates variables and their allocated polynomials.
                    if(var == null)
                        curState = PrState.ERR;
                    else{
                        pl.add(var);
                        curState = PrState.OP;
                    }
                } else if(c == '('){
                    pl.add(new ParToken(c));
                } else curState = PrState.ERR;
                break;
                case WDB:
                if(Character.isDigit(c)){
                    polyStr += c;
                    curState = PrState.DB;
                } else if(c == 'x'){
                    curState=PrState.X;
                    polyStr += c;
                } else if(c == '.'){
                    curState = PrState.WDEC;
                    polyStr += c;
                } else curState = PrState.ERR;
                break;
                case DB:
                if(Character.isDigit(c)){
                    polyStr += c;
                } else if (c == 'x'){
                    curState = PrState.X;
                    polyStr += c;
                } else if(c == '.'){
                    curState = PrState.WDEC;
                    polyStr += c;
                }else if(c == '+' || c == '-' || c == '*' || c == '/'){
                    curState = PrState.WSIG;
                    pl.add(new PolyOperand(polyStr));
                    pl.add(Operator.allocate(c));
                    polyStr = "";
                }else if(c == ')'){
                    curState = PrState.OP;
                    pl.add(new PolyOperand(polyStr));
                    pl.add(new ParToken(c));
                    polyStr = "";
                } else curState = PrState.ERR;
                break;
                case WDEC:
                if(Character.isDigit(c)){
                    polyStr += c;
                    curState = PrState.DEC;
                } else curState = PrState.ERR;
                break;
                case DEC:
                if(Character.isDigit(c)){
                    polyStr += c;
                }else if(c == 'x'){
                    curState = PrState.X;
                    polyStr += c;
                }else if(c == '+' || c == '-' || c == '*' || c == '/'){
                    curState = PrState.WSIG;
                    pl.add(new PolyOperand(polyStr));
                    pl.add(Operator.allocate(c));
                    polyStr = "";
                }else if(c == ')'){
                    curState = PrState.OP;
                    pl.add(new PolyOperand(polyStr));
                    pl.add(new ParToken(c));
                    polyStr = "";
                }else curState = PrState.ERR;
                break;
                case X:
                if(c == '^'){
                    polyStr += c;
                    curState = PrState.WINT;
                }else if(c == '+' || c == '-' || c == '*' || c == '/'){
                    curState = PrState.WSIG;
                    pl.add(new PolyOperand(polyStr));
                    pl.add(Operator.allocate(c));
                    polyStr = "";
                }else if(c == ')'){
                    curState = PrState.OP;
                    pl.add(new PolyOperand(polyStr));
                    pl.add(new ParToken(c));
                    polyStr = "";
                }else curState = PrState.ERR;
                break;
                case WINT:
                if(Character.isDigit(c)){
                    polyStr += c;
                    curState = PrState.INT;
                }else curState = PrState.ERR;
                break;
                case INT:
                if(Character.isDigit(c)){
                    polyStr += c;
                }else if(c == '+' || c == '-' || c == '*' || c == '/'){
                    curState = PrState.WSIG;
                    pl.add(new PolyOperand(polyStr));
                    pl.add(Operator.allocate(c));
                    polyStr = "";
                }else if(c == ')'){
                    curState = PrState.OP;
                    pl.add(new PolyOperand(polyStr));
                    pl.add(new ParToken(c));
                    polyStr = "";
                }else curState = PrState.ERR;
                break;
                case OP:
                if(c == '+' || c == '-' || c == '*' || c == '/'){
                    curState = PrState.WSIG;
                    pl.add(Operator.allocate(c));
                }else if(c == ')'){
                    pl.add(new ParToken(c));
                } else curState = PrState.ERR;
                break;
            }
        }
        if(!curState.isAccept())
            return null;
        if(!polyStr.isEmpty())
            pl.add(new PolyOperand(polyStr));
        return pl;
    }
}

