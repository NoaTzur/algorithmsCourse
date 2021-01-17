import java.util.ArrayList;
import java.util.List;

public class LCS {

    static int count = 1;
    public static int[] buildHelperArray(String s){

        int[] helper = new int[26];

        for (int i=0; i<s.length(); i++){ //O(min(m+n))
            int indexInHelper = s.charAt(i) - 'a';
            helper[indexInHelper]++;
        }
        return helper;
    }


    public static StringBuilder LCSgreedyImpro(String s1, String s2){


        if(s2.length() < s1.length()){ // ensuring that s1 will always contains the shortest string
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        int[] helper = buildHelperArray(s1);

        int pointer = 0;
        StringBuilder ans = new StringBuilder(); //string builder in java is more efficient in memory space

        for (int i=0; i<s2.length(); i++){
            int indexInHelper = s2.charAt(i)-'a';
            if (helper[indexInHelper] > 0){
                int indexFrom = s1.indexOf(s2.charAt(i), pointer); //in this line, we checks if the char we checking from s2 is exist in s1 from the index "pointer" and further on. because we cant go backwards in the string
                if (indexFrom!=-1){//indexOf returns -1 if the char isnt exist in the string fro the pointer and further on
                  ans.append(s2.charAt(i));//so in this case we dont want to count this char as valid
                  pointer = indexFrom+1; // we are continue the search from this index because we can go backwards in the string
                  helper[indexFrom]--;
                }
            }
        }
        return ans;
    }
    /*
    dynamic programming  - with the help of matrix and this:

    F(a,b) =    1, if a=b
                0, if a!=b

    bigger problem :

         F(Xa,Yb) =     F(X,Y) +1, if a=b
                        max(F(Xa,Y), F(X, Yb)), if a!=b

    we are replacing the () with [] and gets a matrix
     */
    public static int[][] helperGreed(String s1, String s2){
        int[][] greed = new int[s1.length()+1][s2.length()+1];

        //automatically in java
//        for (int i=0; i<greed.length; i++){
//            greed[i][0] = 0;
//        }
//        for (int i=0; i<greed[0].length; i++){
//            greed[0][i] = 0;
//        }

        for (int rows=1; rows< greed.length; rows ++){
            for (int cols=1; cols< greed[0].length; cols++){
                if (s1.charAt(rows-1) == s2.charAt(cols-1)){
                    greed[rows][cols] = greed[rows-1][cols-1] +1;
                }
                else{
                    greed[rows][cols] = Math.max(greed[rows-1][cols], greed[rows][cols-1]);
                }
            }
        }
        return greed;
    }

    public static StringBuilder oneCommonString(int[][] greed, String s1, String s2){

        StringBuilder ans= new StringBuilder();
        int rows = greed.length-1;
        int cols = greed[0].length-1;

        while(rows>0 && cols >0) {
            if (s1.charAt(rows-1) == s2.charAt(cols-1)) {
                ans.append(s1.charAt(rows-1));
                rows--;
                cols--;
            }
            else{
                if (greed[rows-1][cols] >= greed[rows][cols-1]){
                    rows--;
                }
                else{
                    cols--;
                }
            }
        }
        return ans;
    }

    public static void allCommonStrings(int[][] greed, String s1, String s2, int rows, int cols, String ans){
        if(count > 100){ // supremum on the number of iteration of the recursion
            return;
        }
        if(rows>0 && cols>0) {
            if (s1.charAt(rows-1) == s2.charAt(cols-1)) {
                ans = s1.charAt(rows-1) + ans ;
                allCommonStrings(greed, s1, s2, rows-1, cols-1, ans);
            }
            else{
                if (greed[rows-1][cols] > greed[rows][cols-1]){
                    allCommonStrings(greed, s1, s2, rows-1, cols, ans);
                }
                else if (greed[rows-1][cols] < greed[rows][cols-1]){
                    allCommonStrings(greed, s1, s2, rows, cols-1, ans);
                }
                else{// we can get up and left as well - SPLIT
                    count++;
                    allCommonStrings(greed, s1, s2, rows, cols-1, ans);
                    allCommonStrings(greed, s1, s2, rows-1, cols, ans);

                }
            }
        }
        if(cols==0 || rows==0) {
            System.out.println(ans);
           // System.out.println(counter);
        }
    }

    public static void main(String[] args) {
        String aa = "abba";
        String bb = "bamba";

        int[][] greed = helperGreed("abcbdabsfdadaf", "bdcabaaasf");
        //System.out.println(oneCommonString(greed, "bdcaba", "abcbdab"));

        allCommonStrings(greed,"abcbdabsfdadaf", "bdcabaaasf", greed.length-1, greed[0].length-1, "");
        System.out.println(count);
    }
}
