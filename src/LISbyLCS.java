import java.util.Arrays;

public class LISbyLCS {

    static int count = 0;
    /*
    dynamic programming  - with the help of matrix and this:

    F(a,b) =    1, if a=b
                0, if a!=b

    bigger problem :

         F(Xa,Yb) =     F(X,Y) +1, if a=b
                        max(F(Xa,Y), F(X, Yb)), if a!=b

    we are replacing the () with [] and gets a matrix
     */
    public static int[][] helperGreed(int[] s1, int[] s2){
        int[][] greed = new int[s1.length+1][s2.length+1];

        for (int rows=1; rows< greed.length; rows ++){
            for (int cols=1; cols< greed[0].length; cols++){
                if (s1[rows-1] == s2[cols-1]){
                    greed[rows][cols] = greed[rows-1][cols-1] +1;
                }
                else{
                    greed[rows][cols] = Math.max(greed[rows-1][cols], greed[rows][cols-1]);
                }
            }
        }
        return greed;
    }

    public static int index_binary_search(int[] arr, int end, int element_to_insert){

        int startIndex = 0, middle =end/2, endIndex = end;

        if(element_to_insert<arr[startIndex]){ //element is smaller then the element in the first cell - should replace it
            return 0;
        }
        else if(element_to_insert>arr[endIndex]){ //element greater then the element in the end of the array - should insert this element after it
            return endIndex+1;
        }
        while(startIndex<=endIndex){

            if (element_to_insert>arr[middle]){
                startIndex=middle+1;
            }
            else{//element < arr[middle]
                endIndex = middle-1;
            }
            middle = (startIndex+endIndex+1)/2;
        }
        return middle;
    }


    /*
    O(nlogn) - running with for on the array and for each element search for its index to be insert it in the helper array
    binarysearch for each element - logn
    return the longest sorted from small to big array that can be find in the given array

     */
    public static int LISlength(int[] arr){
        int pointer =0;
        int[] helper = new int[arr.length];
        helper[0] = arr[0]; //initiate

        for (int i=1;i<arr.length; i++){
            int next_index = index_binary_search(helper, pointer, arr[i]);
            helper[next_index] = arr[i];
            if (next_index > pointer){
                pointer++;
            }
        }
        return pointer+1;
    }


    public static int[] one_LIS(int[][] greed,int[] s1, int[] s2){

        int[] ans= new int[LISlength(s1)];
        int index = ans.length-1;
        int rows = greed.length-1;
        int cols = greed[0].length-1;

        while(rows>0 && cols >0) {
            if (s1[rows-1] == s2[cols-1]) {
                ans[index--] = s1[rows-1];
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

    public static void allCommonStrings(int[][] greed, int[] s1, int[] s2, int rows, int cols, String ans){
        if(count > 100){ // supremum on the number of iteration of the recursion
            return;
        }
        if(rows>0 && cols>0) {
            if (s1[rows-1] == s2[cols-1]) {
                ans = s1[rows-1] +", "+ ans;
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
        }
    }


    public static void main(String[] args) {
//        String aa = "abba";
//        String bb = "bamba";
//
//        int[][] greed = helperGreed("abcbdabsfdadaf", "bdcabaaasf");
//        //System.out.println(oneCommonString(greed, "bdcaba", "abcbdab"));

        //allCommonStrings(greed,"abcbdabsfdadaf", "bdcabaaasf", greed.length-1, greed[0].length-1, "");
        //System.out.println(count);
        int[] arr = {5,9,4,20,6,3,7,8,11};
        int[] arr2 = {5,9,4,20,6,3,7,8,11};
        Arrays.sort(arr2);

        int[][] greed = helperGreed(arr, arr2);
        //int[] ans = one_LIS(greed, arr, arr2);
        //System.out.println(Arrays.toString(ans));
        //allCommonStrings(greed,arr,arr2,arr.length, arr.length, "");


    }
}
