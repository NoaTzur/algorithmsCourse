import jdk.jfr.StackTrace;

import java.util.Stack;

public class max1Max2 {

    public static class number{

        private int key;
        private Stack<Integer> potentialMax2;

        public number(int key){
            this.key = key;
            potentialMax2 = new Stack<>();
        }

        public int getKey(){
            return this.key;
        }

        public Stack<Integer> getPotentialMax2() {
            return this.potentialMax2;
        }
    }
    /*
    O(n-1 + logn) = minimal!!!
    recursive

     */

    public static int max1Max2(number[] arr, int start, int end){

        if(start < end){
            int max1Index = 0;
            int middle = (end+start)/2;
            int index1 = max1Max2(arr, start, middle);
            int index2 = max1Max2(arr, middle+1, end);
            if (arr[index1].getKey() > arr[index2].getKey()){
                arr[index1].getPotentialMax2().push(arr[index2].getKey());
                max1Index = index1;
            }
            else{
                arr[index2].getPotentialMax2().push(arr[index1].getKey());
                max1Index = index2;
            }
            return max1Index; //recursion will arrive to this line at the end after it will split to pairs.
        }
        return start; // when the condition of (start<end) will be false because start==end,
                      // it will tell us that the recursion split and arrived to subarray in length 1 (one node)
                      // so we will return start\end and it will enter to index1 / index2 params
                      // depend on who calls the function max1Max2 with params of start, middle when
                      //middle == start. or middle==end....
                      //after this will happen it will get in to the code at lines 37-43 will be executed on the first pair
                      //then the next pair and so on, each pair will return to index1 and index2 the index of the bigger
                      // number from the pair, and will create new pair and so on
    }
    //O(n-1 + logn) = minimal!!!
    //length of the stack od max1 - logn
    //number of comparisons in max1Max2(number[] arr, int start, int end) function is n-1 = n/2+n/4...+1
    public static void max1Max2(int[] arr){
        number[] numbersArr = new number[arr.length];

        for (int i=0; i<arr.length; i++){
            numbersArr[i] = new number(arr[i]); //creates the array of nodes that will help us on calculating
        }
        int max1Index = max1Max2(numbersArr, 0, arr.length-1); //will return the index in the array of the max1

        int max2 = numbersArr[max1Index].getPotentialMax2().pop(); //pop one potential from max1 stack-the stack contains number, not indexes

        while(!numbersArr[max1Index].getPotentialMax2().empty()){
            if (max2 < numbersArr[max1Index].getPotentialMax2().peek()){
                max2 = numbersArr[max1Index].getPotentialMax2().pop();
            }
            else{
                numbersArr[max1Index].getPotentialMax2().pop();
            }
        }

        int max1 = numbersArr[max1Index].getKey();

        System.out.println("max1 is: " + max1);
        System.out.println("max2 is: " + max2);
    }



    public static int max1max2_dynamic(int[] arr){
        //O ( 3*(n/2))
        int comparesCounter = 0;
        int max1;
        int max2;

        comparesCounter++;
        if(arr[0] > arr[1]){
            max1 = arr[0];
            max2 = arr[1];
        }
        else{
            max1 = arr[1];
            max2 = arr[0];
        }

        for (int i=2; i < arr.length; i=i+2){ //step 2 in a time !!
            comparesCounter++;
            if (arr[i] > arr[i+1]){
                comparesCounter++;
                if (arr[i]>max1){
                    comparesCounter++;
                    max2 = Math.max(max1, arr[i+1]);
                    max1=arr[i];
                }
                else { // arr[i] < max1
                    comparesCounter++;
                    if (arr[i] > max2){
                        max2 = arr[i];
                    }
                }
            }
            else{ //arr[i]<arr[i+1]
                comparesCounter++;
                if (arr[i+1]>max1){
                    comparesCounter++;
                    max2 = Math.max(max1, arr[i]);
                    max1=arr[i+1];
                }
                else { // arr[i+1] < max1
                    comparesCounter++;
                    if (arr[i+1] > max2){
                        max2 = arr[i+1];
                    }
                }
            }
            //if the length of the array is odd , there is not enough couples.
            //so check the last element in array
            if (arr.length % 2 != 0){
                comparesCounter++;
                if (arr[arr.length-1] > max1){
                    max2 = max1;
                    max1 = arr[arr.length-1];
                }
                comparesCounter++;
                if (arr[arr.length-1] > max2){
                    max2 = arr[arr.length-1];
                }
            }

        }
        System.out.println(max1);
        System.out.println(max2);
        return comparesCounter;
    }



    public static void main(String[] args) {
        int[] arr = {6,7,8,2,4,5,54,3};
       // max1Max2(arr);
        //int arr[] = {7,2,3,16,1,2,3,4,10,5};

        max1max2_dynamic(arr);
    }





    /*
                      5i            5i wil have a stack with all the number that 8i has and 5i from the prev steps
              _______________________
              |                     |
             4i                     5i
        _______________         _________
        |             |         |        |
       1i            4i        5i       8i
      _____         _____    _____    _______
     |    |        |    |    |   |    |     |
    1i   21       3i   4i   5i   6i   7i   8i
     */

}
