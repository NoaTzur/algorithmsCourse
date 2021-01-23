import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Integer.MIN_VALUE;

public class LIS {


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

    public static int index_binary_search(int[][] arr, int end, int element_to_insert){

        int startIndex = 0, middle =end/2, endIndex = end;

        if(element_to_insert<arr[startIndex][startIndex]){ //element is smaller then the element in the first cell - should replace it
            return 0;
        }
        else if(element_to_insert>arr[endIndex][endIndex]){ //element greater then the element in the end of the array - should insert this element after it
            return endIndex+1;
        }
        while(startIndex<=endIndex){

            if (element_to_insert>arr[middle][middle]){
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
    /*
    O(n^2 - nlogn)
     */
    public static int[] one_longest_LIS(int[] arr){
        int pointer =0;

        int[][] helper = new int[arr.length][arr.length];
        helper[0][0] = arr[0];

        for (int i=1; i<arr.length; i++){ //O(n)
            int next_index = index_binary_search(helper, pointer, arr[i]); // O(logn)
            helper[next_index][next_index] = arr[i];
            copy_prev(helper, next_index, next_index-1); //O(n) - copy_prev
            if (next_index > pointer){
                pointer++;
            }
        }
        System.out.println("length of LIS: "+ (pointer+1));
        int[] ans = new int[pointer+1];
        for (int i=0; i<pointer+1; i++){ //copy the last row of the helper matrix - this is the longest increasing subarray
            ans[i] = helper[pointer][i];
        }
        return ans;
    }

    public static void copy_prev(int[][] arr, int row, int col){

        while(col>=0){
            arr[row][col] = arr[row-1][col];
            col--;
        }

    }

    public static void main(String[] args) {
        int[] a = {1,2,5,7,8,12,13,15,17};
        int[] arr3 = {10,1,4,20,6,3,7,8,11,18};
        int[] aaa = {5,9,4,20,6,3,7,8,11};
        int[] aaa1 = {4,9,20,20,6,3,7,8,11};

        //System.out.println(index_binary_search(aaa1, 2, 6));
        System.out.println(Arrays.toString(one_longest_LIS(arr3)));
    }

    //Questions from exams
    /*
    question 1 - longest increasing sub - linked-array.
    the idea = take the array, find the min element.
    take all the elements previous to this number and add to the end of the array (in the SAME order!!)

    example - 9,10,8,0,1,4,3,7
    after the re-arrangement 0,1,4,3,7
     */

    public static int[] LIS_linkedlist(LinkedList<Integer> linkedlist){

        int[] helper = new int[linkedlist.size()];

        int index_min = MIN_VALUE;
        for (int i=0; i<linkedlist.size(); i++){ //O(n)
            if (linkedlist.get(i) < index_min){
                index_min = i;
            }
        }
        /*
        fill in helper array - O(n)
         */
        helper[index_min] = linkedlist.get(index_min); //the min element = now we need to take all the elements before him and place the min the end

        for (int i = index_min+1; i<linkedlist.size(); i++){
            helper[i] = linkedlist.get(i-index_min); // from the index of minimum and end - put them after the minimum
                                                      //their indexes should be their original index minus index_min
        }
        for(int i=0; i<index_min; i++){ //now take all element before the minimum and put them in the end
            helper[i+index_min+1] = linkedlist.get(i);
        }

        return one_longest_LIS(helper);

    }
    /*
    O(n^2)
    Given an array of n size, the task is to find the longest subsequence such that difference between adjacents is one.

     */

    static int longestSubseqWithDiffOne(int arr[],
                                        int n)
    {
        // Initialize the dp[] array with 1 as a
        // single element will be of 1 length
        int dp[] = new int[n];
        for (int i = 0; i< n; i++)
            dp[i] = 1;

        // Start traversing the given array
        for (int i = 1; i < n; i++)
        {
            // Compare with all the previous
            // elements
            for (int j = 0; j < i; j++)
            {
                // If the element is consecutive
                // then consider this subsequence
                // and update dp[i] if required.
                if ((arr[i] == arr[j] + 1) ||
                        (arr[i] == arr[j] - 1))

                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }

        // Longest length will be the maximum
        // value of dp array.
        int result = 1;
        for (int i = 0 ; i < n ; i++)
            if (result < dp[i])
                result = dp[i];
        return result;
    }

}
