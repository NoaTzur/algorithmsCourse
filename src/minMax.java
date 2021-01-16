public class minMax {

    static int  max;
    static int min;

    public static int minMax1(int[] arr){ //comparisons in pairs
        //O ( 3*(n/2)) - explanation in the end
        int comparesCounter = 0;

        comparesCounter++;
        if(arr[0] > arr[1]){
            max = arr[0];
            min = arr[1];
        }
        else{
            max = arr[1];
            min = arr[0];
        }

        for (int i=2; i < arr.length;i=i+2){ //step 2 in a time !!
            comparesCounter++;
            if(arr[i] > arr[i+1]){ // it means that arr[i] is in good potential to be the next max, and arr[i+1] cant be the max because arr[i] is bigger
                comparesCounter++; // rely on Transitive relation
                if (arr[i] > max){
                    max = arr[i];
                }
                comparesCounter++;
                if(arr[i+1]<min){
                    min = arr[i+1];
                }
            }

            else{ // arr[i+1] > arr[i]
                comparesCounter++;
                if (arr[i+1] > max){
                    max = arr[i+1];
                }
                comparesCounter++;
                if (arr[i] < min ){
                    min = arr[i];
                }
            }

            //so each iteration we are doing 3 comparisons !! one to check if arr[i]>arr[i+1] or opposite
            //and more 2 comp to check if replace the max and min parameters.
        }

        //if the length of the array is odd , there is not enough couples.
        //so check the last element in array
        if (arr.length % 2 != 0){
            comparesCounter++;
            if (arr[arr.length-1] > max){
                max = arr[arr.length-1];
            }
            comparesCounter++;
            if (arr[arr.length-1] < min){
                min = arr[arr.length-1];
            }
        }
        return comparesCounter;
    }

    /*
    the complexity is O(3*(n/2))
    because we are doing 3 compares for every couple
    we jump 2 steps each iteration
     */
    public static void main(String[] args) {
        int[] aa =  {8,7,6,5,4,3,2,1};

        int ans = minMax1(aa);
        System.out.println(ans);
        System.out.println("min:" + min + "max:"+max);
    }
}
