import java.util.Arrays;

public class medianProb {

    /*
    O(1) !!
    problem1
     - find an element that bigger then the median in unsorted array
    the optimal solution is to take a fixed number, like 64 (it depends on the length of the array)
    and search the maximum from this 64 elements (look how to do so in the minMax problem .
    in this case, we get 63/64 probability that this element is bigger then the median

    example on 3 first elements from the array:

   1 - means the elements is bigger then the median
   0 - its smaller 
   
   
   
    a[0]    a[1]    a[2]
    
     0        0       0    - first line - no one is bigger 
     0        0       1    -  a[2] is bigger 
     0        1       0    -  a[1] bigger 
     1        0       0    -   a[0] is bigger ,  and so on
     0        1       1 
     1        1       0
     1        0       1
     1        1       1
     
     so we get 7 cases that we found bigger element and 1 case that we dont
     
     7/8 probabilty



     prob2 = finding the median -
     take the array and sort it. - O(nlogn)
     after the sort, if the array is with odd number of elements - take the n/2 element

     if the array is with even number of elements, take the 2 lements in the middle = n/2 and n/2-1
     and calc the average between them - this will be the median even if it is not in the array


     prob3 -
     given 2 sorted array find the median -

     1.merge sort - O(n+n) (if the 2 arrays in the same length n)
     2.do the findMedian function (always we even number because - n+n=2n)


     prob4-
     given 2 arrays (sorted) - return all elements bigger the the median
     1.sort
     2.compare one element from the beginning of array1
       and one element from the end of array2
       the max(a0, bn) when a is array1 and b is array2, will be bigger then the median
       do this with all the elements (n elements) of the 2 arrays and get a new array of elements bigger then the median

       PROOF:
       a1>bn in this case a1 is bigger from all n elements in the array2 and thats why he is in the right
       of the median (the right side of the 2n elements of 2 arrays).

       bn>a1 means that a1 and b1...bn-1 is in the left !! of the median (because a1 + b1....bn-1 is exactly n elements!!)
       so bn is in the right side
     */

    public static double findMedian(int[] arr) {
        Arrays.sort(arr); //O(nlogn)
        int medianIndex = 0;
        double theMedian = 0;

        if (arr.length % 2 == 0) {
            int average = arr[arr.length / 2] + arr[arr.length / 2 - 1];
            theMedian = average / 2.0;
            medianIndex = arr.length / 2; // and another one is arr.length/2 -1
        } else {
            medianIndex = arr.length / 2;
            theMedian = arr[arr.length / 2];
        }

        return theMedian;
    }

    /*
    O(1) because firstElements parameter should be a constant number like 64 or 3 !
     */
    public static int one_elment_bigger_then_median(int[] arr, int firstElements){

        //if the array isnt sorted, add Arrays.sort(arr);
        int maxElement = Integer.MIN_VALUE;

        for (int i=0; i<firstElements; i++){
            if (arr[i] > maxElement){
                maxElement = arr[i];
            }
        }
        return maxElement;
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int[] merges = new int[arr1.length + arr2.length];
        int startArr1 = 0;
        int startArr2 = 0;
        int k = 0;
        while (startArr1 < arr1.length || startArr2 < arr2.length) {

            if (arr1[startArr1] < arr2[startArr2]) {
                merges[k++] = arr1[startArr1++];
            } else {
                merges[k++] = arr2[startArr2++];
            }
        }

        while (startArr1 < arr1.length){
            merges[k++] = arr1[startArr1++];
        }
        while(startArr2<arr2.length){
            merges[k++] = arr2[startArr2++];
        }

        return merges;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7, 8};
        System.out.println(findMedian(a));
    }



}
