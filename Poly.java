import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
/** Represents a Polynomial in the structure of a map
 */
public class Poly
{
    /** Represents the values of the polynomial organized so that the exponent is the key and the coefficient is the value tied to it */
    public Map<Integer,Double> pmap = new HashMap<Integer,Double>();
    /** A set that is tied to the map, allows iteration through the map */
    public Set<Map.Entry<Integer,Double>> entrySet = pmap.entrySet();
    
    /** Empty Constructor for Polynomial objects, this specific constructor creates an empty polynomial.
     */
    public Poly (){};

    /** Constructor for Polynomial objects, this specific constructor takes in only one coefficient and exponent.
     * @param x - The coefficient of the polynomial
     * @param y - The exponent of the polynomial
     */
    public Poly(double x, int y)
    {
        Double cf = new Double(x);
        Integer xp = new Integer(y);   
        pmap.put(xp, cf);
        this.clean();
    }   

    /** Constructor for Polynomial objects, this specific constructor takes in a Poly objects, effectively creating a copy.
     * @param p - Poly object that is being copied
     */
    public Poly(Poly p){
        Iterator<Map.Entry<Integer,Double>> itr = p.entrySet.iterator();
        while(itr.hasNext()){
            Map.Entry<Integer,Double> e = itr.next();
            pmap.put(e.getKey(),e.getValue());
        }
    }

    /** Adds Poly object that is called upon to Poly object that is inputted 
     * @param p Poly object that will be added to Poly object that calls add method
     * @return out result of adding the two Poly objects
     */
    public Poly add(Poly p){
        Iterator<Map.Entry<Integer, Double>> itr = p.entrySet.iterator();
        Poly out = new Poly(this);
        while(itr.hasNext()){
            Map.Entry<Integer,Double> e = itr.next();
            if (out.pmap.get(e.getKey()) == null)
                out.pmap.put(e.getKey(),e.getValue());
            else { out.pmap.put(e.getKey(), out.pmap.get(e.getKey())+e.getValue());
            }
        }
        out.clean();
        return out;
    }

    /** Subtracts Poly that is inputted from Poly is that it is called upon 
     * @param p Poly object that will be subtracted from Poly object that calls sub method
     * @return out result of subtracting the two Poly objects
     */
    public Poly sub(Poly p){
        Iterator<Map.Entry<Integer,Double>> itr = p.entrySet.iterator();
        Poly out = new Poly(this);
        while(itr.hasNext()){
            Map.Entry<Integer,Double> e = itr.next();
            if (out.pmap.get(e.getKey()) == null)
                out.pmap.put(e.getKey(),0 - e.getValue());
            else { out.pmap.put(e.getKey(), out.pmap.get(e.getKey())-e.getValue());
            }
        }
        out.clean();
        return out;
    }

   /** multiplies Poly object that it is called upon by the Poly object that is inputted 
     * @param p Poly object that will be multiplied by Poly object that calls mul method
     * @return out result of multiplying the two Poly objects
     */
    public Poly mul(Poly p){
        Iterator<Map.Entry<Integer,Double>> itr2 = p.entrySet.iterator();
        Poly out = new Poly();
        while(itr2.hasNext()){
            Map.Entry<Integer,Double> a = itr2.next();
            Poly x = new Poly();
            Iterator<Map.Entry<Integer,Double>> itr = entrySet.iterator();
            while(itr.hasNext()){
                Map.Entry<Integer,Double> e = itr.next();
                x.pmap.put(a.getKey() + e.getKey(),a.getValue() * e.getValue());
            }
            out = out.add(x);
        }
        out.clean();
        return out;
    }

    /** Divides Poly object that it is called upon by the Poly object that is inputted 
     * @param p Poly object that will divide the Poly object that calls div method
     * @return out result of dividing the two Poly objects, or null if p is empty Poly
     */
    public Poly div(Poly p){
        if (p.degree() == -1)
            return null;
        Poly out = new Poly();
        Poly r = new Poly(this);
        Iterator<Map.Entry<Integer,Double>> itr2 = p.entrySet.iterator();
        Map.Entry<Integer,Double> first = itr2.next();
        int pd = p.degree();
        while(r.degree() != -1 && r.degree() >= pd){
            int rd = r.degree();
            Poly x  = new Poly();
            Iterator<Map.Entry<Integer,Double>> itr = r.entrySet.iterator();
            Map.Entry<Integer,Double> e = itr.next();
            Poly d = new Poly (r.pmap.get(rd) / p.pmap.get(pd) ,rd - pd);
            out = out.add(d);
            x = d.mul(p);
            r = r.sub(x);   
        }
        if(r.degree() != -1)
            return null;
        return out;
    }

    /** Cleans the Poly map of any keys that have values of zero */
    public boolean clean(){
        Iterator<Map.Entry<Integer,Double>> itr = entrySet.iterator();
        while(itr.hasNext()){
            Map.Entry<Integer,Double> e = itr.next();
            if(e.getValue() == 0)
                itr.remove();
        }
        return true;
    }

    /** Calculates the degree of polynomial
     * @return deg integer that signifies degree of polynomial
     */
    public int degree(){
        Iterator<Map.Entry<Integer,Double>> itr = entrySet.iterator();
        int deg = -1;
        while(itr.hasNext()){
            Map.Entry<Integer,Double> e = itr.next();
            deg = e.getKey();
        }
        return deg;
    }

    /** Creates String representation of the polynomial */
    public String toString(){
        Iterator<Map.Entry<Integer,Double>> itr = entrySet.iterator();
        String ac = "";
        if(!itr.hasNext())
            return "";
        Map.Entry<Integer,Double> e;
        while(itr.hasNext()){
            e = itr.next();
            if(!itr.hasNext()){
                if(e.getKey() == 0){
                    ac = "" + e.getValue() + ac;
                }else ac = "" + e.getValue() + "x^" + e.getKey() + ac;
                continue;
            }
            if(e.getKey() == 0)
                ac = "" + e.getValue() + ac;
            else if(e.getKey() == 1)
                ac = "" + e.getValue() + "x" + ac;
            else ac = "" + e.getValue() + "x^" + e.getKey() + ac;
            if (e.getValue() > 0)
                ac = "+" + ac;
        }
        return ac; 
    }
}
