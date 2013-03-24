/*
 * Name: Phani Rahul Pedduri
 * ID: 800803441
 */

package lcs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phani Rahul
 */
public class LCS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        /*creating files from the paths provided from the command line arguments*/
//        File f1 = new File(args[0]);
//        File f2 = new File(args[1]);
//        File output = new File(args[2]);
//        String line1, line2;
//        try {
//           /*read from the input files into line1 and line2*/
//            line1 = (new BufferedReader(new FileReader(f1))).readLine();
//        
//            line2 = (new BufferedReader(new FileReader(f2))).readLine();
//           
//            /*Because main method is static, the class has to be instantiated*/
//            LCS lcs = new LCS();
//            /*appending all the output data into newLines*/
//            String newLines = "";
//            /*appending normalized edit distance to the output variable*/
//            newLines += lcs.normalizedEditDistance(line1, line2) + "\n";
//            Object[] list=lcs.LCSUsingFullTable(line1, line2);
//            /*appending the lcs sequence obtained using full table*/
//            for(int i=0; i<list.length; i++){
//                newLines +=list[i]+" ";
//            }
//            newLines +=  "\n";
//            ArrayList list1 = new ArrayList();
//            lcs.lcs_recursive(line1, line2, list1);
//            Iterator itr=list1.iterator();
//            while(itr.hasNext()){
//                /*Appending the lcs sequence obtained using linear recursive algorithm*/
//                newLines += itr.next()+ " ";
//            }
//            newLines +=  "\n";
//            /*Please note that every different output is seperated using a new line-'\n'*/
//            //System.out.println("new line: "+newLines);
//          
//            if (!output.exists()) {
//                output.createNewFile();
//            }
//            FileOutputStream fs= new FileOutputStream(output);
//            /*writing to the file output stream*/
//            fs.write(newLines.getBytes());
//
//        } catch (IOException ex) {
//            Logger.getLogger(LCS.class.getName()).log(Level.SEVERE, null, ex);
//            
//        } finally {
//        }
//        
        System.out.println("");
//        new LCS().normalizedEditDistance1("zalgoxrithmsrxocksxzilozxxvethzisclzaxzzsxs", "qalgoriythmsqrowwcksqqwqilowvqethiqsclqawssw");
        Object[] o=new LCS().LCSUsingFullTable("gtatcgtat", "agtacgtcat");
//        Object[] o=new LCS().LCSUsingFullTable("abcdefg", "abced");
        for(int i=0; i<o.length ;i++){
            System.out.print(o[i]+" ");
        }
//        System.out.println(
//          new LCS().normalizedEditDistance("ncaatournament", "northcarolina")
//                );
        ArrayList lc = new ArrayList();
//        //   new LCS().lcs_recursive("gtat", "agta", lcs);
        new LCS().lcs_recursive("gtatcgtat", "agtacgtcat", lc);
//         new LCS().lcs_recursive("abcdefg", "abced", lc);
////             new LCS().lcs_recursive("zalgoxrithmsrxocksxzilozxxvethzisclzaxzzsxs", "qalgoriythmsqrowwcksqqwqilowvqethiqsclqawssw", lc);
        System.out.println("lcs: " + lc);
