import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class glassBalls {

    /*
    f(n,k,s) = (n/k) + k -1

    when n==number of floors
    k == number of division
    s == number of floors per division

    n = s*k so s=n/k
    and number of maximum checks (of what floor the ball will broke if we throw it from that floor)
    is k checks from the beginning of each division, the ball will break at the last division, so we will check all s
    floors at this division and the ball will break only in the n floor(last)
    so we will be making s+k-1 checks ! thats bring us to =  (n/k) +k -1.

    seeking for the optimal k (optimal division of the building)

    a^2+b^2 >= 2ab
    (sqrt(a))^2 + ((sqrt(b)^2) >= 2(sqrt(ab))

    place a=n/k
    and b=k
    and we will get infimum of 2*sqrt(n), so we will choose k=sqrt(n) to get to this infimum !!

    improvment is to divert n like this:
    n = 1+2+3+4+...+k = ((k+1)k) / 2
    thats gives us
    k ~~ sqrt(2n)
    thats improvment from 2*sqrt(n)


    Dynamic programming -
    F(f,b) = min ( max(F(f-i, b), F(i-1, b-1)) + 1 )
    when f is the number of floors and b is number of balls.
    i is running from i to f.

    F(f-i, b) - case when we throw the ball from floor number i and the ball does not break,
        so we have to check from i to f (f-i) floors are left, and number of balls stays b
    F(i-1, b-1) - case when we throw the ball from floor i and the ball breaks, we need to check
        from floor 0 to i . and b-1 balls are left.



     */
    //O(n^2)
    public static int twoBalls(int f) { //b==2
        if(f==0) return 0; // floor 0 - 0 checks
        if (f==1) return 1;
        int[] helper = new int[f+1];  //+1 for initiate
        //basic cases !!
        helper[0] = 0; //floor 0 with 2 balls - 0 checks
        helper[1] = 1; //floor 1 with 2 balls - 1 checks
        helper[2] = 2; //floor 2 with 2 balls - 2 checks

        for (int floor=3; floor<=f; floor++){ // O(n) n number of floors
            int min = Integer.MAX_VALUE;
            for (int currFloor_to_throw=1; currFloor_to_throw<floor; currFloor_to_throw++){ //O(n)
                int temp = Math.max(currFloor_to_throw, helper[floor-currFloor_to_throw]+1);
                // currFloor_to_throw = we throw the ball from currFloor_to_throw and it does not break so we left with currFloor_to_throw-1 to check +1 for the check if in currFloor_to_throw it breaks == currFloor_to_throw
                // helper[floor-currFloor]+1 = ball breaks in currFloor_to_throw so we need to check floor we are checking now - currFloor_to_throw we throw from , +1 for the check if the ball is break (which it did in this case)
                if (temp<min){
                    min = temp;
                }
            }
            helper[floor] = min; // this is the minimum floor we need to throw, for a building with "floor" number of floors.
        }

        System.out.println("number of checks in building with "+f+" floors, is: "+ helper[f]);
        return helper[f];
    }
    //O(n^2)
    public static int threeBalls(int f){

        if (f==0) return 0;
        if (f==1) return 1;
        if (f==2|| f==3) return 2;

        int[] twoBalls = new int[f+1];
        for (int floor=1; floor<=f; floor++){
            twoBalls[floor] = twoBalls(floor); // init the case of 2 balls to each floor, for the basic case with 3 balls...
        }
        int[] threeBalls_helper = new int[f+1];
        threeBalls_helper[0]=0;
        threeBalls_helper[1]=1;
        threeBalls_helper[2]=2;
        threeBalls_helper[3] =2; //floor number 3 with 2 balls - checks the third floor, then the second floor, the first floor -if the ball break in floor 2 no need to check floor 1, if doesnt also no need to check(so 2 checks)

        for (int floor=4; floor<=f; floor++){
            int min = Integer.MAX_VALUE;
            for (int currFloor_to_throw=1; currFloor_to_throw<floor; currFloor_to_throw++){
                int temp = Math.max(twoBalls[currFloor_to_throw-1]+1, threeBalls_helper[floor-currFloor_to_throw]+1);
                //twoBalls[currFloor_to_throw-1]+1 - balls breaks on floor "currFloor_to_throw" so checks case with 2 balls and currFloor_to_throw-1 floors
                //threeBalls_helper[floor-currFloor_to_throw]+1 - balls does not breaks, checks the floor-currFloor_to_throw that left with same three balls
                if (temp<min){
                    min = temp;
                }
            }
            threeBalls_helper[floor] = min;
        }
        System.out.println("number of checks in building with "+f+" floors, is: "+ threeBalls_helper[f]);
        return threeBalls_helper[f];
    }

    //O(f*b)
    public static int glassBalls(int f, int b){

        int[][] helper = new int[b+1][f+1];

        for (int i=0; i<=f; i++){
            helper[0][i] = 0; //init the first row (0 balls)with 0
            helper[1][i] = i; //init the second row (1 balls) with i (number of checks with 1 balls is the number of floors- move from first floor up
        }
        for (int balls=2; balls<=b; balls++){
            helper[balls][0] = 0; // first col - with floor 0 , no checks
            helper[balls][1] = 1; //second col with floor 1, 1 check

            if (f>=2){
                helper[balls][2] = 2; // third col (if there second floor and up - checks like we did in the prev functions)
            }
            for (int floor=2; floor<=f; floor++){
                int min = Integer.MAX_VALUE;
                for (int curr_floor_to_throw=1; curr_floor_to_throw<floor; curr_floor_to_throw++){
                    int temp = Math.max(helper[balls-1][curr_floor_to_throw-1]+1, helper[balls][floor-curr_floor_to_throw]+1);
                    //helper[balls-1][curr_floor_to_throw-1]+1 = ball breaks
                    //helper[balls][floor-curr_floor_to_throw]+1 = ball does not break
                    if (temp<min){
                        min = temp;
                    }
                }
                helper[balls][floor] = min;
            }

        }
        return helper[b][f];
    }


    public static void main(String[] args) {
        System.out.println(twoBalls(100));//14

    }


    /*
    return the tries of checks as list
     */

    public static class node{
        int number_of_checks;
        int min_floor_to_throw;

        public node(int number_of_checks, int min_floor_to_throw){
            this.min_floor_to_throw = min_floor_to_throw;
            this.number_of_checks = number_of_checks;
        }
    }

    public static node[][] glassBalls_path(int f, int b){

        node[][] helper = new node[b+1][f+1];
        for (int i=0; i<helper.length; i++){
            for (int j=0;j<helper[0].length; j++){
                helper[i][j] = new node(0,0);
            }
        }

        for (int i=0; i<=f; i++){
            helper[0][i] = new node(0,0);
//            helper[0][i].number_of_checks=0; //init the first row (0 balls)with 0
            helper[1][i] = new node(i,i); //init the second row (1 balls) with i (number of checks with 1 balls is the number of floors- move from first floor up
        }
        for (int balls=2; balls<=b; balls++){
            helper[balls][0] = new node(0,0); // first col - with floor 0 , no checks
            helper[balls][1]= new node(1,1); //second col with floor 1, 1 check

            if (f>=2){
                helper[balls][2]= new node(2,0); // third col (if there second floor and up - checks like we did in the prev functions)
            }
            for (int floor=2; floor<=f; floor++){
                int min = Integer.MAX_VALUE;
                for (int curr_floor_to_throw=1; curr_floor_to_throw<floor; curr_floor_to_throw++){
                    int temp = Math.max(helper[balls-1][curr_floor_to_throw-1].number_of_checks+1, helper[balls][floor-curr_floor_to_throw].number_of_checks+1);
                    //helper[balls-1][curr_floor_to_throw-1]+1 = ball breaks
                    //helper[balls][floor-curr_floor_to_throw]+1 = ball does not break
                    if (temp<min){
                        helper[balls][floor].min_floor_to_throw=curr_floor_to_throw;
                        min = temp;
                    }
                }
                helper[balls][floor].number_of_checks = min;
            }
        }
        return helper;
    }

    //return path for 2 balls and floor n (given)
    public static List<Integer> path(int[][] greed, int floors){
        List<Integer> path = new ArrayList<>();
        int first_floor = greed[2][floors];
        int reduce_from_first_floor =1;
        int current_floor = greed[2][floors]; //Triangular number - first floor to check
        path.add(current_floor);

        boolean found = false;
        while(!found){
            Random rand = new Random();
            boolean is_break = rand.nextBoolean();

            if (is_break){
                int prev_floor = current_floor-(first_floor-reduce_from_first_floor-1);//going back to the previous floor that we checkd
                is_break =false;
                while (!is_break){ //now checks the floor from the current to the floor that it was break
                    /*
                    if(prev_floor!= floor it should break)
                        path.add(prev_floor);
                        prev_floor++;
                    else
                        path.add(prev_floor);
                        is_break = true;
                        found = true;
                     */
                }
            }
            else{ //ball does not break = take the next Triangular number after "first_floor"+the current floor, as the next floor to check
                current_floor = current_floor + (first_floor-reduce_from_first_floor); //example - first floor to check is 14, after this 14+(14-1), after this 27+(14-2) and so on
                path.add(current_floor);
                reduce_from_first_floor++;
            }
        }

        return path;
    }


}



