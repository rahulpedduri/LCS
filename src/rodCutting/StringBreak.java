/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rodCutting;

import java.util.ArrayList;

/**
 *
 * @author Phani Rahul
 */
public class StringBreak {

    public int stringBreak( int n, int [] L) {//we dont need 2 params here because n also defines the cost
        if (n == 0) {
            return 0;
        }
        int minValue = n;//this is just to initialize to a maximum value
        
        for(int i:L){
            int [] rest = new int[L.length-1];
            
            /*taking the current value out of the array */
            for(int j=0; j<rest.length; i++){
                if(L[j]!=i)
                rest[j]= L[j];
            }
             /*this would give us the min cost of the array*/
            minValue= Math.min(minValue, i+stringBreak(n-i,rest)); 
            /*Here, the cost is given by i*/
        }
        return minValue;
    }
    //This is just a crude algorithm. Make a table out of the recursion and you'll be able to optimize it.
    /*
     CUT-ROD(p, n)
     1 if n == 0
     2      return 0
     3 q = -infinity
     4 for i = 1 to n
     5      q = max(q, p[i] + CUT-ROD(p, n - i) )
     6 return q
     */
}