//        new LCS().test("gtatcgtat", "agtacgtcat");
//        new LCS().test1("tatgctatg", "tactgcatga");
////        int [] i= new LCS().getReverseMiddleRow("abc", "abcdef");
//        for(int a:i) System.out.print(" "+a);
    }
    
    

    /*LCS recursive using linear memory*/
    void lcs_recursive(String X, String Y, ArrayList LCS) {

        if (X.length() == 1) {
            /* Compare X[0] to each symbol in Y[0, ..., Y.size() - 1 ]
             If there is a symbol match push_back X[0] on to LCS */
            for (int i = 0; i < Y.length(); i++) {
                if (X.charAt(0) == Y.charAt(i)) {
                    LCS.add(X.charAt(0));
                    break;
                }
            }

        } else if (Y.length() == 1) {
            /* Compare Y[0] to each symbol in X[0, ..., X.size() - 1]
             If there is a symbol match push_back Y[0] on to LCS*/
            for (int j = 0; j < X.length(); j++) {
                if (Y.charAt(0) == X.charAt(j)) {
                    LCS.add(Y.charAt(0));
                    break;
                }
            }
        } else {
            
            /*Compute the middle rows */
            int[] forwardMiddle = getForwardMiddleRow(X, Y);

            int[] reverseMiddle = getReverseMiddleRow(X, Y);
//            int[] forwardMiddle =test(X,Y);
//            int[] reverseMiddle =test1(X,Y);
            /*Find the Vertical(x) and Horizontal (y) split indices for the table */
            int x = getHorizontalSplit(X);//
            int y = getVerticalSplit(forwardMiddle, reverseMiddle);
      

/*if x or y becomes -1, it means that one of the 2 given subsequences
  is totally contained in the other sub sequence, hence adding 
 the smallest of the two subsequeces to the LCS*/
            /* by vishnu  */
            String temp="";
            if (x <0 || y <=0) {
                if (X.length() < Y.length()) {
                    for(int i=0; i<X.length(); i++){
                        for(int j=0; j<Y.length(); j++){
                            if(X.charAt(i) == Y.charAt(j)){
                                if(temp.indexOf(X.charAt(i))<0){
                                    temp=temp+X.charAt(i);}
                            }
                        }
                    }
                    LCS.add(temp);
                } else {
                    for(int i=0; i<Y.length(); i++){
                        for(int j=0; j<X.length(); j++){
                            if(Y.charAt(i) == X.charAt(j)){
                                if(temp.indexOf(Y.charAt(i))<0){
                                    temp=temp+Y.charAt(i);}
                            }
                        }
                    }
                    LCS.add(temp);
                    /*  by vishnu */
                }
            } else {
                String X_front = X.substring(0, x + 1);
                /*Generate Sequence Y_front = Y[0, ..., y]*/
                String Y_front = Y.substring(0, y + 1);
                /*Generate Sequence X_back = X[x + 1, ..., X.size() - 1]*/
                String X_back = X.substring(x + 1, X.length());
                /*Generate Sequence Y_back = Y[y + 1, ..., Y.size() - 1]*/
                String Y_back = Y.substring(y + 1, Y.length());

                /*Partitioning it into parts and discarding 2 halves*/
                lcs_recursive(X_front, Y_front, LCS);
                lcs_recursive(X_back, Y_back, LCS);
            }
        }
    }
    
    /*computes the forward middle row and retuns it. This uses 2 rows to compute */
    private int[] getForwardMiddleRow(String str1, String str2) {

        int horizontal, vertical;
        int i = 0, j = 0;

        /*length of the sequence + 1 for the extra row and column*/
        horizontal = str2.length() + 1;
        vertical = str1.length() + 1;

        /*this gives us our stopping point vertically*/
        int m = (vertical) / 2;

        /*subtracting these offsets to access the element to the left,right 
         or diagonal to the current element*/
        int LEFT = 1;
        int TOP = horizontal;
        int DIAGONAL = horizontal + 1;
        /*creates an array double the size of a single table row. this way 
         an entire table need not be required to be stored. */
        int[] rows = new int[horizontal * 2];

        /*initializing the table[i][j]*/
        for (i = 0; i < horizontal; i++) {
            rows[i] = i;
        }
        for (j = horizontal; j < horizontal * 2; j++) {
            rows[j] = j - horizontal;//
        }

        /*computing from left to right, note that m is exclusive here.*/
        for (i = 1; i < m; i++) {
            for (j = horizontal; j < 2 * horizontal; j++) {
                /*initializing the first element of the row*/
                boolean check = j - horizontal == 0;
                if (check) {
                    rows[j] = i;
                    //continue;
                } else {
                    //subtracting the horizontal offset to get zero based index
                    int pos = j - horizontal - 1;
                    char ac = str2.charAt(pos);
                    char bc = str1.charAt(i - 1);
                    boolean check1 = str1.charAt(i - 1) == str2.charAt(j - horizontal - 1);
                    if (check1) {
                        /*if they match*/
                        rows[j] = rows[j - DIAGONAL];
                    } else {
                        if (rows[j - LEFT] > rows[j - TOP]) {
                            /*if top element is lesser than the left element*/
                            rows[j] = rows[j - TOP] + 1;
                        } else {
                            /*if left element i lesses than top element*/
                            rows[j] = rows[j - LEFT] + 1;
                        }
                    }
                }

            }
            /*moving the bottom row to the top so that
             bottom row can be computed again*/
            for (j = 0; j < horizontal; j++) {
                rows[j] = rows[j + horizontal];
            }

        }
        return Arrays.copyOfRange(rows, horizontal, horizontal * 2);

    }

    /*computes the reverse middle row and retuns it. This uses 2 rows to compute */
    private int[] getReverseMiddleRow(String str1, String str2) {
        int horizontal, vertical;
        int i = 0, j = 0;

        /*length of the sequence + 1 for the extra row and column*/
        horizontal = str2.length() + 1;
        vertical = str1.length() + 1;

        /*this gives us our stopping point vertically*/
        int m = ((vertical) / 2);

        /*adding these offsets to access the element to the right,bottom 
         or diagonal to the current element*/
        int RIGHT = 1;
        int BOTTOM = horizontal;
        int DIAGONAL = horizontal + 1;

        /*creates an array double the size of a single table row. this way 
         an entire table need not be required to be stored. */
        int[] rows = new int[horizontal * 2];

        /*init is the value of the last element in a row. 
         Because we are using only 2 rows, it is not possible to initialize
         all elements in the last column at once */
        int init = 0;
        /*initializing the 2 rows*/
        for (j = (horizontal * 2) - 1, init = 0; j >= 0; j--, init++) {
            rows[j] = init;
        }
        for (i = horizontal - 1, init = 0; i >= 0; i--, init++) {
            rows[i] = init;
        }

        /*computing from right to left, note that m is inclusive here.*/
        for (i = vertical - 1, init = 1; i >= m; i--, init++) {
            for (j = horizontal - 1; j >= 0; j--) {
                /*initializing the last element of the row*/
                boolean check = j - (horizontal - 1) == 0;
                if (check) {
                    rows[j] = init;
                    //continue;
                } else {
                    int pos = j;
                    char ac = str2.charAt(pos);
                    char bc = str1.charAt(i - 1);

                    boolean check1 = str1.charAt(i - 1) == str2.charAt(pos);
                    if (check1) {
                        /*if they match, move diagonally*/
                        rows[j] = rows[j + DIAGONAL];
                    } else {
                        if (rows[j + RIGHT] > rows[j + BOTTOM]) {
                            /*if right element > bottom element*/
                            rows[j] = rows[j + BOTTOM] + 1;
                        } else {
                            /*if right element <= bottom element, add 1 to it
                             and set it as the current element*/
                            rows[j] = rows[j + RIGHT] + 1;
                        }
                    }
                }

            }
            /*moving the top row to the bottom so that the top row can be computed again*/
            for (int k = (2 * horizontal) - 1; k >= horizontal; k--) {
                rows[k] = rows[k - horizontal];
            }
        }
        return Arrays.copyOfRange(rows, 0, horizontal);

    }
    
    /*gets the sequence's index horizontal split in the table*/
    private int getHorizontalSplit(String X) {
        /*-1 is added to match the index of the sequence which is 1 index behind*/
        return (X.length() / 2) - 1;
    }
    
   
    /*gets the sequence's vertical split in the table*/
    private int getVerticalSplit(int[] forwardMiddle, int[] reverseMiddle) {
        /*the 2 arrays are added  to find the minimum edit distance*/
        for (int i = 0; i < forwardMiddle.length && i < reverseMiddle.length; i++) {
            forwardMiddle[i] += reverseMiddle[i];
        }
        /* -1 is added to the minimum index to find the 
         * split in the sequence which is 1 index behind*/
        int ret = (min(forwardMiddle) - 1);
        return ret;
    }

    /*This method returns the index of the least number in an array*/
    private int min(int[] array) {
        int i = 0;
        for (int j = 1; j < array.length; j++) {
            // = ensures that last minimum is selected in case of repetitions 
            if (array[i] >= array[j]) {
                i = j;
            }
        }
        //returning the least value's rightmost index
        return i;
    }

    /*computes the normailzed edit distance from a given two sequences*/
    private double normalizedEditDistance(String str1, String str2) {//using just 2 rows
        
        int horizontal, vertical;
        int i = 0, j = 0;
        
        /*length of the sequence + 1 for the extra row and column*/
        horizontal = str1.length() + 1;
        vertical = str2.length() + 1;
        
/*subtracting these offsets to access the element to the left,right 
         or diagonal to the current element*/
        int LEFT = 1;
        int TOP = horizontal;
        int DIAGONAL = horizontal + 1;

         /*creates an array double the size of a single table row. this way 
         an entire table need not be required to be stored. */
        int[] rows = new int[horizontal * 2];
        
        /*initializing the table[i][j]*/
        for (i = 0; i < horizontal; i++) {
            rows[i] = i;
        }
        for (j = horizontal; j < horizontal * 2; j++) {
            rows[j] = j - horizontal;//
        }
       
/*computing from left to right*/
        for (i = 1; i < vertical; i++) {
            for (j = horizontal; j < 2 * horizontal; j++) {
                 /*initializing the first element of the row*/
                boolean check = j - horizontal == 0;
                if (check) {
                    rows[j] = i;
                    //continue;
                } else {
                    /*the offset needs to be removed if a zero based index is required*/
                    int pos = j - horizontal - 1;
                    char ac = str1.charAt(pos);
                    char bc = str2.charAt(i - 1);
                    boolean check1 = str2.charAt(i - 1) == str1.charAt(j - horizontal - 1);
                    if (check1) {
                        /*if a match is found, copy the left-top diagonal element*/
                        rows[j] = rows[j - DIAGONAL];
                    } else {
                        if (rows[j - LEFT] > rows[j - TOP]) {
                            /*if top element is less than left element */
                            rows[j] = rows[j - TOP] + 1;
                        } else {
                            /*if left element <= top element*/
                            rows[j] = rows[j - LEFT] + 1;
                        }
                    }
                }

            }
           /*moving the bottom row to the top*/
            for (j = 0; j < horizontal; j++) {    
                rows[j] = rows[j + horizontal];
            }
            
        }
        
       /*element: table[i - 1][j - 1]) is the edit distance. this i,j
         decrement is because they come out of the loop after exceeding the bound.
         therefore we are decrementing them by 1to bring them in the array bounds.       
         we now have edit distance, we have to calculate the normalized edit distance using the formula:
        
         (length(string 1)+length(string 2) - edit_distance)/(length(string 1)+length(string 2)
         or 1 - (edit_distance/length(string 1)+length(string 2))
         */
        double normalizedEditDistance = 1 - ((double) rows[horizontal + horizontal - 1] / (str1.length() + str2.length()));

        return normalizedEditDistance;
    }

    /*using an entire table to compute LCS*/
    private Object[] LCSUsingFullTable(String str1, String str2) {

        int vertical, horizontal;
        int i = 0, j = 0;

        /*length of the sequence + 1 for the extra row and column*/
        vertical = str1.length() + 1;
        horizontal = str2.length() + 1;

        /*making the 2-dimesnsional array to accomodate the table*/
        int table[][] = new int[vertical][horizontal];

        /*initializing the table[i][j]*/
        for (i = 0; i < vertical; i++) {
            table[i][0] = i;
        }
        for (j = 0; j < horizontal; j++) {
            table[0][j] = j;
        }


        /*for every tabe[i][j] i.e for every element in the table apart from the topmost and leftmost elements*/
        for (i = 1; i < vertical; i++) {
            for (j = 1; j < horizontal; j++) {
                /*reduced by 1 index to start character comparisions at 0 index*/
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1];
                } else {
                    if (table[i][j - 1] > table[i - 1][j]) {
                        /*when left element > top element*/
                        table[i][j] = table[i - 1][j] + 1;
                    } else {
                        /*when left element < top element*/
                        table[i][j] = table[i][j - 1] + 1;
                    }
                }
            }
        }
        /*element: table[i - 1][j - 1]) is the edit distance. this i,j
         decrement is because they come out of the loop after exceeding the bound.
         therefore we are decrementing them by 1to bring them in the array bounds.       
         we now have edit distance, we have to calculate the normalized edit distance using the formula:
        
         (length(string 1)+length(string 2) - edit_distance)/(length(string 1)+length(string 2)
         or 1 - (edit_distance/length(string 1)+length(string 2))
         */
        double d = 1 - ((double) table[i - 1][j - 1] / (str1.length() + str2.length()));




        /*backtracking to find the LCS*/
        Stack lcs = new Stack();
        /*the i-- and j-- in the for loop ensure that we move diagonally 
         in case of no problems*/
        for (i = vertical - 1, j = horizontal - 1; i > 0 && j > 0; i--, j--) {
            /*Note that the indexes being used to access the sequences are off by 1*/
            char ch1 = str1.charAt(i - 1);
            char ch2 = str2.charAt(j - 1);
            /*if a match is found at the current element, pushing it to 
             a stack and allowing the regular path to be followed 
             i.e diagonally*/
            if (ch1 == ch2) {
                lcs.push(str1.charAt(i - 1));

            } else if (table[i][j - 1] > table[i - 1][j]) {
                /*if top element < left element move to top. j++ ensures 
                 that j remains same after decrementing*/
                j++;
            } else {
                /*if top element > left element move to left. i++ ensures 
                 that i remains same after decrementing*/
                i++;
            }

        }
        //reversing the stack
        ArrayList forRev=(new ArrayList(lcs));
        Collections.reverse(forRev);
        
        //returning the stack in the form of an array.
        return forRev.toArray();

    }
    
    
    
    private int[] test(String vertical_string, String horizontal_string){
          
        int horizontal, vertical;
        int i = 0, j = 0;
        
        /*length of the sequence + 1 for the extra row and column*/
        horizontal = horizontal_string.length() + 1;
        vertical = vertical_string.length() + 1;
        
/*subtracting these offsets to access the element to the left,right 
         or diagonal to the current element*/
        int LEFT = 1;
        int TOP = horizontal;
        int DIAGONAL = horizontal + 1;

         /*creates an array double the size of a single table row. this way 
         an entire table need not be required to be stored. */
        int[][] rows = new int [2][horizontal];
        
        /*initializing the table[i][j]*/
        for (i = 0; i < horizontal; i++) {
            rows[0][i] = i;
        }
       
       int m=horizontal/2;
/*computing from left to right*/
        for (i = 1; i < m; i++) {
            for (j = 0; j < horizontal; j++) {
                 /*initializing the first element of the row*/
                boolean check = j  == 0;
                if (check) {
                    rows[1][j] = i;
                    //continue;
                } else {
                    /*the offset needs to be removed if a zero based index is required*/
                    int pos = j - 1;
                    char ac = horizontal_string.charAt(pos);
                    char bc = vertical_string.charAt(i - 1);
                    boolean check1 = ac==bc;
                    if (check1) {
                        /*if a match is found, copy the left-top diagonal element*/
                        rows[1][j] = rows[0][j-1];
                    } else {
                        if (rows[1][j - 1] > rows[0][j]) {
                            /*if top element is less than left element */
                            rows[1][j] = rows[0][j] + 1;
                        } else {
                            /*if left element <= top element*/
                            rows[1][j] = rows[1][j-1 ] + 1;
                        }
                    }
                }

            }
           /*moving the bottom row to the top*/
            for (j = 0; j < horizontal; j++) {    
                rows[0][j] = rows[1][j];
            }
            
        }
        for(i=0;i<horizontal;i++){
            System.out.println(" "+rows[1][i]);
            
        }
        return Arrays.copyOfRange(rows[1], 0, horizontal );
    }
    
    
    private int[] test1(String vertical_string, String horizontal_string){
          
        int horizontal, vertical;
        int i = 0, j = 0;
        
        /*length of the sequence + 1 for the extra row and column*/
        horizontal = horizontal_string.length() + 1;
        vertical = vertical_string.length() + 1;
        
/*subtracting these offsets to access the element to the left,right 
         or diagonal to the current element*/
        int LEFT = 1;
        int TOP = horizontal;
        int DIAGONAL = horizontal + 1;

         /*creates an array double the size of a single table row. this way 
         an entire table need not be required to be stored. */
        int[][] rows = new int [2][horizontal];
        int init=0;
        /*initializing the table[i][j]*/
        for (i = horizontal-1,init=0; i >= 0;init++, i--) {
            rows[0][i] = init;
        }
      int m=(vertical/2)-1; 
       
/*computing from left to right*/
        for (i = vertical-1-1,init=1; i >= m; i--,init++) {
            for (j = horizontal-1; j >= 0; j--) {
                 /*initializing the first element of the row*/
                boolean check = j  == horizontal-1;
                if (check) {
                    rows[1][j] = init;
                    //continue;
                } else {
                    /*the offset needs to be removed if a zero based index is required*/
                    int pos = j ;
                    char ac = horizontal_string.charAt(pos);
                    char bc = vertical_string.charAt(i );
                    boolean check1 = ac==bc;
                    if (check1) {
                        /*if a match is found, copy the left-top diagonal element*/
                        rows[1][j] = rows[0][j+1];
                    } else {
                        if (rows[1][j + 1] > rows[0][j]) {
                            /*if top element is less than left element */
                            rows[1][j] = rows[0][j] + 1;
                        } else {
                            /*if left element <= top element*/
                            rows[1][j] = rows[1][j+1 ] + 1;
                        }
                    }
                }

            }
           /*moving the bottom row to the top*/
            for (j = 0; j < horizontal; j++) {    
                rows[0][j] = rows[1][j];
            }
            
        }
        for(i=0;i<horizontal;i++){
            System.out.println(" - "+rows[1][i]);
            
        }
        return Arrays.copyOfRange(rows[1], 0,horizontal );
    }
}
