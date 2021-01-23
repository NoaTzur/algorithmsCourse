public class subArray {


    /*
    O(n)
     */
    public static void subOnes(int[] arr){

        int[] helperArr = new int[arr.length];
        helperArr[0] = arr[0]; //so we can start the for from 1 and not 0
        int indexMaxSub=0;

        for(int i=1;i<arr.length; i++){
            if(arr[i] == 1) {
                helperArr[i] = helperArr[i - 1] + 1;
            }
            if (helperArr[indexMaxSub] < helperArr[i]){  //found longer substring of '1'
                indexMaxSub = i;
            }
        }
        int maxSub = helperArr[indexMaxSub];
        System.out.println("The longest sub array is:" + maxSub);
        System.out.println("the subArray beggins at index: " + (indexMaxSub-maxSub+1));
    }

    public static void main(String[] args) {

        int arr[] = {1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1,1,1,0,0,1,1,0,1,0,0};
        int arr1[] = {1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1};
        int arr2[] = {1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1};
        subOnes(arr);
        subOnes(arr1);
        subOnes(arr2);
    }
}
