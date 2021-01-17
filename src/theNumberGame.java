import static java.lang.Integer.max;

public class theNumberGame {

    static int oddSum = 0;
    static int evenSum = 0;

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

    public static int[][] fillTheMat(int[] origin){
        int[][] helperMat = new int[origin.length][origin.length];

        for (int i=0; i<origin.length; i++){ //fill the diagonal
            helperMat[i][i] = origin[i];
        }
        for (int i=origin.length-2; i>=0; i--){
            for (int j=i+1; j<origin.length; j++){
                helperMat[i][j] = Math.max(origin[i] - helperMat[i+1][j], origin[j] - helperMat[i][j-1]);
            }
        }
        return helperMat;
    }

    /*

     */

    public static void dynamicGame(int[] arr, int[][] helperMat){ //calc the difference\ profit

        int start = 0;
        int end = arr.length-1;

        int player1 = 0;
        int player2 = 0;

        for (int i=0; i<arr.length; i++){
            //first player
            if (arr[start]-helperMat[start+1][end] > arr[end] - helperMat[start][end-1]){
                player1 = player1 + arr[start];
                System.out.println("player number 1 choose: " + arr[start]);
                start++;

            }
            else{
                player1 = player1 + arr[end];
                System.out.println("player number 1 choose: " + arr[end]);
                end--;

            }

            if(start!=end) {
                //second player
                if (arr[start] - helperMat[start + 1][end] > arr[end] - helperMat[start][end - 1]) {

                    player2 = player2 + arr[start];
                    System.out.println("player number 2 choose: " + arr[start]);
                    start++;
                } else {
                    player2 = player2 + arr[end];
                    System.out.println("player number 2 choose: " + arr[end]);
                    end--;

                }
            }
            else{ //start == end
                //last element remain
                System.out.println("player number 2 choose: " + arr[end]);
                player2 = player2+arr[end];
                break;
            }

        }
        System.out.println("player 1 summary:" + player1);
        System.out.println("player 2 summary:" + player2);
        System.out.println("The diff is:" + Math.abs(player1-player2));

    }

    public static void main(String[] args) {
        int[] arr = {1,3,6,1,3,6};
        int[][] mat = fillTheMat(arr);

        dynamicGame(arr, mat);
    }

}
