public class theNumberGame {

    static int oddSum =0;
    static int evenSum =0;

    public static void calcSum(int[] arr, int start, int end){
        for (int i=start; i<end; i++){
            if(i%2 == 0){
                evenSum = evenSum + arr[i];
            }
            else{
                oddSum = oddSum+arr[i];
            }
        }
    }

    /*
    every iteration calc the evenSum ans oddSum again (we can subtract from the sums we already calc at the beginning
    The array changes dynamically during the game
     */

//    public static int adaptiveGame(int[] arr){ //calc the difference\ profit
//
//        boolean Flag = true; //if flag is true it means the evenSum is bigger !! false - the oddSum in
//
//    }

}
