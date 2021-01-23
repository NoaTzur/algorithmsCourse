import java.lang.reflect.Array;
import java.util.Arrays;

public class LDS {


    public static int index_binary_search(int[] arr, int end, int element_to_insert){

        int startIndex = 0, middle =end/2, endIndex = end;

        if(element_to_insert>arr[startIndex]){ //element is smaller then the element in the first cell - should replace it
            return 0;
        }
        else if(element_to_insert<arr[endIndex]){ //element greater then the element in the end of the array - should insert this element after it
            return endIndex+1;
        }
        while(startIndex<=endIndex){

            if (element_to_insert<arr[middle]){
                startIndex=middle+1;
            }
            else if(element_to_insert > arr[middle]){
                endIndex = middle-1;
            }
            else{ //element == arr[middle]
                return middle;

            }
            middle = (startIndex+endIndex+1)/2;
        }
        return middle;
    }

    public static int index_binary_search(int[][] arr, int end, int element_to_insert){

        int startIndex = 0, middle =end/2, endIndex = end;

        if(element_to_insert>arr[startIndex][startIndex]){ //element is smaller then the element in the first cell - should replace it
            return 0;
        }
        else if(element_to_insert<arr[endIndex][endIndex]){ //element greater then the element in the end of the array - should insert this element after it
            return endIndex+1;
        }
        while(startIndex<=endIndex){

            if (element_to_insert<arr[middle][middle]){
                startIndex=middle+1;
            }
            else if (element_to_insert>arr[middle][middle]){//element > arr[middle]
                endIndex = middle-1;
            }
            else {
                return middle;
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
    public static int LDSlength(int[] arr){
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
    public static int[] one_longest_LDS(int[] arr){
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
        System.out.println("length of LDS: "+ (pointer+1));
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
        int[] a = {78,4,3,2,5,6,7};
        int[] arr3 = {10,1,4,20,6,3,7,8,11,18};
        int[] aaa = {5,9,4,20,6,3,7,8,11};
        int[] aaa1 = {90,70,66,5,90,2};

        //System.out.println(index_binary_search(a, 3,0));
        int[] ans = one_longest_LDS(aaa1);
        System.out.println(LDSlength(aaa1));
        System.out.println(Arrays.toString(ans));
    }

}
